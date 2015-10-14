package coddi.com.br.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import coddi.com.br.coddi.R;
import coddi.com.br.model.Conta;

/**
 * Created by Bruno on 03/04/2015.
 */
public class ListViewContaAdapter extends BaseAdapter {

    private Context context;
    private List<ItemListConta> lista;

    public ListViewContaAdapter(Context context, List<Conta> lista) {
        this.context = context;

        atualizaLista(lista);
    }

    public void atualizaLista(List<Conta> lista) {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        List<ItemListConta> listaAux = new ArrayList<>();
        for (Conta conta : lista) {
            listaAux.add(new ItemListConta(conta));
        }
        listaAux.add(0, new ItemListConta("Contas"));
        this.lista = listaAux;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemListConta itemListConta = lista.get(position);

        if (itemListConta.getTipoItem() == TipoItem.N) {
            convertView = getViewNormal(position);
        } else {
            convertView = getViewSection(position);
        }

        return convertView;
    }

    private View getViewNormal(int position) {
        View convertView;
        Conta conta = lista.get(position).getConta();
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.conta_lista_item, null);

        TextView txtNomeConta = (TextView) convertView.findViewById(R.id.txtNomeConta);
        txtNomeConta.setText(conta.getNome());

        TextView txtTipo = (TextView) convertView.findViewById(R.id.txtTipo);
        txtTipo.setText(conta.getTipoConta().toString());

        return convertView;
    }

    private View getViewSection(int position) {
        View convertView;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.conta_lista_section, null);

        TextView txtNomeContaSection = (TextView) convertView.findViewById(R.id.txtNomeContaSection);
        txtNomeContaSection.setText(lista.get(position).getDescricao());

        txtNomeContaSection.setClickable(false);
        txtNomeContaSection.setSelected(false);

        return convertView;
    }


    public List<ItemListConta> getLista() {
        return lista;
    }

    public class ItemListConta {

        private String descricao;
        private Conta conta;
        private TipoItem tipoItem;

        public ItemListConta(Conta conta) {
            this.descricao = null;
            this.conta = conta;
            this.tipoItem = TipoItem.N;
        }

        public ItemListConta(String descricao) {
            this.conta = null;
            this.tipoItem = TipoItem.S;
            this.descricao = descricao;
        }

        public TipoItem getTipoItem() {
            return tipoItem;
        }

        public Conta getConta() {
            return conta;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    private enum TipoItem {
        N, S;
    }
}
