package coddi.com.br.view.activity.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;

import coddi.com.br.Adapter.ListViewCategoriaAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.Categoria;
import coddi.com.br.model.TipoFinanceiro;
import coddi.com.br.view.activity.categoria.CadastrarCategoriaActivity;

/**
 * Created by Bruno on 28/03/2015.
 */
public class CategoriaFragment extends MainActivityFragment {

    private BOPool pool;
    private ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        setHasOptionsMenu(true);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getActivity().getApplicationContext());

        List<Categoria> listaEntrada = pool.getCategoriaBO().buscarPorTipoFinanceiro(TipoFinanceiro.ENTRADA);
        List<Categoria> listaSaida = pool.getCategoriaBO().buscarPorTipoFinanceiro(TipoFinanceiro.SAÍDA);

        rootView = inflater.inflate(R.layout.fragment_categorias, container, false);

        lista = (ListView) rootView.findViewById(R.id.listCategorias);
        ListViewCategoriaAdapter adapter = new ListViewCategoriaAdapter(getActivity().getApplicationContext(), listaEntrada, listaSaida);
        lista.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Categoria> listaEntrada = pool.getCategoriaBO().buscarPorTipoFinanceiro(TipoFinanceiro.ENTRADA);
        List<Categoria> listaSaida = pool.getCategoriaBO().buscarPorTipoFinanceiro(TipoFinanceiro.SAÍDA);

        ((ListViewCategoriaAdapter) lista.getAdapter()).atualizaLista(listaEntrada, listaSaida);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem m2 = menu.add(1, 1, 1, "+Cat");
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent j = new Intent(getActivity(), CadastrarCategoriaActivity.class);
                startActivity(j);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
