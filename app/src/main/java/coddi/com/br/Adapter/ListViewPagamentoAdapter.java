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
import coddi.com.br.model.Lancamento;

/**
 * Created by Bruno on 03/04/2015.
 */
public class ListViewPagamentoAdapter extends BaseAdapter {

    private Context context;
    private List<Lancamento> lista;

    public ListViewPagamentoAdapter(Context context, List<Lancamento> lista) {
        this.context = context;

        atualizaLista(lista);
    }

    public void atualizaLista(List<Lancamento> lista) {
        if (lista == null) {
            lista = new ArrayList<>();
        }

        this.lista = lista;
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

        Lancamento lancamento = lista.get(position);

        convertView = getViewNormal(position);

        return convertView;
    }

    private View getViewNormal(int position) {
        View convertView;
        Lancamento lancamento = lista.get(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.pagamento_lista_item, null);

        TextView txtNomeConta = (TextView) convertView.findViewById(R.id.txtNomeConta);
        txtNomeConta.setText(lancamento.getDescricao() + " - " + lancamento.getConta().getString());

        TextView txtValor = (TextView) convertView.findViewById(R.id.txtSaldo);
        txtValor.setText(lancamento.getCategoria().getNome() + " - R$ " + lancamento.getValor().setScale(2).toString());

        return convertView;
    }

    public List<Lancamento> getLista() {
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
