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
@Table(name = "sala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findByIdSali", query = "SELECT s FROM Sala s WHERE s.idSali = :idSali"),
    @NamedQuery(name = "Sala.findBySala", query = "SELECT s FROM Sala s WHERE s.sala = :sala")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sali")
    private Integer idSali;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sala")
    private String sala;
    @OneToMany(mappedBy = "sala")
    private List<Element> elementList;

    public Sala() {
    }

    public Sala(Integer idSali) {
        this.idSali = idSali;
    }

    public Sala(Integer idSali, String sala) {
        this.idSali = idSali;
        this.sala = sala;
    }

    public Integer getIdSali() {
        return idSali;
    }

    public void setIdSali(Integer idSali) {
        this.idSali = idSali;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
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
        hash += (idSali != null ? idSali.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.idSali == null && other.idSali != null) || (this.idSali != null && !this.idSali.equals(other.idSali))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sala;
    }

}
