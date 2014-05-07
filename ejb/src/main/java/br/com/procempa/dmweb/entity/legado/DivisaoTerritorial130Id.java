package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import br.com.procempa.dmweb.util.PropertyAsString;

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Embeddable
public class DivisaoTerritorial130Id implements Serializable {
	public static final long serialVersionUID = 1L;
	private BigDecimal macrozona = null;
	private BigDecimal ueu = null;
	private BigDecimal quarteirao = null;
	/**
	 * 
	 * C�digo do logradouro da face (CDL)
	 */
	private BigDecimal faceCodLogradouro = null;
	private BigDecimal faceSequencia = null;
	private Integer faceData = null;
	private Integer tipoObs = null;
	private Integer observacao = null;

	/**
	 * M�todos para manter o atributo macrozona
	 */
	@Column(name="UTS", length=2, scale=2, columnDefinition="decimal(3,0)")
	public BigDecimal getMacrozona() {
		return this.macrozona;
	}

	public void setMacrozona(BigDecimal macrozona) {
		if (macrozona != null) {
			macrozona.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.macrozona = macrozona;
	}

	@Transient
	public String getMacrozonaAsString() {
		return macrozona == null ? "" : ""+(macrozona.intValue()-900);
	}

	@Transient
	public void setMacrozonaAsString(String macrozona) {
		this.macrozona = (StringUtils.isEmpty(macrozona)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(macrozona)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo ueu
	 */
	@Column(name="UTP", length=2, scale=2, columnDefinition="decimal(3,0)")
	public BigDecimal getUeu() {
		return this.ueu;
	}

	public void setUeu(BigDecimal ueu) {
		if (ueu != null) {
			ueu.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.ueu = ueu;
	}

	@Transient
	public String getUeuAsString() {
		return ueu == null ? "" : PropertyAsString.prepareBigDecimalToString(ueu);
	}

	@Transient
	public void setUeuAsString(String ueu) {
		this.ueu = (StringUtils.isEmpty(ueu)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(ueu)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo quarteirao
	 */
	@Column(name="QTR", length=2, scale=2, columnDefinition="decimal(3,0)")
	public BigDecimal getQuarteirao() {
		return this.quarteirao;
	}

	public void setQuarteirao(BigDecimal quarteirao) {
		if (quarteirao != null) {
			quarteirao.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.quarteirao = quarteirao;
	}

	@Transient
	public String getQuarteiraoAsString() {
		return quarteirao == null ? "" : PropertyAsString.prepareBigDecimalToString(quarteirao);
	}

	@Transient
	public void setQuarteiraoAsString(String quarteirao) {
		this.quarteirao = (StringUtils.isEmpty(quarteirao)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(quarteirao)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo faceCodLogradouro
	 */
	@Column(name="CODLOGR", length=2, scale=2, columnDefinition="decimal(7,0)")
	public BigDecimal getFaceCodLogradouro() {
		return this.faceCodLogradouro;
	}

	public void setFaceCodLogradouro(BigDecimal faceCodLogradouro) {
		if (faceCodLogradouro != null) {
			faceCodLogradouro.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.faceCodLogradouro = faceCodLogradouro;
	}

	@Transient
	public String getFaceCodLogradouroAsString() {
		return faceCodLogradouro == null ? "" : PropertyAsString.prepareBigDecimalToString(faceCodLogradouro);
	}

	@Transient
	public void setFaceCodLogradouroAsString(String faceCodLogradouro) {
		this.faceCodLogradouro = (StringUtils.isEmpty(faceCodLogradouro)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(faceCodLogradouro)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo faceSequencia
	 */
	@Column(name="SEQFACE", length=2, scale=2, columnDefinition="decimal(1,0)")
	public BigDecimal getFaceSequencia() {
		return this.faceSequencia;
	}

	public void setFaceSequencia(BigDecimal faceSequencia) {
		if (faceSequencia != null) {
			faceSequencia.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.faceSequencia = faceSequencia;
	}

	@Transient
	public String getFaceSequenciaAsString() {
		return faceSequencia == null ? "" : PropertyAsString.prepareBigDecimalToString(faceSequencia);
	}

	@Transient
	public void setFaceSequenciaAsString(String faceSequencia) {
		this.faceSequencia = (StringUtils.isEmpty(faceSequencia)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(faceSequencia)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo faceData
	 */
	@Column(name="DATAFACE")
	public Integer getFaceData() {
		return this.faceData;
	}

	public void setFaceData(Integer faceData) {
		this.faceData = faceData;
	}

	@Transient
	public String getFaceDataAsString() {
		return (faceData == null) ? "" : faceData.toString();
	}

	@Transient
	public void setFaceDataAsString(String faceData) {
		this.faceData = (StringUtils.isEmpty(faceData)) ? null : new Integer(faceData);
	}

	/**
	 * M�todos para manter o atributo tipoObs
	 */
	@Column(name="TIPOBS")
	public Integer getTipoObs() {
		return this.tipoObs;
	}

	public void setTipoObs(Integer tipoObs) {
		this.tipoObs = tipoObs;
	}

	@Transient
	public String getTipoObsAsString() {
		return (tipoObs == null) ? "" : tipoObs.toString();
	}

	@Transient
	public void setTipoObsAsString(String tipoObs) {
		this.tipoObs = (StringUtils.isEmpty(tipoObs)) ? null : new Integer(tipoObs);
	}

	/**
	 * M�todos para manter o atributo observacao
	 */
	@Column(name="CODOBS", length=3)
	public Integer getObservacao() {
		return this.observacao;
	}

	public void setObservacao(Integer observacao) {
		this.observacao = observacao;
	}

	@Transient
	public String getObservacaoAsString() {
		return (observacao == null) ? "" : observacao.toString();
	}

	@Transient
	public void setObservacaoAsString(String observacao) {
		this.observacao = (StringUtils.isEmpty(observacao)) ? null : new Integer(observacao);
	}
}
