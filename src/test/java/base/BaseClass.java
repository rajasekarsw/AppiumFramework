package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;


public class BaseClass {

    static ThreadLocal<AndroidDriver> driverThreadLocal=new ThreadLocal<>();

    @BeforeSuite
    public void setUp()  {

    }

    @AfterSuite
    public void tearDown(){
        driverThreadLocal.get().terminateApp("com.android.settings");
        if(driverThreadLocal.get()!=null)
            driverThreadLocal.get().quit();
    }

   public static AppiumDriver getDriver() {
        return driverThreadLocal.get() == null ? initDriver() : driverThreadLocal.get();
    }

   static AppiumDriver initDriver(){
        UiAutomator2Options uiAutomator2Options=new UiAutomator2Options();
        uiAutomator2Options.setPlatformName("android");
        uiAutomator2Options.setAppPackage("com.android.settings");
        uiAutomator2Options.setAppActivity("com.android.settings.Settings");
       // uiAutomator2Options.noReset();
        driverThreadLocal.set(new AndroidDriver(getUrl(),uiAutomator2Options));
        return driverThreadLocal.get();
    }

   static URL getUrl()  {
        try {
          return  new URI("http://127.0.0.1:4723").toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
