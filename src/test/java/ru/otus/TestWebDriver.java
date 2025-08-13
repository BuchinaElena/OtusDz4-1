package ru.otus;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestWebDriver {
        WebDriver driver;
        String trainingUrl = System.getProperty("base.url");
        private  static final Logger logger = LogManager.getLogger(TestWebDriver.class);

        @BeforeEach
        public void setup() {
            logger.info("Установка ChromeWebDriver");
            WebDriverManager.chromedriver().setup();
        }

        @AfterEach
        public void endDriver() {
            if (driver != null) {
                driver.close();
            }
        }

        @Test
        public void checkBoxText() {
            logger.info("Открытие веббраузера в режиме headless");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.get(trainingUrl);//режим headless
            var boxText = driver.findElement(By.id("textInput"));
            boxText.sendKeys("ОТУС");
            String testText = boxText.getAttribute("value");
            System.out.println(testText + " - это введенный текст на пред. шагах");
        }

        @Test
        public void openModalWooden(){
            driver = new ChromeDriver();
            logger.info("Открытие веббраузера в режиме киоска");
            driver.manage().window().fullscreen();
            driver.get(trainingUrl);//режим киоска
            var buttonClick = driver.findElement(By.id("openModalBtn"));
            buttonClick.click();
            var modalWindow = driver.findElement(By.id("myModal")).getText();
            System.out.println(modalWindow + " - модальное окно открылось.");
        }

        @Test
        public void sendNameEmail() {
            driver = new ChromeDriver();
            logger.info("Открытие веббраузера в полноэкранном режиме");
            driver.manage().window().maximize();
            driver.get(trainingUrl);//полноэкранный режим
            var name = driver.findElement(By.id("name"));
            name.sendKeys("фыв");
            var email = driver.findElement(By.id("email"));
            email.sendKeys("asdf@sdfg.rt");
            var buttonSend = driver.findElement(By.xpath("//button[@type='submit']"));
            buttonSend.click();
            var message = driver.findElement(By.id("messageBox")).getText();
            System.out.println(message + " - Текст с формы.");
        }
}
