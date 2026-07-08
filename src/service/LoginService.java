package service;

import dao.LoginDAO;
import model.Person;

public class LoginService {

    private final LoginDAO loginDAO;

    public LoginService() {

        loginDAO = new LoginDAO();

    }

    public Person login(String username, String password) {

        return loginDAO.login(username, password);

    }

}