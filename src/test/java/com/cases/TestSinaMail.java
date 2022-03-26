package com.cases;

import com.common.Login.LoginSinaPage;
import com.common.SendEmail.SendMailSina;
import com.utils.ReadExcel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


public class TestSinaMail extends Login{
    public final static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static LoginSinaPage LoginSinaPage = new LoginSinaPage();
    public static SendMailSina SendMailSina = new SendMailSina();

    @DataProvider(name = "LoginSina")
    public static Object[][] Login() throws IOException {
        File file = new File(dataFileTest);
        return ReadExcel.getData(file,"Sinamail");
    }

    @Test(dataProvider = "LoginSina")
    public void LoginSinaTest(String mailUrl,String account,String password,String recipient,String Subject,String filePath,String titleAst,String end) throws InterruptedException {
            LoginSinaPage.loginSinaMali(reportFile,mailUrl,account,password,titleAst);
            SendMailSina.senSinaMail(reportFile,recipient,Subject,testfile,titleAst);

    }

}
