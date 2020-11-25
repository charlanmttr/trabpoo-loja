package br.com.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dao.ClienteDAO;
import br.com.model.Cliente;

@Path("/clientes")
public class ClienteREST {
	ClienteDAO clienteDAO = null;

	public ClienteREST() {
		this.clienteDAO = new ClienteDAO();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientes() throws SQLException {	
		try {
			List clientes = clienteDAO.findAll();
			
			if (clientes.isEmpty()) {
				return Response.noContent().build();
			}else {
				return Response.ok(clientes).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCliente(@PathParam("id") int id) {
		try {
			Cliente cliente = clienteDAO.findById(id);
			
			if(cliente != null) {
				return Response.ok(cliente).build();			
			}else {
				return Response.noContent().build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(Cliente cliente) {
		try {
			boolean result = clienteDAO.inserir(cliente);
			if (result) {
				return Response.status(Response.Status.CREATED).entity(cliente).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).entity(cliente).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizaCliente(Cliente cliente) {
		try {
			boolean result = clienteDAO.atualizar(cliente);
			if (result) {
				return Response.status(Response.Status.CREATED).entity(cliente).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).entity(cliente).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletaCliente(@PathParam("id") int id) {
		try {
			boolean result = clienteDAO.deletar(clienteDAO.findById(id));
			if (result) {
				return Response.status(Response.Status.OK).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}