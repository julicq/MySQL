package ru.netology.mode.page;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.mode.data.DataHelper;
import ru.netology.mode.data.User;
import ru.netology.mode.page.VerificationPage;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private SelenideElement form = $("form");
    private SelenideElement loginField = form.$("[data-test-id=login] input");
    private SelenideElement passwordField = form.$("[data-test-id=password] input");
    private SelenideElement loginButton = form.$("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");
    private SelenideElement personPage = $(Selectors.ByText.cssSelector("Личный кабинет"));

    public void openLoginPage() {
        open("http://localhost:9999");
    }

    public void errorNotificationCreate() {
        errorNotification.shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public static void getNewLoginPageAfterClean() throws SQLException {
        val cleanUser = "DELETE FROM users WHERE created < NOW() - INTERVAL 5 MINUTE;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")) {
            val usersAfterClean = runner.query(conn, cleanUser, new BeanHandler<>(User.class));
        }
    }
}
