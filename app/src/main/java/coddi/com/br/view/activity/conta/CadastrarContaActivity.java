package coddi.com.br.view.activity.conta;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import coddi.com.br.Adapter.SpinnerArrayAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.Conta;
import coddi.com.br.model.TipoConta;

public class CadastrarContaActivity extends Activity {

    private TextView txtNomeConta;
    private Spinner comboTipoConta;
    private Button btnCadastrarConta;
    private SpinnerArrayAdapter spinnerAdapter;

    private BOPool pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_conta);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getApplicationContext());

        txtNomeConta = (TextView) findViewById(R.id.txtNomeConta);
        btnCadastrarConta = (Button) findViewById(R.id.btnCadastrarConta);

        spinnerAdapter = new SpinnerArrayAdapter(getApplicationContext(), TipoConta.getListaString());
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_drop_down);

        comboTipoConta = (Spinner) findViewById(R.id.comboTipoConta);
        comboTipoConta.setAdapter(spinnerAdapter);
        comboTipoConta.setSelection(this.spinnerAdapter.getCount());

        btnCadastrarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoContaAux = (String) comboTipoConta.getAdapter().getItem(comboTipoConta.getSelectedItemPosition());
                String nome = txtNomeConta.getText().toString();

                if (nome.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar o nome da conta.", Toast.LENGTH_LONG).show();
                    return;
                }

                TipoConta tipoConta = TipoConta.converte(tipoContaAux);
                if (tipoConta == null) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar o tipo da conta.", Toast.LENGTH_LONG).show();
                    return;
                }

                Conta conta = new Conta();
                conta.setNome(nome);
                conta.setDataCadastro(new Date());
                conta.setTipoConta(tipoConta);

                pool.getContaBO().incluir(conta);

                Toast.makeText(getApplicationContext(), "Registro incluído com sucesso.", Toast.LENGTH_LONG);

                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastrar_conta, menu);
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
}
