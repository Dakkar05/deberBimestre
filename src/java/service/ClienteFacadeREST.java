/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Cliente;

/**
 *
 * @author santiago
 */
@Stateless
@Path("modelo.cliente")
public class ClienteFacadeREST extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "Deber_JoyeriaPU")
    private EntityManager em;

    public ClienteFacadeREST() {
        super(Cliente.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cliente entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cliente entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @POST
    @Path("crearCli")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crearCli(@FormParam("IdCliente")int IdCliente,@FormParam("Nombre")String Nombre,@FormParam("Direccion")String Direccion,@FormParam("Correo")String Correo,@FormParam("Telefono")String Telefono,@FormParam("Ciudad")String Ciudad,@FormParam("Edad")String Edad,@FormParam("joyeriaidJoyeria")int joyeriaidJoyeria){
    Cliente ob = new Cliente (IdCliente,Nombre,Direccion,Correo,Telefono,Ciudad,Edad,joyeriaidJoyeria);
    super.create(ob);
    return "El cliente se registro con Exito";
    
    }
    
    @POST
    @Path("editarcli")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("IdCliente")int IdCliente,@FormParam("Nombre")String Nombre,@FormParam("Direccion")String Direccion,@FormParam("Correo")String Correo,@FormParam("Telefono")String Telefono,@FormParam("Ciudad")String Ciudad,@FormParam("Edad")String Edad,@FormParam("joyeriaidJoyeria")int joyeriaidJoyeria){
    Cliente ob = super.find(IdCliente);
    
    ob.setNombre(Nombre);
    ob.setDireccion(Direccion);
    ob.setCorreo(Correo);
    ob.setTelefono(Telefono);
    ob.setCiudad(Ciudad);
    ob.setEdad(Edad);
    ob.setJoyeriaidJoyeria(joyeriaidJoyeria);
    
    return "El cliente se edito con Exito";
    
    }
    
    @POST
    @Path("eliminarcli")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String eliminar(@FormParam("IdCliente")int IdCliente){
        Cliente ob = super.find(IdCliente);
        super.remove(ob);
        return "El cliente se elimino con Exito";
    }
    
    @POST
    @Path("consultajoy")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List <Cliente> consulta(@FormParam("joyeriaidJoyeria")int joyeriaidJoyeria){
        TypedQuery consulta= getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.joyeriaidJoyeria = :joyeriaidJoyeria",Cliente.class);
        consulta.setParameter("joyeriaidJoyeria", joyeriaidJoyeria);
        return consulta.getResultList();
    }
    
    @POST
    @Path("consultaedad")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List <Cliente> consultaedad (@FormParam("edad")String edad){
        TypedQuery consulta = getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.edad >= :edad", Cliente.class);
        consulta.setParameter("edad", edad);
        return consulta.getResultList();
    }
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
