package migueldev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class testProductos {

    private WebDriver driver;

    public static void main(String[] args) {
        testProductos test = new testProductos();
        test.setupDriver();
        test.login("standard_user", "secret_sauce");
        test.addProductsToCart(4);
        test.viewCart();
        test.closeDriver();
    }

    // Configura el controlador de Chrome
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\elsup\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    // Inicia sesión en Swag Labs
    public void login(String username, String password) {
        driver.get("https://www.saucedemo.com/v1/");

        try {
            Thread.sleep(2000); // Pausa antes de interactuar con el campo de usuario
            WebElement usernameField = driver.findElement(By.xpath("//input[@id='user-name']"));
            usernameField.sendKeys(username);

            Thread.sleep(2000); // Pausa antes de interactuar con el campo de contraseña
            WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
            passwordField.sendKeys(password);

            Thread.sleep(2000); // Pausa antes de hacer clic en el botón de login
            WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
            loginButton.click();

            Thread.sleep(2000); // Pausa para ver la página de productos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Añade productos al carrito
    public void addProductsToCart(int numberOfProducts) {
        List<WebElement> addButtons = driver.findElements(By.xpath("//button[@class='btn_primary btn_inventory' and normalize-space()='ADD TO CART']"));

        for (int i = 0; i < numberOfProducts && i < addButtons.size(); i++) {
            addButtons.get(i).click();
            System.out.println("Producto " + (i + 1) + " añadido al carrito.");

            try {
                Thread.sleep(2000); // Pausa después de añadir cada producto al carrito
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Navega al carrito y visualiza los productos añadidos
    public void viewCart() {
        try {
            Thread.sleep(2000); // Pausa antes de hacer clic en el ícono del carrito
            WebElement cartButton = driver.findElement(By.xpath("(//*[name()='path'][@fill='currentColor'])[1]"));
            cartButton.click();

            Thread.sleep(2000); // Pausa antes de mostrar los productos en el carrito
            List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));
            System.out.println("Productos en el carrito:");
            for (WebElement item : cartItems) {
                System.out.println(item.getText());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Cierra el navegador
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
