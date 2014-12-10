/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Pracownik;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import jsf.Util;
import jsf.util.JsfUtil;

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
            Pracownik p = (Pracownik) query.getSingleResult();
            return p;
        } catch (Exception ex) {
//            JsfUtil.addErrorMessage(ex.getMessage());
            System.out.println("Error in login() -->" + ex.getMessage());
            return null;
        }

    }

    public String haszuj(String dane) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            byte[] b = dane.getBytes();
            m.update(b, 0, b.length);
            BigInteger hash = new BigInteger(1, m.digest());
            return String.format("%1$032X", hash);
        } catch (NoSuchAlgorithmException ex) {

            return null;
        }
    }

    public boolean sprawdzCzyLoginIstnieje(String login, int... id) {
        Query q = em.createQuery("SELECT p FROM Pracownik p WHERE p.login = :login");
        q.setParameter("login", login);
        Pracownik p = null;
        try {
            p = (Pracownik) q.getSingleResult();
            boolean czyIdTakieSame = false;
            if (id.length > 0) {
                czyIdTakieSame = p.getIdPracownika() == id[0];
            }
            return (p == null || czyIdTakieSame);
        } catch (Exception e) {
            return p == null;
        }

    }

    public boolean sprawdzCzyEmailIstnieje(String email, int... id) {
        Query q = em.createQuery("SELECT p FROM Pracownik p WHERE p.email = :email");
        q.setParameter("email", email);
        Pracownik p = null;
        try {
            p = (Pracownik) q.getSingleResult();
            boolean czyIdTakieSame = false;
            if (id.length > 0) {
                czyIdTakieSame = p.getIdPracownika() == id[0];
            }
            return (p == null || czyIdTakieSame);
        } catch (Exception e) {
            return p == null;
        }
    }

    public Pracownik getPraconikById(int id) {
        Query q = em.createQuery("SELECT p FROM Pracownik p WHERE p.idPracownika = :id");
        q.setParameter("id", id);
        Pracownik p = null;
        try {
            return (Pracownik) q.getSingleResult();
        } catch (Exception ex) {
            return p;
        }
    }

    public Pracownik getZalogowanyPracownik() {
        HttpSession s = Util.getSession();
        Object id = s.getAttribute("userid");
        Pracownik p = find(id);
        return p;
    }

}
