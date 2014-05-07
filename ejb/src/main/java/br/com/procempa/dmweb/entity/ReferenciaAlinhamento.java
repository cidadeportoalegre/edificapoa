package br.com.procempa.dmweb.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.OptimisticLockType;

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverrides( {
	@AttributeOverride(name="id", column=@Column(name="idReferenciaAlinhamento"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmReferenciaAlinhamento", schema="SQLDBA")
public class ReferenciaAlinhamento extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	private Integer codigo = null;
	private String descricao = null;

	/**
	 * M�todos para manter o atributo codigo
	 */
	@Column(nullable=false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Transient
	public String getCodigoAsString() {
		return (codigo == null) ? "" : codigo.toString();
	}

	@Transient
	public void setCodigoAsString(String codigo) {
		this.codigo = (StringUtils.isEmpty(codigo)) ? null : new Integer(codigo);
	}

	/**
	 * M�todos para manter o atributo descricao
	 */
	@Column(nullable=false)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
