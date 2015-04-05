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
    private List<Conta> lista;

    public ListViewContaAdapter(Context context, List<Conta> lista) {
        this.context = context;

        if (lista == null) {
            lista = new ArrayList<>();
        }
        this.lista = lista;
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

        Conta conta = lista.get(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.conta_lista_item, null);

        TextView txtNomeConta = (TextView) convertView.findViewById(R.id.txtNomeConta);
        txtNomeConta.setText(conta.getNome());

        return convertView;
    }

    public void atualizaLista(List<Conta> lista) {
        if (lista == null) {
            lista = new ArrayList<>();
        }

        this.lista = lista;
        notifyDataSetChanged();
    }

    public List<Conta> getLista() {
        return lista;
    }
}
