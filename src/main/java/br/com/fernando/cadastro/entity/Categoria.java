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
@SequenceGenerator(name = "seq_categoria", sequenceName = "seq_categoria", initialValue = 1, allocationSize = 1)
@Table(name="categoria")
@Audited
public class Categoria extends AbstractDateEntity implements Serializable {
	
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_categoria")
    private int id;

    @Column(length = 100)
    private String nome;

	@Column(length = 1000)
    private String observacoes;
	
    @Column(name="ativo")
    private Boolean ativo = true;

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

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + this.id;
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
		final Categoria other = (Categoria) obj;
		return this.id == other.id;
	}
}
