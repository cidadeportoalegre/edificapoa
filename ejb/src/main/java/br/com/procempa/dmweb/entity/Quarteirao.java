package br.com.procempa.dmweb.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@AttributeOverride(name="id", column=@Column(name="idQuarteirao"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmQuarteirao", schema="SQLDBA")
public class Quarteirao extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	private Macrozona macrozona = new Macrozona();
	private Integer quarteirao = null;
	private Documento documento = null;
	private Set<Face> faceList = new LinkedHashSet<Face>();
	private Boolean legado = Boolean.FALSE;

	/**
	 * M�todos para manter o atributo macrozona
	 */
	@Column(nullable=false)
	@Transient
	public Integer getMacrozonaMZ() {
		return this.macrozona.getMacrozona();
	}

	@Transient
	public void setMacrozonaMZ(Integer macrozona) {
		this.macrozona.setMacrozona(macrozona);
	}

	@Transient
	public String getMacrozonaMZAsString() {
		return macrozona.getMacrozonaAsString();
	}

	@Transient
	public void setMacrozonaMZAsString(String macrozona) {
		this.macrozona.setMacrozonaAsString(macrozona);
	}

	/**
	 * M�todos para manter o atributo ueu
	 */
	@Column(nullable=false)
	@Transient
	public Integer getUeu() {
		return macrozona.getUeu();
	}

	@Transient
	public void setUeu(Integer ueu) {
		macrozona.setUeu(ueu);
	}

	@Transient
	public String getUeuAsString() {
		return macrozona.getUeuAsString();
	}

	@Transient
	public void setUeuAsString(String ueu) {
		macrozona.setUeuAsString(ueu);
	}

	/**
	 * M�todos para manter o atributo quarteirao
	 */
	@Column(nullable=false)
	public Integer getQuarteirao() {
		return this.quarteirao;
	}

	public void setQuarteirao(Integer quarteirao) {
		this.quarteirao = quarteirao;
	}

	@Transient
	public String getQuarteiraoAsString() {
		return (quarteirao == null) ? "" : quarteirao.toString();
	}

	@Transient
	public void setQuarteiraoAsString(String quarteirao) {
		this.quarteirao = (StringUtils.isEmpty(quarteirao)) ? null : new Integer(quarteirao);
	}

	@Transient
	public String getQuarteiraoAsText() {
		return "MZ "+(getMacrozona())+" UEU "+getUeuAsString()+" QUARTEIR�O "+getQuarteiraoAsString();
	}
	
	/**
	 * M�todos para manter o atributo documento
	 */
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="idDocumento")
	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	/**
	 * M�todos para manter o atributo faceList
	 */
	@OneToMany(mappedBy="quarteirao", fetch=FetchType.EAGER, cascade={ CascadeType.ALL })
	public Set<Face> getFaceList() {
		return this.faceList;
	}

	public void setFaceList(Set<Face> faceList) {
		this.faceList = faceList;
	}

	/**
	 * M�todos para manter o atributo legado
	 */
	public Boolean getLegado() {
		return this.legado;
	}

	public void setLegado(Boolean legado) {
		this.legado = legado;
	}

	@Transient
	public String getLegadoAsString() {
		return (legado == null) ? "" : legado.toString();
	}

	@Transient
	public void setLegadoAsString(String legado) {
		this.legado = (StringUtils.isEmpty(legado)) ? null : new Boolean(legado);
	}

	@ManyToOne(optional=false, fetch=FetchType.EAGER, cascade={ CascadeType.MERGE })
	@JoinColumn(name="idMacrozona")
	public Macrozona getMacrozona() {
		return macrozona;
	}

	public void setMacrozona(Macrozona macrozona) {
		this.macrozona = macrozona;
	}
}
