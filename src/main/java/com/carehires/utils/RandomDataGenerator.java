package com.carehires.utils;

import com.github.javafaker.Faker;
import java.util.Random;
import java.util.UUID;

public class RandomDataGenerator {
    
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateEmail(String domain) {
        return faker.name().firstName().toLowerCase() + "." + 
               faker.name().lastName().toLowerCase() + "@" + domain;
    }

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generateFullName() {
        return faker.name().fullName();
    }

    public static String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateAddress() {
        return faker.address().fullAddress();
    }

    public static String generateCity() {
        return faker.address().city();
    }

    public static String generateZipCode() {
        return faker.address().zipCode();
    }

    public static String generateCompanyName() {
        return faker.company().name();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public static String generateAlphabeticString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public static String generateNumericString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static int generateRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static double generateRandomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static boolean generateRandomBoolean() {
        return random.nextBoolean();
    }
}
