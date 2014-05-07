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
	@AttributeOverride(name="id", column=@Column(name="idMacrozona"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmMacrozona", schema="SQLDBA")
public class Macrozona extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	private Integer macrozona = null;
	private Integer ueu = null;

	/**
	 * M�todos para manter o atributo macrozona
	 */
	@Column(nullable=false)
	public Integer getMacrozona() {
		return this.macrozona;
	}

	public void setMacrozona(Integer macrozona) {
		this.macrozona = macrozona;
	}

	@Transient
	public String getMacrozonaAsString() {
		return (macrozona == null) ? "" : macrozona.toString();
	}

	@Transient
	public void setMacrozonaAsString(String macrozona) {
		this.macrozona = (StringUtils.isEmpty(macrozona)) ? null : new Integer(macrozona);
	}

	/**
	 * M�todos para manter o atributo ueu
	 */
	@Column(nullable=false)
	public Integer getUeu() {
		return this.ueu;
	}

	public void setUeu(Integer ueu) {
		this.ueu = ueu;
	}

	@Transient
	public String getUeuAsString() {
		return (ueu == null) ? "" : ueu.toString();
	}

	@Transient
	public void setUeuAsString(String ueu) {
		this.ueu = (StringUtils.isEmpty(ueu)) ? null : new Integer(ueu);
	}
}
