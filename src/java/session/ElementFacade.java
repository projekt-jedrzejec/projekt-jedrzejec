/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Element;
import entity.Pracownik;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import jsf.Util;

/**
 *
 * @author Michał
 */
@Stateless
public class ElementFacade extends AbstractFacade<Element> {
    
    @EJB
    private PracownikFacade pf;

    @PersistenceContext(unitName = "ZarzadzanieTestPU")
    private EntityManager em;
    
    private PracownikFacade getFacede() {
        return pf;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementFacade() {
        super(Element.class);
    }

    public List<Element> wyswietlElementy() {
        HttpSession s = Util.getSession();
        Pracownik p = getFacede().find(s.getAttribute("userid"));
        Query q = em.createQuery("Select e from Element e where e.osobaOdpowiedzialna = :user");
        q.setParameter("user", p);
        return q.getResultList();
    }

}
