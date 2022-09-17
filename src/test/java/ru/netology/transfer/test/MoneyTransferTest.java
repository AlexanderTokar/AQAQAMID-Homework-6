package ru.netology.transfer.test;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.transfer.data.*;
import ru.netology.transfer.page.*;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void shouldTransferMoneyHappyPath() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = ru.netology.transfer.page.DashboardPage.moneyTransferFromSecondToFirstCard();
    }

    @Test
    public void  shouldTransferMoneyAnotherHappyPath() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = ru.netology.transfer.page.DashboardPage.moneyTransferFromFirstToSecondCard();
    }

    @Test
    public void shouldCancelMoneyTransfer() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = ru.netology.transfer.page.DashboardPage.cancelMoneyTransfer();
    }

    @Test
    public void shouldReloadBalance() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = ru.netology.transfer.page.DashboardPage.reloadBalance();
    }

    @Test
    public void shouldNotTransferFromSecondToInvalidNumberFirstCard() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = ru.netology.transfer.page.DashboardPage.moneyTransferFromSecondToInvalidNumberFirstCard();
    }

    @Test
    public void shouldNotTransferFromSecondToNoNumberFirstCard() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = ru.netology.transfer.page.DashboardPage.moneyTransferFromSecondToNoNumberFirstCard();
    }

    @Test
    public void shouldNotTransferFromFirstToInvalidNumberSecondCard() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = ru.netology.transfer.page.DashboardPage.moneyTransferFromFirstToInvalidNumberSecondCard();
    }

    @Test
    public void shouldNotTransferFromFirstToNoNumberSecondCard() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var DashboardPage = ru.netology.transfer.page.DashboardPage.moneyTransferFromFirstToNoNumberSecondCard();
    }
}
