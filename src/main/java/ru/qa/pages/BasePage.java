package ru.qa.pages;

/**
 * Base class for all Page Objects.
 * Implements Template Method pattern for page initialization.
 */
public abstract class BasePage {
    // protected WebDriver driver;  // TODO: добавим позже

    /**
     * Template method - initializes page in defined order.
     */
    public void initializePage() {
        waitForPageLoad();
        verifyPageOpened();
        System.out.println("[BasePage] Page initialized: " + getPageName());
    }

    /**
     * Each page must specify its name.
     */
    protected abstract String getPageName();

    /**
     * Each page defines its own load condition.
     */
    protected abstract void waitForPageLoad();

    /**
     * Each page defines its own verification.
     */
    protected abstract void verifyPageOpened();
}
