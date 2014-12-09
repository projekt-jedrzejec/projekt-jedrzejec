/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Pracownik;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Michał
 */
@Stateless
public class PracownikFacade extends AbstractFacade<Pracownik> {

    @PersistenceContext(unitName = "ZarzadzanieTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PracownikFacade() {
        super(Pracownik.class);
    }

    public Pracownik login(String user, String password) {

        try {
            Query query = em.createQuery("select p from Pracownik p where p.login = :login and p.password = :pass");
            query.setParameter("login", user);
            query.setParameter("pass", password);

            return (Pracownik) query.getSingleResult();
        } catch (Exception ex) {
//            JsfUtil.addErrorMessage(ex.getMessage());
            System.out.println("Error in login() -->" + ex.getMessage());
            return null;
        }

    }

}
