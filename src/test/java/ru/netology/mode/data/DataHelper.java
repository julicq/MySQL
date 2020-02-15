package ru.netology.mode.data;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {

    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithWrongPassword() {
        return new AuthInfo("vasya", "12345");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode() throws SQLException {
        String verificationCode = "";
        val codesSQL = "SELECT * FROM auth_codes ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/app", "app", "pass")) {
            val usersCode = runner.query(conn, codesSQL, new BeanHandler<>(User.class));
            verificationCode = usersCode.getCode();
        }
        return new VerificationCode(verificationCode);
    }
}
