package documts;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import config.ConfigReader;

public class ExtentManager {

	private static ExtentReports extent;

	private ExtentManager() {
	}

	public static synchronized ExtentReports getInstance() {

		if (extent == null) {

			String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator
					+ "AutomationReport.html";

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

			sparkReporter.config().setDocumentTitle("Selenium Automation Report");

			sparkReporter.config().setReportName("E-Commerce Test Automation");

			extent = new ExtentReports();

			extent.attachReporter(sparkReporter);

			extent.setSystemInfo("Framework", "Selenium + TestNG");

			String browser = ConfigReader.getRequiredProperty("browser");

			String headless = ConfigReader.getRequiredProperty("headless");
			extent.setSystemInfo("Browser", browser);

			extent.setSystemInfo("Headless", headless);

			extent.setSystemInfo("Environment", "Test");
		}

		return extent;
	}
}