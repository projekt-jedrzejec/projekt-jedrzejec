/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entity.Pracownik;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import jsf.util.JsfUtil;
import session.PracownikFacade;

/**
 *
 * @author Michał
 */
@ManagedBean(name = "zalogowanyPracownikController")
@SessionScoped
public class ZalogowanyPracownikController implements Serializable {
    
    @EJB
    private session.PracownikFacade ejbFacade;
    private Pracownik pracownik = null;
    private String haslo = "";
    private String stareHaslo = "";
    
    public String getStareHaslo() {
        return stareHaslo;
    }
    
    public void setStareHaslo(String stareHaslo) {
        this.stareHaslo = stareHaslo;
    }
    
    public String getHaslo() {
        return haslo;
    }
    
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    
    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
    
    private PracownikFacade getFacade() {
        return ejbFacade;
    }
    
    public Pracownik getPracownik() {
        HttpSession s = Util.getSession();
        Integer id = (Integer) s.getAttribute("userid");
        if (pracownik == null) {
            pracownik = getFacade().find(id);
        }
        return pracownik;
    }
    
    public void update() {
        Pracownik staryPracownik = getFacade().getPraconikById(pracownik.getIdPracownika());
        pracownik.setPassword(haslo);
        
        if (getFacade().sprawdzCzyEmailIstnieje(pracownik.getEmail(), pracownik.getIdPracownika())) {
            if (!pracownik.getPassword().equals("")) {
                String h = pracownik.getPassword();
                String hash = getFacade().haszuj(stareHaslo);
                if (!hash.equals(staryPracownik.getPassword())) {
                    JsfUtil.addErrorMessage("Podano błędne aktualne hasło");
                    return;
                }
                pracownik.setPassword(getFacade().haszuj(h));
            } else {
                pracownik.setPassword(staryPracownik.getPassword());
            }
            getFacade().edit(pracownik);
            JsfUtil.addSuccessMessage("Zmiana danych zakończyła się sukcesem");
        } else {
            JsfUtil.addErrorMessage("Pracownik z takim emailem już istnieje");
            pracownik.setEmail(staryPracownik.getEmail());
        }
        
    }
    
}
