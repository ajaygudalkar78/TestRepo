package vTigerCRM.stepdefinations;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTigerCRMPages.HomePage;
import vTigerCRMPages.LoginPage;

public class BaseDefinations {
	public WebDriver driver;
	public SoftAssert sa;
	public Properties prop;
	public Map<String,Map<String,String>> dt;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public static LoginPage lp;
	public static HomePage hp;
	public static String TCName;


	
	public void init()
	{
		dt=GetTestData();
		prop=GetProperties();
		launchApp();
	}
	public void launchApp()
	{
		if(prop.getProperty("Browser").equals("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new FirefoxDriver();
		}
		else if(prop.getProperty("Browser").equals("edge"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new EdgeDriver();
		}
		else if(prop.getProperty("Browser").equals("headless"))
		{
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--headless=chrome");
			driver = new ChromeDriver(options);
		}
		else
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();	
		}
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
		sa=new SoftAssert();
	}
	
	public Properties GetProperties()
	{
		Properties prop=null;
		try
		{
		prop= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/Settings.properties");
		prop.load(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return prop;
	}
	public Map<String, Map<String, String>> GetTestData()
	{
		try
		{

		String excelPath=System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx";
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(excelPath);
		String strQuery="Select * from Sheet1";
		Recordset recordset=connection.executeQuery(strQuery);
		Map<String,Map<String,String>> dt = new HashMap<String,Map<String,String>>();
		List<String> colms = recordset.getFieldNames();
		int counter = 0;
		while(recordset.next()){
		Map<String,String> rowdata = new HashMap<String,String>();	
		System.out.println(colms.get(counter));
		for(String colm:colms)
		{
			String colvalue = recordset.getField(colm);
			rowdata.put(colm, colvalue);
		}
		dt.put(recordset.getField("TCName"), rowdata);
		counter++;
		}
		
		recordset.close();
		connection.close();
		return dt;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	public void createReport() 
	{
		
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/Reports/ExtentReport"+str+".html");//creates blank report
		// Create an object of Extent Reports
		extent = new ExtentReports();  //obj created to attach proprty to report htmlReporter
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Ajay Gudalkar");
		htmlReporter.config().setDocumentTitle("VTiger Test Automation Report"); 
		            // Name of the report
		htmlReporter.config().setReportName("Regression Test Result"); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD); //2 themes std and dark	
	
	}
	
	public void closeApp()
	{
		sa.assertAll();
		driver.quit();
	}

}
