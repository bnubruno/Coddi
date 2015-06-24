package coddi.com.br.view.activity.categoria;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import coddi.com.br.Adapter.SpinnerArrayAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.Categoria;
import coddi.com.br.model.TipoFinanceiro;
import coddi.com.br.model.TipoStatus;

public class CadastrarCategoriaActivity extends Activity {

    private TextView txtNomeCategoria;
    private Spinner comboTipoFinanceiro;
    private Button btnCadastrarCategoria;
    private SpinnerArrayAdapter spinnerAdapter;

    private BOPool pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_categoria);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getApplicationContext());

        txtNomeCategoria = (TextView) findViewById(R.id.txtNomeCategoria);

        comboTipoFinanceiro = (Spinner) findViewById(R.id.comboTipoFinanceiro);
        spinnerAdapter = new SpinnerArrayAdapter(getApplicationContext(), TipoFinanceiro.getListaString());
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        comboTipoFinanceiro.setAdapter(spinnerAdapter);
        comboTipoFinanceiro.setSelection(this.spinnerAdapter.getCount());


        btnCadastrarCategoria = (Button) findViewById(R.id.btnCadastrarCategoria);
        btnCadastrarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoFinanceiroAux = (String) comboTipoFinanceiro.getAdapter().getItem(comboTipoFinanceiro.getSelectedItemPosition());
                String nome = txtNomeCategoria.getText().toString();

                if (nome.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar o nome da categoria.", Toast.LENGTH_LONG);
                    return;
                }

                TipoFinanceiro tipoFinanceiro = TipoFinanceiro.converte(tipoFinanceiroAux);
                if (tipoFinanceiro == null) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar o tipo financeiro da categoria.", Toast.LENGTH_LONG);
                    return;
                }

                Categoria categoria = new Categoria();
                categoria.setNome(nome);
                categoria.setTipoFinanceiro(tipoFinanceiro);
                categoria.setStatus(TipoStatus.ATIVO);

                try {
                    pool.getCategoriaBO().incluir(categoria);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Registro incluído com sucesso.", Toast.LENGTH_LONG);

                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastrar_categoria, menu);
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
