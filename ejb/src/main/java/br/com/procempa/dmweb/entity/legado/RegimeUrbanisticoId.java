package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Embeddable
public class RegimeUrbanisticoId implements Serializable {
	public static final long serialVersionUID = 1L;
	/**
	 * 
	 * C�digo do logradouro da face (CDL)
	 */
	private Integer codLogradouro = null; 
	/**
	 * 
	 * N�mero do primeiro im�vel da face
	 */
	private Integer faceLimiteInicial = null;
	/**
	 * 
	 * Limite inicial do regime por numera��o de im�vel
	 */
	private Integer regUrbLimInicial = null;
	/**
	 * 
	 * Limite inicial do regime por dist�ncia em metros
	 */
	private Integer regUrbDistInicial = null;
	/**
	 * 
	 * N�mero do �ltimo im�vel da face
	 */
	private Integer faceLimiteFinal = null;

	/**
	 * M�todos para manter o atributo codLogradouro
	 */
	@Column(name="CODLOGR", length=7)
	public Integer getCodLogradouro() {
		return this.codLogradouro;
	}

	public void setCodLogradouro(Integer codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	@Transient
	public String getCodLogradouroAsString() {
		return (codLogradouro == null) ? "" : codLogradouro.toString();
	}

	@Transient
	public void setCodLogradouroAsString(String codLogradouro) {
		this.codLogradouro = (StringUtils.isEmpty(codLogradouro)) ? null : new Integer(codLogradouro);
	}

	/**
	 * M�todos para manter o atributo faceLimiteInicial
	 */
	@Column(name="IMOVEL_INI", length=5)
	public Integer getFaceLimiteInicial() {
		return this.faceLimiteInicial;
	}

	public void setFaceLimiteInicial(Integer faceLimiteInicial) {
		this.faceLimiteInicial = faceLimiteInicial;
	}

	@Transient
	public String getFaceLimiteInicialAsString() {
		return (faceLimiteInicial == null) ? "" : faceLimiteInicial.toString();
	}

	@Transient
	public void setFaceLimiteInicialAsString(String faceLimiteInicial) {
		this.faceLimiteInicial = (StringUtils.isEmpty(faceLimiteInicial)) ? null : new Integer(faceLimiteInicial);
	}

	/**
	 * M�todos para manter o atributo regUrbLimInicial
	 */
	@Column(name="RU_LIMITE_INI", length=5)
	public Integer getRegUrbLimInicial() {
		return this.regUrbLimInicial;
	}

	public void setRegUrbLimInicial(Integer regUrbLimInicial) {
		this.regUrbLimInicial = regUrbLimInicial;
	}

	@Transient
	public String getRegUrbLimInicialAsString() {
		return (regUrbLimInicial == null) ? "" : regUrbLimInicial.toString();
	}

	@Transient
	public void setRegUrbLimInicialAsString(String regUrbLimInicial) {
		this.regUrbLimInicial = (StringUtils.isEmpty(regUrbLimInicial)) ? null : new Integer(regUrbLimInicial);
	}

	/**
	 * M�todos para manter o atributo regUrbDistInicial
	 */
	@Column(name="RU_DIST_INI", length=3)
	public Integer getRegUrbDistInicial() {
		return this.regUrbDistInicial;
	}

	public void setRegUrbDistInicial(Integer regUrbDistInicial) {
		this.regUrbDistInicial = regUrbDistInicial;
	}

	@Transient
	public String getRegUrbDistInicialAsString() {
		return (regUrbDistInicial == null) ? "" : regUrbDistInicial.toString();
	}

	@Transient
	public void setRegUrbDistInicialAsString(String regUrbDistInicial) {
		this.regUrbDistInicial = (StringUtils.isEmpty(regUrbDistInicial)) ? null : new Integer(regUrbDistInicial);
	}
	
	/**
	 * M�todos para manter o atributo faceLimiteFinal
	 */
	@Column(name="IMOVEL_FIN", length=5)
	public Integer getFaceLimiteFinal() {
		return this.faceLimiteFinal;
	}

	public void setFaceLimiteFinal(Integer faceLimiteFinal) {
		this.faceLimiteFinal = faceLimiteFinal;
	}

	@Transient
	public String getFaceLimiteFinalAsString() {
		return (faceLimiteFinal == null) ? "" : faceLimiteFinal.toString();
	}

	@Transient
	public void setFaceLimiteFinalAsString(String faceLimiteFinal) {
		this.faceLimiteFinal = (StringUtils.isEmpty(faceLimiteFinal)) ? null : new Integer(faceLimiteFinal);
	}

	@Override
	public String toString() {
        return new ToStringBuilder(this)
        .append( "codLogradouro", getCodLogradouro())
        .append( "faceLimiteInicial", getFaceLimiteInicial())
        .append( "faceLimiteFinal", getFaceLimiteFinal())
        .append( "regUrbLimInicial", getRegUrbLimInicial())
        .append( "regUrbDistInicial", getRegUrbDistInicial())
            .toString();
    }
    
	@Override
    public boolean equals(Object other) {
        if ( !(other instanceof RegimeUrbanisticoId) ) 
            return false;
        
        RegimeUrbanisticoId castOther = (RegimeUrbanisticoId) other;
        return new EqualsBuilder()
            .append(this.getCodLogradouro(), castOther.getCodLogradouro())
            .append(this.getFaceLimiteInicial(), castOther.getFaceLimiteInicial())
            .append(this.getFaceLimiteFinal(), castOther.getFaceLimiteFinal())
            .append(this.getRegUrbLimInicial(), castOther.getRegUrbLimInicial())
            .append(this.getRegUrbDistInicial(), castOther.getRegUrbDistInicial())
            .isEquals();
    }
 

	@Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append( getCodLogradouro())
        .append( getFaceLimiteInicial())
        .append( getFaceLimiteFinal())
        .append( getRegUrbLimInicial())
        .append( getRegUrbDistInicial())
            .toHashCode();
    }
	
}
