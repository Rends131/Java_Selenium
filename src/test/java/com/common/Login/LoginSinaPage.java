package com.common.Login;

import com.cases.TestSinaMail;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginSinaPage extends TestSinaMail {

    // 获取包名
    private String packageName = this.getClass().getPackage().getName();
    //获取类名
    private String className = this.getClass().getName();


    //登录新浪邮箱
    public void loginSinaMali(String reportFile,String mailUrl, String account, String password, String titleAst) throws InterruptedException {

        logger.info("进入邮箱");
        driver.get(mailUrl);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(mailUrl, currentUrl);
        logger.info("登录");
        driver.findElement(By.id("freename")).sendKeys(account);
        driver.findElement(By.id("freepassword")).sendKeys(password);
        Thread.sleep(5000);
        driver.findElement(By.className("loginBtn")).click();

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Thread.sleep(2000);
        String impatl = driver.getTitle();
        if (impatl.equals(titleAst)) {
            logger.info("断言成功");
            logger.info("登陆完成");
            writeExcel(reportFile,packageName + "sina登录测试", className, methodName, "sina登录", "pass", "");
        } else {
            logger.info("断言失败");
            writeExcel(reportFile,packageName + "sina登录测试", className, methodName, "sina登录", "fail", "");
        }
    }
}
