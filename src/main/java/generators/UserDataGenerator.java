package generators;

import client.User;
import com.github.javafaker.Faker;

import java.util.Locale;

public class UserDataGenerator {
    private static final Faker generatorRU = Faker.instance(new Locale("ru_RU"));
    private static final Faker generatorEN = Faker.instance(new Locale("en_EN"));

    // Генерация имени пользователя
    public static String userName() { return generatorRU.name().username(); }

    // Генерация емейла
    public static String userEmail() {
        return generatorEN.internet().emailAddress();
    }

    // Генерация пароля
    public static String userPassword() {
        return generatorEN.internet().password();
    }

    // Генерация пароля в котором не хватает символов
    public static String userIncorrectPassword() {
        return generatorEN.internet().password(1, 5);
    }

    // Создание пользователя с корректными данными
    public static User createRandomUser() {
        String name = userName();
        String email = userEmail();
        String password = userPassword();
        return new User(name, email, password);
    }

    // Создание пользователя с некорректным паролем
    public static User createRandomUserWithIncorrectPassword() {
        String name = userName();
        String email = userEmail();
        String password = userIncorrectPassword();
        return new User(name, email, password);
    }
}
