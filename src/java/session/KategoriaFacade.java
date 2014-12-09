/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Kategoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michał
 */
@Stateless
public class KategoriaFacade extends AbstractFacade<Kategoria> {
    @PersistenceContext(unitName = "ZarzadzanieTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KategoriaFacade() {
        super(Kategoria.class);
    }
    
}
