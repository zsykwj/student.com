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
	 //1.界面初始化打开首页进行搜索 Open homepage and search
		driver.get(baseUrl + "/");
		driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='0.6'");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 1.1 加载输入框，sendkeys 要输入的搜索文字   get input box and sendkeys
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
	    //2 下拉列表包含搜索値   pull-down list
		//2.2 List 获取下拉列表 for 得到下拉列表子元素
	    List<WebElement> lis=driver.findElements(By.cssSelector("#home-hero-banner > div.banner-search > div > form > div:nth-child(1) > div > div > ul > li"));
	   
	    for(WebElement li:lis){
	    	//System.out.println(li.getAttribute("data-detail"));
	    	
	    	 System.out.println(li.getText());
	    	//2.3 判断下拉列表中是否包含输入关键字  assert if every child element contains key
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
	    	 System.out.println("选种第一个元素");//因为输入英文后，点击搜索无法跳转，必须点击下拉列表的某一项，再点击搜索
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
	    //3.2点击首页搜索框  click seach button
	   
	    //driver.findElement(By.className("banner-search")).findElement(By.className("autocomplete__submit-btn")).click();
	    System.out.println(":):)"); 
	    try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String str= driver.findElement (By.xpath("//span[@class='search-result-list__count']/strong[2]")).getText();
	    //getText拿到“1 - 10” 
	    System.out.println(str);
	    String str1=str.substring(3);
	    //substring 得到 “ 10”
		String str2=str1.trim();//首尾去空格
		int i=Integer.parseInt(str2);
		//3.3 断言搜索结果不大于10 assert results <=10
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
	



