

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CadastrarPessoa {
    private WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\bruno\\Downloads\\geckodriver.exe");
    }

    @BeforeEach
    public void initializeDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        driver = new FirefoxDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testeSelenium() throws InterruptedException {
        driver.get("http://localhost:8080/");
        login();
        preencherFormularioPessoas();
        preencherFormularioEndereco();
        preencherFormularioContato();
        validarMensagemAlerta();
    }

    public void login() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("user")).sendKeys("gerente");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("btn-login")).click();
    }

    private void preencherFormularioPessoas() throws InterruptedException {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement linkPessoas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Pessoas")));
        
    	
        Actions actions = new Actions(driver);
        actions.moveToElement(linkPessoas).perform();
        
        driver.findElement(By.linkText("Pessoas")).click();
        driver.findElement(By.linkText("Novo")).click();
        driver.findElement(By.id("nome")).sendKeys("Bruno de Oliveira dos Santos");
        driver.findElement(By.id("apelido")).sendKeys("Bruno");
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('cpfcnpj').value='123.456.789-23';");

        driver.findElement(By.id("nascimento")).sendKeys("2002/11/12");
        driver.findElement(By.id("observacao")).sendKeys("Teste do Junit");
    }

    private void preencherFormularioEndereco() throws InterruptedException {
        driver.findElement(By.linkText("Endereço")).click();
        driver.findElement(By.id("cidade")).sendKeys("Niterói");
        driver.findElement(By.id("rua")).sendKeys("Rua A");
        driver.findElement(By.id("bairro")).sendKeys("Bairro B");
        driver.findElement(By.id("numero")).sendKeys("12");
        driver.findElement(By.id("cep")).sendKeys("24110-000");
        driver.findElement(By.id("referencia")).sendKeys("Rua");
    }

    private void preencherFormularioContato() throws InterruptedException {
        driver.findElement(By.linkText("Contato")).click();
        driver.findElement(By.id("fone")).sendKeys("21985725466");
        driver.findElement(By.cssSelector("input[type='submit'][value='Salvar']")).click();
    }

    private void validarMensagemAlerta() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        String mensagemAlerta = driver.switchTo().alert().getText();
        assertEquals("Pessoa salva com sucesso", mensagemAlerta);
        driver.switchTo().alert().accept();
    }
    
    @Test
    public void testarEnvioComCamposEmBranco() throws InterruptedException {
        driver.get("http://localhost:8080/");
        login();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement linkPessoas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Pessoas")));

        Actions actions = new Actions(driver);
        actions.moveToElement(linkPessoas).perform();
        driver.findElement(By.linkText("Pessoas")).click();
        driver.findElement(By.linkText("Novo")).click();


        WebElement campoNome = driver.findElement(By.id("nome"));
        campoNome.click();
        driver.findElement(By.id("apelido")).click();

        WebElement campoCpfCnpj = driver.findElement(By.id("cpfcnpj"));
        campoCpfCnpj.click();
        driver.findElement(By.id("nascimento")).click(); // Sai do campo

        WebElement campoNascimento = driver.findElement(By.id("nascimento"));
        campoNascimento.click();
        driver.findElement(By.id("observacao")).click(); // Sai do campo

        driver.findElement(By.cssSelector("input[type='submit'][value='Salvar']")).click();

        WebElement erroNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome-error")));
        assertEquals("Este campo é requerido.", erroNome.getText());

        WebElement erroCpfCnpj = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cpfcnpj-error")));
        assertEquals("Este campo é requerido.", erroCpfCnpj.getText());

    }
    
    @Test
    public void testarNomeComMenosDeCincoCaracteres() throws InterruptedException {
        driver.get("http://localhost:8080/");
        login();
        
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement linkPessoas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Pessoas")));
    	
        Actions actions = new Actions(driver);
        actions.moveToElement(linkPessoas).perform();
        driver.findElement(By.linkText("Pessoas")).click();
        driver.findElement(By.linkText("Novo")).click();
        driver.findElement(By.id("nome")).sendKeys("Brun");
        driver.findElement(By.id("apelido")).click();

        WebElement erroNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome-error")));
        assertEquals("Por favor, forneça ao menos 5 caracteres.", erroNome.getText());
    }
}
