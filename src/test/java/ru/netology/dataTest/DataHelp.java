package ru.netology.dataTest;

import lombok.Value;

import java.util.Map;
import java.util.Random;

public class DataHelp {
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static int generateValidAmount(int balance){
        return new Random().nextInt(Math.abs(balance)) + 1;
    }
    public static int generateInvalidAmount(int balance){
        return Math.abs(balance) + new Random().nextInt(10000);
    }

    @Value
    public static class CardInfo {
        private String cardNum;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002");
    }


}
