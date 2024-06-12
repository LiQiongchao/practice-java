package com.oio.practice.image;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Coordinate;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * @Author: Liqc
 * @Date: 2023/2/20 11:51
 */
@Slf4j
public class HutoolThumbnail {

    @Test
    public void testThumbnail() {
        String img = "d:/002.jpg";

        ImgUtil.pressText(//
                FileUtil.file(img), //
                FileUtil.file("d:/002-001.jpg"), //
                "综合巡查", Color.BLACK, //文字
                new Font("宋体", Font.BOLD, 100), //字体
                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.6f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );
        ImgUtil.pressText(//
                FileUtil.file("d:/002-001.jpg"), //
                FileUtil.file("d:/001-" + Instant.now().toEpochMilli() + ".jpg"), //
                "2023-02-20", Color.BLACK, //文字
                new Font("宋体", Font.BOLD, 100), //字体
                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                -120, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.6f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );
    }

    @Test
    public void compress() {
        Img.from(FileUtil.file("C:\\Users\\Qiongchao\\Downloads\\1.jpg"))
                .setQuality(0.3)//压缩比率
                .write(FileUtil.file("C:\\Users\\Qiongchao\\Downloads\\1-0.3.jpg"));
    }


    /**
     * 加多条水印左对齐
     * @throws IOException
     */
    @Test
    public void addWatermark() throws IOException {
        File file = FileUtil.file("C:\\Users\\Qiongchao\\Desktop\\img\\11.jpg");

        // 图片格式后缀
        String originalFilename = file.getName();
        String imgSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        BufferedImage oldImg = ImageIO.read(file);
        int width = oldImg.getWidth();
        int height = oldImg.getHeight();
        log.info("width: {}, height: {}", width, height); // 4032 x 3024
        File outFile = FileUtil.file("C:\\Users\\Qiongchao\\Desktop\\img\\test_103.jpg");
        Font font = new Font("黑体", Font.BOLD, 70);
        int defaultx = 0;
        width -= 100; // 写文字时左边框留 50
        String pressText = "古城慧智";
        int firstWidth = (calLineWidth(font, pressText) / 2) - width / 2;
        int firstHeight = height / 2 - 300;
        log.info("first width: {}, height: {}", firstWidth, firstHeight);
        String secondText = "时间：2024-03-27 14:08:01";
        String threeText = "位置：北京市石景山区古城路102号";

        ImgUtil.pressText(
                file, //
                outFile, //
                pressText, Color.BLACK, //文字
                font, //字体
                firstWidth, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                firstHeight, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );
        int secondWidth = (calLineWidth(font, secondText) / 2) - width / 2;
        int secondHeight = height /2 - 200;
        log.info("first width: {}, height: {}", secondWidth, secondHeight);
        ImgUtil.pressText(
                outFile, //
                outFile, //
                secondText, Color.BLACK, //文字
                font, //字体
                secondWidth , //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                secondHeight, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );
        int threeWidth = (calLineWidth(font, threeText) / 2) - width / 2;
        int threeHeight = height /2 - 100;
        log.info("first width: {}, height: {}", threeWidth, threeHeight);
        ImgUtil.pressText(
                outFile, //
                outFile, //
                threeText, Color.BLACK, //文字
                font, //字体
                threeWidth , //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                threeHeight, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );
    }


    /**
     * 计算一行文本占用的长度
     * @param font
     * @param text
     * @return
     */
    private int calLineWidth(Font font, String text) {
        // 创建一个Graphics2D对象，用于获取FontMetrics
        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dummyImage.createGraphics();

        // 设置字体
        g2d.setFont(font);

        // 获取FontMetrics对象
        FontMetrics fontMetrics = g2d.getFontMetrics();

        // 使用FontMetrics计算字符串宽度
        int width = fontMetrics.stringWidth(text);

        // 释放资源
        g2d.dispose();

        return width;
    }


    /**
     * 原生加水印
     */
    @Test
    public void addWatermarkTest() {
        // 古城慧智
        // 	时间：2024-03-27 14:08:01
        // 	位置：北京市石景山区古城路102号
        String watermark = "古城慧智";
        File file = FileUtil.file("C:\\Users\\Qiongchao\\Desktop\\img\\11.jpg");
        // 图片格式后缀
        String originalFilename = file.getName();
        String lastName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        ByteArrayOutputStream os = null;
        try {
            BufferedImage oldImg = ImageIO.read(file);
            int width = oldImg.getWidth();
            int height = oldImg.getHeight();
            // 动态计算水印宽高和字体大小
            int watermarkWidth = (int) (width * 0.5);
            int watermarkHeight = height * watermarkWidth / width;
            int faceSize = watermarkWidth / 12;

            BufferedImage bi = new BufferedImage(watermarkWidth, watermarkHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            //设置绘图区域透明
            bi = g.getDeviceConfiguration().createCompatibleImage(watermarkWidth, watermarkHeight, Transparency.TRANSLUCENT);
//			g.dispose();
            //字体、字体大小，透明度，旋转角度
            g = bi.createGraphics();
            //设置linux支持的字体
            g.setFont(new Font("宋体", Font.BOLD, faceSize));
            // 角度，-0.1 表示逆时针10度角
            g.rotate(-0.1);
            g.setColor(Color.lightGray);

            //水印
            char[] watermark1 = watermark.toCharArray();
            char[] watermark2 = DateUtil.formatDateTime(new Date()).toCharArray();
            //设置文本显示坐标，坐标是从左上角开始的，y轴向下是正增长，watermark2 在watermark1下面
            g.drawChars(watermark1, 0, watermark1.length, faceSize * 3, faceSize * 5);
            g.drawChars(watermark2, 0, watermark2.length, faceSize, faceSize * 7);

            g.dispose();
            // ImageIO.write(bi, "JPG", new File("d:/001001.jpg"));
            Thumbnails.Builder builder = Thumbnails.of(file).scale(1);
            for (int w = 0; w <= width / watermarkWidth; w++) {
                for (int h = 0; h <= height / watermarkHeight; h++) {
                    builder.watermark(new Coordinate(w * watermarkWidth, h * watermarkHeight), bi, 0.5f);
                }
            }
            BufferedImage newImg = builder.asBufferedImage();
            //创建一个ByteArrayOutputStream
            os = new ByteArrayOutputStream();
            //把BufferedImage写入ByteArrayOutputStream
            // ImageIO.write(newImg, lastName, os);
            // 保存图片
            ImageIO.write(newImg, lastName, new File("C:\\Users\\Qiongchao\\Desktop\\img\\66.jpg"));
        } catch (IOException e) {
            log.error("水印上传图片异常：", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
