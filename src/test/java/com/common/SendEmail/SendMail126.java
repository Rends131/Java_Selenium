package com.common.SendEmail;

import com.cases.Test126Mail;
import com.utils.WriteFile;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SendMail126 extends Test126Mail {

    //获取包名
    private String packageName = this.getClass().getPackage().getName();
    //获取类名
    private String className = this.getClass().getName();

    public void sen126Mail(String reportFile,String recipient, String subject, String filePath, String end) throws InterruptedException {
        logger.info("126邮箱：点击写信");
        driver.findElement(By.xpath("//*[@id='_mail_component_149_149']/span[2]")).click(); //点击写信

        logger.info("126邮箱：收件人");
        driver.findElement(By.className("nui-editableAddr-ipt")).sendKeys(recipient);//收件人

        driver.findElement(By.xpath("//*[@aria-label='邮件主题输入框，请输入邮件主题']/input")).sendKeys(subject);//邮件主题

        String fileHash = WriteFile.writeFile(filePath);//写入随机字符串，更改文件hash

        try {
            logger.info("126邮箱：上传文件");
            driver.findElement(By.xpath("//*[@title='一次可发送2000张照片，600首MP3，一部高清电影']/input")).sendKeys(reportFile);//添加附件
            Thread.sleep(10000);
        } catch (RuntimeException ex) {
            logger.info(ex);
        }
        logger.info("126邮箱：上传完成");

        driver.findElement(By.className("nui-toolbar-item")).click();
        logger.info("126邮箱：点击发送");
        Thread.sleep(1000);
        logger.info("126邮箱：发送完成，断言是否发送成功");
        Thread.sleep(3000);


        String useraddr = driver.findElement(By.className("tK1")).getText();
        logger.info("页面的元素:" + useraddr + ",文件的内容:" + end);

        Assert.assertEquals(useraddr, end);

        // 获取方法名
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        if (useraddr.equals(end)) {
            logger.info("126邮箱：断言成功");
            writeExcel(reportFile,packageName + "发送邮件测试", className, methodName, "文件hash:" + fileHash, "pass", "");
        } else {
            writeExcel(reportFile,packageName + "发送邮件测试", className, methodName, "文件hash:" + fileHash, "fail", "登录失败");
        }
    }
}
