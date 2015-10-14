package coddi.com.br.sincronizacao;

import coddi.com.br.App.Rest;
import coddi.com.br.model.Fila;

/**
 * Created by Bruno on 21/07/2015.
 */
public class ContaSincronizavel extends AbstractSincronizacao implements Sincronizavel {

    public ContaSincronizavel(String host, String path) {
        super(host, path);
    }

    @Override
    public void processa(Fila fila) throws Exception {
        try {
            String usuarioGravadoJSon = Rest.post(getHost(path), fila.getJson());

        } catch (Throwable e) {
            e.printStackTrace();
            throw new Exception("Ops! Sem conexão.", e);
        }
    }


}
