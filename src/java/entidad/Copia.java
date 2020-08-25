/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kil_5
 */
@Entity
@Table(name = "Copia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Copia.findAll", query = "SELECT c FROM Copia c")
    , @NamedQuery(name = "Copia.findByIdCopia", query = "SELECT c FROM Copia c WHERE c.idCopia = :idCopia")})
public class Copia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCopia")
    private Integer idCopia;
    @JoinTable(name = "Copia_has_Foramto", joinColumns = {
        @JoinColumn(name = "Copia_idCopia", referencedColumnName = "idCopia")}, inverseJoinColumns = {
        @JoinColumn(name = "Foramto_idForamto", referencedColumnName = "idForamto")})
    @ManyToMany
    private Collection<Formato> formatoCollection;
    @JoinColumn(name = "Juego_idJuego", referencedColumnName = "idJuego")
    @ManyToOne(optional = false)
    private Juego juegoidJuego;

    public Copia() {
    }

    public Copia(Integer idCopia) {
        this.idCopia = idCopia;
    }

    public Integer getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(Integer idCopia) {
        this.idCopia = idCopia;
    }

    @XmlTransient
    public Collection<Formato> getFormatoCollection() {
        return formatoCollection;
    }

    public void setFormatoCollection(Collection<Formato> formatoCollection) {
        this.formatoCollection = formatoCollection;
    }

    public Juego getJuegoidJuego() {
        return juegoidJuego;
    }

    public void setJuegoidJuego(Juego juegoidJuego) {
        this.juegoidJuego = juegoidJuego;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCopia != null ? idCopia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Copia)) {
            return false;
        }
        Copia other = (Copia) object;
        if ((this.idCopia == null && other.idCopia != null) || (this.idCopia != null && !this.idCopia.equals(other.idCopia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Copia[ idCopia=" + idCopia + " ]";
    }
    
}
