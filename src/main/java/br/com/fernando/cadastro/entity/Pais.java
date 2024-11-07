/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author fernando
 */
@Entity
@SequenceGenerator(name = "seq_pais", sequenceName = "seq_pais", initialValue = 1, allocationSize = 1)
@Table(name = "pais")
@Audited
public class Pais extends AbstractDateEntity implements Serializable {

	private static final long serialVersionUID = 1908239383113433136L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pais")
	private int id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@Column(name = "codigo")
	private Integer codigo;
    
    // Getters && Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
    
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
		final Pais other = (Pais) obj;
		return this.id == other.id;
	}
}