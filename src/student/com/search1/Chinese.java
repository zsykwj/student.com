package student.com.search1;

import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Chinese {
	 private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
 @Test(dataProvider = "dp")
  public void f(String key) {
	 //1.�����ʼ������ҳ�������� Open homepage and search
		driver.get(baseUrl + "/");
	
		driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='0.6'");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	// 1.1 ���������sendkeys Ҫ�������������   get input box and sendkeys
	    WebDriverWait waiting = new WebDriverWait(driver,35,6000);
		ExpectedCondition<WebElement> input= ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='home-hero-banner']/div[2]/div/form/div[1]/input"));
		WebElement inputWeb= waiting.until(input);
	    inputWeb.clear();
	    inputWeb.sendKeys(key);
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 //2 �����б����������   pull-down list
	 //2.2 List ��ȡ�����б� for �õ������б���Ԫ��
	    List<WebElement> lis=driver.findElements(By.cssSelector("html.no-js body#top.home-page.desktop section#home-hero-banner.hero-banner-special-offers div.banner-search div.banner-search__form   form.autocomplete__form--datepicker.autocomplete__form div div.autocomplete.autocomplete--hero-input.autocomplete--presuggest-all   div.autocomplete__results.autocomplete--search-results ul.autocomplete__list li.autocomplete__item"));
	    for(WebElement li:lis){
	    	System.out.println(li.getText());
	 //2.3 �ж������б��Ƿ�ȫ������������   Assert if pull-down list contains input key
	    	assertTrue(li.getText().contains(key));
	    	System.out.println("=======================");
	    }
	    System.out.println(":)"); 
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //3  ��������� �ж� ���������10
	    driver.findElement(By.xpath("//*[@id='home-hero-banner']/div[2]/div/form/input")).click();
	   
	    System.out.println(":):)"); 
	    try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String str= driver.findElement (By.xpath("//span[@class='search-result-list__count']/strong[2]")).getText();
	   
	    //getText�õ���1 - 10��
	    System.out.println(str);
	    String str1=str.substring(3);
	    //substring �õ� �� 10��
		String str2=str1.trim();//��βȥ�ո�
		int i=Integer.parseInt(str2);
		//���������10
		assertTrue(i<=10);
  }
  
  
  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] {  "�׶�" },
      new Object[] {  "ī����" },
    };
  }
  @BeforeMethod
  public void beforeMethod() {
	  // System.setProperty("webdriver.chrome.driver", "F:\\jave_selenium_tools\\chromedriver.exe");
		 System.setProperty("webdriver.firefox.bin", "D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	     driver = new FirefoxDriver();
		 // driver=new ChromeDriver();
	     baseUrl = "https://cn.student.com";
	     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
  }
  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
	



