package seleniumAdvance;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.Network;
import org.openqa.selenium.devtools.v135.network.model.ConnectionType;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class NetworkConditions {
    ChromeDriver driver;
    DevTools devTools;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        devTools = driver.getDevTools();
    }

    @Test
    public void doNotEnableFahmiWahyu(){
        driver.get("https://fahmiwazu.github.io");
        System.out.println("Do Not Enable Network: " + driver.getTitle());
    }

    @Test
    public void enableSlowGitHUb(){
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));
        
        devTools.send(Network.emulateNetworkConditions(
                false,                              // offline
                150,                                // latency
                2500,                               // downloadThroughput
                2000,                               // uploadThroughput
                Optional.of(ConnectionType.WIFI), // connectionType
                Optional.empty(),                   // packetLoss
                Optional.empty(),                   // packetQueueLength
                Optional.empty()                    // packetReordering
        ));

        driver.get("https://github.com");
        System.out.println("Enable Slow Network: " + driver.getTitle());
    }
}