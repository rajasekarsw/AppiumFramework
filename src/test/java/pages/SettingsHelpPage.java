package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Utility;

public class SettingsHelpPage {


    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Help\"]")
    public WebElement helpPageHeader;

    @AndroidFindBy(accessibility = "More options")
    public WebElement moreOptions;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Version info\")")
    public WebElement versionInfoOption;

    @AndroidFindBy (xpath = "//android.widget.LinearLayout[@content-desc=\"Version info dialog\"]")
    public WebElement versionWindow;

    @AndroidFindBy(id="com.google.android.gms:id/gh_application_version")
    public WebElement versionInfo;

    public SettingsHelpPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void verifyHelpPageHeader(){
        Utility.verifyElementVisibility(helpPageHeader);
    }
    public void verifyMoreOptionsButton(){
        Utility.verifyElementVisibility(moreOptions);
    }
    public void clickMoreOptionsButton(){
        Utility.verifyElementClickability(moreOptions).click();
    }
    public void verifyVersionInfoOption(){
        Utility.verifyElementVisibility(versionInfoOption);
    }
    public void clickVersionInfoOption(){
        Utility.verifyElementClickability(versionInfoOption).click();
    }
    public void verifyVersionWindow(){
        Utility.verifyElementVisibility(versionWindow);
    }
    public void verifyVersionDetails(){
        Utility.verifyElementVisibility(versionInfo);
    }
    public String getVersionDetails(){
        return versionInfo.getText();
    }
}
