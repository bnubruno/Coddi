package coddi.com.br.controller;

import java.sql.SQLException;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.CategoriaDAO;

/**
 * Created by Bruno on 02/02/2015.
 */
public class CategoriaBO extends AbstractBO {

    public CategoriaBO(CoddiApplication context) throws SQLException {
        super(context, new CategoriaDAO(context.getBd().getConnectionSource()), "categoria");
    }

}
