package coddi.com.br.App;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import coddi.com.br.model.Usuario;

public class CoddiApplication extends Application {

    private static final String KEY_SERVIDOR = "tempoAlarme";
    private static final String KEY_PORTA = "tempo";
    public static final String KEY_PREFERENCES = "FDV_PREFERENCES";
    private static final String APP_NAME = "Coddi";
    private static final String DIR_NAME = "rest";
    private static PreferenciasCoddi preferencias;
    private static Gson gson;
    private static Usuario usuarioLogado;

    private DatabaseHelper bd;


    @Override
    public void onCreate() {
        super.onCreate();
        preferencias = buscarPreferencias(this);
    }

    public static PreferenciasCoddi buscarPreferencias(Context context) {
        SharedPreferences pref = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
        PreferenciasCoddi preferencias = new PreferenciasCoddi(pref.getString(KEY_SERVIDOR, "192.168.0.2"), pref.getString(KEY_PORTA, "8080"));
        return preferencias;
    }

    public String getHost() {
        StringBuilder host = new StringBuilder();
        return host.append("http://").append(getServidor()).append(':').append(getPorta()).append('/').append(APP_NAME).append('/').append(DIR_NAME).append('/').toString();
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();


        }
        return gson;
    }

    public void salvar(PreferenciasCoddi preferencias) {
        SharedPreferences pref = this.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
        Editor editor = pref.edit();
        editor.putString(KEY_SERVIDOR, preferencias.getServidor());
        editor.putString(KEY_PORTA, preferencias.getPorta());
        editor.commit();

    }

    public void salvarPreferencias() {
        if (preferencias != null) {
            salvar(preferencias);
        }
    }

    public DatabaseHelper getBd() {
        if (bd == null) {
            bd = new DatabaseHelper(this);
        }
        return bd;
    }

    public String getServidor() {
        return preferencias.getServidor();
    }

    public void setServidor(String servidor) {
        preferencias.setServidor(servidor);
    }

    public String getPorta() {
        return preferencias.getPorta();
    }

    public void setPorta(String porta) {
        preferencias.setPorta(porta);
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        CoddiApplication.usuarioLogado = usuarioLogado;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
