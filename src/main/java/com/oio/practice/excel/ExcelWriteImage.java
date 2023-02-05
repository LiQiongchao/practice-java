package com.oio.practice.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.Instant;

/**
 * @Author: Liqc
 * @Date: 2023/2/3 10:54
 */
public class ExcelWriteImage {

    @Test
    public void testWriteImg3() throws IOException {
        String path = "C:\\Users\\Qiongchao\\Downloads\\test3.xlsx";
//        String img = "src/main/resources/static/image/adjustSign.png";
        String img = "D:\\temp/adjustSign-230x230.png";
        FileInputStream is = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(2);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);

        InputStream jdis;
        byte[] jdbytes = null;
        try {
            jdis = new FileInputStream(img);
            jdbytes = IOUtils.toByteArray(jdis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Drawing drawing  = null;
        if (sheet instanceof XSSFSheet) {
            XSSFSheet xSSFSheet = (XSSFSheet)sheet;
            drawing = xSSFSheet.getDrawingPatriarch();
        } else if (sheet instanceof HSSFSheet) {
            HSSFSheet hSSFSheet = (HSSFSheet)sheet;
            drawing = hSSFSheet.getDrawingPatriarch();
        }
        if (drawing == null) {
            drawing = sheet.createDrawingPatriarch();
        }
        // 图片插入坐标
        int jdpictureIdx = workbook.addPicture(jdbytes, Workbook.PICTURE_TYPE_PNG);// 根据需要调整参数，如果是PNG，就改为 Workbook.PICTURE_TYPE_PNG
        CreationHelper helper = workbook.getCreationHelper();
        ClientAnchor jdanchor = helper.createClientAnchor();
        jdanchor.setCol1(23);
        jdanchor.setRow1(36);
        // 获取原图片的宽度和高度，单位都是像素
        File image = new File(img);
        BufferedImage sourceImg = ImageIO.read(image);
        double imageWidth = sourceImg.getWidth();
        double imageHeight = sourceImg.getHeight();

        double width = 256;

        // 获取单元格宽度和高度，单位都是像素
        double cellWidth = sheet.getColumnWidthInPixels(cell.getColumnIndex());
        double cellHeight = cell.getRow().getHeightInPoints() / 72 * 96;// getHeightInPoints()方法获取的是点（磅），就是excel设置的行高，1英寸有72磅，一般显示屏一英寸是96个像素
        // 插入图片，如果原图宽度大于最终要求的图片宽度，就按比例缩小，否则展示原图
        Picture pict = drawing.createPicture(jdanchor, jdpictureIdx);
        if (imageWidth > width) {
            double scaleX = width / cellWidth;// 最终图片大小与单元格宽度的比例
            // 最终图片大小与单元格高度的比例
            // 说一下这个比例的计算方式吧：( imageHeight / imageWidth ) 是原图高于宽的比值，则 ( width * ( imageHeight / imageWidth ) ) 就是最终图片高的比值，
            // 那 ( width * ( imageHeight / imageWidth ) ) / cellHeight 就是所需比例了
            double scaleY = ( width * ( imageHeight / imageWidth ) ) / cellHeight;
            pict.resize(scaleX, scaleY);
        } else {
            // pict.resize(imageWidth/cellWidth, imageHeight/cellHeight);
            pict.resize();
        }

        // Write the workbook to a file
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Qiongchao\\Downloads\\image" + Instant.now().getEpochSecond() + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
        is.close();
    }

}
