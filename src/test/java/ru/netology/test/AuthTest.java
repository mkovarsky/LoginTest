package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.dataGenerator.RegistrationDataGenerator;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Тест авторизации")
public class AuthTest {
    @Test
    @DisplayName("Позитивный кейс")
    void shouldSubmitValidCredentials() {
        open("http://localhost:9999");
        val user = RegistrationDataGenerator.generateActiveUser();
        $("input[name =login]").setValue(user.getLogin());
        $("input[name=password]").setValue(user.getPassword());
        $("button[type=button][data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс, пользователь заблокирован")
    void shouldNotBlockedUser() {
        open("http://localhost:9999");
        val user = RegistrationDataGenerator.generateBlockedUser();
        $("input[name =login]").setValue(user.getLogin());
        $("input[name=password]").setValue(user.getPassword());
        $("button[type=button][data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс, невведен логин")
    void shouldNotSubmitWithoutLogin() {
        open("http://localhost:9999");
        val user = RegistrationDataGenerator.generateActiveUser();
        $("input[name=password]").setValue(user.getPassword());
        $("button[type=button][data-test-id=action-login]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс, невведен пароль")
    void shouldNotSubmitWithoutPassword() {
        open("http://localhost:9999");
        val user = RegistrationDataGenerator.generateActiveUser();
        $("input[name =login]").setValue(user.getLogin());
        $("button[type=button][data-test-id=action-login]").click();
        $(withText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс, неверный логин")
    void shouldNotSubmitInvalidLogin() {
        open("http://localhost:9999");
        val user = RegistrationDataGenerator.generateActiveUser();
        $("input[name =login]").setValue("abracadabra");
        $("input[name=password]").setValue(user.getPassword());
        $("button[type=button][data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс, неверный пароль")
    void shouldNotSubmitInvalidPassword() {
        open("http://localhost:9999");
        val user = RegistrationDataGenerator.generateActiveUser();
        $("input[name =login]").setValue(user.getLogin());
        $("input[name=password]").setValue("abracadabra");
        $("button[type=button][data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(Condition.visible);
    }
    @Test
    @DisplayName("Негативный кейс, пользователь не зарегистрирован")
    void shouldNotSubmitCredentialsOfUnregisteredUser() {
        open("http://localhost:9999");
        val user = RegistrationDataGenerator.generateUnregisteredUser();
        $("input[name =login]").setValue(user.getLogin());
        $("input[name=password]").setValue(user.getPassword());
        $("button[type=button][data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(Condition.visible);
    }
}
