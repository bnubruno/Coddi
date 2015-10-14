package coddi.com.br.sincronizacao;

/**
 * Created by Bruno on 21/07/2015.
 */
public class AbstractSincronizacao {

    protected String path;
    protected String host;

    public AbstractSincronizacao(String host, String path) {
        this.host = host;
        this.path = path;
    }

    protected String getHost(String... parameters) {
        StringBuilder ahost = new StringBuilder(this.host);
        if (parameters != null && parameters.length > 0) {
            for (String parameter : parameters) {
                ahost.append(parameter);
                ahost.append('/');
            }
        }
        return ahost.toString();
    }
}
