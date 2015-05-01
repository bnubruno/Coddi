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

import coddi.com.br.Adapter.ListViewCategoriaAdapter;
import coddi.com.br.Adapter.ListViewSaqueAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.Lancamento;
import coddi.com.br.model.TipoOperacao;
import coddi.com.br.view.activity.transferencia.CadastrarTransferenciaActivity;

/**
 * Created by Bruno on 28/03/2015.
 */
public class TransferenciaFragment extends MainActivityFragment {

    private BOPool pool;
    private ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        setHasOptionsMenu(true);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getActivity().getApplicationContext());

        List<Lancamento> lancamentos = pool.getLancamentoBO().buscarLancamentosPagamento(TipoOperacao.TRANSFERENCIA);

        rootView = inflater.inflate(R.layout.fragment_transferencia, container, false);

        lista = (ListView) rootView.findViewById(R.id.listTransferencia);
        ListViewSaqueAdapter adapter = new ListViewSaqueAdapter(getActivity().getApplicationContext(), lancamentos);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listPagamentos) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            ListViewCategoriaAdapter adapter = (ListViewCategoriaAdapter) lista.getAdapter();
            menu.setHeaderTitle(adapter.getListaUnificada().get(info.position).getCategoria().getNome());

            menu.add(Menu.NONE, 0, 0, "Deletar");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        if (menuItemIndex == 0) {
            ListViewSaqueAdapter adapter = (ListViewSaqueAdapter) lista.getAdapter();
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
        List<Lancamento> listaSaida = pool.getLancamentoBO().buscarLancamentosPagamento(TipoOperacao.TRANSFERENCIA);

        ((ListViewSaqueAdapter) lista.getAdapter()).atualizaLista(listaSaida);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem m2 = menu.add(1, 1, 1, "+Transf");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent j = new Intent(getActivity(), CadastrarTransferenciaActivity.class);
                startActivity(j);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
