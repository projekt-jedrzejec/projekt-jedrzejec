/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Michał
 */
@Entity
@Table(name = "typ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typ.findAll", query = "SELECT t FROM Typ t"),
    @NamedQuery(name = "Typ.findByIdTypu", query = "SELECT t FROM Typ t WHERE t.idTypu = :idTypu"),
    @NamedQuery(name = "Typ.findByTyp", query = "SELECT t FROM Typ t WHERE t.typ = :typ")})
public class Typ implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_typu")
    private Integer idTypu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "typ")
    private String typ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typ")
    private List<Element> elementList;

    public Typ() {
    }

    public Typ(Integer idTypu) {
        this.idTypu = idTypu;
    }

    public Typ(Integer idTypu, String typ) {
        this.idTypu = idTypu;
        this.typ = typ;
    }

    public Integer getIdTypu() {
        return idTypu;
    }

    public void setIdTypu(Integer idTypu) {
        this.idTypu = idTypu;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    @XmlTransient
    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypu != null ? idTypu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typ)) {
            return false;
        }
        Typ other = (Typ) object;
        if ((this.idTypu == null && other.idTypu != null) || (this.idTypu != null && !this.idTypu.equals(other.idTypu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Typ[ idTypu=" + idTypu + " ]";
    }
    
}
