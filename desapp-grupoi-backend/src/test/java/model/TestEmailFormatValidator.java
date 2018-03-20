package model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestEmailFormatValidator {

    private String email;
    private static EmailFormatValidator emailFormatValidator;
    private Boolean expectedValidation;

    public TestEmailFormatValidator(String email, Boolean expectedValidation) {
        this.email = email;
        this.expectedValidation = expectedValidation;
    }

    @BeforeClass
    public static void initialize() {
        emailFormatValidator = new EmailFormatValidator();
    }

    @Parameters
    public static Collection<Object[]> emailPatternsToTest() {
        Object[][] emailPatternsToTest = new Object[][] {
                { "newuser@gmail.com.2u",false },               // number in the second level
                { "newuser@user@carpnd.com", false },           // @ twice in the address
                { "newuser!!!@carpnd.com", false },             // special character '!' in the address
                { "newuser@.com", false },                      // tld cannot start with a dot
                { "newuser.com", false },                       // must contain a @ character and a tld
                { ".newuser.com@carpnd.com", false },           // the address cannot start with a dot
                { "newuser..newuser@carpnd.com", false },       // you cannot have double dots in your address
                { "newuser@gmail.com",true },
                { "new+user@gmail.com", true },
                { "new.user-900@gmail-list.com", true },
                { "newuser123@carpnd.com.gr", true } };

        return Arrays.asList(emailPatternsToTest);
    }

    @Test
    public void testValidatingEmail() {
        Boolean isValidEmailAddress = emailFormatValidator.validate(this.email);
        assertEquals(this.expectedValidation, isValidEmailAddress);
    }

}