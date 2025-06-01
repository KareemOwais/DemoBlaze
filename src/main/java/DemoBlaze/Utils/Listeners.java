package DemoBlaze.Utils;

import DemoBlaze.pages.BaseTest;
import Factory.WebDriverFactory;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Listeners implements ITestListener {
    protected static final Logger logger = LoggerFactory.getLogger(Listeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("-----------------  Test STARTED :" + result.getName() + " ------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("[PASS] Test: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("[FAIL] Test: " + result.getName() + " - Error: " + result.getThrowable());

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = now.format(formatter);

        String testName = sanitizeFileName(result.getName());
        String filePath = "screenshots/" + testName + "_" + formattedDateTime + ".png";

        TakesScreenshot scrShot = (TakesScreenshot) WebDriverFactory.getDriver();
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath));
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getMessage());
        }

    }

    private String sanitizeFileName(String name) {
        return name.replaceAll("[\\\\/:*?\"<>|]", "_");
    }}
