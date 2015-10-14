package coddi.com.br.controller;

import java.sql.SQLException;

import coddi.com.br.App.CoddiApplication;

/**
 * Created by Bruno on 14/03/2015.
 */
public class BOPool {

    private static BOPool singleton;

    private CoddiApplication context;
    private CategoriaBO categoriaBO;
    private UsuarioBO usuarioBO;
    private ContaBO contaBO;
    private LancamentoBO lancamentoBO;
    private FilaBO filaBO;

    public BOPool(CoddiApplication context) {
        this.context = context;
    }

    public CategoriaBO getCategoriaBO() {
        if (categoriaBO == null) {
            try {
                categoriaBO = new CategoriaBO(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categoriaBO;
    }

    public UsuarioBO getUsuarioBO() {
        if (usuarioBO == null) {
            try {
                usuarioBO = new UsuarioBO(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarioBO;
    }

    public ContaBO getContaBO() {
        if (contaBO == null) {
            try {
                contaBO = new ContaBO(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contaBO;
    }


    public LancamentoBO getLancamentoBO() {
        if (lancamentoBO == null) {
            try {
                lancamentoBO = new LancamentoBO(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lancamentoBO;
    }

    public FilaBO getFilaBO() {
        if (filaBO == null) {
            try {
                filaBO = new FilaBO(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return filaBO;
    }

    public static BOPool getInstance(CoddiApplication context) {
        if (singleton == null) {
            singleton = new BOPool(context);
        }
        return singleton;
    }


}
