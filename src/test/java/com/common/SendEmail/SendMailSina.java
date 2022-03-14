package com.common.SendEmail;

import com.cases.TestSinaMail;
import com.utils.WriteFile;
import org.openqa.selenium.By;

public class SendMailSina extends TestSinaMail {
    //获取包名
    private String packageName = this.getClass().getPackage().getName();
    //获取类名
    private String className = this.getClass().getName();

    public void senSinaMail(String recipient, String subject, String filePath, String end) throws InterruptedException {

        logger.info("进入sina邮箱");
        driver.findElement(By.xpath("//*[@id='coreBtn']/ul/li[1]/a")).click();
        logger.info("接收人");
        driver.findElement(By.xpath("//*[@id='tr_to']/td/ul/li/input")).sendKeys(recipient);
        Thread.sleep(3000);
        logger.info("主题");
        driver.findElement(By.name("subj")).sendKeys(subject);
        Thread.sleep(3000);

        logger.info("上传文件");
        String fileHash = fileHash = WriteFile.writeFile(filePath);//写入随机字符串，更改文件hash
        try {
            driver.findElement(By.name("atth1")).sendKeys(filePath);
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.info("文件上传报错");
        }

        logger.info("发送");
        driver.findElement(By.xpath("//*[@id='panel_main']/div[1]/span/span[1]/a/i[2]")).click();
        logger.info("jieshu");
        String endAss = driver.findElement(By.xpath("//*[@id='send_normal']/div/p[1]")).getText();
        logger.info(endAss);
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        if (endAss.equals(end)) {
            logger.info("sina邮箱：断言成功");
            writeExcel(packageName + "Sina发送邮件测试", className, methodName, "文件hash:" + fileHash, "pass", "");

        } else {
            logger.info("sina邮箱：断言失败");
            writeExcel(packageName + "Sina发送邮件测试", className, methodName, "文件hash:" + fileHash, "fail", "发送失败");
        }
        logger.info("运行完成");
    }
}
