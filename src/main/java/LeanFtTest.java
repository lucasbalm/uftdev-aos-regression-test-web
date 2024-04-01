import static org.junit.Assert.*;

import com.hp.lft.report.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.web.*;
import com.hp.lft.verifications.*;

import unittesting.*;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRenderedImage;
import java.awt.Robot;
import java.net.URI;
import java.net.URL;

public class LeanFtTest extends UnitTestClassBase {

    private static Browser browser;

    public LeanFtTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);

        BrowserDescription bd = new BrowserDescription();
        BrowserType chrome = BrowserType.CHROME;

        browser = BrowserFactory.launch(chrome);
         try
        {
            browser.clearCache();
        }
        catch (GeneralReplayException e){
            if ( !e.getMessage().equals("Browser not valid anymore") ){
                throw e;
            }
            System.out.println("Browser has become invalid, relaunching");
            browser = BrowserFactory.launch(chrome);
            browser.clearCache();
        }
        
        browser.navigate("http://nimbusserver.aos.com:8000");
//      browser.navigate("http://advantageonlineshopping.com/#/");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // Close the browser - this must occur before the globalTearDown. Otherwise, you will see the following error:
        // "An Internal problem has occurred, please make sure the LeanFT sdk was properly initialized." - Jason H.
        try {
            browser.close();
        }
        catch (GeneralReplayException e){
            if ( !e.getMessage().equals("Browser not valid anymore") ){
                throw e;
            }
            System.out.println("Browser no longer valid, cannot close");
        }

        globalTearDown();

    }

    @Before
    public void setUp() throws Exception {
        browser.clearCache();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTablets() throws GeneralLeanFtException  {
        // Tablets

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("TABLETS").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP ElitePad 1000 G2 Tablet $1,009.00 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("TABLETS ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP Elite x2 1011 G1 Tablet $1,279.00 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("TABLETS ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP Pro Tablet 608 G1 $479.00 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("TABLETS ").build()).click();

        // Go Home
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("dvantage DEMO ").build()).click();
    }

    @Test
    public void testSpeakersLabel() throws GeneralLeanFtException, ReportException {

        // Get the label of the SPEAKERS object
        String label_text = browser.describe(Link.class, new LinkDescription.Builder()
                .cssSelector("div#speakersImg > div > span")
                .tagName("SPAN").build()).getInnerText();

        // Build up a verification for the SPEAKERS text
        VerificationData verificationData = new VerificationData();
        verificationData.name = "Check SPEAKERS Label";
        verificationData.description = "Simply checks if the SPEAKERS label on the home page is equal to 'SPEAKERS'";
        verificationData.operationName = "content equals (string compare)";

        VerificationParameter verificationParameter1 = new VerificationParameter("Text found", label_text);
        VerificationParameter verificationParameter2 = new VerificationParameter("Expected ", "SPEAKERS");
        verificationData.verificationParameters.add(verificationParameter1);
        verificationData.verificationParameters.add(verificationParameter2);
        verificationData.image = browser.getSnapshot();


        // Compare against expected result "SPEAKERS" and write to the test report
        Boolean label_says_SPEAKERS = label_text.contentEquals("SPEAKERS");
        if (label_says_SPEAKERS) {
            Reporter.reportVerification(Status.Passed, verificationData);
        } else {
            Reporter.reportVerification(Status.Failed, verificationData);
        }

        // Report result to JUnit framework
        assertTrue("[AOS Homepage] Expected: SPEAKERS, Actual: " + label_text, label_says_SPEAKERS);
    }

    @Test
    public void testLaptops() throws GeneralLeanFtException  {
        // Laptops
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("LAPTOPS").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP Chromebook 14 G1(ENERGY STAR) $299.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("LAPTOPS ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP Chromebook 14 G1(ES) $1,261.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("LAPTOPS ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP ENVY - 17t Touch Laptop $849.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("LAPTOPS ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP ENVY x360 - 15t Laptop $699.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("LAPTOPS ").build()).click();

        // Go Home
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("dvantage DEMO ").build()).click();
    }

    @Test
    public void testMice() throws GeneralLeanFtException  {

        // Mice
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("MICE").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP USB 3 Button Optical Mouse $9.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("MICE ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP Z3200 Wireless Mouse $29.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("MICE ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP Z3600 Wireless Mouse $15.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("MICE ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP Z3600 Wireless Mouse $15.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("MICE ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP Z4000 Wireless Mouse $9.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("MICE ").build()).click();

        // Go Home
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("dvantage DEMO ").build()).click();
    }

    @Test
    public void testHeadphones() throws GeneralLeanFtException  {

        // Headphones
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("HEADPHONES").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW Beats Studio 2 Over-Ear Matte Black Headphones $179.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("HEADPHONES ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW Bose SoundLink Around-ear Wireless Headphones II $279.95 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("HEADPHONES ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW HP H2310 In-ear Headset $13.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("HEADPHONES ").build()).click();

        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("LI").innerText("SOLD OUT SHOP NOW Logitech USB Headset H390 $39.99 ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("HEADPHONES ").build()).click();

        // Go Home
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("dvantage DEMO ").build()).click();
    }



}
