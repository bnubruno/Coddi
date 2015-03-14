package coddi.com.br.controller;

import android.util.Log;

import com.google.gson.JsonParser;

import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.App.Rest;
import coddi.com.br.json.JsonController;
import coddi.com.br.model.Categoria;

/**
 * Created by Bruno on 02/02/2015.
 */
public class CategoriaController extends AbstractController {

    public CategoriaController(CoddiApplication context) {
        super(context, "categoria");
    }

    public synchronized void buscarTodos() {
        String json = "";
        try {
            System.out.print(getHost(getPath()));
            json = Rest.get(getHost(getPath()));
            if ("".equals(json)) {
                json = "[]";
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        try {
            List<Categoria> produtos = JsonController.categoriaJson.fromJson(parser.parse(json).getAsJsonArray(), Categoria.class);
        } catch (Throwable e) {
            Log.e("teste", "falha ao criar a categoria", e);
        }
    }


}
