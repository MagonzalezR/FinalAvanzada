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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
 * @author kil_5
 */
@Entity
@Table(name = "Juego")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juego.findAll", query = "SELECT j FROM Juego j")
    , @NamedQuery(name = "Juego.findByIdJuego", query = "SELECT j FROM Juego j WHERE j.idJuego = :idJuego")
    , @NamedQuery(name = "Juego.findByNombreJuego", query = "SELECT j FROM Juego j WHERE j.nombreJuego = :nombreJuego")})
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idJuego")
    private Integer idJuego;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreJuego")
    private String nombreJuego;
    @ManyToMany(mappedBy = "juegoCollection")
    private Collection<Categoria> categoriaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "juegoidJuego")
    private Collection<Copia> copiaCollection;
    @JoinColumn(name = "Desarrollador_idDesarrollador", referencedColumnName = "idDesarrollador")
    @ManyToOne(optional = false)
    private Desarrollador desarrolladoridDesarrollador;

    public Juego() {
    }

    public Juego(Integer idJuego) {
        this.idJuego = idJuego;
    }

    public Juego(Integer idJuego, String nombreJuego) {
        this.idJuego = idJuego;
        this.nombreJuego = nombreJuego;
    }

    public Integer getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    @XmlTransient
    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    @XmlTransient
    public Collection<Copia> getCopiaCollection() {
        return copiaCollection;
    }

    public void setCopiaCollection(Collection<Copia> copiaCollection) {
        this.copiaCollection = copiaCollection;
    }

    public Desarrollador getDesarrolladoridDesarrollador() {
        return desarrolladoridDesarrollador;
    }

    public void setDesarrolladoridDesarrollador(Desarrollador desarrolladoridDesarrollador) {
        this.desarrolladoridDesarrollador = desarrolladoridDesarrollador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJuego != null ? idJuego.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juego)) {
            return false;
        }
        Juego other = (Juego) object;
        if ((this.idJuego == null && other.idJuego != null) || (this.idJuego != null && !this.idJuego.equals(other.idJuego))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Juego[ idJuego=" + idJuego + " ]";
    }
    
}
