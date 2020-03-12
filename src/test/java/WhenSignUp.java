import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.SignUpSteps;

@RunWith(SerenityRunner.class)
public class WhenSignUp {
    @Steps
    SignUpSteps steps;

    @Managed
    WebDriver driver;

    @Test
    public void typeInvalidYear(){
        steps.open_signUpPage();
        steps.set_month("December");
        steps.set_day("20");
        steps.set_year("85");
        steps.set_share(true);
        steps.should_see_error("Please enter a valid year.");
        steps.should_not_see_error("What should we call you?");

    }
    @Test
    public void typeInvalidEmail(){
        steps.open_signUpPage();
        steps.type_name("Mark");
        steps.type_email("test1@mail.test");
        steps.type_confirmation_email("test@mail.test");
        steps.click_signUp();
        steps.should_see_error("Email address doesn't match.");
    }

    @Test
    public void signUpWithEmptyPassword(){
        steps.open_signUpPage();
        steps.type_name("Mark");
        steps.type_email("test@mail.test");
        steps.type_confirmation_email("test@mail.test");
        steps.type_password("");
        steps.click_signUp();
        steps.should_see_error("Enter a password to continue.");
    }
    @Test
    public void typeInvalidValues(){
        steps.open_signUpPage();
        steps.type_name("Mark");
        steps.type_email("testmail");
        steps.type_confirmation_email("test@mail.test");
        steps.select_sex("Female");
        steps.type_password("fdsfsfs");

        steps.set_share(true);
        steps.click_signUp();
        steps.should_see_errors_count(8);
        steps.should_see_error_by_number(2,"Email address doesn't match.");
    }
}
