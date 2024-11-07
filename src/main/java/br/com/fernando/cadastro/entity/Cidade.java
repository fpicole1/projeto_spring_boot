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
@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", initialValue = 1, allocationSize = 1)
@Table(name="cidade")
@Audited
public class Cidade extends AbstractDateEntity implements Serializable {
    
    private static final long serialVersionUID = 3091937967573615509L;

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_cidade")
    private int id;

    @Column(length = 100)
    private String nome;

    @Column(name="codigo_ibge")
    private Integer codigoIbge;

    @JoinColumn(name = "id_estado", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cidade_estado"))
    @ManyToOne(fetch= FetchType.LAZY)
    private Estado estado;
    
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final Cidade other = (Cidade) obj;
        return this.id == other.id;
    }
}