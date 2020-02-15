package ru.netology.mode.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.mode.data.DataHelper;
import ru.netology.mode.page.LoginPage;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
//import static ru.netology.mode.page.LoginPage.getNewLoginPageAfterClean;


public class LoginPageTest {

   /* @BeforeEach
    void setUp() throws SQLException {

        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        ) {
        }
    }*/

   /* @AfterEach
    void cleanBase() throws SQLException {
        val loginPage = new LoginPage();
        loginPage.getNewLoginPageAfterClean();
    }*/


    @Test
    void shouldLoginWithSmsCode() throws SQLException{
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPage();
        val verificationCode = DataHelper.getVerificationCode();
        val dashboardPage = verificationPage.validVerify(verificationCode.getCode());
        dashboardPage.dashboardPage();
    }

    @Test
    void errorNotificationVisibleIfLoginWithWrongPassword() {
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getAuthInfoWithWrongPassword();
        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();
    }
}
