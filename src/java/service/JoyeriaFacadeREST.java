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
import modelo.Joyeria;

/**
 *
 * @author santiago
 */
@Stateless
@Path("modelo.joyeria")
public class JoyeriaFacadeREST extends AbstractFacade<Joyeria> {

    @PersistenceContext(unitName = "Deber_JoyeriaPU")
    private EntityManager em;

    public JoyeriaFacadeREST() {
        super(Joyeria.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Joyeria entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Joyeria entity) {
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
    public Joyeria find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    
    @POST
    @Path("crearJoy")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crearJoy(@FormParam("idJoyeria")int idJoyeria,@FormParam("Nombre")String nombre,@FormParam("Ubicacion")String ubicacion,@FormParam("Direccion")String direccion,@FormParam("Encargado")String encargado,@FormParam("Dim_local")String dimlocal,@FormParam("Ciudad")String ciudad){
    Joyeria ob = new Joyeria (idJoyeria,nombre,ubicacion,direccion,encargado,dimlocal,ciudad);
    super.create(ob);
    return "La Joyeria se registro con Exito";
    
    }
    
    @POST
    @Path("editarJoy")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editarJoy(@FormParam("idJoyeria")int idJoyeria,@FormParam("Nombre")String nombre,@FormParam("Ubicacion")String ubicacion,@FormParam("Direccion")String direccion,@FormParam("Encargado")String encargado,@FormParam("Dim_local")String dimlocal,@FormParam("Ciudad")String ciudad){
    Joyeria ob =  super.find(idJoyeria);
    ob.setNombre(nombre);
    ob.setUbicacion(ubicacion);
    ob.setDireccion(direccion);
    ob.setEncargado(encargado);
    ob.setDimlocal(dimlocal);
    ob.setCiudad(ciudad);

    return "La Joyeria se edito con Exito";
    }

    @POST
    @Path("eliminarJoy")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String eliminarJoy(@FormParam("idJoyeria")int idJoyeria){
        Joyeria ob = super.find(idJoyeria);
        super.remove(ob);
        return "la Joyeria se elimino con Exito";
    }
    
    @POST
    @Path("consultaciudad")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List <Joyeria> consultaedad (@FormParam("ciudad")String Ciudad){
        TypedQuery consulta = getEntityManager().createQuery("SELECT j FROM Joyeria j WHERE j.ciudad = :ciudad", Joyeria.class);
        consulta.setParameter("Ciudad", Ciudad);
        return consulta.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
