package br.com.procempa.dmweb.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OptimisticLockType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverrides( {
	@AttributeOverride(name="id", column=@Column(name="idCurvaNivelRuido"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmCurvaNivelRuido", schema="SQLDBA")
public class CurvaNivelRuido extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	private String descricao = null;
	
	/**
	 * M�todos para manter o atributo descricao
	 */
	@Lob
	@Column(columnDefinition="CLOB(2G) NOT LOGGED ", nullable=false)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Transient
	public String getCurvaId() {
		return "curva"+getIdAsString();
	}
}
