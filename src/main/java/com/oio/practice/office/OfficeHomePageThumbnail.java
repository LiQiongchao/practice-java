package com.oio.practice.office;

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.spire.doc.Document;
import com.spire.doc.documents.ImageType;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.graphics.PdfImageType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
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
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
     * 生成的图片是名称后面加 -序号，如：test-1.jpg
     * See more: https://www.e-iceblue.com/Tutorials/Java/Spire.Doc-for-Java/Program-Guide/Conversion/Convert-Word-to-Images-in-Java.html
     *
     * @param wordFile      word文件地址
     * @param outputImgPath 输出图片目录
     * @param limit 生成图片的最大数量, 0表示无限制
     * @create 2023-01-09
     */
    private static void wordToImage(String wordFile, String outputImgPath, int limit) throws Exception {
        //Create a Document object
        Document doc = new Document();

        //Load a Word document
        doc.loadFromFile(wordFile);

        //Convert the whole document into individual buffered images
        // BufferedImage[] images = doc.saveToImages(ImageType.Bitmap);
        BufferedImage image = doc.saveToImages(0, ImageType.Bitmap);

        // 获取文件名（不包含路径）
        String fullFileName = new java.io.File(wordFile).getName();
        int lastDot = fullFileName.lastIndexOf(".");
        String name = fullFileName.substring(0, lastDot);

        //Loop through the images
        // for (int i = 0; i < images.length; i++) {

            //Get the specific image
            // BufferedImage image = images[i];

            //Re-write the image with a different color space
            BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            newImg.getGraphics().drawImage(image, 0, 0, null);

            //Write to a JPG file
            // File file = new File(outputImgPath);
            // String imageName = name + (i == 0 ? "" : String.format(("-%d"), i)) + ".jpg";
            String imageName = name + ".jpg";
            File file = new File(outputImgPath + "\\" + imageName);

            ImageIO.write(newImg, "JPEG", file);

            // if (limit > 0 && i >= limit) {
            //     break;
            // }
        // }
        doc.close();
    }

    /**
     * PDF获取首页图片
     * 生成的图片是名称后面加 -序号，如：test-1.png
     * See more: https://www.e-iceblue.com/Tutorials/JAVA/Spire.PDF-for-JAVA/Program-Guide/Conversion/Convert-PDF-to-Image-in-Java.html
     *
     * @param filePath 文件地址
     * @param outputImgPath 输出图片目录
     * @param limit 生成图片的最大数量, 0表示无限制
     * @create 2024-7-6
     */
    private static void pdfToImage(String filePath, String outputImgPath, int limit) throws Exception {
        //Create a PdfDocument instance

        PdfDocument pdf = new PdfDocument();

        //Load a PDF sample document
        pdf.loadFromFile(filePath);

        // 获取文件名（不包含路径）
        String fullFileName = new java.io.File(filePath).getName();
        int lastDot = fullFileName.lastIndexOf(".");
        String name = fullFileName.substring(0, lastDot);

        //Loop through every page
        for (int i = 0; i < pdf.getPages().getCount(); i++) {
            //Convert all pages to images and set the image Dpi
            BufferedImage image = pdf.saveAsImage(i, PdfImageType.Bitmap, 500, 500);

            //Save images to a specific folder as a .png files
            String imageName = name + (i == 0 ? "" : String.format(("-%d"), i)) + ".png";
            File file = new File(outputImgPath + "\\" + imageName);

            ImageIO.write(image, "PNG", file);

            if (limit > 0 && i >= limit) {
                break;
            }
        }
        pdf.close();
    }

    /**
     * Excel 获取首页图片
     * 生成的图片是名称后面加 -序号，如：test-1.png
     * See more: https://www.e-iceblue.com/Tutorials/Java/Spire.XLS-for-Java/Program-Guide/Conversion/Convert-Excel-to-Image-in-Java.html
     *
     * @param filePath 文件地址
     * @param outputImgPath 输出图片目录
     * @param sheetIndex 生成图片的sheet
     * @create 2024-7-6
     */
    private static void excelToImage(String filePath, String outputImgPath, int sheetIndex) throws Exception {
        //Create a workbook instance
        Workbook workbook = new Workbook();
        //Load a sample Excel document
        workbook.loadFromFile(filePath);

        //Get the first worksheet
        Worksheet sheet = workbook.getWorksheets().get(sheetIndex);

        // 获取文件名（不包含路径）
        String fullFileName = new java.io.File(filePath).getName();
        int lastDot = fullFileName.lastIndexOf(".");
        String name = fullFileName.substring(0, lastDot);

        String imageName = name + ".png";
        //Save the sheet to an image
        sheet.saveToImage(outputImgPath + "\\" + imageName);
    }


    /**
     * 压缩图片
     *
     * @param quality
     */
    public static void compressImg(double quality) {
        compressImg(quality, "C:\\Users\\Qiongchao\\Desktop\\test\\1.jpg", "C:\\Users\\Qiongchao\\Desktop\\test\\3.jpg");
    }


    /**
     * 压缩图片
     *
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
     *
     * @param inputFile    原文件
     * @param watermarkImg 水印图片
     * @param outputFile   输出文件
     */
    private static void addMaskWatermark(String inputFile, String watermarkImg, String outputFile) throws IOException {
        // cutImageFromMiddle(inputFile, watermarkImg);
        addImgWatermark(inputFile, outputFile, watermarkImg);
    }

    /**
     * 从中间裁剪图片
     * @param inputFile
     * @param watermarkImg
     * @throws IOException
     */
    private static void cutImageFromMiddle(String inputFile, String watermarkImg) throws IOException {
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

        // 新的水印图片，水印图片太大，需要压缩
        int index = watermarkImg.lastIndexOf(".");
        // 文件名加后缀
        String newWaterMarkImg = watermarkImg.substring(0, index) + "-" + RandomUtil.randomInt(0, 100) + Instant.now().toEpochMilli() + watermarkImg.substring(index + 1);

        ImgUtil.cut(
                FileUtil.file(watermarkImg),
                FileUtil.file(newWaterMarkImg),
                // new Rectangle(0, 3254, 1586, 2244)//裁剪的矩形区域
                new Rectangle(0, startHeight, width, height)//裁剪的矩形区域
        );
    }


    /**
     * 缩放图片
     * - 按比例缩放
     * - 按长宽缩放
     *
     * @param inputFile
     * @param outputFile
     */
    private static void scale(String inputFile, String outputFile) {
        ImgUtil.scale(
                FileUtil.file(inputFile),
                FileUtil.file(outputFile),
                0.2f//缩放比例
                // 200, 200, Color.white
        );
    }


    public static void main(String[] args) throws Exception {
        // 递归生成蒙层图和缩略图
        // recursionGenerateImage();

        // 生成蒙层图
        generateWatermarkImage();
    }

    private static void generateWatermarkImage() {
        String outFolder = "E:\\template\\template-out\\addwater";
        String watermarkImg = "E:\\template\\watermark-793x1122.png";
        Path path = Paths.get("E:\\template\\template-out\\native");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                String fullFileName = entry.getFileName().toString();
                log.info("开始处理文件：{}", fullFileName);

                int index = fullFileName.lastIndexOf(".");
                // 文件后缀
                String fileName = fullFileName.substring(0, index);
                String fileSuffix = fullFileName.substring(index + 1).toLowerCase();
                // 加蒙层
                String watermarkFilePath = outFolder + "\\" + fullFileName;
                addMaskWatermark(entry.toFile().getAbsolutePath(), watermarkImg, watermarkFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 递归生成蒙层图和缩略图
     */
    private static void recursionGenerateImage() {
        LocalDateTime start = LocalDateTime.now();
        String basePath = "E:\\template\\";
        String baseFolder = basePath + "template-all";
        String outFolder = basePath + "template-out";
        String watermarkFile = basePath + "watermark-cut.png";
        Path folderPath = Paths.get(baseFolder); //替换为你的文件夹路径

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
            for (Path entry : stream) {
                // executorService.submit(() -> processToImage(entry, outFolder, watermarkFile));
                processToImage(entry, outFolder, watermarkFile);
            }
            log.info("任务总消耗：{} s", Duration.between(start, LocalDateTime.now()).getSeconds());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processToImage(Path entry, String outFolder, String watermarkFile) {
        LocalDateTime fileStart = LocalDateTime.now();
        String fileAbsolutePath = entry.toAbsolutePath().toString();
        String fullFileName = entry.getFileName().toString();
        log.info("开始处理文件：{}", fullFileName);

        int index = fullFileName.lastIndexOf(".");
        // 文件后缀
        String fileName = fullFileName.substring(0, index);
        String fileSuffix = fullFileName.substring(index + 1).toLowerCase();

        String imageSuffix = ".png";
        String nativePath = outFolder + "\\native";
        try {
            if (fileSuffix.equals("docx") || fileSuffix.equals("doc")) {
                imageSuffix = ".jpg";
                wordToImage(fileAbsolutePath, nativePath, 1);
            } else if (fileSuffix.equals("pdf")) {
                pdfToImage(fileAbsolutePath, nativePath, 1);
            } else if (fileSuffix.equals("xlsx")) {
                excelToImage(fileAbsolutePath, nativePath, 0);
            }
            LocalDateTime imageTime = LocalDateTime.now();
            log.info("【{}】转换图片消耗：{} s", fullFileName, Duration.between(fileStart, imageTime).getSeconds());

            String imageFileName = fileName + imageSuffix;
            String imageFilePath = outFolder + "\\native\\" + imageFileName;

            // 加蒙层
            String watermarkFilePath = outFolder + "\\addwater\\" + imageFileName;
            addMaskWatermark(imageFilePath, watermarkFile, watermarkFilePath);
            LocalDateTime waterTime = LocalDateTime.now();
            log.info("【{}】图片添加水印消耗：{} s", fullFileName, Duration.between(imageTime, waterTime).getSeconds());

            // 缩略图，输出只支持 jpg
            String miniFileName = outFolder + "\\mini\\" + fileName + "-mini" + ".jpg";
            scale(imageFilePath, miniFileName);
            LocalDateTime miniTime = LocalDateTime.now();
            log.info("【{}】缩略图消耗：{} s", fullFileName, Duration.between(waterTime, miniTime).getSeconds());
            log.info("【{}】总消耗：{} s", fullFileName, Duration.between(fileStart, miniTime).getSeconds());
        } catch (Exception e) {
            log.error("处理文件：{} 失败", fullFileName, e);
        }
    }

}
