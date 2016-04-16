package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class MultipleArguments_HtmlUnit {
	
	@Parameter(names = {"-l", "--list"}, description = "List of pairs", required = true, variableArity = true)
	private static List<String> list = new ArrayList<String>();
	
	@Parameter(names = "--help", help = true, hidden = true)
	private static boolean help = false;
	
	public static void main(String[] args) {
		JCommander jCommander = new JCommander(new MultipleArguments_HtmlUnit(), args);
        if (help) {
     	   jCommander.usage();
            System.exit(0);
        }

		WebDriver driver = new HtmlUnitDriver();  
		
		for (int i = 0; i < list.size(); i++) {
			String param[] = list.get(i).split("\\|");
			String test_case_id = "TC-001.0" + (i + 1);
			String url = param[0];
			String expectedTitle = param[1];
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String title_actual = driver.getTitle();

			if (expectedTitle.equals(title_actual)) {
				System.out.println("Test Case ID: \t\t" + test_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + expectedTitle);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "PASSED");
			} else {
				System.out.println("Test Case ID: \t\t" + test_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + expectedTitle);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "FAILED");
			}
			System.out.println();
		}
		driver.quit();
	}
}
