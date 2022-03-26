package com.cases;

import com.common.Login.Login126Page;
import com.common.SendEmail.SendMail126;
import com.utils.ReadExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Test126Mail extends Login {
    //    private String MailUrl = getBaseUrl("126mail");


    public static Login126Page login126Page = new Login126Page();

    public static SendMail126 sendMail126 = new SendMail126();

    //用数组接收从TestData中读取的数据，这里要注意，一个xlsx文件可能有很多sheet表，所以sheetname要对应
    @DataProvider(name = "Login126")
    public static Object[][] Login() throws IOException {
        File file = new File(dataFileTest);
        return ReadExcel.getData(file, "126mail");
    }

    /**
     * @param account   账号
     * @param password  密码
     * @param recipient 接受人
     * @param subject   主题
     * @param filePath  文件路径
     * @param titleAst  断言title
     * @param end       断言是否发送成功
     */

    @Test(dataProvider = "Login126")
    public void LoginTest(String mailUrl, String account, String password, String recipient, String subject, String filePath, String titleAst, String end) throws InterruptedException {
        login126Page.login126Mail(reportFile,mailUrl, account, password, titleAst);
        logger.info("3,发送邮件");
        sendMail126.sen126Mail(reportFile,recipient, subject, testfile, end);
    }

}
