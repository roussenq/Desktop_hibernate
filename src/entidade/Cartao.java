/*
 * 1° passo criar essa classe
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id  //primary key no BD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto_increment no mysql
    private Long id;
    
    @Column(nullable = false, length = 19) //
    private String numero;
    @Column(nullable = false, length = 20)
    private String bandeira;
    @Column(nullable = false, length = 05) //validade com formato de 5 digitos ex:02/21
    private String validade;

    public Cartao() {  //construtor padrao
    }

    public Cartao(Long id, String numero, String bandeira, String validade) {
        this.id = id;
        this.numero = numero;
        this.bandeira = bandeira;
        this.validade = validade;
    }
      
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartao)) {
            return false;
        }
        Cartao other = (Cartao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Cartao[ id=" + id + " ]";
    }
    
}
