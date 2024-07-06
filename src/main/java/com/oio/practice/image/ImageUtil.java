package com.oio.practice.image;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.func.Func;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.RandomUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: Liqc
 * @Date: 2023/2/20 13:06
 */
@Slf4j
public class ImageUtil {

    /**
     * 上传水印图片和缩略图
     * @param bucketName
     * @param file
     * @return
     * @throws IOException
     */
    public RileTransferVo uploadWatermarkImageAndMini(String bucketName, MultipartFile file) {

        //后缀
        String lastName ="png";

        RileTransferVo fileVo = null;
        InputStream inputStream = null;
        ByteArrayOutputStream os = null;
        try {
            inputStream = file.getInputStream();
            BufferedImage oldImg = ImageIO.read(inputStream);
            int width = oldImg.getWidth();
            int height = oldImg.getHeight();
            // 动态计算水印宽高和字体大小
            int watermarkWidth = (int) (width * 0.5);
            int watermarkHeight = height * watermarkWidth / width;
            int faceSize = watermarkWidth / 12;

            BufferedImage bi = new BufferedImage(watermarkWidth, watermarkHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bi.createGraphics();
            //设置绘图区域透明
            bi = graphics.getDeviceConfiguration().createCompatibleImage(watermarkWidth, watermarkHeight, Transparency.TRANSLUCENT);
//			graphics.dispose();
            //字体、字体大小，透明度，旋转角度
            graphics = bi.createGraphics();
            //设置linux支持的字体
            graphics.setFont(new Font("宋体", Font.BOLD, faceSize));
            // 角度，-0.1 表示逆时针10度角
            graphics.rotate(-0.1);
            graphics.setColor(Color.lightGray);

            //水印
            char[] watermark1 = "地铁巡查".toCharArray();
            char[] watermark2 = DateUtil.formatDateTime(new Date()).toCharArray();
            //设置文本显示坐标，坐标是从左上角开始的，y轴向下是正增长，watermark2 在watermark1下面
            graphics.drawChars(watermark1, 0, watermark1.length, faceSize * 3, faceSize * 5);
            graphics.drawChars(watermark2, 0, watermark2.length, faceSize, faceSize * 7);

            graphics.dispose();

            Thumbnails.Builder builder = Thumbnails.of(file.getInputStream()).scale(1);
            for (int w = 0; w <= width / watermarkWidth; w++) {
                for (int h = 0; h <= height / watermarkHeight; h++) {
                    builder.watermark(new Coordinate(w * watermarkWidth, h * watermarkHeight), bi, 0.5f);
                }
            }
            BufferedImage newImg = builder.outputQuality(0.5).asBufferedImage();
            //创建一个ByteArrayOutputStream
            os = new ByteArrayOutputStream();
            //把BufferedImage写入ByteArrayOutputStream
            ImageIO.write(newImg, lastName, os);
            //ByteArrayOutputStream转成InputStream
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            //随机文件UUID名
            String fileName = UUID.randomUUID().toString().replace("-", "");
            if (fileName.length() < 32) {
                //不足32位，补全位数
                String s = randomNum(32 - fileName.length());
                fileName = fileName + s;
            }

            // 上传
            String  folderFileName = fileName+"."+lastName;
            String contentType = file.getContentType();

            if (fileVo != null) {
                // 上传缩略图
                RileTransferVo transferVo = uploadCompressImage(bucketName, fileVo.getUrl());

                if (transferVo != null) {
                    fileVo.setMiniUrl(transferVo.getUrl());
                    fileVo.setMiniOpenUrl(transferVo.getOpenUrl());
                }
            }
        } catch (IOException e) {
            log.error("水印上传图片异常：", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
        return fileVo;
    }

    /**
     * 水印图片
     * @param imagePath
     * @return
     * @throws IOException
     */
    public static void addWatermarkToImage(String imagePath) throws IOException {

        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        //后缀
        String lastName = imagePath.substring(imagePath.lastIndexOf(".") + 1);

        InputStream inputStream = null;
        ByteArrayOutputStream os = null;
        try {
            inputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage oldImg = ImageIO.read(inputStream);
            int width = oldImg.getWidth();
            int height = oldImg.getHeight();
            // 动态计算水印宽高和字体大小
            int watermarkWidth = (int) (width * 0.5);
            int watermarkHeight = height * watermarkWidth / width;
            int fontSize = watermarkWidth / 12;

            BufferedImage bi = new BufferedImage(watermarkWidth, watermarkHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bi.createGraphics();
            // graphics.clearRect(0, 0, width, height);
            //设置绘图区域透明
            bi = graphics.getDeviceConfiguration().createCompatibleImage(watermarkWidth, watermarkHeight, Transparency.TRANSLUCENT);
			graphics.dispose();
            //字体、字体大小，透明度，旋转角度
            graphics = bi.createGraphics();
            //设置linux支持的字体
            graphics.setFont(new Font("宋体", Font.BOLD, fontSize));
            // 角度，-0.1 表示逆时针10度角
            graphics.rotate(-0.1);
            graphics.setColor(Color.lightGray);

            //水印
            char[] watermark1 = "地铁巡查".toCharArray();
            char[] watermark2 = DateUtil.formatDateTime(new Date()).toCharArray();
            //设置文本显示坐标，坐标是从左上角开始的，y轴向下是正增长，watermark2 在watermark1下面
            graphics.drawChars(watermark1, 0, watermark1.length, fontSize * 3, fontSize * 5);
            graphics.drawChars(watermark2, 0, watermark2.length, fontSize, fontSize * 7);

            graphics.dispose();

            BufferedImage originalImage = ImageIO.read(inputStream);
            Thumbnails.Builder builder = Thumbnails.of(originalImage).scale(1);
            for (int w = 0; w <= width / watermarkWidth; w++) {
                for (int h = 0; h <= height / watermarkHeight; h++) {
                    builder.watermark(new Coordinate(w * watermarkWidth, h * watermarkHeight), bi, 0.6f);
                }
            }
            // builder.toFile("d:/img-" + Instant.now().toEpochMilli() + lastName);
            BufferedImage newImg = builder.asBufferedImage();
            //创建一个ByteArrayOutputStream
            os = new ByteArrayOutputStream();
            //把BufferedImage写入ByteArrayOutputStream
            ImageIO.write(newImg, lastName, os);
            //ByteArrayOutputStream转成InputStream
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            File file = new File("d:/100.jpg");
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(inputStream.read());


        } catch (IOException e) {
            log.error("水印上传图片异常：", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 图片压缩处理并上传 Minio
     * @param bucketName Minio 桶名
     * @param url
     * @return
     */
    public static RileTransferVo uploadCompressImage(String bucketName, String url){
        if (StringUtils.isEmpty(url) || !url.startsWith("http")) {
            return null;
        }
        RileTransferVo transferVo = null;
        ByteArrayInputStream compressImgIs = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Double scale = 0.5, quality = 0.5;
            Thumbnails.of(url).scale(scale).outputQuality(quality).toOutputStream(outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            log.info("【图片压缩】图片缩放比例={} | 图片质量={}kb | 压缩后大小={}", scale, quality, imageBytes.length / 1024);

            // output 转 Input
            compressImgIs = new ByteArrayInputStream(outputStream.toByteArray());

            String urlFileName = url.substring(url.lastIndexOf("/") + 1);
            int dotIndex = urlFileName.lastIndexOf(".");
            String fileName = urlFileName.substring(0, dotIndex) + "-mini" + urlFileName.substring(dotIndex);
            // transferVo = fileUpload(bucketName, compressImgIs, fileName, null);
        } catch (Exception e) {
            log.error("【图片压缩】msg=图片压缩失败!", e);
        } finally {
            if (compressImgIs != null) {
                try {
                    compressImgIs.close();
                } catch (IOException e) {
                    log.error("上传图片输入流关闭异常", e);
                }
            }
        }
        return transferVo;
    }


    /**
     * 获取指定位随机数
     */
    public static String randomNum(int len) {
        SecureRandom random = new SecureRandom();
        String returnValue = "";
        int randomInt = 0;
        int range = 9;
        for (int i = 0; i < len; i++) {
            randomInt = random.nextInt(range + 1);
            returnValue = returnValue + randomInt;
        }
        return returnValue;
    }

    /**
     * 裁剪图片到A4大小
     */
    @Test
    public void imageCropToA4 () throws IOException {
        // A4尺寸的宽度和高度（以像素为单位）
        int a4Width = 595;
        int a4Height = 842;
        // int a4Width = 792;
        // int a4Height = 1122;

        boolean isScale = false;
        int xOffset = 0;
        int yOffset = 0;

        File inputFile = FileUtil.file("C:\\Users\\Qiongchao\\Desktop\\template\\template-out\\native\\社区工作过程记录表.png");
        File outFile = FileUtil.file("C:\\Users\\Qiongchao\\Desktop\\template\\template-out\\native\\社区工作过程记录表-1.png");

        // 先计算水印图片的大小，如果水印图片太大，无法添加图片水印
        BufferedImage image = ImageIO.read(inputFile);
        int iw = image.getWidth(null);
        int ih = image.getHeight(null);

        // 获取要裁剪的位置
        BufferedImage wImage = ImageIO.read(inputFile);
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
            if ((startHeight + yOffset + ih) <= wh) {
                startHeight += yOffset;
            }
        }

        String watermarkImg = inputFile.getName();
        // 新的水印图片，水印图片太大，需要压缩
        int index = watermarkImg.lastIndexOf(".");
        // 文件名加后缀
        String newWaterMarkImg = watermarkImg.substring(0, index) + "-" + RandomUtil.randomInt(0, 100) + Instant.now().toEpochMilli() + watermarkImg.substring(index + 1);

        ImgUtil.cut(
                inputFile,
                outFile,
                // new Rectangle(0, 3254, 1586, 2244)//裁剪的矩形区域, 图像切割(按指定起点坐标和宽高切割)
                new Rectangle(0, startHeight, width, height)//裁剪的矩形区域
        );
    }


}
