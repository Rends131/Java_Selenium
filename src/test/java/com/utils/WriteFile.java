package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class WriteFile {
    public final static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static String writeFile(String filePath){
        File file = new File(filePath);
        logger.info("文件hash："+ HashCodeUtil.getFilesha1Hash(file));
        WrithExcel(file,10);
        logger.info("文件hash："+ HashCodeUtil.getFilesha1Hash(file));
        return HashCodeUtil.getFilesha1Hash(file);
    }

    //传入 .xlsx 文件路径，传入随机生成字符串字数
    public static void WrithExcel(File path,int num){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            fileOutputStream = new FileOutputStream(path);

            XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);//新增一行
            XSSFCell cell1 = row.createCell(0);//行中的第“0+1”列
            cell1.setCellValue(RundomString.getRandomString(num));//列中放的值
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(RundomString.getRandomString(num));
            fileOutputStream.flush();
            xssfWorkbook.write(fileOutputStream);
        }catch (Exception ex) {
            System.out.println(ex);
        }finally {
            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            }catch (Exception ex){
                System.out.println(ex);
            }finally {
                try {
                    if (fileOutputStream != null){
                        fileOutputStream.close();
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
        }
    }

    // 传入 .txt 文件路径，和插入随机字符串长度
    public static void writeTxt(File path,int num) {
        String conent = RundomString.getRandomString(num);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path, true)));
            out.write(conent + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
