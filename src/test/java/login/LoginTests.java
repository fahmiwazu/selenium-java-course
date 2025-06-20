package login;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTests extends BaseTests{

    @Test
    public void testSuccessfulLogin(){
        LoginPage loginPage = theInternetHomePage.clickFormAuthentication();

        loginPage.setUsername("tomsmith");

        loginPage.setPassword("SuperSecretPassword!");

        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();

        assertEquals(secureAreaPage.getAlertText(),
                "You logged into a secure area!\n" +
                        "×",
                "Alert text is incorrect");
    }
}
