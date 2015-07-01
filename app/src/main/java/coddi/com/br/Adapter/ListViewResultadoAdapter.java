package coddi.com.br.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import coddi.com.br.coddi.R;
import coddi.com.br.model.ResultadoMensal;

/**
 * Created by Bruno on 27/06/2015.
 */
public class ListViewResultadoAdapter extends BaseAdapter {

    private Context context;
    private List<ResultadoMensal> lista;

    public ListViewResultadoAdapter(Context context, List<ResultadoMensal> lista) {
        this.lista = lista;
        this.context = context;
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
        ResultadoMensal resultado = lista.get(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.resultado_mensal_item, null);

        TextView txtMesReferencia = (TextView) convertView.findViewById(R.id.tvMesReferencia);
        TextView txtReceitas = (TextView) convertView.findViewById(R.id.tvReceitas);
        TextView txtDespesas = (TextView) convertView.findViewById(R.id.tvDespesas);
        TextView txtSaldoMes = (TextView) convertView.findViewById(R.id.tvSaldoMes);

        txtMesReferencia.setText(resultado.getData());
        txtReceitas.setText("R$ " + resultado.getReceitas().toString());
        txtReceitas.setTextColor(Color.GREEN);

        txtDespesas.setText("R$ " + resultado.getDespesas().toString());
        txtDespesas.setTextColor(Color.RED);
        txtSaldoMes.setText("R$ " + resultado.getSaldo().toString());

        if (resultado.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
            txtSaldoMes.setTextColor(Color.GREEN);
        } else {
            txtSaldoMes.setTextColor(Color.RED);
        }

        return convertView;
    }

    public void atualizaLista(List<ResultadoMensal> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

}
