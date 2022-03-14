package com.common.Login;

import com.cases.Test126Mail;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Login126Page extends Test126Mail {

    //获取包名
    private String packageName = this.getClass().getPackage().getName();
    //获取类名
    private String className = this.getClass().getName();

    // 登录163邮箱
    public void login126Mail(String mailUrl, String username, String password, String titleAst) throws InterruptedException {
        driver.get(mailUrl);
        logger.info("进入126邮箱");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(mailUrl, currentUrl);

        logger.info("126邮箱：进入登录");
        Thread.sleep(1000);
//            driver.switchTo().frame(iframea);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id, 'x-URS-iframe')]")));
        logger.info("126邮箱：输入账号密码");
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);

        logger.info("126邮箱：等待2秒，点击登录");
        Thread.sleep(2000);
        driver.findElement(By.id("dologin")).click();


        driver.switchTo().defaultContent();
        logger.info("126邮箱：等待2秒，点击登录");
        Thread.sleep(2000);


        String impatl = driver.findElement(By.id("imgLogo")).getAttribute("alt");

        logger.info("126邮箱：" + impatl + "，并断言");
        Thread.sleep(5000);
        logger.info(impatl + "    " + titleAst);
        // 获取方法名
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        if (impatl.equals(titleAst)) {
            writeExcel(packageName + "登录测试", className, methodName, "126登录", "pass", "");
        } else {
            writeExcel(packageName + "登录测试", className, methodName, "126登录", "fail", "登录失败");
        }

    }
}
