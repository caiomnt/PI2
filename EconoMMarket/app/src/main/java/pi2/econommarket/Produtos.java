package pi2.econommarket;

public class Produtos {

    private String categoria;
    private Integer disp;
    private String imagem;
    private Double preco;
    private String titulo;
    private String url;
    private String mercado;

    public Produtos() {
    }

    public Produtos(String categoria, Integer disp, String imagem, Double preco, String titulo, String url) {
        this.categoria = categoria;
        this.disp = disp;
        this.imagem = imagem;
        this.preco = preco;
        this.titulo = titulo;
        this.url = url;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getDisp() {
        return disp;
    }

    public void setDisp(Integer disp) {
        this.disp = disp;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMercado() { return mercado; }

    public void setMercado(String mercado) { this.mercado = mercado; }
}