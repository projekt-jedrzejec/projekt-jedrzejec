/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michał
 */
@Entity
@Table(name = "uprawnienia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uprawnienia.findAll", query = "SELECT u FROM Uprawnienia u"),
    @NamedQuery(name = "Uprawnienia.findById", query = "SELECT u FROM Uprawnienia u WHERE u.id = :id"),
    @NamedQuery(name = "Uprawnienia.findByRola", query = "SELECT u FROM Uprawnienia u WHERE u.rola = :rola")})
public class Uprawnienia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "rola")
    private String rola;
    @JoinColumn(name = "uzytkownik", referencedColumnName = "id_pracownika")
    @ManyToOne
    private Pracownik uzytkownik;

    public Uprawnienia() {
    }

    public Uprawnienia(Integer id) {
        this.id = id;
    }

    public Uprawnienia(Integer id, String rola) {
        this.id = id;
        this.rola = rola;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public Pracownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Pracownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uprawnienia)) {
            return false;
        }
        Uprawnienia other = (Uprawnienia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Uprawnienia[ id=" + id + " ]";
    }
    
}
