package com.common;

import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.Properties;

public class SetUp {
    public void setPorperty(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
    }

    //访问的ip
    public static String getBaseUrl(String url){
        Properties porp = new Properties();
        String baseUrl = null;
        try {
            //读取URL地址
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("src\\test\\java\\com\\datas\\baseUrl.properties")));
            porp.load(inputStream);
            baseUrl = porp.getProperty(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseUrl;
    }

    public static ChromeOptions setChromeOption() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        return options;
    }

}
