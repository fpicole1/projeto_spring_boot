/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author fernando
 */
@Entity
@SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado", initialValue = 1, allocationSize = 1)
@Table(name = "estado")
@Audited
public class Estado extends AbstractDateEntity implements Serializable {

	private static final long serialVersionUID = 2287774790587900300L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado")
	private int id;

	@Column(length = 2)
	private String sigla;

	@Column(length = 100)
	private String nome;

	@Column(name = "codigo_ibge")
	private Integer codigoIbge;

	@JoinColumn(name = "id_pais", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_estado_pais"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Pais pais;
    
    // Geral
    public String retornarNomeComSigla() {
        return nome.concat(" (").concat(sigla).concat(")");
    }

    // Getters && Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
    
    // Equals && Hashcode
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + this.id;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Estado other = (Estado) obj;
		return this.id == other.id;
	}
}