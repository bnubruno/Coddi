package coddi.com.br.view.activity.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import coddi.com.br.coddi.R;

/**
 * Created by Bruno on 31/01/2015.
 */
public class NavigationDrawerItemAdapter extends BaseAdapter {


    private final LayoutInflater mInflater;
    private Context context;
    private List<NavigationDrawerItem> itens;

    public NavigationDrawerItemAdapter(Context context, List<NavigationDrawerItem> itens) {
        this.context = context;
        this.itens = itens;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        NavigationDrawerItem item = itens.get(position);

        if (item.isSection()) {
            view = buscarViewSection(item);
        } else {
            view = buscarViewItem(item);
        }
        return view;
    }

    private View buscarViewItem(NavigationDrawerItem item) {
        View view = mInflater.inflate(R.layout.drawer_list_item, null);

        ImageView txtImagem = (ImageView) view.findViewById(R.id.icon);
        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(item.getTitulo());
        txtImagem.setImageResource(item.getIcone());

        return view;
    }

    private View buscarViewSection(NavigationDrawerItem item) {

        View view = mInflater.inflate(R.layout.drawer_list_section, null);
        view.setClickable(false);
        view.setSelected(false);

        TextView txtTituloSection = (TextView) view.findViewById(R.id.txtTituloSection);
        txtTituloSection.setText(item.getTitulo());

        return view;
    }
}
