package serviceRepo;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum DriversConfig {

    CHROME("webdriver.chrome.driver","tools/chromedriver.exe"),
    OPERA("webdriver.opera.driver", "tools/operadriver.exe"),
    MOZILLA("webdriver.gecko.driver", "tools/geckodriver.exe"),
    EDGE("webdriver.ie.driver", "tools/msedgedriver.exe");
//    SAFARI(driverKey);

    private final String driverKey;
    private final String path;

    DriversConfig(String driverKey, String path) {
        this.driverKey = driverKey;
        this.path = path;
    }

    public String getDriverKey() {
        return driverKey;
    }

    public String getPath() {
        return path;
    }

    public static DriversConfig initDriver(String driverName) {
        return switch (driverName) {
            case "Chrome" -> CHROME;
            case "Opera" -> OPERA;
            case "Mozilla" -> MOZILLA;
            case "Edge" -> EDGE;
            default -> throw new IllegalArgumentException(String.valueOf(driverName));
        };
    }

    public static RemoteWebDriver create(String driverName) {
        return switch (driverName) {
            case "Chrome" -> new ChromeDriver();
            case "Opera" -> new OperaDriver();
            case "Mozilla" -> new FirefoxDriver();
            case "Edge" -> new EdgeDriver();
            default -> throw new IllegalArgumentException(driverName);
        };
    }
}
