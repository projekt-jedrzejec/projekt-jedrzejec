/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Stan;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michał
 */
@Stateless
public class StanFacade extends AbstractFacade<Stan> {
    @PersistenceContext(unitName = "ZarzadzanieTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StanFacade() {
        super(Stan.class);
    }
    
}
