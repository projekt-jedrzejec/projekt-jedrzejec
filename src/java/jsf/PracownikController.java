package jsf;

import entity.Pracownik;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import session.PracownikFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletException;

@ManagedBean(name = "pracownikController")
@SessionScoped
public class PracownikController implements Serializable {
    
    @EJB
    private session.PracownikFacade ejbFacade;
    private List<Pracownik> items = null;
    private Pracownik selected;
    private String haslo = "";
    
    public String getHaslo() {
        return haslo;
    }
    
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    
    public PracownikController() {
    }
    
    public Pracownik getSelected() {
        return selected;
    }
    
    public void setSelected(Pracownik selected) {
        
        this.selected = selected;
    }
    
    protected void setEmbeddableKeys() {
    }
    
    protected void initializeEmbeddableKey() {
    }
    
    private PracownikFacade getFacade() {
        return ejbFacade;
    }
    
    public Pracownik prepareCreate() {
        selected = new Pracownik();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        if (getFacade().sprawdzCzyLoginIstnieje(selected.getLogin())) {
            if (getFacade().sprawdzCzyEmailIstnieje(selected.getEmail())) {
                String h = selected.getPassword();
                selected.setPassword(getFacade().haszuj(h));
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PracownikCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    items = null;    // Invalidate list of items to trigger re-query.
                }
            } else {
                JsfUtil.addErrorMessage("Pracownik z takim emailem już istnieje");
            }
        } else {
            JsfUtil.addErrorMessage("Pracownik z takim loginem już istnieje");
        }
    }
    
    public void update() {
        Pracownik staryPracownik = getFacade().getPraconikById(selected.getIdPracownika());
        selected.setPassword(haslo);
        if (getFacade().sprawdzCzyLoginIstnieje(selected.getLogin(), selected.getIdPracownika())) {
            if (getFacade().sprawdzCzyEmailIstnieje(selected.getEmail(), selected.getIdPracownika())) {
                if (!selected.getPassword().equals("")) {
                    String h = selected.getPassword();
                    selected.setPassword(getFacade().haszuj(h));
                } else {
                    selected.setPassword(staryPracownik.getPassword());
                }
                persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PracownikUpdated"));
            } else {
                JsfUtil.addErrorMessage("Pracownik z takim emailem już istnieje");
                selected.setEmail(staryPracownik.getEmail());
                selected.setLogin(staryPracownik.getLogin());
            }
        } else {
            JsfUtil.addErrorMessage("Pracownik z takim loginem już istnieje");
            selected.setLogin(staryPracownik.getLogin());
            selected.setEmail(staryPracownik.getEmail());
        }
    }
    
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PracownikDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public List<Pracownik> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    
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
    
    public List<Pracownik> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    public List<Pracownik> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = Pracownik.class)
    public static class PracownikControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PracownikController controller = (PracownikController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pracownikController");
            return controller.getFacade().find(getKey(value));
        }
        
        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }
        
        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }
        
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pracownik) {
                Pracownik o = (Pracownik) object;
                return getStringKey(o.getIdPracownika());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pracownik.class.getName()});
                return null;
            }
        }
        
    }
    
}
