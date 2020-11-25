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

import br.com.dao.ProdutoDAO;
import br.com.model.Produto;

@Path("/produtos")
public class ProdutoREST {
	ProdutoDAO produtoDAO = null;

	public ProdutoREST() {
		this.produtoDAO = new ProdutoDAO();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdutos() throws SQLException {
		try {
			List produtos = produtoDAO.findAll();
			
			if (!produtos.isEmpty()) {
				return Response.ok(produtos).build();
			}else {
				return Response.noContent().build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduto(@PathParam("id") int id) {
		try {
			Produto produto = produtoDAO.findById(id);
			
			if(produto != null) {
				return Response.ok(produto).build();			
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
	public Response addProduto(Produto produto) {
		try {
			boolean result = produtoDAO.inserir(produto);
			if (result) {
				return Response.status(Response.Status.CREATED).entity(produto).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).entity(produto).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizaProduto(Produto produto) {
		try {
			boolean result = produtoDAO.atualizar(produto);
			if (result) {
				return Response.status(Response.Status.CREATED).entity(produto).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).entity(produto).build();
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
			boolean result = produtoDAO.deletar(produtoDAO.findById(id));
			if (result) {
				return Response.status(Response.Status.OK).build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}