import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HDTVFLIX {
	public static void main(String[] args) {
		ChromeDriver driver = null;
		try {
			String url = "https://hdtoday.tv/";
			WebDriverManager.chromedriver().setup();

			String downloadFilepath = "C:\\Users\\NomanAlahi\\Desktop\\chrome_download_test";
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=C:/Users/NomanAlahi/AppData/Local/Google/Chrome/User Data");
			options.addArguments("--start-maximized");
			
			driver = new ChromeDriver(options);
			driver.get(url);
			

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (driver != null) {
				
			}
		}
	}
}
