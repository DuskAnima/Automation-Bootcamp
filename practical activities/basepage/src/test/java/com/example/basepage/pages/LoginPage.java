package com.example.basepage.pages;

public class LoginPage extends BasePage {
    // Localizadores 
    private String emailField = "//input[@id='email']";
    private String passwordField = "//input[@id='password']";
    private String submitButton = "//button[@type='submit']";
    private String emailFieldByID = "email";
    private String passwordFieldById = "password";
    /* 
     private String emailErrorId = "email-error";
     private String passwordError = "password-error";
     private String emailInputId = "email";
     private String passwordInputId = "password";
     */
    
    public void navigateToLogin() {
        navigateTo("https://testing-qa.netlify.app/pages/login");
    }

    public void enterEmail(String email) {
        writte("xpath", emailField, email);
    }

    public void enterPassword(String password) {
        writte("xpath", passwordField, password);
    }

    public void submitLogin() {
        clickElement("xpath", submitButton);
    }

    public String getEmailValidationMessageBootstrap5() {
        return getInputValidationByIdBootstrap5(emailFieldByID);
    }

    public String getPasswordValidationMessageBootstrap5() {
        return getInputValidationByIdBootstrap5(passwordFieldById);
    }
}
