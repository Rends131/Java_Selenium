package com.cases;

import com.utils.ExcelReport;
import com.common.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class Login extends ExcelReport {

    public static WebDriver driver;

    private StringBuffer verificationErrors = new StringBuffer();

    public final static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static String testfile = System.getProperty("user.dir") + "/src/main/resources/7.20部分商品上新清单1.xlsx" ;

    public static String dataFileTest = System.getProperty("user.dir")+ "\\src\\main\\resources\\datas\\TestData.xlsx";

    //测试前初始化
    @BeforeMethod
    public void setUp() {
        SetUp login = new SetUp();
        login.setPorperty();
        //设置浏览器属性
        ChromeOptions options = SetUp.setChromeOption();
        //初始化driver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        // 设置隐士等待
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    //测试结束关闭driver，收尾  使用AfterMethod 代表每执行一个测试方法，就运行一次
//    @AfterClass
    @AfterMethod
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
