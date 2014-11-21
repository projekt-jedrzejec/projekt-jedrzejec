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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pracownik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pracownik.findAll", query = "SELECT p FROM Pracownik p"),
    @NamedQuery(name = "Pracownik.findByIdPracownika", query = "SELECT p FROM Pracownik p WHERE p.idPracownika = :idPracownika"),
    @NamedQuery(name = "Pracownik.findByImie", query = "SELECT p FROM Pracownik p WHERE p.imie = :imie"),
    @NamedQuery(name = "Pracownik.findByNazwisko", query = "SELECT p FROM Pracownik p WHERE p.nazwisko = :nazwisko"),
    @NamedQuery(name = "Pracownik.findByNrTelefonu", query = "SELECT p FROM Pracownik p WHERE p.nrTelefonu = :nrTelefonu"),
    @NamedQuery(name = "Pracownik.findByEmail", query = "SELECT p FROM Pracownik p WHERE p.email = :email"),
    @NamedQuery(name = "Pracownik.findByLogin", query = "SELECT p FROM Pracownik p WHERE p.login = :login"),
    @NamedQuery(name = "Pracownik.findByPassword", query = "SELECT p FROM Pracownik p WHERE p.password = :password")})
public class Pracownik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pracownika")
    private Integer idPracownika;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "imie")
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nazwisko")
    private String nazwisko;
    @Size(max = 15)
    @Column(name = "nr_telefonu")
    private String nrTelefonu;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "pokoj", referencedColumnName = "id_pokoju")
    @ManyToOne
    private Pokoj pokoj;
    @JoinColumn(name = "stanowisko", referencedColumnName = "id_stanowiska")
    @ManyToOne(optional = false)
    private Stanowisko stanowisko;
    @OneToMany(mappedBy = "uzytkownik")
    private List<Uprawnienia> uprawnieniaList;
    @OneToMany(mappedBy = "osobaOdpowiedzialna")
    private List<Element> elementList;

    public Pracownik() {
    }

    public Pracownik(Integer idPracownika) {
        this.idPracownika = idPracownika;
    }

    public Pracownik(Integer idPracownika, String imie, String nazwisko, String email, String login, String password) {
        this.idPracownika = idPracownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Integer getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(Integer idPracownika) {
        this.idPracownika = idPracownika;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pokoj getPokoj() {
        return pokoj;
    }

    public void setPokoj(Pokoj pokoj) {
        this.pokoj = pokoj;
    }

    public Stanowisko getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(Stanowisko stanowisko) {
        this.stanowisko = stanowisko;
    }

    @XmlTransient
    public List<Uprawnienia> getUprawnieniaList() {
        return uprawnieniaList;
    }

    public void setUprawnieniaList(List<Uprawnienia> uprawnieniaList) {
        this.uprawnieniaList = uprawnieniaList;
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
        hash += (idPracownika != null ? idPracownika.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pracownik)) {
            return false;
        }
        Pracownik other = (Pracownik) object;
        if ((this.idPracownika == null && other.idPracownika != null) || (this.idPracownika != null && !this.idPracownika.equals(other.idPracownika))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pracownik[ idPracownika=" + idPracownika + " ]";
    }
    
}
