package coddi.com.br.view.activity.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import coddi.com.br.Adapter.ListViewResultadoAdapter;
import coddi.com.br.coddi.R;
import coddi.com.br.controller.BOPool;
import coddi.com.br.model.ResultadoMensal;

/**
 * Created by Bruno on 28/03/2015.
 */
public class ResultadoMensalFragment extends MainActivityFragment {

    private BOPool pool;
    private ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        setHasOptionsMenu(true);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getActivity().getApplicationContext());
        List<ResultadoMensal> resultado = pool.getLancamentoBO().buscarResultados();
        rootView = inflater.inflate(R.layout.fragment_resultado, container, false);

        lista = (ListView) rootView.findViewById(R.id.listResultadoMensal);
        ListViewResultadoAdapter adapter = new ListViewResultadoAdapter(getActivity(), resultado);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizaLista();
    }

    private void atualizaLista() {
        List<ResultadoMensal> resultado = pool.getLancamentoBO().buscarResultados();

        ((ListViewResultadoAdapter) lista.getAdapter()).atualizaLista(resultado);
    }


}
