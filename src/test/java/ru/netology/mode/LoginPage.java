package ru.netology.mode;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private SelenideElement form = $("form");
    private SelenideElement loginField = form.$("[data-test-id=login] input");
    private SelenideElement passwordField = form.$("[data-test-id=password] input");
    private SelenideElement loginButton = form.$("[data-test-id=action-login]");

    public void openLoginPage() {
        open("http://localhost:9999");
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}
