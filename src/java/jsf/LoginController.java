/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entity.Pracownik;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import jsf.util.JsfUtil;
import session.PracownikFacade;

@ManagedBean(name = "loginController")
@SessionScoped
/**
 *
 * @author Michał
 */
public class LoginController implements Serializable {

    @EJB
    private PracownikFacade pracownikFacade;

    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;
    private boolean isAdmin;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public boolean getIsAdmin() {
        HttpSession session = Util.getSession();
        isAdmin = (session.getAttribute("role").equals(1));
        return isAdmin;
    }

    private PracownikFacade getFacede() {
        return pracownikFacade;
    }

    public String loginProject() {
        String zahashowaneHaslo = getFacede().haszuj(password);
        Pracownik p = getFacede().login(uname, zahashowaneHaslo);
        if (p != null) {
            HttpSession session = Util.getSession();
            session.setAttribute("login", uname);
            session.setAttribute("role", p.getRola().getId());
            session.setAttribute("userid", p.getIdPracownika());
            return "index";
        } else {
            JsfUtil.addErrorMessage("Login Fail!");

            // invalidate session, and redirect to other pages
            //message = "Invalid Login. Please Try Again!";
            return "login";
        }
    }

    public void logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return "login?faces-redirect=true";
    }

}
