package cn.tjucic.selenium;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.Count;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import net.bytebuddy.asm.Advice.This;

@RunWith(Parameterized.class)
public class TestStudent {
	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Info currentInfo = new Info();

	public TestStudent(Info info) {
		this.currentInfo = info;
	}
	
    @Parameterized.Parameters
    public static Collection<Object[]> getData() throws Exception {
		String driverPath = System.getProperty("user.dir") + "\\src\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		baseUrl = "http://121.193.130.195:8800";
		driver.manage().timeouts().implicitlyWait(900, TimeUnit.SECONDS);
		ReadExcel readExcel = new ReadExcel();
		System.out.println(".");
		List<Object[]> list = readExcel.read();
        return list;
    }
    
	@Test
	public void testBaidu() throws Exception {
		driver.get(baseUrl);
		// 输入内容，并确定
		driver.findElement(By.name("id")).clear();
		driver.findElement(By.name("id")).sendKeys(currentInfo.id);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(currentInfo.password);
		driver.findElement(By.id("btn_login")).sendKeys(Keys.ENTER);
		// 测试信息
		assertEquals(currentInfo.id, driver.findElement(By.id("student-id")).getText());
		assertEquals(currentInfo.name, driver.findElement(By.id("student-name")).getText());
		assertEquals(currentInfo.github, driver.findElement(By.id("student-git")).getText());
		// 返回
		driver.findElement(By.id("btn_logout")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("btn_return")).sendKeys(Keys.ENTER);
	}

}
