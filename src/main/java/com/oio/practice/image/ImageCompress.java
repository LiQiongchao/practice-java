package com.oio.practice.image;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: Liqc
 * @Date: 2023/2/19 10:11
 */
@Slf4j
public class ImageCompress {

    @Test
    public void compressTest0() throws IOException {
        // URL url = ImageCompress.class.getClassLoader().getResource("static/img/001.jpg");

        byte[] imageBytes = Files.readAllBytes(Paths.get("src/main/resources/static/img/001.jpg"));
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length)) {
            Double scale = 0.5, quality = 0.5;
            Thumbnails.of(inputStream).scale(scale).outputQuality(quality).toOutputStream(outputStream);
            imageBytes = outputStream.toByteArray();
            log.info(
                    "【图片压缩】图片缩放比例={} | 图片质量={}kb | 压缩后大小={}",
                    scale, quality,
                    imageBytes.length / 1024);

            // output 转 Input
            // ByteArrayInputStream compressImgIs = new ByteArrayInputStream(outputStream.toByteArray());

            // output 转出文件
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/static/img/001_" + scale + "_" + quality + ".jpg");
            fileOutputStream.write(outputStream.toByteArray());

        } catch (Exception e) {
            log.error("【图片压缩】msg=图片压缩失败!", e);
        }




    }


    @Test
    public void compressTest1() throws IOException {
        /**
         * scale 是图片缩放的比例。值在0到1之间，1f就是原图大小，0.5就是原图的一半大小，这里的大小是指图片的长宽。
         * outputQuality 是输出图片的质量。值也是在0到1，越接近于1质量越好，越接近于0质量越差。
         */
        Thumbnails.of("src/main/resources/static/img/001.jpg").scale(0.5f).outputQuality(0.5).toFile("D:/image_25%.jpg");

    }

}
