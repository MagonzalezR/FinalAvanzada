/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author kil_5
 */
@Entity
@Table(name = "Desarrollador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desarrollador.findAll", query = "SELECT d FROM Desarrollador d")
    , @NamedQuery(name = "Desarrollador.findByIdDesarrollador", query = "SELECT d FROM Desarrollador d WHERE d.idDesarrollador = :idDesarrollador")
    , @NamedQuery(name = "Desarrollador.findByNombreDesarrollador", query = "SELECT d FROM Desarrollador d WHERE d.nombreDesarrollador = :nombreDesarrollador")})
public class Desarrollador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDesarrollador")
    private Integer idDesarrollador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreDesarrollador")
    private String nombreDesarrollador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "desarrolladoridDesarrollador")
    private Collection<Juego> juegoCollection;

    public Desarrollador() {
    }

    public Desarrollador(Integer idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public Desarrollador(Integer idDesarrollador, String nombreDesarrollador) {
        this.idDesarrollador = idDesarrollador;
        this.nombreDesarrollador = nombreDesarrollador;
    }

    public Integer getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(Integer idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public String getNombreDesarrollador() {
        return nombreDesarrollador;
    }

    public void setNombreDesarrollador(String nombreDesarrollador) {
        this.nombreDesarrollador = nombreDesarrollador;
    }

    @XmlTransient
    public Collection<Juego> getJuegoCollection() {
        return juegoCollection;
    }

    public void setJuegoCollection(Collection<Juego> juegoCollection) {
        this.juegoCollection = juegoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDesarrollador != null ? idDesarrollador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desarrollador)) {
            return false;
        }
        Desarrollador other = (Desarrollador) object;
        if ((this.idDesarrollador == null && other.idDesarrollador != null) || (this.idDesarrollador != null && !this.idDesarrollador.equals(other.idDesarrollador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Desarrollador[ idDesarrollador=" + idDesarrollador + " ]";
    }
    
}
