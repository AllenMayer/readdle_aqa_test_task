package driver;

import consts.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class DriverFactory {

    private static WebDriver webDriver;

    protected void initDriver(final String browserName) {

        if (Constants.DriverConfigs.CHROME_NAME.equalsIgnoreCase(browserName)) {
            System.setProperty(Constants.DriverConfigs.CHROME_NAME, chromeDriverPath());
            webDriver=new ChromeDriver();
        } else if (Constants.DriverConfigs.FIREFOX_NAME.equalsIgnoreCase(browserName)) {
            System.setProperty(Constants.DriverConfigs.FIREFOX_NAME, Constants.DriverConfigs.FIREFOX_DRIVER_LOCATION);
            webDriver = new FirefoxDriver();
        } else if (Constants.DriverConfigs.EDGE_NAME.equalsIgnoreCase(browserName)) {
            System.setProperty(Constants.DriverConfigs.EDGE_NAME, Constants.DriverConfigs.EDGE_DRIVER_LOCATION);
            webDriver = new EdgeDriver();
        }
        webDriver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    protected void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver=null;
        }
    }

    private String chromeDriverPath() {
        String OS = System.getProperty("os.name");
        if (OS.contains("Window")) {
            return Constants.DriverConfigs.CHROME_DRIVER_LOCATION + "_win.exe";
        } else if (OS.contains("Mac")) {
            return Constants.DriverConfigs.CHROME_DRIVER_LOCATION + "_mac";
        } else {
            return Constants.DriverConfigs.CHROME_DRIVER_LOCATION + "_linux";
        }
    }
}