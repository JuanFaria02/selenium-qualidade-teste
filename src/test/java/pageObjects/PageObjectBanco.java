package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

public class PageObjectBanco {

    @FindBy(xpath = "/html/body/div[3]/div/div[4]/a")
    private WebElement menuOptionLink;

    @FindBy(xpath = "/html/body/section[2]/div/div/div/h1")
    private WebElement titleBanco;

    @FindBy(xpath = "//*[@id=\"btn-padrao\"]/a")
    private WebElement abrirNovoLink;

    @FindBy(xpath = "//*[@id=\"form_caixa\"]/h2")
    private WebElement tituloCaixa;

    @FindBy(xpath = "//*[@id=\"descricao\"]")
    private WebElement observacaoInput;

    @FindBy(xpath = "//*[@id=\"caixatipo\"]")
    private WebElement tipoInput;

    @FindBy(xpath = "//*[@id=\"agencia\"]")
    private WebElement agenciaInput;

    @FindBy(xpath = "//*[@id=\"conta\"]")
    private WebElement contaInput;

    @FindBy(xpath = "//*[@id=\"valorAbertura\"]")
    private WebElement valorAbertura;

    @FindBy(xpath = "//*[@id=\"form_caixa\"]/a")
    private WebElement buttonLink;

    @FindBy(xpath = "//*[@id=\"secGerenciar\"]/div/div/div[1]/div[1]/h2")
    private WebElement tituloGerenciarBanco;

    @FindBy(xpath = "//*[@id=\"descricaoTab\"]")
    private WebElement descricaoBanco;

    public void preencherValoresInput(String observacao, String tipo, String valorAbertura, String agencia, String conta) {
        this.observacaoInput.sendKeys(observacao);
        this.valorAbertura.sendKeys(valorAbertura);
        Utils.selectOptionByName(tipoInput, tipo);
        this.agenciaInput.sendKeys(agencia);
        this.contaInput.sendKeys(conta);
    }

    public void enviarDados() {
        buttonLink.click();
    }

    public String getDescricaoBanco() {
        return descricaoBanco.getAccessibleName();
    }

    public String getTituloGerenciarBanco() {
        return tituloGerenciarBanco.getText();
    }

    public void acessarTelaInicial() {
        menuOptionLink.click();
    }

    public void acessarTelaAbrirCaixa() {
        abrirNovoLink.click();
    }

    public String getTituloBanco() {
        return titleBanco.getText();
    }

    public String getTituloCaixa() {
        return tituloCaixa.getText();
    }

    public String getObservacaoInput() {
        return observacaoInput.getText();
    }
}
