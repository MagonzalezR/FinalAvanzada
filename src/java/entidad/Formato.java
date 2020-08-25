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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kil_5
 */
@Entity
@Table(name = "Formato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formato.findAll", query = "SELECT f FROM Formato f")
    , @NamedQuery(name = "Formato.findByIdForamto", query = "SELECT f FROM Formato f WHERE f.idForamto = :idForamto")
    , @NamedQuery(name = "Formato.findByNombreFormato", query = "SELECT f FROM Formato f WHERE f.nombreFormato = :nombreFormato")})
public class Formato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idForamto")
    private Integer idForamto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreFormato")
    private String nombreFormato;
    @ManyToMany(mappedBy = "formatoCollection")
    private Collection<Copia> copiaCollection;

    public Formato() {
    }

    public Formato(Integer idForamto) {
        this.idForamto = idForamto;
    }

    public Formato(Integer idForamto, String nombreFormato) {
        this.idForamto = idForamto;
        this.nombreFormato = nombreFormato;
    }

    public Integer getIdForamto() {
        return idForamto;
    }

    public void setIdForamto(Integer idForamto) {
        this.idForamto = idForamto;
    }

    public String getNombreFormato() {
        return nombreFormato;
    }

    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    @XmlTransient
    public Collection<Copia> getCopiaCollection() {
        return copiaCollection;
    }

    public void setCopiaCollection(Collection<Copia> copiaCollection) {
        this.copiaCollection = copiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idForamto != null ? idForamto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formato)) {
            return false;
        }
        Formato other = (Formato) object;
        if ((this.idForamto == null && other.idForamto != null) || (this.idForamto != null && !this.idForamto.equals(other.idForamto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Formato[ idForamto=" + idForamto + " ]";
    }
    
}
