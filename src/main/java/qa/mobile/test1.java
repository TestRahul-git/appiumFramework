package qa.mobile;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class test1 {
	public static AppiumDriver driver;
	
  @Test
  public void invalidUsername() {
	  MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
	  MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
	  MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-Login");
	  
	  usernameTxtFld.sendKeys("invaliusername");
	  passwordTxtFld.sendKeys("secret_sauce");
	  loginBtn.click();
	  MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
	  String actualErrTxt= errTxt.getAttribute("text");
	  System.out.println("Actual error txt - " + actualErrTxt);
	  String expectedErrTxt= "Username and password do not match any user in this service.";
	  Assert.assertEquals(actualErrTxt, expectedErrTxt);
  }
  
  @Test
  public void invalidPassword() {
	  MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
	  MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
	  MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-Login");
	  
	  usernameTxtFld.sendKeys("standard_user");
	  passwordTxtFld.sendKeys("invaliPassword");
	  loginBtn.click();
	  MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
	  String actualErrTxt= errTxt.getAttribute("text");
	  System.out.println("Actual error txt - " + actualErrTxt);
	  String expectedErrTxt= "Username and password do not match any user in this service.";
	  Assert.assertEquals(actualErrTxt, expectedErrTxt);
  }
  
  @Test
  public void successfulLogin() {
	  MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
	  MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
	  MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-Login");
	  
	  usernameTxtFld.sendKeys("standard_user");
	  passwordTxtFld.sendKeys("secret_sauce");
	  loginBtn.click();
	  MobileElement productTitleTxt = (MobileElement) driver.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup/android.widget.TextView");
	  String actualProductTitle= productTitleTxt.getAttribute("text");
	  System.out.println("Actual product title - " + actualProductTitle);
	  String expectedProductTitle= "PRODUCTS";
	  Assert.assertEquals(actualProductTitle, expectedProductTitle);
  
  }
  @BeforeClass
  public void beforeClass() throws Exception {
	  DesiredCapabilities dc= new DesiredCapabilities();
		dc.setCapability("platformName","Android");
		dc.setCapability("platformVersion","10.0");
		dc.setCapability("deviceName","Pixel 2");
		dc.setCapability("automationName","UiAutomator2");
		dc.setCapability("appPackage","com.swaglabsmobileapp");
		dc.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
		dc.setCapability("app","/Users/rahulranade/Downloads/Android.SauceLabs.Mobile.Sample.app.2.2.1.apk");
		dc.setCapability("avd","Pixel_2");
		
		URL url =new URL("http://127.0.0.1:4723/wd/hub");
		
		driver=new AndroidDriver(url,dc);
		String sessionId=driver.getSessionId().toString();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		

  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
