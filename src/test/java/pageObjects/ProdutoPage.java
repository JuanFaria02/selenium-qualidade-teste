package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProdutoPage {
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    public ProdutoPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acessarPaginaProduto() {
        driver.findElement(By.cssSelector(".sidebar:nth-child(10) .imagem-menu")).click();
        driver.findElement(By.linkText("Produto")).click();
    }

    public void criarNovoProduto(String descricao, String valorVenda, String unidade, String ncm, String cest) {
        driver.findElement(By.linkText("Novo")).click();
        driver.findElement(By.id("descricao")).sendKeys(descricao);
        driver.findElement(By.id("validade")).click();
        driver.findElement(By.cssSelector("tr:nth-child(5) > .day:nth-child(6)")).click();
        WebElement valorVendaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("valorVenda")));
        valorVendaElement.clear();
        js.executeScript("arguments[0].value='" + valorVenda + "'; arguments[0].dispatchEvent(new Event('input'));", valorVendaElement);
        driver.findElement(By.id("unidade")).sendKeys(unidade);
        driver.findElement(By.id("ncm")).sendKeys(ncm);
        driver.findElement(By.id("cest")).sendKeys(cest);
        driver.findElement(By.id("modbc")).click();
        WebElement dropdown = driver.findElement(By.id("modbc"));
        dropdown.findElement(By.xpath("//option[. = 'Preço Tabelado Máx. (valor)']")).click();
        driver.findElement(By.name("enviar")).click();
    }

    public void editarProduto(String novoValorVenda) {
        driver.findElement(By.cssSelector("tr:nth-child(2) .glyphicon")).click();
        WebElement valorVendaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("valorVenda")));
        valorVendaElement.clear();
        js.executeScript("arguments[0].value='" + novoValorVenda + "'; arguments[0].dispatchEvent(new Event('input'));", valorVendaElement);
        driver.findElement(By.id("validade")).click();
        driver.findElement(By.cssSelector("tr:nth-child(5) > .day:nth-child(5)")).click();
        driver.findElement(By.name("enviar")).click();
    }
}
