package main.java.POM;


import main.java.Framework.Utils;
import main.java.Logger;
import main.java.Models.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GetNadaPage extends BasePage {
    @FindBy(xpath = "//i[@class='pale icon-user']/..")
    private WebElement emailsList;
    @FindBy(xpath = ADD_NEW_USER_BUTTON_XPATH)
    private WebElement addNewEmailButton;
    @FindBy(className = USER_NAME_MODAL_INPUT_CLASS_NAME)
    private WebElement userNameInput;
    @FindBy(xpath = MODAL_WINDOW_ACCEPT_BUTTON_XPATH)
    private WebElement modalWindowAccept;
    @FindBy(className = MESSAGE_ITEM_CLASS_NAME)
    private WebElement message;
    @FindBy(className = ACTIVE_EMAIL_BOX_LINK_CLASS_NAME)
    private WebElement activeEmailUserButton;

    public GetNadaPage createNewUser(String name) {
        Logger.info(new StringBuilder().append("Creating new accout on getnada.com named ").append(name).toString());
        Utils.waitForElementPresent(addNewEmailButton, this.driver, 10);
        this.addNewEmailButton.click();
        Utils.waitForElementPresent(userNameInput, this.driver, 10);
        this.userNameInput.clear();   //does't work properly
        for (int i = 0; i <= 10; i++) {
            this.userNameInput.sendKeys(Keys.BACK_SPACE);
        }
        this.userNameInput.sendKeys(name);
        this.modalWindowAccept.click();
        return this;
    }


    public Email waitForANewEmail(Integer delay) {
        Logger.info("Waiting for a new incoming email");
        String to = this.driver.findElement(By.className(this.ACTIVE_EMAIL_BOX_LINK_CLASS_NAME)).getText();
        Utils.waitUntilElementsCountChanges(By.className("msg_item"), this.driver, delay);
        String from = this.message.findElement(By.className("fn")).getText().split(" ")[0];
        String subject = this.message.findElement(By.className("subject")).getText();
        this.message.click();
        Utils.waitForElementPresent(By.className("open_mail"), this.driver, 10);
        this.driver.switchTo().frame("idIframe");
        String text = this.driver.findElement(By.tagName("body")).getText();
        this.driver.switchTo().parentFrame();
        return new Email(from, to, subject, text);
    }

    public String getActiveUserName() {
        return this.activeEmailUserButton.getText();
    }


    private final String ACTIVE_EMAIL_BOX_LINK_CLASS_NAME = "is-active";
    private final String ADD_NEW_USER_BUTTON_XPATH = "//i[@class='icon-plus']/..";
    private final String USER_NAME_MODAL_INPUT_CLASS_NAME = "user_name";
    private final String MODAL_WINDOW_ACCEPT_BUTTON_XPATH = "//a[@class='button success']";
    private final String MESSAGE_ITEM_CLASS_NAME = "msg_item";

}
