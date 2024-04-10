package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    AndrewWebServices andrewWebService;
    RecSys stubRecommender;
    Database fakeDatabase;
    PromoService mockPromoService;

    @Before
    public void setUp() {
        // // You need to use some mock objects here
        stubRecommender = new StubRecommender();
        fakeDatabase = new InMemoryDatabase();
        mockPromoService = mock(PromoService.class);
        andrewWebService = new AndrewWebServices(fakeDatabase, stubRecommender, mockPromoService);
    }

    @Test
    public void testLogIn() {
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?
        String email = "test@example.com";
        andrewWebService.sendPromoEmail(email);
        verify(mockPromoService).mailTo(email);
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like
        // right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        // Assume "Scotty" is a valid user that can log in.
        String email = "test@example.com";
        // Execute the login method, which in this scenario, should not trigger an email
        // being sent.
        andrewWebService.logIn("Scotty", 17214);
        // Verify that the mailTo method was never called on the promoService mock
        verify(mockPromoService, never()).mailTo(email);
    }
}
