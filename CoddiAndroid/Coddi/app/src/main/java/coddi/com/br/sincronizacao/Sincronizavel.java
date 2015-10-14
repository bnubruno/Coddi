package coddi.com.br.sincronizacao;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.model.Fila;

/**
 * Created by Bruno on 21/07/2015.
 */
public interface Sincronizavel {
    public void processa(Fila fila) throws Exception;
}
