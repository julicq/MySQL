package ru.netology.mode.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public void dashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public static void cleanDataBase() throws SQLException {
        val cleanCards = "DELETE FROM cards WHERE created < NOW() - INTERVAL 5 MINUTE;";
        val cleanAuthCodes = "DELETE FROM auth_codes WHERE created < NOW() - INTERVAL 5 MINUTE;";
        val cleanUser = "DELETE FROM users WHERE created < NOW() - INTERVAL 5 MINUTE;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/app", "app", "pass")) {

        }
    }
}
