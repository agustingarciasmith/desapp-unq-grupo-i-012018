package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.DesApp;
import ar.edu.unq.desapp.grupoi.model.support.ReservationBuilder;
import ar.edu.unq.desapp.grupoi.services.mail.MailClient;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DesApp.class)
public class MailClientTest {

    @Autowired
    private MailClient mailClient;

    private GreenMail smtpServer;
    private ReservationBuilder builder = new ReservationBuilder();

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(9099, null, "smtp"));
        smtpServer.start();
    }

    @Test
    public void shouldSendMail() throws Exception {
        String recipient = "carpnbgrupoi@gmail.com";
        String message = "Test message content";
        mailClient.prepareAndSend(recipient, message);
        assertReceivedMessageContainsandIsSendFrom(message, recipient, 0);
    }

//    @Test
//    public void whenAReservationIsMadeAnEmailIsSentToOwnerAndClient() throws Exception {
//        Reservation newReservation = builder.newReservation();
//        String owner = newReservation.getOwner().getEmail();
//        String client = newReservation.getClient().getEmail();
//        String message = "Publication N°: 1 has got a reservation on pending state";
//        String content = "<span>" + message + "</span>";
//
//        assertReceivedMessageContainsandIsSendFrom(content, owner, 0);
//        assertReceivedMessageContainsandIsSendFrom(content, client, 1);
//
//    }

    private void assertReceivedMessageContainsandIsSendFrom(String expected, String recipient, int mailNumber) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        String content = (String) receivedMessages[mailNumber].getContent();
        System.out.println(content);
        assertTrue(content.contains(expected));
        assertThat(receivedMessages[mailNumber].getAllRecipients()[0].toString()).isEqualTo(recipient);
    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }

}
