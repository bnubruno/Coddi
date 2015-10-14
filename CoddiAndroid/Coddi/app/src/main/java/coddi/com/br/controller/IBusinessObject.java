package coddi.com.br.controller;

import java.util.List;

/**
 * Created by Bruno on 08/02/2015.
 */
public interface IBusinessObject<E, F> {

    public List<E> buscarTodos(Long idUsuario);

    public List<E> buscarTodos(Long idUsuario, String orderBy);

    public E buscarPorId(F id);

    public void incluir(E objeto) throws Exception;

    public void alterar(E objeto);

    public void excluir(E objeto);

    public void inativar(E objeto);

}
