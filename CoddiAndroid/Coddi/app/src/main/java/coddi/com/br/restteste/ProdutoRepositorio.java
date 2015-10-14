package coddi.com.br.restteste;

import android.util.Log;

import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.App.Rest;


/**
 * Repositório de Produtos
 * <p/>
 * Classe utilitária, responsável por gerenciar a carga dos dados dos Produtos
 * Representa a camada de abstração entre a aplicação e o servidor
 */
public class ProdutoRepositorio {

    private static List<Produto> produtos = new ArrayList<Produto>();
    private CoddiApplication context;
    private static final String PATH = "produto";

    public ProdutoRepositorio(CoddiApplication context) {
        super();
        this.context = context;
    }

    public synchronized void load() {
        String json = "";
        try {
            json = Rest.get(getHost(PATH));
            if ("".equals(json)) {
                json = "[]";
            }
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        try {
            produtos = Produto.fromJson(parser.parse(json).getAsJsonArray());

        } catch (Throwable e) {
            Log.e("teste", "falha ao criar o produto", e);
        }
    }

    public synchronized List<Produto> buscar() {
        return produtos;
    }

    public void salvar(Produto produto) {
        try {
            if (produto.getId() == null) {
                Rest.post(getHost(PATH), produto.toJSON());
            } else {
                String json = Rest.put(getHost(PATH, produto.getId().toString()), produto.toJSON());
                if (!"".equals(json)) {
                    produto = CoddiApplication.getGson().fromJson(json, Produto.class);
                }
            }

            produtos.remove(produto);
            produtos.add(produto);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void remover(Produto produto) {
        try {
            Rest.delete(getHost(PATH, produto.getId().toString()));
            produtos.remove(produto);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


    private String getHost(String... parameters) {
        StringBuilder host = new StringBuilder(context.getHost());
        if (parameters != null && parameters.length > 0) {
            for (String parameter : parameters) {
                host.append(parameter);
                host.append('/');
            }
        }
        return host.toString();
    }


    public void loadProdutosEmEstoque() {
        String json = "";
        try {
            json = Rest.get(getHost("estoque", "produtos"));
            if ("".equals(json)) {
                json = "[]";
            }
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        try {
            produtos = Produto.fromJson(parser.parse(json).getAsJsonArray());

        } catch (Throwable e) {
            Log.e("teste", "falha ao criar o produto", e);
        }

    }


}
