
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.PageObjectBanco;
import pageObjects.PageObjectLogin;

public class TestBanco {
    private WebDriver driver;
    private static final String LOGIN = "gerente";
    private static final String PASSWORD = "123";

    @BeforeAll
    public static void configuraDriver() {
        System.setProperty("webdriver.gecko.driver", "/home/juan/Downloads/geckodriver");
    }

    @BeforeEach
    public void inicializarDriver() {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");
    }

    @BeforeEach
    public void login() {
        PageObjectLogin pageObjectLogin = PageFactory.initElements(driver, PageObjectLogin.class);
        pageObjectLogin.login(LOGIN, PASSWORD);
    }

    @Test
    public void testAcessarTelaBanco() {
        PageObjectBanco pageObjectBanco = PageFactory.initElements(driver, PageObjectBanco.class);
        pageObjectBanco.acessarTelaInicial();
        final String tituloBanco = pageObjectBanco.getTituloBanco();
        Assertions.assertEquals(tituloBanco, "Bancos");
    }

    @Test
    public void testAcessarTelaAbrirCaixa() {
        PageObjectBanco pageObjectBanco = PageFactory.initElements(driver, PageObjectBanco.class);
        pageObjectBanco.acessarTelaInicial();
        pageObjectBanco.acessarTelaAbrirCaixa();
        final String tituloCaixa = pageObjectBanco.getTituloCaixa();
        Assertions.assertEquals(tituloCaixa, "Caixa");
    }

    @Test
    public void testAdicionarValoresMinimosBanco() {
        PageObjectBanco pageObjectBanco = PageFactory.initElements(driver, PageObjectBanco.class);
        pageObjectBanco.acessarTelaInicial();
        pageObjectBanco.acessarTelaAbrirCaixa();
        pageObjectBanco.preencherValoresInput("Teste Caixa", "BANCO", "0.01", "11111", "11111-1");

        pageObjectBanco.enviarDados();
        String tituloGerenciarBanco = pageObjectBanco.getTituloGerenciarBanco();

        Assertions.assertEquals(tituloGerenciarBanco, "Gerenciar Banco");
    }

    @Test
    public void testAdicionarValoresMaximosBanco() {
        PageObjectBanco pageObjectBanco = PageFactory.initElements(driver, PageObjectBanco.class);
        pageObjectBanco.acessarTelaInicial();
        pageObjectBanco.acessarTelaAbrirCaixa();
        pageObjectBanco.preencherValoresInput("Teste Caixa", "BANCO", "9.999.999.999.99", "11111", "11111-1");

        pageObjectBanco.enviarDados();
        String tituloGerenciarBanco = pageObjectBanco.getTituloGerenciarBanco();

        Assertions.assertEquals(tituloGerenciarBanco, "Gerenciar Banco");
    }

    @AfterEach
    public void fecharDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
