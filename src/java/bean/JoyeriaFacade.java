/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Joyeria;

/**
 *
 * @author santiago
 */
@Stateless
public class JoyeriaFacade extends AbstractFacade<Joyeria> {

    @PersistenceContext(unitName = "Deber_JoyeriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JoyeriaFacade() {
        super(Joyeria.class);
    }
    
}
