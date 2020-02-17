package forgotpassword;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.EmailSentPage;
import pages.ForgotPasswordPage;

import static org.testng.Assert.*;

public class ForgotPasswordTests extends BaseTests {

    @Test
    public void testEmailRetrieve() {
        ForgotPasswordPage forgotPasswordPage = homePage.clickForgotPassword();
        forgotPasswordPage.writeEmail();
        EmailSentPage emailSentPage = forgotPasswordPage.clickRetrieveButton();
        assertTrue(emailSentPage.getContentText().contains("Your e-mail's been sent!"));
    }
}
