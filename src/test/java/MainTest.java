package test.java;

import main.java.APIhandler;
import main.java.Framework.BaseTest;
import main.java.Framework.Utils;
import main.java.GmailHandler;
import main.java.Models.Email;
import main.java.POM.GetNadaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainTest extends BaseTest {

    @Test
    public void main() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd_hh_mm_ss");
        String strDate = dateFormat.format(date);

        GetNadaPage getnada = this.siteNavigator.goToGetnadaPage();

        String user = new StringBuilder().append("test").append(strDate).toString();
        String createdUser = getnada.createNewUser(user).getActiveUserName().replace("@getnada.com", "").trim();
        Assert.assertEquals(user, createdUser);

        String catURL = APIhandler.getRandomCat();
        String dogURL = APIhandler.getRandomDog();
        String foxURL = APIhandler.getRandomFox();

        Assert.assertTrue(Utils.isValidURL(catURL));
        Assert.assertTrue(Utils.isValidURL(dogURL));
        Assert.assertTrue(Utils.isValidURL(foxURL));

        String emailContent = new StringBuilder().append(catURL).append(" ").append(dogURL).append(" ").append(foxURL).toString();
        String emailSubject = "test " + strDate;

        GmailHandler gmailHandler = new GmailHandler();
        boolean isCredentioanlsValid = gmailHandler.setCredantionals(this.settings.getEmail(), this.settings.getPassword());
        Assert.assertTrue(isCredentioanlsValid);

        Email sent_email = gmailHandler.sendEmail(new StringBuilder().append(user).append("@getnada.com").toString(), emailSubject, emailContent);
        Email email_result = getnada.waitForANewEmail(20);

        Assert.assertTrue(sent_email.equals(email_result));

        String catScreenShoot = this.siteNavigator.goToUrl(catURL).takeSreanshot(new StringBuilder().append(strDate).append("cat").toString());
        Assert.assertTrue(Utils.checkIfFileExist(catScreenShoot));
        String dogScreenShoot = this.siteNavigator.goToUrl(dogURL).takeSreanshot(new StringBuilder().append(strDate).append("dog").toString());
        Assert.assertTrue(Utils.checkIfFileExist(dogScreenShoot));
        String foxScreenShoot = this.siteNavigator.goToUrl(foxURL).takeSreanshot(new StringBuilder().append(strDate).append("fox").toString());
        Assert.assertTrue(Utils.checkIfFileExist(foxScreenShoot));

    }

}
