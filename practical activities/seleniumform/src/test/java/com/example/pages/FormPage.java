package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FormPage {
        private WebDriver driver;

    // Localizadores 
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By userEmail = By.id("userEmail");
    
    private By genderMale = By.id("genderMale"); 
    private By genderFemale = By.id("genderFemale"); 
    private By genderOther = By.id("genderOther"); 

    private By userNumber = By.id("userNumber");

    private By hobbyReading = By.id("hobbyReading");
    private By hobbyMusic = By.id("hobbyMusic");
    private By hobbySports = By.id("hobbySports");

    private By currentAddress = By.id("currentAddress");
    private By stateSelect = By.id("stateSelect");
    private By citySelect = By.id("citySelect");

    private By submitButton = By.id("submitBtn");

    private By modalBodyContent = By.id("confirmationModal");

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String name) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(name);
    } 

    public void setLastName(String lastName) {
        driver.findElement(this.lastName).clear();
        driver.findElement(this.lastName).sendKeys(lastName);
    }

    public void setUserEmail(String email) {
        driver.findElement(userEmail).clear();
        driver.findElement(userEmail).sendKeys(email);
    }

    public void setMobile(String mobile) {
        driver.findElement(userNumber).clear();
        driver.findElement(userNumber).sendKeys(mobile);
    }

    public void setAddress(String address) {
        driver.findElement(currentAddress).clear();
        driver.findElement(currentAddress).sendKeys(address);
    }

    public void selectGenderByClick(String gender) {
        driver.findElement(By.xpath("//Label[text()='" + gender + "']")).click();
    }

    public void selectGender(String gender) {
        switch(gender.toLowerCase()) {
            case "masculino":
                driver.findElement(genderMale).click();
                break;
            case "femenino":
                driver.findElement(genderFemale).click();
                break;
            case "otro":
                driver.findElement(genderOther).click();
                break;
            default:
                System.out.println("Género no válido");
                break; 
        }
    }

    public void selectHobby(String hobby) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", driver.findElement(hobbyReading));
        switch(hobby.toLowerCase()) {
            case "lectura":
                driver.findElement(hobbyReading).click();
                break;
            case "música":
                driver.findElement(hobbyMusic).click();
                break;
            case "deportes":
                driver.findElement(hobbySports).click();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public void selectState(String state) {
        Select stateDropdown = new Select(driver.findElement(stateSelect));
        stateDropdown.selectByVisibleText(state);
    }

    public void selectCity(String city) {
        Select cityDropdown = new Select(driver.findElement(citySelect));
        cityDropdown.selectByVisibleText(city);
    }

    public void submitForm() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(submitButton));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(submitButton).click();
    }

    public String getModalBodyContent() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(modalBodyContent).getText();
    }

}
