package coddi.com.br.controller;

import java.util.List;

import coddi.com.br.App.CoddiApplication;
import coddi.com.br.dao.IDao;
import coddi.com.br.model.AbstractPojo;
import coddi.com.br.model.TipoStatus;

/**
 * Created by Bruno on 02/02/2015.
 */
public class AbstractBO<E extends AbstractPojo, F> implements IBusinessObject<E, F> {

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
    public void incluir(E objeto) throws Exception {
        if (objeto.getId() != null) {
            throw new NullPointerException("ID do pojo deve ser nulo.");
        }
        dao.incluir(objeto);
    }

    @Override
    public void alterar(E objeto) {
        if (objeto.getId() == null) {
            throw new NullPointerException("ID do pojo não deve ser nulo.");
        }

        dao.alterar(objeto);
    }

    @Override
    public void excluir(E objeto) {
        dao.excluir(objeto);
    }

    protected IDao<E, F> getDao() {
        return dao;
    }

    @Override
    public void inativar(E objeto) {
        objeto.setStatus(TipoStatus.INATIVO);

        dao.alterar(objeto);
    }
}
