package ru.netology.transfer.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.transfer.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private static final ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public static int getCardBalance (String id) {
        String cardBalance = String.valueOf(cards.findBy(Condition.attribute("data-test-id")));
        return extractBalance(cardBalance);
    }

    public static int extractBalance(String cardBalance) {
        val start = cardBalance.indexOf(balanceStart);
        val finish = cardBalance.indexOf(balanceFinish);
        val value = cardBalance.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public static DashboardPage moneyTransferFromSecondToFirstCard() {
        $$("[data-test-id='action-deposit']").first().click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(100));
        $("[data-test-id='from'] input").setValue(DataHelper.getSecondCardInfo().getNumber());
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

    public static DashboardPage moneyTransferFromFirstToSecondCard() {
        $$("[data-test-id='action-deposit']").last().click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(100));
        $("[data-test-id='from'] input").setValue(DataHelper.getFirstCardInfo().getNumber());
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }

    public static DashboardPage moneyTransferFromSecondToInvalidNumberFirstCard() {
        $$("[data-test-id='action-deposit']").first().click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(100));
        $("[data-test-id='from'] input").setValue("0000 0000 0000 0002");
        $("[data-test-id='action-transfer']").click();
        $("[data-test-id='error-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(Condition.text("Произошла ошибка"));
        return new DashboardPage();
    }

    public static DashboardPage moneyTransferFromSecondToNoNumberFirstCard() {
        $$("[data-test-id='action-deposit']").first().click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(100));
        $("[data-test-id='from'] input").setValue("");
        $("[data-test-id='action-transfer']").click();
        $("[data-test-id='error-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(Condition.text("Произошла ошибка"));
        return new DashboardPage();
    }

    public static DashboardPage moneyTransferFromFirstToInvalidNumberSecondCard() {
        $$("[data-test-id='action-deposit']").last().click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(100));
        $("[data-test-id='from'] input").setValue("0000 0000 0000 0001");
        $("[data-test-id='action-transfer']").click();
        $("[data-test-id='error-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(Condition.text("Произошла ошибка"));
        return new DashboardPage();
    }

    public static DashboardPage moneyTransferFromFirstToNoNumberSecondCard() {
        $$("[data-test-id='action-deposit']").last().click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(100));
        $("[data-test-id='from'] input").setValue("");
        $("[data-test-id='action-transfer']").click();
        $("[data-test-id='error-notification']").shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldHave(Condition.text("Произошла ошибка"));
        return new DashboardPage();
    }

    public static DashboardPage cancelMoneyTransfer() {
        $$("[data-test-id='action-deposit']").last().click();
        $("[data-test-id='amount'] input").setValue(String.valueOf(100));
        $("[data-test-id='from'] input").setValue(DataHelper.getFirstCardInfo().getNumber());
        $("[data-test-id='action-cancel']").click();
        return new DashboardPage();
    }

    public static DashboardPage reloadBalance() {
        $("[data-test-id='action-reload']").click();
        return new DashboardPage();
    }
}
