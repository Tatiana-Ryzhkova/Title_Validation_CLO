package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class SingleTitle_Firefox {
	 @Parameter(names = {"-u", "--url"}, description = "Url", required = true)
	 private static String url = "There is no Url";
	 
	 @Parameter(names = {"-t", "--title"}, description = "Expected Title", required = true)
	 private static String expectedTitle = "There is no Title";
	
	 @Parameter(names = "--help", help = true, hidden = true)
	 private static boolean help = false;
	
	 public static void main(String[] args) {
           JCommander jCommander = new JCommander(new SingleTitle_Firefox(), args);
           if (help) {
        	   jCommander.usage();
               System.exit(0);
           }

           WebDriver driver = new FirefoxDriver();
			
			String text_case_id = "TC-001.01";
			
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String title_actual = driver.getTitle();

			if (expectedTitle.equals(title_actual)) {
				System.out.println("Test Case ID: \t\t" + text_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + expectedTitle);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "PASSED");
			} else {
				System.out.println("Test Case ID: \t\t" + text_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + expectedTitle);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "FAILED");
			}
			driver.quit();
		}
	}