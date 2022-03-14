package com.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelReport {
    public static int runNumber = 1;

    public static void writeExcel(String packageName, String className, String methodName, String remark, String result, String reason) {
        try {

            String path = System.getProperty("user.dir") + "/src/main/resources/datas/TestReport.xlsx";
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(path));
            //指定文件sheet页
            XSSFSheet sheet = wb.getSheetAt(0);
            //获得EXCEL行数
            int rowNums = sheet.getLastRowNum();
            System.out.println("exce一共：" + rowNums + "行");

            //往sheet中追加一行数据
            int rowCurrentNumber = rowNums + 1;
            sheet.createRow(rowCurrentNumber);
            XSSFRow row = sheet.getRow(rowCurrentNumber);
            //格式
            CellStyle cellStyle2 = wb.createCellStyle();
            cellStyle2.setFillForegroundColor(IndexedColors.RED.getIndex());// 前景色
            cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);//底部边框

            if (row != null) {
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String currentTime = dateFormat.format(now);

                //创建单元格
                row.createCell(0).setCellValue(currentTime);
                row.createCell(1).setCellValue(packageName);
                row.createCell(2).setCellValue(className);
                row.createCell(3).setCellValue(methodName);
                row.createCell(4).setCellValue(remark);
                row.createCell(5).setCellValue(result);
                if (result.equals("fail")) {
                    row.getCell(5).setCellStyle(cellStyle2);
                }
                row.createCell(6).setCellValue(reason);

            } else {
                System.out.println("行为空");
            }
            FileOutputStream os = new FileOutputStream(path);
            wb.write(os);
            os.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
