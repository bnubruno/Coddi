package coddi.com.br.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import coddi.com.br.coddi.R;
import coddi.com.br.model.Lancamento;

/**
 * Created by Bruno on 03/04/2015.
 */
public class ListViewPagamentoAdapter extends BaseAdapter {

    private Context context;
    private List<ItemListPagamento> lista;

    public ListViewPagamentoAdapter(Context context, List<Lancamento> lista) {
        this.context = context;

        atualizaLista(lista);
    }

    public void atualizaLista(List<Lancamento> lancamentos) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (lancamentos == null) {
            lancamentos = new ArrayList<>();
        }

        List<ItemListPagamento> itens = new ArrayList<>();

        String ultimaData = null;
        BigDecimal saldo = BigDecimal.ZERO;
        ItemListPagamento item = null;

        for (Lancamento lancamento : lancamentos) {
            if (ultimaData == null || !ultimaData.equalsIgnoreCase(df.format(lancamento.getData()))) {

                if (item != null) {
                    item.setSaldo(saldo);
                }

                saldo = BigDecimal.ZERO;

                ultimaData = df.format(lancamento.getData());
                item = new ItemListPagamento(ultimaData);
                itens.add(item);
            }
            itens.add(new ItemListPagamento(lancamento));
            saldo = saldo.add(lancamento.getValor());
        }

        if (item != null) {
            item.setSaldo(saldo);
        }

        this.lista = itens;
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

        if (lista.get(position).getTipoItem() == TipoItem.N) {
            convertView = getViewNormal(position);
        } else {
            convertView = getViewSection(position);
        }

        return convertView;
    }

    private View getViewNormal(int position) {

        View convertView;
        Lancamento lancamento = lista.get(position).getLancamento();
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.pagamento_lista_item, null);

        TextView txtNomeConta = (TextView) convertView.findViewById(R.id.txtNomeConta);
        txtNomeConta.setText(lancamento.getDescricao());

        TextView txtForma = (TextView) convertView.findViewById(R.id.txtFormaPagamento);
        txtForma.setText(lancamento.getConta().getString());

        TextView txtValor = (TextView) convertView.findViewById(R.id.txtSaldo);
        txtValor.setText(lancamento.getCategoria().getNome() + " - R$ " + lancamento.getValor().setScale(2).toString());

        return convertView;
    }


    private View getViewSection(int position) {
        View convertView;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.conta_lista_section, null);

        TextView txtNomeContaSection = (TextView) convertView.findViewById(R.id.txtNomeContaSection);
        txtNomeContaSection.setText("Data: " + lista.get(position).getDescricao() + " Total do dia: " + lista.get(position).getSaldo());

        txtNomeContaSection.setClickable(false);
        txtNomeContaSection.setSelected(false);

        return convertView;
    }


    public List<ItemListPagamento> getLista() {
        return lista;
    }

    public class ItemListPagamento {

        private String descricao;
        private Lancamento lancamento;
        private TipoItem tipoItem;
        private BigDecimal saldo = BigDecimal.ZERO;

        public ItemListPagamento(Lancamento lancamento) {
            this.descricao = null;
            this.lancamento = lancamento;
            this.tipoItem = TipoItem.N;
        }

        public ItemListPagamento(String descricao) {
            this.lancamento = null;
            this.tipoItem = TipoItem.S;
            this.descricao = descricao;
        }

        public TipoItem getTipoItem() {
            return tipoItem;
        }

        public Lancamento getLancamento() {
            return lancamento;
        }

        public String getDescricao() {
            return descricao;
        }

        public BigDecimal getSaldo() {
            return saldo;
        }

        public void setSaldo(BigDecimal saldo) {
            this.saldo = saldo;
        }
    }

    private enum TipoItem {
        N, S;
    }
}
