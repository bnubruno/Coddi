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

import coddi.com.br.Adapter.ListViewContaAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.Conta;
import coddi.com.br.view.activity.conta.CadastrarContaActivity;

/**
 * Created by Bruno on 28/03/2015.
 */
public class ContaFragment extends MainActivityFragment {

    private BOPool pool;
    private ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        setHasOptionsMenu(true);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getActivity().getApplicationContext());

        rootView = inflater.inflate(R.layout.fragment_contas, container, false);

        List<Conta> contas = pool.getContaBO().buscarTodosAtivos();

        lista = (ListView) rootView.findViewById(R.id.listContas);
        ListViewContaAdapter adapter = new ListViewContaAdapter(getActivity().getApplicationContext(), contas);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);

        return rootView;
    }

    private void atualizaLista() {
        List<Conta> list = pool.getContaBO().buscarTodosAtivos();
        ((ListViewContaAdapter) lista.getAdapter()).atualizaLista(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listContas) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            ListViewContaAdapter adapter = (ListViewContaAdapter) lista.getAdapter();
            menu.setHeaderTitle(adapter.getLista().get(info.position).getNome());

            menu.add(Menu.NONE, 0, 0, "Deletar");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        if (menuItemIndex == 0) {
            ListViewContaAdapter adapter = (ListViewContaAdapter) lista.getAdapter();
            Conta contaDeletada = adapter.getLista().get(info.position);

            pool.getContaBO().inativar(contaDeletada);
            atualizaLista();
            Toast.makeText(getActivity().getApplicationContext(), "Pronto! Conta \"" + contaDeletada.getNome() + "\" deletada com sucesso.", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizaLista();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem m2 = menu.add(1, 1, 1, "+Conta");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent j = new Intent(getActivity(), CadastrarContaActivity.class);
                startActivity(j);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
