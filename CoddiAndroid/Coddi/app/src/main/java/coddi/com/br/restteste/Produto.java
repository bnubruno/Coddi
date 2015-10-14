package coddi.com.br.restteste;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import coddi.com.br.App.CoddiApplication;

/**
 * TO para representação de um Produto
 */
public class Produto {

    private Long id;
    private String nome;
    private String imagem;
    private transient String pathImagem;
    private double preco;

    public Produto() {
        // TODO Auto-generated constructor stub
    }

    public Produto(Long id, String nome, String imagem, double preco) {
        super();
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.setPreco(preco);
    }

    public static Produto fromJson(JsonObject produtoJson) {
        Produto produto = CoddiApplication.getGson().fromJson(produtoJson.toString(), Produto.class);
        return produto;
    }

    public static List<Produto> fromJson(JsonArray array) {
        List<Produto> produtos = new ArrayList<Produto>();
        Iterator<JsonElement> iterator = array.iterator();
        while (iterator.hasNext()) {
            JsonElement element = iterator.next();
            produtos.add(fromJson(element.getAsJsonObject()));
        }
        return produtos;
    }

    public String toJSON() {
        return CoddiApplication.getGson().toJson(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getPathImagem() {
        return pathImagem;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }


}
