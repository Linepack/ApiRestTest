/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.apiresttest.service;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
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

    @PersistenceContext(unitName = "homologa")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("homologa");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UsuarioRest validaLogin(
            @HeaderParam("email") String email,
            @HeaderParam("password") String password,
            @HeaderParam("token") String token) {

        StoredProcedureQuery query = getEntityManager().createStoredProcedureQuery("pkg_unimob.prc_login_cooperado");

        query.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_senha", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_token", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_mensagem", String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_data", Usuario.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_email", email);
        query.setParameter("p_senha", password);
        query.setParameter("p_token", token);

        boolean execute = query.execute();

        List<Object> results = (List<Object>) query.getResultList();
        Usuario usuario = new Usuario();
        Mensagem msg = new Mensagem();

        if (results != null) {
            Object[] each = (Object[]) results.get(0);
            usuario.setId((BigDecimal) each[0]);
            usuario.setNome((String) each[1]);
            usuario.setCaminhoFoto((String) each[2]);
            msg.setTexto("");
        } else {            
            msg.setTexto((String) query.getOutputParameterValue("p_mensagem"));            
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
