package testcases;

import base.BaseClass;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SettingsHelpPage;
import pages.SettingsPage;

public class SettingappVersionVerification {

    AppiumDriver driver=null;
    SettingsPage settingsPage=null;
    SettingsHelpPage settingsHelpPage=null;

    @BeforeClass
    public void beforeClass(){
        driver=BaseClass.getDriver();
        settingsPage=new SettingsPage(driver);
        settingsHelpPage=new SettingsHelpPage(driver);
    }

    @Test(priority = 1)
    public void verifySettingTipsOption(){
           settingsPage.verifyHelpSubmenu();
    }

    @Test(priority = 2)
    public void settingsServicesVersion(){
        settingsPage.verifyHelpSubmenu();
        settingsPage.clickHelpSubmenu();
        settingsHelpPage.verifyHelpPageHeader();
        settingsHelpPage.verifyMoreOptionsButton();
        settingsHelpPage.clickMoreOptionsButton();
        settingsHelpPage.verifyVersionInfoOption();
        settingsHelpPage.clickVersionInfoOption();
        settingsHelpPage.verifyVersionWindow();
        settingsHelpPage.verifyVersionDetails();

        Assert.assertEquals(settingsHelpPage.getVersionDetails(), "Version 1.1.0.732770030.sr");
    }

}
