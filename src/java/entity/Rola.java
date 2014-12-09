/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "rola")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rola.findAll", query = "SELECT r FROM Rola r"),
    @NamedQuery(name = "Rola.findById", query = "SELECT r FROM Rola r WHERE r.id = :id"),
    @NamedQuery(name = "Rola.findByNazwa", query = "SELECT r FROM Rola r WHERE r.nazwa = :nazwa")})
public class Rola implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nazwa")
    private String nazwa;
    @OneToMany(mappedBy = "rola", fetch = FetchType.EAGER)
    private List<Pracownik> pracownikList;

    public Rola() {
    }

    public Rola(Integer id) {
        this.id = id;
    }

    public Rola(Integer id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public List<Pracownik> getPracownikList() {
        return pracownikList;
    }

    public void setPracownikList(List<Pracownik> pracownikList) {
        this.pracownikList = pracownikList;
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
        if (!(object instanceof Rola)) {
            return false;
        }
        Rola other = (Rola) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazwa;
    }
    
}
