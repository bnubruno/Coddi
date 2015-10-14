package coddi.com.br.view.activity.pagamento;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import coddi.com.br.Adapter.SpinnerArrayAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.Categoria;
import coddi.com.br.model.Conta;
import coddi.com.br.model.Lancamento;
import coddi.com.br.model.TipoFinanceiro;
import coddi.com.br.model.TipoOperacao;

public class CadastrarPagamentoActivity extends Activity {

    private TextView txtValor;
    private Spinner comboCategoria;
    private Spinner comboFormaPagamento;
    private TextView txtDescricao;
    private Button btnCadastrarPagamento;
    private SpinnerArrayAdapter spinnerAdapterCategoria;
    private SpinnerArrayAdapter spinnerAdapterPagamento;

    private List<Categoria> listaCategorias;
    private List<Conta> listaContas;

    private BOPool pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pagamento);


        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getApplicationContext());

        txtValor = (TextView) findViewById(R.id.txtValor);
        comboCategoria = (Spinner) findViewById(R.id.comboCategoria);
        comboFormaPagamento = (Spinner) findViewById(R.id.comboFormaPagamento);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        btnCadastrarPagamento = (Button) findViewById(R.id.btnCadastrarPagamento);

        listaCategorias = pool.getCategoriaBO().buscarPorTipoFinanceiro(TipoFinanceiro.SAIDA);
        List<String> categorias = pool.getCategoriaBO().buscarPorTipoFinanceiroString(TipoFinanceiro.SAIDA);
        spinnerAdapterCategoria = new SpinnerArrayAdapter(getApplicationContext(), categorias);
        spinnerAdapterCategoria.setDropDownViewResource(R.layout.spinner_drop_down);
        comboCategoria.setAdapter(spinnerAdapterCategoria);
        comboCategoria.setSelection(this.spinnerAdapterCategoria.getCount());

        listaContas = pool.getContaBO().buscarContasParaPagamento();
        List<String> formas = pool.getContaBO().buscarContasParaPagamentoString();
        spinnerAdapterPagamento = new SpinnerArrayAdapter(getApplicationContext(), formas);
        spinnerAdapterPagamento.setDropDownViewResource(R.layout.spinner_drop_down);
        comboFormaPagamento.setAdapter(spinnerAdapterPagamento);
        comboFormaPagamento.setSelection(spinnerAdapterPagamento.getCount());

        btnCadastrarPagamento = (Button) findViewById(R.id.btnCadastrarPagamento);
        btnCadastrarPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valorAux = txtValor.getText().toString();
                if (valorAux.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar o valor do lançamento.", Toast.LENGTH_LONG).show();
                    return;
                }

                BigDecimal valor = new BigDecimal(valorAux);

                int posCategoria = comboCategoria.getSelectedItemPosition();
                if (posCategoria == comboCategoria.getAdapter().getCount()) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar a categoria.", Toast.LENGTH_LONG).show();
                    return;
                }
                Categoria categoria = listaCategorias.get(posCategoria);

                int posConta = comboFormaPagamento.getSelectedItemPosition();
                if (posConta == comboFormaPagamento.getAdapter().getCount()) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar a forma de pagamento.", Toast.LENGTH_LONG).show();
                    return;
                }
                Conta conta = listaContas.get(posConta);

                String descricao = txtDescricao.getText().toString();
                if (descricao.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar a descrição do lançamento.", Toast.LENGTH_LONG).show();
                    return;
                }

                Lancamento lancamento = new Lancamento();
                lancamento.setValor(valor);
                lancamento.setDataCadastro(new Date());
                lancamento.setData(new Date());
                lancamento.setTipoFinanceiro(TipoFinanceiro.SAIDA);
                lancamento.setIdCategoria(categoria.getId());
                lancamento.setIdConta(conta.getId());
                lancamento.setDescricao(descricao);
                lancamento.setTipoOperacao(TipoOperacao.PAGAMENTO);

                try {
                    pool.getLancamentoBO().incluir(lancamento);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar_pagamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
