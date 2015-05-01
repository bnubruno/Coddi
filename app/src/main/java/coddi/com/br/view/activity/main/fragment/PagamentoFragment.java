package coddi.com.br.view.activity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import coddi.com.br.Adapter.ListViewPagamentoAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.Lancamento;
import coddi.com.br.model.TipoOperacao;
import coddi.com.br.view.activity.pagamento.CadastrarPagamentoActivity;

/**
 * Created by Bruno on 28/03/2015.
 */
public class PagamentoFragment extends MainActivityFragment {

    private BOPool pool;
    private ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        setHasOptionsMenu(true);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getActivity().getApplicationContext());

        List<Lancamento> lancamentos = pool.getLancamentoBO().buscarLancamentosPagamento(TipoOperacao.PAGAMENTO);

        rootView = inflater.inflate(R.layout.fragment_pagamentos, container, false);

        lista = (ListView) rootView.findViewById(R.id.listPagamentos);
        ListViewPagamentoAdapter adapter = new ListViewPagamentoAdapter(getActivity().getApplicationContext(), lancamentos);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listPagamentos) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            ListViewPagamentoAdapter adapter = (ListViewPagamentoAdapter) lista.getAdapter();
            menu.setHeaderTitle(adapter.getLista().get(info.position).getDescricao());

            menu.add(Menu.NONE, 0, 0, "Deletar");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        if (menuItemIndex == 0) {
            ListViewPagamentoAdapter adapter = (ListViewPagamentoAdapter) lista.getAdapter();
            Lancamento lancamentoDeletado = adapter.getLista().get(info.position);

            pool.getUsuarioBO().inativar(lancamentoDeletado);
            atualizaLista();
            Toast.makeText(getActivity().getApplicationContext(), "Pronto! Lançamento deletado com sucesso.", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        atualizaLista();
    }

    private void atualizaLista() {
        List<Lancamento> lancamentos = pool.getLancamentoBO().buscarLancamentosPagamento(TipoOperacao.PAGAMENTO);

        ((ListViewPagamentoAdapter) lista.getAdapter()).atualizaLista(lancamentos);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem m2 = menu.add(1, 1, 1, "+Pag");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent j = new Intent(getActivity(), CadastrarPagamentoActivity.class);
                startActivity(j);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
