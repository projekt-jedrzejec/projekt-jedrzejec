/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michał
 */
@Entity
@Table(name = "element")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Element.findAll", query = "SELECT e FROM Element e"),
    @NamedQuery(name = "Element.findByIdElementu", query = "SELECT e FROM Element e WHERE e.idElementu = :idElementu"),
    @NamedQuery(name = "Element.findByNazwa", query = "SELECT e FROM Element e WHERE e.nazwa = :nazwa"),
    @NamedQuery(name = "Element.findByNrEwidencyjny", query = "SELECT e FROM Element e WHERE e.nrEwidencyjny = :nrEwidencyjny"),
    @NamedQuery(name = "Element.findByWartoscEwidencyjna", query = "SELECT e FROM Element e WHERE e.wartoscEwidencyjna = :wartoscEwidencyjna"),
    @NamedQuery(name = "Element.findByDataZakupu", query = "SELECT e FROM Element e WHERE e.dataZakupu = :dataZakupu"),
    @NamedQuery(name = "Element.findByUwagi", query = "SELECT e FROM Element e WHERE e.uwagi = :uwagi")})
public class Element implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_elementu")
    private Integer idElementu;
    @Size(max = 80)
    @Column(name = "nazwa")
    private String nazwa;
    @Size(max = 20)
    @Column(name = "nr_ewidencyjny")
    private String nrEwidencyjny;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "wartosc_ewidencyjna")
    private BigDecimal wartoscEwidencyjna;
    @Column(name = "data_zakupu")
    @Temporal(TemporalType.DATE)
    private Date dataZakupu;
    @Size(max = 250)
    @Column(name = "uwagi")
    private String uwagi;
    @JoinColumn(name = "kategoria", referencedColumnName = "id_kategorii")
    @ManyToOne(optional = false)
    private Kategoria kategoria;
    @JoinColumn(name = "osoba_odpowiedzialna", referencedColumnName = "id_pracownika")
    @ManyToOne
    private Pracownik osobaOdpowiedzialna;
    @JoinColumn(name = "sala", referencedColumnName = "id_sali")
    @ManyToOne
    private Sala sala;
    @JoinColumn(name = "stan", referencedColumnName = "id_stanu")
    @ManyToOne(optional = false)
    private Stan stan;
    @JoinColumn(name = "typ", referencedColumnName = "id_typu")
    @ManyToOne(optional = false)
    private Typ typ;

    public Element() {
    }

    public Element(Integer idElementu) {
        this.idElementu = idElementu;
    }

    public Element(Integer idElementu, BigDecimal wartoscEwidencyjna) {
        this.idElementu = idElementu;
        this.wartoscEwidencyjna = wartoscEwidencyjna;
    }

    public Integer getIdElementu() {
        return idElementu;
    }

    public void setIdElementu(Integer idElementu) {
        this.idElementu = idElementu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNrEwidencyjny() {
        return nrEwidencyjny;
    }

    public void setNrEwidencyjny(String nrEwidencyjny) {
        this.nrEwidencyjny = nrEwidencyjny;
    }

    public BigDecimal getWartoscEwidencyjna() {
        return wartoscEwidencyjna;
    }

    public void setWartoscEwidencyjna(BigDecimal wartoscEwidencyjna) {
        this.wartoscEwidencyjna = wartoscEwidencyjna;
    }

    public Date getDataZakupu() {
        return dataZakupu;
    }

    public void setDataZakupu(Date dataZakupu) {
        this.dataZakupu = dataZakupu;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public Pracownik getOsobaOdpowiedzialna() {
        return osobaOdpowiedzialna;
    }

    public void setOsobaOdpowiedzialna(Pracownik osobaOdpowiedzialna) {
        this.osobaOdpowiedzialna = osobaOdpowiedzialna;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Stan getStan() {
        return stan;
    }

    public void setStan(Stan stan) {
        this.stan = stan;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idElementu != null ? idElementu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Element)) {
            return false;
        }
        Element other = (Element) object;
        if ((this.idElementu == null && other.idElementu != null) || (this.idElementu != null && !this.idElementu.equals(other.idElementu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Element[ idElementu=" + idElementu + " ]";
    }
    
}
