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
@Table(name = "stan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stan.findAll", query = "SELECT s FROM Stan s"),
    @NamedQuery(name = "Stan.findByIdStanu", query = "SELECT s FROM Stan s WHERE s.idStanu = :idStanu"),
    @NamedQuery(name = "Stan.findByStan", query = "SELECT s FROM Stan s WHERE s.stan = :stan")})
public class Stan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stanu")
    private Integer idStanu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "stan")
    private String stan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stan")
    private List<Element> elementList;

    public Stan() {
    }

    public Stan(Integer idStanu) {
        this.idStanu = idStanu;
    }

    public Stan(Integer idStanu, String stan) {
        this.idStanu = idStanu;
        this.stan = stan;
    }

    public Integer getIdStanu() {
        return idStanu;
    }

    public void setIdStanu(Integer idStanu) {
        this.idStanu = idStanu;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
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
        hash += (idStanu != null ? idStanu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stan)) {
            return false;
        }
        Stan other = (Stan) object;
        if ((this.idStanu == null && other.idStanu != null) || (this.idStanu != null && !this.idStanu.equals(other.idStanu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return stan;
    }

}
