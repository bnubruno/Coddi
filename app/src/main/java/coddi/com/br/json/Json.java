package coddi.com.br.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import coddi.com.br.App.CoddiApplication;

/**
 * Created by Bruno on 02/02/2015.
 */
public class Json<E> {

    public E fromJson(JsonObject produtoJson, Class c) {
        E e = (E) CoddiApplication.getGson().fromJson(produtoJson.toString(), c);
        return e;
    }

    public List<E> fromJson(JsonArray array, Class c) {
        List<E> list = new ArrayList<E>();
        Iterator<JsonElement> iterator = array.iterator();
        while (iterator.hasNext()) {
            JsonElement element = iterator.next();
            list.add(fromJson(element.getAsJsonObject(), c));
        }
        return list;
    }
}
