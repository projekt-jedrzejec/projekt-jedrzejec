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
@Table(name = "kategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategoria.findAll", query = "SELECT k FROM Kategoria k"),
    @NamedQuery(name = "Kategoria.findByIdKategorii", query = "SELECT k FROM Kategoria k WHERE k.idKategorii = :idKategorii"),
    @NamedQuery(name = "Kategoria.findByKategoria", query = "SELECT k FROM Kategoria k WHERE k.kategoria = :kategoria")})
public class Kategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_kategorii")
    private Integer idKategorii;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "kategoria")
    private String kategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kategoria")
    private List<Element> elementList;

    public Kategoria() {
    }

    public Kategoria(Integer idKategorii) {
        this.idKategorii = idKategorii;
    }

    public Kategoria(Integer idKategorii, String kategoria) {
        this.idKategorii = idKategorii;
        this.kategoria = kategoria;
    }

    public Integer getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(Integer idKategorii) {
        this.idKategorii = idKategorii;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
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
        hash += (idKategorii != null ? idKategorii.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategoria)) {
            return false;
        }
        Kategoria other = (Kategoria) object;
        if ((this.idKategorii == null && other.idKategorii != null) || (this.idKategorii != null && !this.idKategorii.equals(other.idKategorii))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return kategoria;
    }

}
