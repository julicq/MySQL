package ru.netology.mode.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.mode.data.DataHelper;
import ru.netology.mode.page.DashboardPage;
import ru.netology.mode.page.LoginPage;

import java.sql.SQLException;




public class LoginPageTest {


    /*@AfterEach
    @DisplayName("Should clean Data Base after login")
    void cleanBase() throws SQLException {
        val dashboardPage = new DashboardPage();
        dashboardPage.cleanDataBase();
    }*/

    @AfterAll
    @DisplayName("Should clean Data Base after login")
    static void cleanBase() throws SQLException {
        val dashboardPage = new DashboardPage();
        dashboardPage.cleanDataBase();
    }


    @Test
    @DisplayName("Should login with code from sms")
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
    @DisplayName("Should be error notification if login with wrong password")
    void loginWithWrongPassword() {
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getAuthInfoWithWrongPassword();
        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();
    }

    @Test
    @DisplayName("System should be blocked if login with wrong password four times, but it is not")
    void loginFourTimesWithWrongPassword() {
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getAuthInfoWithWrongPassword();
        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();

        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();

        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();

        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();
    }


    @Test
    @DisplayName("Should login with random user and status active")
    void shouldLoginWithRandomUser() throws SQLException {
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val authInfo = DataHelper.getRandomAuthInfo("active");
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPage();
        val verificationCode = DataHelper.getVerificationCode();
        val dashboardPage = verificationPage.validVerify(verificationCode.getCode());
        dashboardPage.dashboardPage();
    }
}
