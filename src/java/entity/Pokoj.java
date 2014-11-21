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
@Table(name = "pokoj")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pokoj.findAll", query = "SELECT p FROM Pokoj p"),
    @NamedQuery(name = "Pokoj.findByIdPokoju", query = "SELECT p FROM Pokoj p WHERE p.idPokoju = :idPokoju"),
    @NamedQuery(name = "Pokoj.findByPokoj", query = "SELECT p FROM Pokoj p WHERE p.pokoj = :pokoj")})
public class Pokoj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pokoju")
    private Integer idPokoju;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pokoj")
    private String pokoj;
    @OneToMany(mappedBy = "pokoj")
    private List<Pracownik> pracownikList;

    public Pokoj() {
    }

    public Pokoj(Integer idPokoju) {
        this.idPokoju = idPokoju;
    }

    public Pokoj(Integer idPokoju, String pokoj) {
        this.idPokoju = idPokoju;
        this.pokoj = pokoj;
    }

    public Integer getIdPokoju() {
        return idPokoju;
    }

    public void setIdPokoju(Integer idPokoju) {
        this.idPokoju = idPokoju;
    }

    public String getPokoj() {
        return pokoj;
    }

    public void setPokoj(String pokoj) {
        this.pokoj = pokoj;
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
        hash += (idPokoju != null ? idPokoju.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pokoj)) {
            return false;
        }
        Pokoj other = (Pokoj) object;
        if ((this.idPokoju == null && other.idPokoju != null) || (this.idPokoju != null && !this.idPokoju.equals(other.idPokoju))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pokoj[ idPokoju=" + idPokoju + " ]";
    }
    
}
