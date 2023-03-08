package com.oio.practice.image;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.awt.*;
import java.time.Instant;

/**
 * @Author: Liqc
 * @Date: 2023/2/20 11:51
 */
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

}
