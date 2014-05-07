package br.com.procempa.dmweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import br.com.procempa.dmweb.util.PropertyAsString;
 
@Embeddable
public class Alinhamento implements Serializable {
	public static final long serialVersionUID = 1L;
	public static final String TEXTO_LARGURA_LOGRADOURO_IGUAL_A = "IGUAL A";
	public static final String TEXTO_LARGURA_LOGRADOURO_MAIOR_QUE = "MAIOR QUE";
	private Integer imovelInicial = null;
	private Integer imovelFinal = null;
	private BigDecimal alinhamento = null;
	private String textoLargura = null;
	private BigDecimal larguraLogradouro = null;
	private ReferenciaAlinhamento refAlinDO = null;
	private PavimentacaoPasseio pavPassDO = null;
	private Boolean legado = Boolean.FALSE;

	/**
	 * M�todos para manter o atributo imovelInicial
	 */
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
	public String getAlinhamentoAsText(){
		return getAlinhamentoAsString()+" m";
	}
	
	@Transient
	public String getLarguraLogradouroAsText(){
		return getLarguraLogradouroAsString()+" m";
	}
	

	@Transient
	public void setImovelInicialAsString(String imovelInicial) {
		this.imovelInicial = (StringUtils.isEmpty(imovelInicial)) ? null : new Integer(imovelInicial);
	}

	/**
	 * M�todos para manter o atributo imovelFinal
	 */
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
	 * M�todos para manter o atributo alinhamento
	 */
	@Column(length=2, scale=2)
	public BigDecimal getAlinhamento() {
		return this.alinhamento;
	}

	public void setAlinhamento(BigDecimal alinhamento) {
		if (alinhamento != null) {
			alinhamento.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.alinhamento = alinhamento;
	}

	@Transient
	public String getAlinhamentoAsString() {
		return alinhamento == null ? "" : PropertyAsString.prepareBigDecimalToString(alinhamento);
	}

	@Transient
	public void setAlinhamentoAsString(String alinhamento) {
		this.alinhamento = (StringUtils.isEmpty(alinhamento)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(alinhamento)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo larguraLogradouro
	 */
	@Column(length=2, scale=2)
	public BigDecimal getLarguraLogradouro() {
		return this.larguraLogradouro;
	}

	public void setLarguraLogradouro(BigDecimal larguraLogradouro) {
		if (larguraLogradouro != null) {
			larguraLogradouro.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.larguraLogradouro = larguraLogradouro;
	}

	public Boolean getLegado() {
		return legado;
	}

	public void setLegado(Boolean legado) {
		this.legado = legado;
	}
	
	@Transient
	public String getLarguraLogradouroAsString() {
		return larguraLogradouro == null ? "" : PropertyAsString.prepareBigDecimalToString(larguraLogradouro);
	}

	@Transient
	public void setLarguraLogradouroAsString(String larguraLogradouro) {
		this.larguraLogradouro = (StringUtils.isBlank(larguraLogradouro)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(larguraLogradouro)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo refAlinDO
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idrefAlinDO_ReferenciaAlinhamento")
	public ReferenciaAlinhamento getRefAlinDO() {
		return this.refAlinDO;
	}

	public void setRefAlinDO(ReferenciaAlinhamento refAlinDO) {
		this.refAlinDO = refAlinDO;
	}

	/**
	 * M�todos para manter o atributo pavPassDO
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idpavPassDO_PavimentacaoPasseio")
	public PavimentacaoPasseio getPavPassDO() {
		return this.pavPassDO;
	}

	public void setPavPassDO(PavimentacaoPasseio pavPassDO) {
		this.pavPassDO = pavPassDO;
	}

	/**
	 * M�todos para manter o atributo textoLargura
	 */
	@Column(length=2, scale=2, name="TEXTOLARGURA")
	public String getTextoLargura() {
		return textoLargura;
	}

	public void setTextoLargura(String textoLargura) {
		this.textoLargura = textoLargura;
	}
}
