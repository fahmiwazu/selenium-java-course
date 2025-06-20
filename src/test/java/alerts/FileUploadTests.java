package alerts;

import base.BaseTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUploadTests extends BaseTests {

    @Test
    public void testFileUpload() {
        var uploadPage = theInternetHomePage.clickFileUpload();

        // Debug: Print page elements to understand what's available
        uploadPage.debugPageElements();

        // First, just select the file
        uploadPage.selectFile("D:\\Repository\\Automation\\IntelliJ Automation Test\\New folder" +
                "\\selenium-java-waz\\resources\\chromedriver.exe");

        // Then click the upload button
        uploadPage.clickUploadButton();

        // Verify the upload was successful
        String uploadedFiles = uploadPage.getUploadedFiles();
        System.out.println("Uploaded files result: " + uploadedFiles);

        assertTrue(uploadedFiles.contains("chromedriver.exe"),
                "Expected 'chromedriver.exe' to be in uploaded files, but got: " + uploadedFiles);
    }

    @Test
    public void testFileUploadAlternative() {
        var uploadPage = theInternetHomePage.clickFileUpload();

        try {
            uploadPage.uploadFile("D:\\Repository\\Automation\\IntelliJ Automation Test\\New folder" +
                    "\\selenium-java-waz\\resources\\chromedriver.exe");

            String uploadedFiles = uploadPage.getUploadedFiles();
            System.out.println("Uploaded files result: " + uploadedFiles);

            assertTrue(uploadedFiles.contains("chromedriver.exe"),
                    "Expected 'chromedriver.exe' to be in uploaded files, but got: " + uploadedFiles);
        } catch (Exception e) {
            System.out.println("Upload failed with error: " + e.getMessage());
            uploadPage.debugPageElements();
            throw e;
        }
    }
}
