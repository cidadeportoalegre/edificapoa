package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OptimisticLockType;

import br.com.procempa.dmweb.util.PropertyAsString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "ZDTE100", schema = "SQLDBA")
@NamedQueries({
		@NamedQuery(name = DivisaoTerritorial.FIND_QUARTEIRAO, query = "select b from DivisaoTerritorial as b "
				+ "where b.divisaoTerritorialId.codLogradouro = :codLogradouro "
				+ "  and b.limiteInicial <= :limiteInicial "
				+ "  and b.limiteFinal >= :limiteFinal "
				+ "  and b.divisaoTerritorialId.macrozona >= :macrozona   "),
		@NamedQuery(name = DivisaoTerritorial.FACES_QUARTEIRAO, query = "select b from DivisaoTerritorial as b "
				+ "where b.divisaoTerritorialId.ueu = :ueu "
				+ "  and b.divisaoTerritorialId.quarteirao = :quarteirao "
				+ "  and b.divisaoTerritorialId.macrozona = :macrozona   ") })
public class DivisaoTerritorial implements Serializable {
	public static final long serialVersionUID = 1L;

	public static final String FIND_QUARTEIRAO = "divisaoTerritorial.find_quarteirao";

	public static final String FACES_QUARTEIRAO = "divisaoTerritorial.faces_quarteirao";

	private DivisaoTerritorialId divisaoTerritorialId = null;
	/**
	 * Número do primeiro imóvel da face
	 */
	private BigDecimal limiteInicial = null;
	/**
	 * 
	 * Número do último imóvel da face
	 */
	private BigDecimal limiteFinal = null;
	/**
	 * 
	 * Código do Logradouro Inicial da Face
	 */
	private BigDecimal logradouroInicial = null;
	private Integer sequenciaInicial = null;
	/**
	 * Código do Logradouro Final da Face
	 */
	private BigDecimal logradouroFinal = null;
	private Integer sequenciaFinal = null;
	private String numeracaoInvertida = null;
	private BigDecimal situacaoInicial = null;
	private BigDecimal situacaoFinal = null;
	/**
	 * 
	 * Limite da situação pelo Número do imóvel
	 */
	private BigDecimal limiteSituacao = null;
	/**
	 * 
	 * Limite da situação pela dist�ncia em metros do inicio da face
	 */
	private BigDecimal distanciaSituacao = null;

	private Integer impar = null;
	private boolean transienteJaAssociada = false;

	/**
	 * Métodos para manter o atributo divisaoTerritorialId
	 */
	@Id
	@AttributeOverrides({
			@AttributeOverride(name = "macrozona", column = @Column(name = "UTS")),
			@AttributeOverride(name = "ueu", column = @Column(name = "UTP")),
			@AttributeOverride(name = "quarteirao", column = @Column(name = "QTR")),
			@AttributeOverride(name = "codLogradouro", column = @Column(name = "CODLOGR")),
			@AttributeOverride(name = "sequencia", column = @Column(name = "SEQFACE")),
			@AttributeOverride(name = "dataFace", column = @Column(name = "dataFace")) })
	public DivisaoTerritorialId getDivisaoTerritorialId() {
		return this.divisaoTerritorialId;
	}

	public void setDivisaoTerritorialId(
			DivisaoTerritorialId divisaoTerritorialId) {
		this.divisaoTerritorialId = divisaoTerritorialId;
	}

	/**
	 * Métodos para manter o atributo limiteInicial
	 */
	@Column(name = "IMINIFAC", length = 2, scale = 2, columnDefinition = "decimal(5,0)")
	public BigDecimal getLimiteInicial() {
		return this.limiteInicial;
	}

	public void setLimiteInicial(BigDecimal limiteInicial) {
		if (limiteInicial != null) {
			limiteInicial.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.limiteInicial = limiteInicial;
	}

	@Transient
	public String getLimiteInicialAsString() {
		return limiteInicial == null ? "" : PropertyAsString
				.prepareBigDecimalToString(limiteInicial);
	}

	@Transient
	public void setLimiteInicialAsString(String limiteInicial) {
		this.limiteInicial = (StringUtils.isEmpty(limiteInicial)) ? null
				: new BigDecimal(
						PropertyAsString
								.prepareStringToBigDecimal(limiteInicial))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Métodos para manter o atributo limiteFinal
	 */
	@Column(name = "IMFIMFAC", length = 2, scale = 2, columnDefinition = "decimal(5,0)")
	public BigDecimal getLimiteFinal() {
		return this.limiteFinal;
	}

	public void setLimiteFinal(BigDecimal limiteFinal) {
		if (limiteFinal != null) {
			limiteFinal.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.limiteFinal = limiteFinal;
	}

	@Transient
	public String getLimiteFinalAsString() {
		return limiteFinal == null ? "" : PropertyAsString
				.prepareBigDecimalToString(limiteFinal);
	}

	@Transient
	public void setLimiteFinalAsString(String limiteFinal) {
		this.limiteFinal = (StringUtils.isEmpty(limiteFinal)) ? null
				: new BigDecimal(
						PropertyAsString.prepareStringToBigDecimal(limiteFinal))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Métodos para manter o atributo situacaoInicial
	 */
	@Column(name = "SITINFAC", length = 2, scale = 2, columnDefinition = "decimal(3,0)")
	public BigDecimal getSituacaoInicial() {
		return this.situacaoInicial;
	}

	public void setSituacaoInicial(BigDecimal situacaoInicial) {
		if (situacaoInicial != null) {
			situacaoInicial.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.situacaoInicial = situacaoInicial;
	}

	@Transient
	public String getSituacaoInicialAsString() {
		return situacaoInicial == null ? "" : PropertyAsString
				.prepareBigDecimalToString(situacaoInicial);
	}

	@Transient
	public void setSituacaoInicialAsString(String situacaoInicial) {
		this.situacaoInicial = (StringUtils.isEmpty(situacaoInicial)) ? null
				: new BigDecimal(
						PropertyAsString
								.prepareStringToBigDecimal(situacaoInicial))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Métodos para manter o atributo situacaoFinal
	 */
	@Column(name = "SITFIFAC", length = 2, scale = 2, columnDefinition = "decimal(3,0)")
	public BigDecimal getSituacaoFinal() {
		return this.situacaoFinal;
	}

	public void setSituacaoFinal(BigDecimal situacaoFinal) {
		if (situacaoFinal != null) {
			situacaoFinal.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.situacaoFinal = situacaoFinal;
	}

	@Transient
	public String getSituacaoFinalAsString() {
		return situacaoFinal == null ? "" : PropertyAsString
				.prepareBigDecimalToString(situacaoFinal);
	}

	@Transient
	public void setSituacaoFinalAsString(String situacaoFinal) {
		this.situacaoFinal = (StringUtils.isEmpty(situacaoFinal)) ? null
				: new BigDecimal(
						PropertyAsString
								.prepareStringToBigDecimal(situacaoFinal))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Métodos para manter o atributo limiteSituacao
	 */
	@Column(name = "LIMSIFAC", length = 2, scale = 2, columnDefinition = "decimal(5,0)")
	public BigDecimal getLimiteSituacao() {
		return this.limiteSituacao;
	}

	public void setLimiteSituacao(BigDecimal limiteSituacao) {
		if (limiteSituacao != null) {
			limiteSituacao.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.limiteSituacao = limiteSituacao;
	}

	@Transient
	public String getLimiteSituacaoAsString() {
		return limiteSituacao == null ? "" : PropertyAsString
				.prepareBigDecimalToString(limiteSituacao);
	}

	@Transient
	public void setLimiteSituacaoAsString(String limiteSituacao) {
		this.limiteSituacao = (StringUtils.isEmpty(limiteSituacao)) ? null
				: new BigDecimal(
						PropertyAsString
								.prepareStringToBigDecimal(limiteSituacao))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Métodos para manter o atributo distanciaSituacao
	 */
	@Column(name = "DISSIFAC", length = 2, scale = 2, columnDefinition = "decimal(3,0)")
	public BigDecimal getDistanciaSituacao() {
		return this.distanciaSituacao;
	}

	public void setDistanciaSituacao(BigDecimal distanciaSituacao) {
		if (distanciaSituacao != null) {
			distanciaSituacao.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.distanciaSituacao = distanciaSituacao;
	}

	@Transient
	public String getDistanciaSituacaoAsString() {
		return distanciaSituacao == null ? "" : PropertyAsString
				.prepareBigDecimalToString(distanciaSituacao);
	}

	@Transient
	public void setDistanciaSituacaoAsString(String distanciaSituacao) {
		this.distanciaSituacao = (StringUtils.isEmpty(distanciaSituacao)) ? null
				: new BigDecimal(
						PropertyAsString
								.prepareStringToBigDecimal(distanciaSituacao))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Column(insertable = false, updatable = false)
	@Formula("mod(int(IMINIFAC),2)")
	public Integer getImpar() {
		return impar;
	}

	public void setImpar(Integer impar) {
		this.impar = impar;
	}

	/**
	 * Métodos para manter o atributo logradouroInicial
	 */
	@Column(name = "LGINIFAC", length = 2, scale = 2, columnDefinition = "decimal(7,0)")
	public BigDecimal getLogradouroInicial() {
		return logradouroInicial;
	}

	public void setLogradouroInicial(BigDecimal logradouroInicial) {
		this.logradouroInicial = logradouroInicial;
	}

	@Transient
	public String getLogradouroInicialAsString() {
		return logradouroInicial == null ? "" : PropertyAsString
				.prepareBigDecimalToString(logradouroInicial);
	}

	@Transient
	public void setLogradouroInicialAsString(String logradouroInicial) {
		this.logradouroInicial = (StringUtils.isEmpty(logradouroInicial)) ? null
				: new BigDecimal(
						PropertyAsString
								.prepareStringToBigDecimal(logradouroInicial))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Métodos para manter o atributo logradouroFinal
	 */
	@Column(name = "LGFIMFAC", length = 2, scale = 2, columnDefinition = "decimal(7,0)")
	public BigDecimal getLogradouroFinal() {
		return logradouroFinal;
	}

	public void setLogradouroFinal(BigDecimal logradouroFinal) {
		this.logradouroFinal = logradouroFinal;
	}

	@Transient
	public String getLogradouroFinalAsString() {
		return logradouroFinal == null ? "" : PropertyAsString
				.prepareBigDecimalToString(logradouroFinal);
	}

	@Transient
	public void setLogradouroFinalAsString(String logradouroFinal) {
		this.logradouroFinal = (StringUtils.isEmpty(logradouroFinal)) ? null
				: new BigDecimal(
						PropertyAsString
								.prepareStringToBigDecimal(logradouroFinal))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Column(name = "ISENTFAC", columnDefinition = "char(1)")
	public String getNumeracaoInvertida() {
		return numeracaoInvertida;
	}

	public void setNumeracaoInvertida(String numeracaoInvertida) {
		this.numeracaoInvertida = numeracaoInvertida;
	}

	@Transient
	public boolean isTransienteJaAssociada() {
		return transienteJaAssociada;
	}

	public void setTransienteJaAssociada(boolean transienteJaAssociada) {
		this.transienteJaAssociada = transienteJaAssociada;
	}

	public void setSequenciaInicial(Integer sequenciaInicial) {
		this.sequenciaInicial = sequenciaInicial;
	}

	@Column(name = "SQINIFAC")
	public Integer getSequenciaInicial() {
		return sequenciaInicial;
	}

	public void setSequenciaFinal(Integer sequenciaFinal) {
		this.sequenciaFinal = sequenciaFinal;
	}

	@Column(name = "SQFIMFAC")
	public Integer getSequenciaFinal() {
		return sequenciaFinal;
	}

	private String logradouro = null;

	@Transient
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	private String situacaoLogradouro = null;

	@Transient
	public String getSituacaoLogradouro() {
		return situacaoLogradouro;
	}

	public void setSituacaoLogradouro(String situacaoLogradouro) {
		this.situacaoLogradouro = situacaoLogradouro;
	}

	private boolean faceSelecionada = true;

	@Transient
	public boolean getFaceSelecionada() {
		return faceSelecionada;
	}

	public void setFaceSelecionada(boolean faceSelecionada) {
		this.faceSelecionada = faceSelecionada;
	}

}
