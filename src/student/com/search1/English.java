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

public class English {
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
	    System.out.println(":)");
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	    //2 �����б����������   pull-down list
		//2.2 List ��ȡ�����б� for �õ������б���Ԫ��
	    List<WebElement> lis=driver.findElements(By.cssSelector("#home-hero-banner > div.banner-search > div > form > div:nth-child(1) > div > div > ul > li"));
	   
	    for(WebElement li:lis){
	    	//System.out.println(li.getAttribute("data-detail"));
	    	
	    	 System.out.println(li.getText());
	    	//2.3 �ж������б����Ƿ��������ؼ���  assert if every child element contains key
	    	 assertTrue(li.getText().contains(key));
	    	
	    	System.out.println("=======================");
	    	
	    }
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    if(lis.isEmpty()==false){
	    	 System.out.println(lis.get(0).getText());
	     //3.1 select one element if pull-down list 
	    	 System.out.println("ѡ�ֵ�һ��Ԫ��");//��Ϊ����Ӣ�ĺ󣬵�������޷���ת�������������б��ĳһ��ٵ������
	    	// ExpectedCondition<WebElement> input= ExpectedConditions.visibilityOfElementLocated("");
	    	lis.get(0).click();
	    	
	    	}
	   
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    System.out.println(":):)");
	    driver.findElement(By.xpath("//*[@id='home-hero-banner']/div[2]/div/form/input")).click();
	    //3.2�����ҳ������  click seach button
	   
	    //driver.findElement(By.className("banner-search")).findElement(By.className("autocomplete__submit-btn")).click();
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
		//3.3 �����������������10 assert results <=10
		assertTrue(i<=10);
  }
  
  
  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] {  "London" },
      new Object[] {  "Mellbourne" },
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
	



