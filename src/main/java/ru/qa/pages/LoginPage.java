package ru.qa.pages;

public class LoginPage extends BasePage {
    @Override
    protected String getPageName() {
        return "Login page";
    }

    @Override
    protected void waitForPageLoad() {
        System.out.println("[LoginPage] Waiting for login form to load");
    }

    @Override
    protected void verifyPageOpened() {
        System.out.println("[LoginPage] Verifying login button is visible");
    }

    public void login(String username, String password) {
        System.out.println("[LoginPage] Logging in as: " + username);
    }
}
