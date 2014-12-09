/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Rola;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michał
 */
@Stateless
public class RolaFacade extends AbstractFacade<Rola> {
    @PersistenceContext(unitName = "ZarzadzanieTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolaFacade() {
        super(Rola.class);
    }
    
}
