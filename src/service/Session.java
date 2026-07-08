package service;

import model.Person;

public class Session {

    private static Person currentUser;

    public static void login(Person person) {

        currentUser = person;

    }

    public static Person getCurrentUser() {

        return currentUser;

    }

    public static void logout() {

        currentUser = null;

    }

    public static boolean isLogin() {

        return currentUser != null;

    }

}