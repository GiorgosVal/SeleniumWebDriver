package alerts;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.FileUploadPage;

import static org.testng.Assert.assertEquals;

public class FileUploadTests extends BaseTests {


    @Test
    public void testFileUpload() {
        FileUploadPage fileUploadPage = homePage.clickFileUpload();
        fileUploadPage.uploadFile("/home/val/IdeaProjects/test_automation_university/selenium_webdriver/resources/chromedriver");
        assertEquals(fileUploadPage.getUploadedFiles(), "chromedriver", "Incorrect uploaded file");
    }

}
