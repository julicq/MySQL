package ru.netology.mode;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement smsCode = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("data-test-id=action-verify");

    public VerificationPage() {
        smsCode.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        smsCode.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
