/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.apiresttest.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.linepack.apiresttest.model.Mensagem;
import org.linepack.apiresttest.model.Usuario;
import org.linepack.apiresttest.rest.UsuarioRest;

/**
 *
 * @author leandro
 */
@Path("usuario")
public class UsuarioService {

    @PersistenceContext(unitName = "derby")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("derby");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    @GET
    @Path("{nome}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UsuarioRest validaLogin(
            @PathParam("nome") String nome,
            @PathParam("password") String password){
        
        Query query = getEntityManager().createQuery(
                 "select u "
                 + "from Usuario u "
                 +"where u.nome = '"+nome+"' "
                 + " and u.senha = '"+password+"'", Usuario.class);
        
        Usuario usuario = new Usuario();
        Mensagem msg =  new Mensagem();
        if (!query.getResultList().isEmpty()){
            for (Object obj : query.getResultList()){
                usuario = (Usuario) obj;
                usuario.setSenha("");
            }
        }else{
            msg.setTexto("Usuário e senha incorretos.");
        }
        
        UsuarioRest retorno = new UsuarioRest();
        retorno.setUsuario(usuario);
        retorno.setMensagem(msg);
        
        return retorno;
    }

    /*
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Usuario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll(String entityName) {
        entityName = "Usuario";
        return super.findAll(entityName);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

     */
}
