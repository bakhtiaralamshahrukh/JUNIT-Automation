import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebformTestingJunit {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @DisplayName("Fill the web-form and submit")
    @Test
    public void webFormFillUp() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-reject-all-handler")).click();
        driver.findElement(By.id("edit-name")).sendKeys("Sayma Mahjuba");
        driver.findElement(By.id("edit-number")).sendKeys("01721394724");
        driver.findElement(By.xpath("//label[contains(text(),'20-30')]")).click();
        driver.findElement(By.id("edit-date")).click();
        DateFormat todayDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date currentDate = new Date();
        String todayDate = todayDateFormat.format(currentDate);
        driver.findElement(By.id("edit-date")).sendKeys(todayDate, Keys.ENTER);
        driver.findElement(By.id("edit-email")).sendKeys("saymamahjuba@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I have completed my BSc in Computer Science & Engineering from Chittagong University of Engineering and Technology(CUET).  " +
                " I am eager to pursue a career in SQA.");

        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        // uploadElement.sendKeys("D:\\ROAD TO SDET VIDEO\\JAVA PRACTICE\\srk.jpg");
        File document = new File("D:\\ROAD TO SDET VIDEO\\JAVA PRACTICE\\Webform-Automation-Junit\\src\\test\\resources\\srk.jpg");
        uploadElement.sendKeys(document.getAbsolutePath());
        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();
        // Switch to the alert and accept it
        Alert alert = driver.switchTo().alert();
        alert.accept();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.titleIs("Thank you for your submission!"));
//
//
        String actualTitle = driver.getTitle();
        String expectedTitle = "Thank you for your submission!";
        Assertions.assertEquals(expectedTitle, actualTitle);
//        String text = driver.findElement(By.id("uploadedFilePath")).getText();
//        Assertions.assertTrue(text.contains("sayma.png"));


        //driver.findElement(By.id("edit-submit")).click();

        // driver.switchTo().alert().accept();

//    String actual_message = driver.findElement(By.className("page-title")).getText();
//      String expected_message = "Thank you for your submission!";
//       Assertions.assertEquals(actual_message,expected_message);


    }


    @AfterAll
    public void closeDriver() {
        driver.quit();

    }
}
