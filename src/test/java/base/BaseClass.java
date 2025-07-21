package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import java.util.Properties;


public class BaseClass {

    static ThreadLocal<AndroidDriver> driverThreadLocal=new ThreadLocal<>();

    static Properties properties;
    @BeforeSuite
    public void setUp() {

    }

    @AfterSuite
    public void tearDown(){
        driverThreadLocal.get().terminateApp(properties.getProperty("appPackage"));
        if(driverThreadLocal.get()!=null)
            driverThreadLocal.get().quit();
    }

   public static AndroidDriver getDriver() {
        return driverThreadLocal.get() == null ? initDriver() : driverThreadLocal.get();
    }

   static AndroidDriver initDriver()  {
       properties=new Properties();
       try {
           properties.load(new FileInputStream(System.getProperty("user.dir")+"/user.properties"));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       UiAutomator2Options uiAutomator2Options=new UiAutomator2Options();
        uiAutomator2Options.setPlatformName(properties.getProperty("platform"));
        uiAutomator2Options.setAppPackage(properties.getProperty("appPackage"));
        uiAutomator2Options.setAppActivity(properties.getProperty("appActivity"));
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
