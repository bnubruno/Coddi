package coddi.com.br.dao;

import java.util.List;

/**
 * Created by Bruno on 07/02/2015.
 */
public interface IDao<E, F> {

    public List<E> buscarTodos();

    public List<E> buscarTodos(Long idUsuario);

    public List<E> buscarTodos(Long idUsuario, String orderBy);

    public List<E> buscarTodos(String orderBy);

    public void incluir(E objeto);

    public void alterar(E objeto);

    public void excluir(E objeto);

    public E buscarPorId(F id);

}
