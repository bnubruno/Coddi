package coddi.com.br.view.activity.abst;

import android.app.Activity;
import android.os.Bundle;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.controller.BOPool;

/**
 * Created by Bruno on 22/07/2015.
 */
public class AbstractActivity extends Activity {

    protected BOPool pool;
    protected CoddiApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pool = BOPool.getInstance((coddi.com.br.App.CoddiApplication) getApplicationContext());
        app = (CoddiApplication) getBaseContext();

    }
}
