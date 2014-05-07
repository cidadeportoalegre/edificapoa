package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.procempa.dmweb.util.PropertyAsString;

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Embeddable
public class DivisaoTerritorialId implements Serializable {
	public static final long serialVersionUID = 1L;
	private BigDecimal macrozona = null;
	private BigDecimal ueu = null;
	private BigDecimal quarteirao = null;
	/**
	 * 
	 * C�digo do logradouro da face (CDL)
	 */
	private BigDecimal codLogradouro = null;
	private BigDecimal sequencia = null;
	private Integer dataFace = null;

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
	public String getMzUeuAsString() {
		return getMacrozonaAsString()+  StringUtils.leftPad( getUeuAsString(), 3, "0") ;
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
		return quarteirao == null ? "" : StringUtils.leftPad( PropertyAsString.prepareBigDecimalToString(quarteirao), 3, "0" );
	}

	@Transient
	public void setQuarteiraoAsString(String quarteirao) {
		this.quarteirao = (StringUtils.isEmpty(quarteirao)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(quarteirao)).setScale(2, BigDecimal.ROUND_HALF_UP);
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
	 * M�todos para manter o atributo sequencia
	 */
	@Column(name="SEQFACE", length=2, scale=2, columnDefinition="decimal(1,0)")
	public BigDecimal getSequencia() {
		return this.sequencia;
	}

	public void setSequencia(BigDecimal sequencia) {
		if (sequencia != null) {
			sequencia.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.sequencia = sequencia;
	}

	@Transient
	public String getSequenciaAsString() {
		return sequencia == null ? "" : PropertyAsString.prepareBigDecimalToString(sequencia);
	}

	@Transient
	public void setSequenciaAsString(String sequencia) {
		this.sequencia = (StringUtils.isEmpty(sequencia)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(sequencia)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo dataFace
	 */
	@Column(length=8)
	public Integer getDataFace() {
		return this.dataFace;
	}

	public void setDataFace(Integer dataFace) {
		this.dataFace = dataFace;
	}
	
	@Override
	public String toString() {
        return new ToStringBuilder(this)
        .append( "macrozona", getMacrozona())
        .append( "ueu", getUeu())
        .append( "quarteirao", getQuarteirao())
        .append( "codLogradouro", getCodLogradouro())
        .append( "sequencia", getSequencia())
        .append( "dataFace", getDataFace())
            .toString();
    }
    
	@Override
    public boolean equals(Object other) {
        if ( !(other instanceof DivisaoTerritorialId) ) 
            return false;
        
        DivisaoTerritorialId castOther = (DivisaoTerritorialId) other;
        return new EqualsBuilder()
            .append(this.getMacrozona(), castOther.getMacrozona())
            .append(this.getUeu(), castOther.getUeu())
            .append(this.getQuarteirao(), castOther.getQuarteirao())
            .append(this.getCodLogradouro(), castOther.getCodLogradouro())
            .append(this.getSequencia(), castOther.getSequencia())
            .append(this.getDataFace(), castOther.getDataFace())            
            .isEquals();
    }
 

	@Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append( getMacrozona())
        .append( getUeu())
        .append( getQuarteirao())
        .append( getCodLogradouro())
        .append( getSequencia())
        .append( getDataFace())
            .toHashCode();
    }
}
