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
public class ExpedienteUnicoId implements Serializable {
	public static final long serialVersionUID = 1L;
	private BigDecimal multiusu = null;
	private BigDecimal expedienteUnico = null;
	private BigDecimal areaPrivativa = null;
	private BigDecimal cdCadast = null;
	private BigDecimal tipoEndereco = null;
	private BigDecimal dtOcoEnd = null;
	private BigDecimal codLogradouro = null;
	private BigDecimal numImovel = null;

	/**
	 * M�todos para manter o atributo multiusu
	 */
	@Column(name="MULTIUSU", length=2, scale=2, columnDefinition="decimal(3,0)")
	public BigDecimal getMultiusu() {
		return this.multiusu;
	}

	public void setMultiusu(BigDecimal multiusu) {
		if (multiusu != null) {
			multiusu.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.multiusu = multiusu;
	}

	@Transient
	public String getMultiusuAsString() {
		return multiusu == null ? "" : PropertyAsString.prepareBigDecimalToString(multiusu);
	}

	@Transient
	public void setMultiusuAsString(String multiusu) {
		this.multiusu = (StringUtils.isEmpty(multiusu)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(multiusu)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo expedienteUnico
	 */
	@Column(name="CODEXUNI", length=2, scale=2, columnDefinition="decimal(13,0)")
	public BigDecimal getExpedienteUnico() {
		return this.expedienteUnico;
	}

	public void setExpedienteUnico(BigDecimal expedienteUnico) {
		if (expedienteUnico != null) {
			expedienteUnico.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.expedienteUnico = expedienteUnico;
	}

	@Transient
	public String getExpedienteUnicoAsString() {
		return expedienteUnico == null ? "" : PropertyAsString.prepareBigDecimalToString(expedienteUnico);
	}

	@Transient
	public void setExpedienteUnicoAsString(String expedienteUnico) {
		this.expedienteUnico = (StringUtils.isEmpty(expedienteUnico)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(expedienteUnico)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo areaPrivativa
	 */
	@Column(name="ARPEXUNI", length=2, scale=2, columnDefinition="decimal(5,0)")
	public BigDecimal getAreaPrivativa() {
		return this.areaPrivativa;
	}

	public void setAreaPrivativa(BigDecimal areaPrivativa) {
		if (areaPrivativa != null) {
			areaPrivativa.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.areaPrivativa = areaPrivativa;
	}

	@Transient
	public String getAreaPrivativaAsString() {
		return areaPrivativa == null ? "" : PropertyAsString.prepareBigDecimalToString(areaPrivativa);
	}

	@Transient
	public void setAreaPrivativaAsString(String areaPrivativa) {
		this.areaPrivativa = (StringUtils.isEmpty(areaPrivativa)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(areaPrivativa)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo cdCadast
	 */
	@Column(name="CDCADAST", length=2, scale=2, columnDefinition="decimal(1,0)")
	public BigDecimal getCdCadast() {
		return this.cdCadast;
	}

	public void setCdCadast(BigDecimal cdCadast) {
		if (cdCadast != null) {
			cdCadast.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.cdCadast = cdCadast;
	}

	@Transient
	public String getCdCadastAsString() {
		return cdCadast == null ? "" : PropertyAsString.prepareBigDecimalToString(cdCadast);
	}

	@Transient
	public void setCdCadastAsString(String cdCadast) {
		this.cdCadast = (StringUtils.isEmpty(cdCadast)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(cdCadast)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo tipoEndereco
	 */
	@Column(name="TPENDER", length=2, scale=2, columnDefinition="decimal(1,0)")
	public BigDecimal getTipoEndereco() {
		return this.tipoEndereco;
	}

	public void setTipoEndereco(BigDecimal tipoEndereco) {
		if (tipoEndereco != null) {
			tipoEndereco.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.tipoEndereco = tipoEndereco;
	}

	@Transient
	public String getTipoEnderecoAsString() {
		return tipoEndereco == null ? "" : PropertyAsString.prepareBigDecimalToString(tipoEndereco);
	}

	@Transient
	public void setTipoEnderecoAsString(String tipoEndereco) {
		this.tipoEndereco = (StringUtils.isEmpty(tipoEndereco)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(tipoEndereco)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo dtOcoEnd
	 */
	@Column(name="DTOCOEND", length=2, scale=2, columnDefinition="decimal(8,0)")
	public BigDecimal getDtOcoEnd() {
		return this.dtOcoEnd;
	}

	public void setDtOcoEnd(BigDecimal dtOcoEnd) {
		if (dtOcoEnd != null) {
			dtOcoEnd.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.dtOcoEnd = dtOcoEnd;
	}

	@Transient
	public String getDtOcoEndAsString() {
		return dtOcoEnd == null ? "" : PropertyAsString.prepareBigDecimalToString(dtOcoEnd);
	}

	@Transient
	public void setDtOcoEndAsString(String dtOcoEnd) {
		this.dtOcoEnd = (StringUtils.isEmpty(dtOcoEnd)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(dtOcoEnd)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo codLogradouro
	 */
	@Column(name="CODLOGR", length=2, scale=2, columnDefinition="decimal(7,0)")
	public BigDecimal getCodLogradouro() {
		return this.codLogradouro;
	}

	public void setCodLogradouro(BigDecimal codLogradouro) {
		if (codLogradouro != null) {
			codLogradouro.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.codLogradouro = codLogradouro;
	}

	@Transient
	public String getCodLogradouroAsString() {
		return codLogradouro == null ? "" : PropertyAsString.prepareBigDecimalToString(codLogradouro);
	}

	@Transient
	public void setCodLogradouroAsString(String codLogradouro) {
		this.codLogradouro = (StringUtils.isEmpty(codLogradouro)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(codLogradouro)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo numImovel
	 */
	@Column(name="NIMOVEL", length=2, scale=2, columnDefinition="decimal(5,0)")
	public BigDecimal getNumImovel() {
		return this.numImovel;
	}

	public void setNumImovel(BigDecimal numImovel) {
		if (numImovel != null) {
			numImovel.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.numImovel = numImovel;
	}

	@Transient
	public String getNumImovelAsString() {
		return numImovel == null ? "" : PropertyAsString.prepareBigDecimalToString(numImovel);
	}

	@Transient
	public void setNumImovelAsString(String numImovel) {
		this.numImovel = (StringUtils.isEmpty(numImovel)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(numImovel)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
