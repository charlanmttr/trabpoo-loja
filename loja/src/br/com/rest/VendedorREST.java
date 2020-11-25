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

import br.com.dao.VendedorDAO;
import br.com.model.Vendedor;

@Path("/vendedores")
public class VendedorREST {
	VendedorDAO vendedorDAO = null;
	
	public VendedorREST() {
		this.vendedorDAO = new VendedorDAO();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVendedores() throws SQLException {
		try {
			List vendedores = vendedorDAO.findAll();
			
			if(!vendedores.isEmpty()) {
				return Response.ok(vendedores).build();			
			}else {
				return Response.noContent().build();
			}
		}catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVendedor(@PathParam("id") int id) {
		try {
			Vendedor vendedor = vendedorDAO.findById(id);
			
			if(vendedor != null) {
				return Response.ok(vendedor).build();			
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
	public Response addVendedor(Vendedor vendedor) {
		try {
			boolean result = vendedorDAO.inserir(vendedor);
			if(result) {
				return Response.status(Response.Status.CREATED).entity(vendedor).build();
			}else {
				return Response.status(Response.Status.NOT_MODIFIED).entity(vendedor).build();
			}
		}catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizaCliente(Vendedor vendedor) {
		try {
			boolean result = vendedorDAO.atualizar(vendedor);
			if (result) {
				return Response.status(Response.Status.CREATED).entity(vendedor).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).entity(vendedor).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletaProduto(@PathParam("id") int id) {
		try {
			boolean result = vendedorDAO.deletar(vendedorDAO.findById(id));
			if(result) {
				return Response.status(Response.Status.OK).build();
			}else {
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
		}catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
