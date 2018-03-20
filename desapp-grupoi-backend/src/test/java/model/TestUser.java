package model;

import model.User;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class TestUser {

    private User newUser;

    @Before
    public void setUp() {
        newUser = new User();

    }

    @Test
    public void testNewSystemUserIsRegisteredWithItsObligatoryProperties() {
        newUser.setCuil("20111111112");
        newUser.setName("carlos perez");
        newUser.setAddress("Viamonte 2506");
        newUser.setEmail("lalopsb@hotmail.com");
        assertThat(newUser.getCuil()).isEqualTo("20111111112");
        assertThat(newUser.getName()).isEqualTo("carlos perez");
        assertThat(newUser.getAddress()).isEqualTo("Viamonte 2506");
        assertThat(newUser.getEmail()).isEqualTo("lalopsb@hotmail.com");
    }

    @Test
    public void testAUserCantBeCreatedWithNameShorterThan4letters(){
        try {
            newUser.setName("nan");
            fail("");
        } catch (Exception e) {
            assertThat(e).hasMessage("Name is too short!");
        }
    }

    @Test
    public void testAUserCantBeCreatedWithNameLongerThan50letters(){
        try {
            newUser.setName("The name and lastname are too long for registration");
            fail("");
        } catch (Exception e) {
            assertThat(e).hasMessage("Name is too long!");
        }
    }

    @Test
    public void testAUserCantBeCreatedWithAnInvalidEmail(){
        try {
            newUser.setEmail("wrongemailadress");
            fail("");
        } catch (Exception e) {
            assertThat(e).hasMessage("Email address is invalid!");
        }
    }
}
