package org.Coddi.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.Coddi.dao.UsuarioDao;
import org.Coddi.model.Usuario;

@Stateless
@Path("/usuarios")
public class UsuarioEndpoint {

	@PersistenceContext(unitName = "Coddi-persistence-unit")
	private EntityManager em;

	private UsuarioDao usuarioDAO;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response create(Usuario entity) {
		getUsuarioDAO().create(entity);
		return Response.ok(entity).status(Status.CREATED).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Usuario entity = getUsuarioDAO().findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		getUsuarioDAO().deleteById(id);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		Usuario entity = getUsuarioDAO().findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<Usuario> listAll(@QueryParam("email") String email, @QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
		return getUsuarioDAO().listAll(startPosition, maxResult);
	}
	
	@GET
	@Path("/email")
	@Produces("application/json")
	public Usuario buscarPorEmail(@QueryParam("email") String email) {
		
		List<Usuario> usuarios = getUsuarioDAO().listAll(null, null);
		
		for (Usuario usuario : usuarios) {
			if(usuario.getEmail().equalsIgnoreCase(email)){
				return usuario;
			}
		}
		
		
		return null;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, Usuario entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (getUsuarioDAO().findById(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			entity = getUsuarioDAO().update(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}

	public UsuarioDao getUsuarioDAO() {
		if (usuarioDAO == null) {
			usuarioDAO = new UsuarioDao(em);
		}
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDao usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}
