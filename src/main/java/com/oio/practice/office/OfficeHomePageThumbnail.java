package com.oio.practice.office;

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.spire.doc.Document;
import com.spire.doc.documents.ImageType;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

/**
 * 办公文档首页缩略图生成
 * 参考：https://blog.csdn.net/weixin_43487532/article/details/128706173
 *
 * @Author: Liqc
 * @Date: 2024/6/12 10:40
 * @Version: 1.0
 */

@Slf4j
public class OfficeHomePageThumbnail {

    /**
     * word获取缩略图
     * See more: https://www.e-iceblue.com/Tutorials/Java/Spire.Doc-for-Java/Program-Guide/Conversion/Convert-Word-to-Images-in-Java.html
     * @param wordFile      word文件地址
     * @param outputImgPath 输出图片目录
     * @return 图片路径
     * @create 2023-01-09
     */
    private static void wordToImage(String wordFile, String outputImgPath) throws Exception {
        //Create a Document object
        Document doc = new Document();

        //Load a Word document
        doc.loadFromFile("C:\\Users\\Qiongchao\\Desktop\\1.5.1运行Cloud版本.docx");

        //Convert the whole document into individual buffered images
        BufferedImage[] images = doc.saveToImages(ImageType.Bitmap);

        //Loop through the images
        for (int i = 0; i < images.length; i++) {

            //Get the specific image
            BufferedImage image = images[i];

            //Re-write the image with a different color space
            BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            newImg.getGraphics().drawImage(image, 0, 0, null);

            //Write to a JPG file
            File file = new File("C:\\Users\\Qiongchao\\Desktop\\" + String.format(("Image-%d.jpg"), i));
            ImageIO.write(newImg, "JPEG", file);
        }
    }


    /**
     * 压缩图片
     * @param quality
     */
    public static void compressImg(double quality) {
        compressImg(quality, "C:\\Users\\Qiongchao\\Desktop\\test\\1.jpg", "C:\\Users\\Qiongchao\\Desktop\\test\\3.jpg");
    }


    /**
     * 压缩图片
     * @param quality
     */
    public static void compressImg(double quality, String inputPath, String outputPath) {
        Img.from(FileUtil.file(inputPath))
                .setQuality(quality) //压缩比率
                .write(FileUtil.file(outputPath));
    }


    /**
     * 添加图片水印
     */
    private static void addImgWatermark(String inputFile, String outputFile, String watermarkFile) {
        ImgUtil.pressImage(
                FileUtil.file(inputFile),
                FileUtil.file(outputFile),
                ImgUtil.read(FileUtil.file(watermarkFile)), //水印图片
                // ImgUtil.read(FileUtil.file("C:\\Users\\Qiongchao\\Desktop\\test\\waterimg.png")), //水印图片
                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                1.0f // 水印图片的透明度
        );
    }

    /**
     * 裁剪图片
     */
    private static void cut() {
        ImgUtil.cut(
                FileUtil.file("C:\\Users\\Qiongchao\\Desktop\\test\\水印2.png"),
                FileUtil.file("C:\\Users\\Qiongchao\\Desktop\\test\\水印d1.png"),
                // new Rectangle(0, 3254, 1586, 2244)//裁剪的矩形区域
                new Rectangle(0, 3854, 1586, 2244)//裁剪的矩形区域
        );

    }

    /**
     * 添加蒙层水印
     * @param inputFile 原文件
     * @param watermarkImg 水印图片
     * @param outputFile 输出文件
     */
    private static void addMaskWatermark(String inputFile, String watermarkImg, String outputFile) throws IOException {
        int waterOffset = 400;
        // 先计算水印图片的大小，如果水印图片太大，无法添加图片水印
        BufferedImage image = ImageIO.read(new File(inputFile));
        int iw = image.getWidth(null);
        int ih = image.getHeight(null);
        // 水印图片会比原图片大，取水印图片中间部分，然后往下偏移 600 像素（防止蒙层盖不住字）
        BufferedImage wImage = ImageIO.read(new File(watermarkImg));
        int ww = wImage.getWidth(null);
        int wh = wImage.getHeight(null);
        int width, height, startHeight;
        width = Math.min(ww, iw);
        height = Math.min(ih, wh);
        // 图片的原点在左上角，x向右是增加，y向下是增加
        if (wh <= ih) {
            startHeight = 0;
        } else {
            int halfWh = wh / 2;
            startHeight = halfWh - (ih / 2);
            if ((startHeight + waterOffset + ih) <= wh) {
                startHeight += waterOffset;
            }
        }
        // 新的水印图片
        String newWaterMarkImg = contactFilePath(watermarkImg, "-" + RandomUtil.randomInt(0, 100) + Instant.now().toEpochMilli());

        ImgUtil.cut(
                FileUtil.file(watermarkImg),
                FileUtil.file(newWaterMarkImg),
                // new Rectangle(0, 3254, 1586, 2244)//裁剪的矩形区域
                new Rectangle(0, startHeight, width, height)//裁剪的矩形区域
        );
        addImgWatermark(inputFile, outputFile, newWaterMarkImg);
    }


    /**
     * 缩放图片
     *  - 按比例缩放
     *  - 按长宽缩放
     * @param inputFile
     * @param outputFile
     */
    private static void scale(String inputFile, String outputFile) {
        ImgUtil.scale(
                FileUtil.file(inputFile),
                FileUtil.file(outputFile),
                // 0.5f//缩放比例
                200, 200, null
        );
    }

    private static String contactFilePath(String filePath, String suffix) {
        int index = filePath.lastIndexOf(".");
        return filePath.substring(0, index) + suffix + filePath.substring(index);
    }


    public static void main(String[] args) throws Exception {
        String baseFolder = "C:\\Users\\Qiongchao\\Desktop\\test1";
        String outFolder = "C:\\Users\\Qiongchao\\Desktop\\test2";
        String watermarkFile = "C:\\Users\\Qiongchao\\Desktop\\水印.png";
        Path folderPath = Paths.get(baseFolder); // 替换为你的文件夹路径

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
            for (Path entry : stream) {
                String fileAbsolutePath = entry.toAbsolutePath().toString();
                String fileName = entry.getFileName().toString();
                System.out.println(fileName);

                int index = fileName.lastIndexOf(".");
                // 加蒙层
                String watermarkFilePath = outFolder + "\\" + fileName.substring(0, index) + "-1" + fileName.substring(index);
                addMaskWatermark(fileAbsolutePath, watermarkFile, watermarkFilePath);

                // 缩略图
                String miniFileName = outFolder + "\\" + fileName.substring(0, index) + "-mini" + fileName.substring(index);
                scale(fileAbsolutePath, miniFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // wordToImage("", "");
        // cut();
        // addImgWater();

        // compressImg(0.01);
    }

}
