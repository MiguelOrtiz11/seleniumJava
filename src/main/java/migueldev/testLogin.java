package migueldev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testLogin {

    private WebDriver driver;

    public static void main(String[] args) {
        testLogin test = new testLogin();
        test.setupDriver();
        test.login("standard_user", "secret_sauce");
        test.closeDriver();
    }

    // Método para configurar el controlador de Chrome
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\elsup\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    // Método para iniciar sesión en Swag Labs
    public void login(String username, String password) {
        driver.get("https://www.saucedemo.com/v1/");

        try {
            Thread.sleep(2000); // Pausa antes de interactuar con el campo de usuario
            WebElement usernameField = driver.findElement(By.xpath("//input[@id='user-name']"));
            usernameField.sendKeys(username);

            Thread.sleep(4000); // Pausa antes de interactuar con el campo de contraseña
            WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
            passwordField.sendKeys(password);

            Thread.sleep(4000); // Pausa antes de hacer clic en el botón de login
            WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
            loginButton.click();

            Thread.sleep(4000); // Pausa para ver el resultado
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar el navegador
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
