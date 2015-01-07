/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entity.Element;
import entity.Sala;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import jsf.util.JsfUtil;
import session.ElementFacade;

/**
 *
 * @author Michał
 */
@ManagedBean(name = "salaElementController")
@SessionScoped
public class SalaElementController implements Serializable {

    @EJB
    private ElementFacade facade;

    private Sala selectedRoom;
    private List<Element> elementy;
    private Element selected;

    public List<Element> getElementy() {
        elementy = getFacade().showElementsBySala(selectedRoom);
        return elementy;
    }

    public Sala getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Sala selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public Element getSelected() {
        return selected;
    }

    public void setSelected(Element selected) {
        this.selected = selected;
    }

    public void setElementy(List<Element> elementy) {
        this.elementy = elementy;
    }

    public ElementFacade getFacade() {
        return facade;
    }

    public void buttonAction(ActionEvent event) {
//        elementy = getFacade().showElementsBySala(selectedRoom);
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ElementUpdated"));
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {

            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

}
