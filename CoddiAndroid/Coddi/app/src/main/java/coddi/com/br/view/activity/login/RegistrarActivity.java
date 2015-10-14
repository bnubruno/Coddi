package coddi.com.br.view.activity.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.TipoStatus;
import coddi.com.br.model.Usuario;

public class RegistrarActivity extends Activity {

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtSenha;
    private EditText txtRepetirSenha;
    private Button btnRegistrar;

    private View mBarraProgressoView;
    private View mRegistroFormView;

    private BOPool pool;

    private RegistrarTask registrarTask = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getApplicationContext());

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSobrenome = (EditText) findViewById(R.id.txtSobrenome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtRepetirSenha = (EditText) findViewById(R.id.txtRepetirSenha);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        mBarraProgressoView = findViewById(R.id.registro_barra_progresso);
        mRegistroFormView = findViewById(R.id.registro_form);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNome(txtNome.getText().toString());
                usuario.setSobrenome(txtSobrenome.getText().toString());
                usuario.setEmail(txtEmail.getText().toString());
                usuario.setSenha(txtSenha.getText().toString());
                usuario.setSenhaAux(txtRepetirSenha.getText().toString());
                usuario.setStatus(TipoStatus.ATIVO);
                usuario.setVersion(0);

                try {
                    mostraBarraDeProgresso(true);
                    registrarTask = new RegistrarTask(usuario);
                    registrarTask.execute((Void) null);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void mostraBarraDeProgresso(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegistroFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegistroFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegistroFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mBarraProgressoView.setVisibility(show ? View.VISIBLE : View.GONE);
            mBarraProgressoView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mBarraProgressoView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mBarraProgressoView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegistroFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registrar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class RegistrarTask extends AsyncTask<Void, Void, Boolean> {

        private final Usuario usuario;

        public RegistrarTask(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                pool.getUsuarioBO().integrar(usuario);
            } catch (Exception e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            registrarTask = null;
            mostraBarraDeProgresso(false);
            if (success) {
                Toast.makeText(getApplicationContext(), getString(R.string.registraractivity_msgok_cadastrosucesso), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Ops! Ocorreu um erro.", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            registrarTask = null;
            mostraBarraDeProgresso(false);
        }
    }

}
