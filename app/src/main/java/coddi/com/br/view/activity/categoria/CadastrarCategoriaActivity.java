package coddi.com.br.view.activity.categoria;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import coddi.com.br.Adapter.SpinnerArrayAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.model.TipoFinanceiro;

public class CadastrarCategoriaActivity extends ActionBarActivity {

    private TextView txtNomeCategoria;
    private Spinner comboTipoFinanceiro;
    private Button btnCadastrarCategoria;
    private SpinnerArrayAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_categoria);
        setTitle(R.string.cadastrar_caterogias_titulo);

        txtNomeCategoria = (TextView) findViewById(R.id.txtNomeCategoria);

        List<String> lista = new ArrayList<>();
        lista.add("Receber");
        lista.add("Pagar");
        lista.add("Tipo:");

        comboTipoFinanceiro = (Spinner) findViewById(R.id.comboTipoFinanceiro);
        spinnerAdapter = new SpinnerArrayAdapter(getApplicationContext(), lista);
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
                    txtNomeCategoria.setError("Nome é obrigatório.");
                }

                TipoFinanceiro tipoFinanceiro = TipoFinanceiro.converte(tipoFinanceiroAux);
                if (tipoFinanceiro == null) {
                    spinnerAdapter.setError(comboTipoFinanceiro.getSelectedView(), "Informe o tipo.");
                }

                Toast.makeText(getApplicationContext(), "Tipo financeiro: " + tipoFinanceiro + ". Nome: " + nome + ".", Toast.LENGTH_SHORT).show();
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
