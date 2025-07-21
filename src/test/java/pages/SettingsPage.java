package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Utility;

public class SettingsPage {
    AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Tips & support\"]")
    private WebElement helpSubmenu;

    public SettingsPage(AndroidDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
    }

    public void verifyHelpSubmenu() {
        try {
              helpSubmenu.isDisplayed();
        }
        catch (NoSuchElementException exception) {
            Utility.scrollToElement(helpSubmenu);
        }
    }

    public void clickHelpSubmenu() {
        Utility.verifyElementClickability(helpSubmenu).click();
    }
}
