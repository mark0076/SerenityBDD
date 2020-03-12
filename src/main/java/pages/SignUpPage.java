package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@DefaultUrl("https://www.spotify.com/us/signup/")
public class SignUpPage extends PageObject {




    private By emailField =By.cssSelector("input#register-email");
    private By confirmEmailField= By.cssSelector("input#register-confirm-email");
    private By passwordField = By.cssSelector("input#register-password");
    private By nicknameField =By.cssSelector("input#register-displayname");
    private By monthDropDown =By.cssSelector( "select#register-dob-month");
    private String getMonthDropDownOption = "//select[@id=\"register-dob-month\"]/option[text()='%s']";
    private By dayInput = By.cssSelector("input#register-dob-day");
    private By yearInput = By.cssSelector("input#register-dob-year");
    private String genderRadioButton = ("//li[@id=\"li-gender\"]//label[normalize-space()='%s']");
    private By shareCheckBox = By.cssSelector("input#register-thirdparty");
    private By registerButton = By.cssSelector("a#register-button-email-submit");
    private By errorLabel =By.xpath("//label[@class ='has-error' and string-length(text())>0]");
    private String errorByText ="//label[@class =\"has-error\" and text()=\"%s\"]";

    public SignUpPage typeEmail(String email){
        find(emailField).sendKeys(email);
        return this;
    }
    public SignUpPage typeConfirmEmail (String confirmEmail){
        find(confirmEmailField).sendKeys(confirmEmail);
        return this;
    }

    public SignUpPage typePassword(String password){
        find(passwordField).sendKeys(password);
        return this;
    }
    public SignUpPage typeName(String name){
        find(nicknameField).sendKeys(name);
        return this;
    }
    public SignUpPage setMonth(String month){
        find(monthDropDown).click();
        find(By.xpath(String.format(getMonthDropDownOption,month))).waitUntilVisible().click();
        return this;
    }
    public SignUpPage setDay(String day){
        find(dayInput).sendKeys(day);
        return this;
    }
    public SignUpPage setYear(String year){
        find(yearInput).sendKeys(year);
        return this;
    }
    public  SignUpPage setSex(String value){
        find(By.xpath(String.format(genderRadioButton,value))).click();
        return this;
    }
    public SignUpPage setShare(boolean value){
        WebElement checkbox=find(shareCheckBox);
        if(!checkbox.isSelected()==value)
            checkbox.click();

        WebElement checkBox = find(shareCheckBox);
        if(!checkBox.isSelected()== value) checkBox.click();
        return this;
    }
    public void  clickSignUpButton(){
        find(registerButton).click();
    }

    public List<WebElementFacade> getErrors(){
        return findAll(errorLabel);
    }
    public String getErrorByNumber(int number){
        return getErrors().get(number-1).getText();
    }
    public boolean isErrorVisible(String message){
        return findAll(By.xpath(String.format(errorByText,message))).size()>0
                && findAll(By.xpath(String.format(errorByText,message))).get(0).isDisplayed();
    }


}
