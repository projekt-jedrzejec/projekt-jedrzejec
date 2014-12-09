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
@Table(name = "stanowisko")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stanowisko.findAll", query = "SELECT s FROM Stanowisko s"),
    @NamedQuery(name = "Stanowisko.findByIdStanowiska", query = "SELECT s FROM Stanowisko s WHERE s.idStanowiska = :idStanowiska"),
    @NamedQuery(name = "Stanowisko.findByStanowisko", query = "SELECT s FROM Stanowisko s WHERE s.stanowisko = :stanowisko")})
public class Stanowisko implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_stanowiska")
    private Integer idStanowiska;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "stanowisko")
    private String stanowisko;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stanowisko", fetch = FetchType.EAGER)
    private List<Pracownik> pracownikList;

    public Stanowisko() {
    }

    public Stanowisko(Integer idStanowiska) {
        this.idStanowiska = idStanowiska;
    }

    public Stanowisko(Integer idStanowiska, String stanowisko) {
        this.idStanowiska = idStanowiska;
        this.stanowisko = stanowisko;
    }

    public Integer getIdStanowiska() {
        return idStanowiska;
    }

    public void setIdStanowiska(Integer idStanowiska) {
        this.idStanowiska = idStanowiska;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
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
        hash += (idStanowiska != null ? idStanowiska.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stanowisko)) {
            return false;
        }
        Stanowisko other = (Stanowisko) object;
        if ((this.idStanowiska == null && other.idStanowiska != null) || (this.idStanowiska != null && !this.idStanowiska.equals(other.idStanowiska))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return stanowisko;
    }
    
}
