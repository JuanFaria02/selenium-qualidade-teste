import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.LoginPage;
import pageObjects.ProdutoPage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestProduto {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProdutoPage produtoPage;

    @BeforeAll
    public void configuraDriver() {
        System.setProperty("webdriver.gecko.driver", "D:\\geckodriver\\geckodriver.exe");
    }

    @BeforeEach
    public void createDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");
        loginPage = new LoginPage(driver);
        produtoPage = new ProdutoPage(driver);
    }

    @Test
    public void produto() {
        // Realiza login
        driver.get("http://localhost:8080/login");
        loginPage.login("gerente", "123");

        // Cria novo produto
        produtoPage.acessarPaginaProduto();
        produtoPage.criarNovoProduto("Frango", "10,00", "10", "123456789", "123456789");

        // Edita produto
        produtoPage.editarProduto("30,00");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
