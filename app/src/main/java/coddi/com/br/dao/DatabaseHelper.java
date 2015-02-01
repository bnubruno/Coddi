package coddi.com.br.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import coddi.com.br.model.Usuario;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "coddi.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Usuario.class);

//			EntidadeDAO entidadeDAO = new EntidadeDAO(getConnectionSource());
//			entidadeDAO.create(new Entidade("Banco do Brasil", TipoEntidade.CC));
//			entidadeDAO.create(new Entidade("HSBC Bank", TipoEntidade.CC));
//			entidadeDAO.create(new Entidade("Cart�o de Cr�dito BB", TipoEntidade.CR));

        } catch (SQLException e) {
            Log.e(TAG, "Erro ao criar banco.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Usuario.class, true);
            onCreate(db, connectionSource);

        } catch (SQLException e) {
            Log.e(TAG, "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

}