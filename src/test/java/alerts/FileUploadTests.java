package alerts;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.FileUploadPage;

import static org.testng.Assert.assertEquals;

public class FileUploadTests extends BaseTests {


    @Test
    public void testFileUpload() {
        FileUploadPage fileUploadPage = homePage.clickFileUpload();
        fileUploadPage.uploadFile(System.getProperty("user.dir") + "/resources/testFile.txt");
        assertEquals(fileUploadPage.getUploadedFiles(), "testFile.txt", "Incorrect uploaded file");
    }

}
