package coddi.com.br.controller;

import java.util.List;

import coddi.com.br.dao.IDao;

/**
 * Created by Bruno on 08/02/2015.
 */
public interface IBusinessObject<E, F> {

    public List<E> buscarTodos();

    public List<E> buscarTodos(String orderBy);

    public E buscarPorId(F id);

    public void incluir(E objeto);

    public void alterar(E objeto);

    public void excluir(E objeto);

    public void inativar(E objeto);

}
