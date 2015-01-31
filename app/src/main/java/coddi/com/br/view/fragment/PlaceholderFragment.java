package coddi.com.br.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import coddi.com.br.coddi.R;
import coddi.com.br.view.ativity.main.MainActivity;

/**
 * Created by Bruno on 31/01/2015.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int teste = this.getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = null;
        switch (teste) {
            case 2:
                rootView = inflater.inflate(R.layout.fragment_outro, container, false);
                break;
            default:
                rootView = inflater.inflate(R.layout.fragment_main, container, false);
                TextView section_label = (TextView) rootView.findViewById(R.id.section_label);
                section_label.setText(teste + "");
                break;
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
