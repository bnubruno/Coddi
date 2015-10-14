package coddi.com.br.App;

/**
 * Created by Bruno on 02/02/2015.
 */
public class PreferenciasCoddi {

    private String servidor;
    private String porta;

    public PreferenciasCoddi(String servidor, String porta) {
        this.servidor = servidor;
        this.porta = porta;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }
}
