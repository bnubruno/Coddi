package coddi.com.br.controller;

import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.IDao;

/**
 * Created by Bruno on 02/02/2015.
 */
public class AbstractBO<E, F> implements IBusinessObject<E, F> {

    private IDao<E, F> dao;
    private CoddiApplication context;
    private String path;

    public AbstractBO(CoddiApplication context, IDao dao, String path) {
        this.context = context;
        this.dao = dao;
        this.path = path;
    }

    protected String getHost(String... parameters) {
        StringBuilder host = new StringBuilder(context.getHost());
        if (parameters != null && parameters.length > 0) {
            for (String parameter : parameters) {
                host.append(parameter);
                host.append('/');
            }
        }
        return host.toString();
    }

    public String getPath() {
        return path;
    }

    public CoddiApplication getContext() {
        return context;
    }

    @Override
    public List<E> buscarTodos() {
        return dao.buscarTodos();
    }

    @Override
    public List<E> buscarTodos(String orderBy) {
        return dao.buscarTodos(orderBy);
    }

    @Override
    public E buscarPorId(F id) {
        return dao.buscarPorId(id);
    }

    @Override
    public void incluir(E objeto) {
        dao.incluir(objeto);
    }

    @Override
    public void alterar(E objeto) {
        dao.alterar(objeto);
    }

    @Override
    public void excluir(E objeto) {
        dao.excluir(objeto);
    }

    @Override
    public IDao<E, F> getDao() {
        return dao;
    }
}
