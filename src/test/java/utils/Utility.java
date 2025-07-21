package utils;

import base.BaseClass;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Utility {
static AppiumDriver driver= BaseClass.getDriver();
    public static void scrollto(){
        Dimension dimension= driver.manage().window().getSize();
        int width=dimension.getWidth();
        int height=dimension.getHeight();

        int startX=width/2;
        int endX=width/2;

        int startY=(int)(height*0.75);
        int endY=(int)(height*0.25);

        PointerInput pointerInput=new PointerInput(PointerInput.Kind.TOUCH,"finger1");

        Sequence sequence=new Sequence(pointerInput,1);

        sequence.addAction(pointerInput.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),startX,startY));
        sequence.addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(pointerInput.createPointerMove(Duration.ofMillis(500),PointerInput.Origin.viewport(),endX,endY));
        sequence.addAction(new Pause(pointerInput,Duration.ofMillis(100)));
        sequence.addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(sequence));
    }

    public static void scrollToElement(WebElement element){
        Dimension dimension= driver.manage().window().getSize();
        int width=dimension.getWidth();
        int height=dimension.getHeight();

        int startX=width/2;
        int endX=width/2;

        int startY=(int)(height*0.75);
        int endY=(int)(height*0.25);

        int scrollCount=0;
        while(scrollCount<5){
            try {
                if (element.isDisplayed())
                    return;
            }catch (NoSuchElementException exception)
            {
            }
            PointerInput pointerInput=new PointerInput(PointerInput.Kind.TOUCH,"finger1");

            Sequence sequence=new Sequence(pointerInput,1);

            sequence.addAction(pointerInput.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),startX,startY));
            sequence.addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(pointerInput.createPointerMove(Duration.ofMillis(500),PointerInput.Origin.viewport(),endX,endY));
            sequence.addAction(new Pause(pointerInput,Duration.ofMillis(100)));
            sequence.addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(List.of(sequence));
            scrollCount++;
        }
        if(!element.isDisplayed())
            throw new NoSuchElementException("element not found");
    }

    public static void verifyElementVisibility(WebElement element){
        new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement verifyElementClickability(WebElement element) {
       return new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String screenshotCapture() {
     return  driver.getScreenshotAs(OutputType.BASE64);
    }
}
