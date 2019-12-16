package mobile.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.URL;

public class MobileAndroidTest {
    AppiumDriver driver;
    AndroidElement element;
    WebDriverWait wait;


    @BeforeTest
    public void setUp() throws Exception {
            URL serverUrl = new URL("http://127.0.0.1:4723/wd/hub");
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
            capabilities.setCapability(MobileCapabilityType.APP, "D:\\Speedtest\\speedtest.apk");
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

            driver = new AndroidDriver(serverUrl, capabilities);
            wait = new WebDriverWait(driver, 60);
    }

    public boolean isPresent(){
        try {
            element = (AndroidElement) driver.findElements(By.id("org.zwanoo.android.speedtest:id/txt_test_result_value")).get(4);
            System.out.println("Loss is " + element.getText());
            return true;
        }
        catch (Exception e){
            System.out.println("Loss is -%");
            return false;
        }
    }

    @Test
    public void testMobile() throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("org.zwanoo.android.speedtest:id/go_button")));
        driver.findElement(By.id("org.zwanoo.android.speedtest:id/go_button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@content-desc=\"DOWNLOAD\"]/android.view.View/android.widget.TextView[3]")));
        System.out.println("Donwload Mbps is " + driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"DOWNLOAD\"]/android.view.View/android.widget.TextView[3]")).getText());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@content-desc=\"UPLOAD\"]/android.view.View/android.widget.TextView[3]")));
        System.out.println("Upload Mbps is " + driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"UPLOAD\"]/android.view.View/android.widget.TextView[3]")).getText());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Ping\"]/android.view.View/android.widget.TextView[2]")));
        System.out.println("Ping is " + driver.findElement(By.xpath("//android.view.View[@content-desc=\"Ping\"]/android.view.View/android.widget.TextView[2]")).getText());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Jitter\"]/android.view.View/android.widget.TextView[2]")));
        System.out.println("Jitter is " + driver.findElement(By.xpath("//android.view.View[@content-desc=\"Jitter\"]/android.view.View/android.widget.TextView[2]")).getText());
        isPresent();
    }

    @AfterTest
    public void tearDown(){
        driver.closeApp();
    }
}





