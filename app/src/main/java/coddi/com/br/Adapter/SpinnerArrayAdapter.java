package coddi.com.br.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import coddi.com.br.coddi.R;

public class SpinnerArrayAdapter extends ArrayAdapter<String> {

    private int hintColor;

    public SpinnerArrayAdapter(Context ctx, String[] objects) {
        super(ctx, R.layout.spinner_view, objects);
        this.hintColor = ctx.getResources().getColor(R.color.list_item_title);

    }

    public SpinnerArrayAdapter(Context ctx, List<String> objects) {
        super(ctx, R.layout.spinner_view, objects);
        this.hintColor = ctx.getResources().getColor(R.color.list_item_title);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (position == getCount()) {
            ((TextView) view.findViewById(R.id.tvSpinner)).setText("");
            ((TextView) view.findViewById(R.id.tvSpinner)).setTextColor(this.hintColor);
            ((TextView) view.findViewById(R.id.tvSpinner)).setText(getItem(getCount())); //Hint
            ((TextView) view.findViewById(R.id.tvSpinner)).setPadding(20, 0, 0, 12);
        }
        return view;
    }

    @Override
    public int getCount() {
        return super.getCount() - 1; // nao mostra o ultimo item. será usado pela hint
    }

    public void setError(View v, String s) {
        TextView name = (TextView) v.findViewById(R.id.tvSpinner);
        name.setError(s);
    }
}
