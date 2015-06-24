package coddi.com.br.view.activity.transferencia;

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

public class CadastrarTransferenciaActivity extends Activity {
    private TextView txtValor;
    private Spinner comboContaOrigem;
    private Spinner comboContaDestino;
    private TextView txtDescricao;
    private Button btnCadastrarTransferencia;
    private SpinnerArrayAdapter spinnerAdapterContaOrigem;
    private SpinnerArrayAdapter spinnerAdapterContaDestino;

    private List<Conta> listaContasOrigem;
    private List<Conta> listaContasDestino;

    private BOPool pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_transferencia);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getApplicationContext());

        txtValor = (TextView) findViewById(R.id.txtValor);
        comboContaOrigem = (Spinner) findViewById(R.id.comboContaOrigem);
        comboContaDestino = (Spinner) findViewById(R.id.comboContaDestino);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        btnCadastrarTransferencia = (Button) findViewById(R.id.btnCadastrarTransferencia);

        listaContasOrigem = pool.getContaBO().buscarContasTransferencia();
        List<String> contasOrigem = pool.getContaBO().buscarContasTransferenciaOrigemString();
        spinnerAdapterContaOrigem = new SpinnerArrayAdapter(getApplicationContext(), contasOrigem);
        spinnerAdapterContaOrigem.setDropDownViewResource(R.layout.spinner_drop_down);
        comboContaOrigem.setAdapter(spinnerAdapterContaOrigem);
        comboContaOrigem.setSelection(this.spinnerAdapterContaOrigem.getCount());

        listaContasDestino = pool.getContaBO().buscarContasTransferencia();
        List<String> contasDestino = pool.getContaBO().buscarContasTransferenciaDestinoString();
        spinnerAdapterContaDestino = new SpinnerArrayAdapter(getApplicationContext(), contasDestino);
        spinnerAdapterContaDestino.setDropDownViewResource(R.layout.spinner_drop_down);
        comboContaDestino.setAdapter(spinnerAdapterContaDestino);
        comboContaDestino.setSelection(spinnerAdapterContaDestino.getCount());

        btnCadastrarTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valorAux = txtValor.getText().toString();
                if (valorAux.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar o valor da transferência.", Toast.LENGTH_LONG).show();
                    return;
                }

                BigDecimal valor = new BigDecimal(valorAux);

                int posCategoria = comboContaOrigem.getSelectedItemPosition();
                if (posCategoria == comboContaOrigem.getAdapter().getCount()) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar a conta de origem da transferêcia.", Toast.LENGTH_LONG).show();
                    return;
                }
                Conta contaOrigem = listaContasOrigem.get(posCategoria);

                int posConta = comboContaDestino.getSelectedItemPosition();
                if (posConta == comboContaDestino.getAdapter().getCount()) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar a conta de destino da transferência.", Toast.LENGTH_LONG).show();
                    return;
                }
                Conta contaDestino = listaContasDestino.get(posConta);

                if (contaOrigem.getId().equals(contaDestino.getId())) {
                    Toast.makeText(getApplicationContext(), "Ops! As contas de destino e origem precisam ser diferentes.", Toast.LENGTH_LONG).show();
                    return;
                }

                String descricao = txtDescricao.getText().toString();
                if (descricao.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Ops! Precisa informar a descrição da transferência.", Toast.LENGTH_LONG).show();
                    return;
                }

                Categoria categoria = pool.getCategoriaBO().buscarPorTipoMovimentacao();

                Lancamento lancamentoOrigem = new Lancamento();
                lancamentoOrigem.setValor(valor);
                lancamentoOrigem.setDataCadastro(new Date());
                lancamentoOrigem.setData(new Date());
                lancamentoOrigem.setTipoFinanceiro(TipoFinanceiro.SAIDA);
                lancamentoOrigem.setIdCategoria(categoria.getId());
                lancamentoOrigem.setIdConta(contaOrigem.getId());
                lancamentoOrigem.setDescricao(descricao);
                lancamentoOrigem.setTipoOperacao(TipoOperacao.TRANSFERENCIA);
                try {
                    pool.getLancamentoBO().incluir(lancamentoOrigem);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                Lancamento lancamentoDestino = new Lancamento();
                lancamentoDestino.setValor(valor);
                lancamentoDestino.setDataCadastro(new Date());
                lancamentoDestino.setData(new Date());
                lancamentoDestino.setTipoFinanceiro(TipoFinanceiro.ENTRADA);
                lancamentoDestino.setIdCategoria(categoria.getId());
                lancamentoDestino.setIdConta(contaDestino.getId());
                lancamentoDestino.setDescricao(descricao);
                lancamentoDestino.setTipoOperacao(TipoOperacao.TRANSFERENCIA);
                try {
                    pool.getLancamentoBO().incluir(lancamentoDestino);
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
