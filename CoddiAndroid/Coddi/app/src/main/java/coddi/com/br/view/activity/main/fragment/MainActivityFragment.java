package coddi.com.br.view.activity.main.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bruno on 31/01/2015.
 */
public class MainActivityFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static MainActivityFragment newInstance(int sectionNumber) {

        if (sectionNumber == 1) {
            ContaFragment categoriaFragment = new ContaFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            categoriaFragment.setArguments(args);
            return categoriaFragment;
        } else if (sectionNumber == 2) {
            CategoriaFragment categoriaFragment = new CategoriaFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            categoriaFragment.setArguments(args);
            return categoriaFragment;
        } else {
            MainActivityFragment fragment = new MainActivityFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int teste = this.getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = null;
        setHasOptionsMenu(true);

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
