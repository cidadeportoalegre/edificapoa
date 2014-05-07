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
public class CirculacaoId implements Serializable {
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
	private Integer imovelInicial = null;
	/**
	 * 
	 * N�mero do �ltimo im�vel da face
	 */
	private Integer imovelFinal = null;
	/**
	 * 
	 * Limite inicial do alinhamento por n�mero de im�vel
	 */
	private Integer alinhamInicial = null;
	/**
	 * 
	 * Limite final do alinhamento por n�mero de im�vel
	 */
	private Integer alinhamFinal = null;

	/**
	 * M�todos para manter o atributo codLogradouro
	 */
	@Column(name="codlogr", length=7)
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
	 * M�todos para manter o atributo imovelInicial
	 */
	@Column(name="IMV_INI", length=5)
	public Integer getImovelInicial() {
		return this.imovelInicial;
	}

	public void setImovelInicial(Integer imovelInicial) {
		this.imovelInicial = imovelInicial;
	}

	@Transient
	public String getImovelInicialAsString() {
		return (imovelInicial == null) ? "" : imovelInicial.toString();
	}

	@Transient
	public void setImovelInicialAsString(String imovelInicial) {
		this.imovelInicial = (StringUtils.isEmpty(imovelInicial)) ? null : new Integer(imovelInicial);
	}

	/**
	 * M�todos para manter o atributo imovelFinal
	 */
	@Column(name="IMV_FIN", length=5)
	public Integer getImovelFinal() {
		return this.imovelFinal;
	}

	public void setImovelFinal(Integer imovelFinal) {
		this.imovelFinal = imovelFinal;
	}

	@Transient
	public String getImovelFinalAsString() {
		return (imovelFinal == null) ? "" : imovelFinal.toString();
	}

	@Transient
	public void setImovelFinalAsString(String imovelFinal) {
		this.imovelFinal = (StringUtils.isEmpty(imovelFinal)) ? null : new Integer(imovelFinal);
	}

	/**
	 * M�todos para manter o atributo alinhamInicial
	 */
	@Column(name="ALI_INI", length=5)
	public Integer getAlinhamInicial() {
		return this.alinhamInicial;
	}

	public void setAlinhamInicial(Integer alinhamInicial) {
		this.alinhamInicial = alinhamInicial;
	}

	@Transient
	public String getAlinhamInicialAsString() {
		return (alinhamInicial == null) ? "" : alinhamInicial.toString();
	}

	@Transient
	public void setAlinhamInicialAsString(String alinhamInicial) {
		this.alinhamInicial = (StringUtils.isEmpty(alinhamInicial)) ? null : new Integer(alinhamInicial);
	}

	/**
	 * M�todos para manter o atributo alinhamFinal
	 */
	@Column(name="ALI_FIN", length=5)
	public Integer getAlinhamFinal() {
		return this.alinhamFinal;
	}

	public void setAlinhamFinal(Integer alinhamFinal) {
		this.alinhamFinal = alinhamFinal;
	}

	@Transient
	public String getAlinhamFinalAsString() {
		return (alinhamFinal == null) ? "" : alinhamFinal.toString();
	}

	@Transient
	public void setAlinhamFinalAsString(String alinhamFinal) {
		this.alinhamFinal = (StringUtils.isEmpty(alinhamFinal)) ? null : new Integer(alinhamFinal);
	}
	
	@Override
	public String toString() {
        return new ToStringBuilder(this)
        .append( "codLogradouro", getCodLogradouro())
        .append( "imovelInicial", getImovelInicial())
        .append( "imovelFinal", getImovelFinal())
        .append( "alinhamentoInicial", getAlinhamInicial())
        .append( "alinhamentoFinal", getAlinhamFinal())
            .toString();
    }
    
	@Override
    public boolean equals(Object other) {
        if ( !(other instanceof CirculacaoId) ) 
            return false;
        
        CirculacaoId castOther = (CirculacaoId) other;
        return new EqualsBuilder()
            .append(this.getCodLogradouro(), castOther.getCodLogradouro())
            .append(this.getImovelInicial(), castOther.getImovelInicial())
            .append(this.getImovelFinal(), castOther.getImovelFinal())
            .append(this.getAlinhamInicial(), castOther.getAlinhamInicial())
            .append(this.getAlinhamFinal(), castOther.getAlinhamFinal())
            .isEquals();
    }
 

	@Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(getCodLogradouro())
        .append(getImovelInicial())
        .append( getImovelFinal())
        .append( getAlinhamInicial())
        .append( getAlinhamFinal())
            .toHashCode();
    }
}
