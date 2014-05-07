package br.com.procempa.dmweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.OptimisticLockType;

import br.com.procempa.dmweb.util.PropertyAsString;

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverrides( {
	@AttributeOverride(name="id", column=@Column(name="idFace"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmFace", schema="SQLDBA")
public class Face extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	private String codLogradouro = null;
	private Integer sequencia = new Integer(0);
	private BigDecimal distanciaSituacao = null;
	private Integer limiteFaceInicial = null;
	private Integer limiteFaceFinal = null;
	private Set<Observacao> observacaoList = new LinkedHashSet<Observacao>();
	private SituacaoLogradouro situacaoLogradouro = null;
	private Quarteirao quarteirao = null;
	private Alinhamento alinhamento = null;
	private Set<Regime> regimeList = new LinkedHashSet<Regime>();
	private String nomeLogradouro = null;
	private Boolean legado = Boolean.FALSE;
	private String prediosRelacionados = null;
	private String recuoViario = null;
	
	public Face() {
		super();
		alinhamento = new Alinhamento();
	}

	/**
	 * M�todos para manter o atributo codLogradouro
	 */
	@Column(nullable=false)
	public String getCodLogradouro() {
		return this.codLogradouro;
	}

	public void setCodLogradouro(String codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	/**
	 * M�todos para manter o atributo distanciaSituacao
	 */
	@Column(length=2, scale=2)
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
		return distanciaSituacao == null ? "" : PropertyAsString.prepareBigDecimalToString(distanciaSituacao);
	}

	@Transient
	public void setDistanciaSituacaoAsString(String distanciaSituacao) {
		this.distanciaSituacao = (StringUtils.isEmpty(distanciaSituacao)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(distanciaSituacao)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo limiteFaceInicial
	 */
	public Integer getLimiteFaceInicial() {
		return this.limiteFaceInicial;
	}

	public void setLimiteFaceInicial(Integer limiteFaceInicial) {
		this.limiteFaceInicial = limiteFaceInicial;
	}

	@Transient
	public String getLimiteFaceInicialAsString() {
		return (limiteFaceInicial == null) ? "" : limiteFaceInicial.toString();
	}

	@Transient
	public void setLimiteFaceInicialAsString(String limiteFaceInicial) {
		this.limiteFaceInicial = (StringUtils.isEmpty(limiteFaceInicial)) ? null : new Integer(limiteFaceInicial);
	}

	/**
	 * M�todos para manter o atributo limiteFaceFinal
	 */
	public Integer getLimiteFaceFinal() {
		return this.limiteFaceFinal;
	}

	public void setLimiteFaceFinal(Integer limiteFaceFinal) {
		this.limiteFaceFinal = limiteFaceFinal;
	}

	@Transient
	public String getLimiteFaceFinalAsString() {
		return (limiteFaceFinal == null) ? "" : limiteFaceFinal.toString();
	}

	@Transient
	public void setLimiteFaceFinalAsString(String limiteFaceFinal) {
		this.limiteFaceFinal = (StringUtils.isEmpty(limiteFaceFinal)) ? null : new Integer(limiteFaceFinal);
	}

	/**
	 * M�todos para manter o atributo observacaoList
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade={ CascadeType.MERGE })
	@JoinTable
			(name="DmObservacao_DmFace", 
			joinColumns={@JoinColumn(name="idFaceDO")}, 
			inverseJoinColumns={@JoinColumn(name="idObsDO")})
	public Set<Observacao> getObservacaoList() {
		return this.observacaoList;
	}

	public void setObservacaoList(Set<Observacao> observacaoList) {
		this.observacaoList = observacaoList;
	}

	/**
	 * M�todos para manter o atributo situacaoLogradouro
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idSituacaoLogradouro")
	public SituacaoLogradouro getSituacaoLogradouro() {
		return this.situacaoLogradouro;
	}

	public void setSituacaoLogradouro(SituacaoLogradouro situacaoLogradouro) {
		this.situacaoLogradouro = situacaoLogradouro;
	}

	/**
	 * M�todos para manter o atributo quarteirao
	 */
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="idQuarteirao")
	public Quarteirao getQuarteirao() {
		return this.quarteirao;
	}

	public void setQuarteirao(Quarteirao quarteirao) {
		this.quarteirao = quarteirao;
	}

	/**
	 * M�todos para manter o atributo alinhamento
	 */
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="imovelInicial", column=@Column(name="alinhamento_imovelInicial")),
		@AttributeOverride(name="imovelFinal", column=@Column(name="alinhamento_imovelFinal")),
		@AttributeOverride(name="alinhamento", column=@Column(name="alinhamento_alinhamento")),
		@AttributeOverride(name="legado", column=@Column(name="alinhamento_legado")),
		@AttributeOverride(name="textoLargura", column=@Column(name="alinhamento_textoLargura")),
		@AttributeOverride(name="larguraLogradouro", column=@Column(name="alinhamento_larguraLogradouro"))
	})
	@AssociationOverrides({
		@AssociationOverride(name="refAlinDO", joinColumns = { @JoinColumn(name="alinhamento_idRefAlinDO") } ),
		@AssociationOverride(name="pavPassDO", joinColumns = { @JoinColumn(name="alinhamento_idPavPassDO") } )
	})
	public Alinhamento getAlinhamento() {
		return this.alinhamento;
	}

	public void setAlinhamento(Alinhamento alinhamento) {
		this.alinhamento = alinhamento;
	}

	/**
	 * M�todos para manter o atributo regimeList
	 */
	@OneToMany(mappedBy="face", fetch=FetchType.EAGER, cascade={ CascadeType.ALL })
	public Set<Regime> getRegimeList() {
		return this.regimeList;
	}

	public void setRegimeList(Set<Regime> regimeList) {
		this.regimeList = regimeList;
	}

	@Transient
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	
	public void setNomeLogradouro(String nomeLogr) {
		this.nomeLogradouro = nomeLogr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((codLogradouro == null) ? 0 : codLogradouro.hashCode());
		result = prime * result
				+ ((limiteFaceFinal == null) ? 0 : limiteFaceFinal.hashCode());
		result = prime
				* result
				+ ((limiteFaceInicial == null) ? 0 : limiteFaceInicial
						.hashCode());
		result = prime * result
				+ ((quarteirao == null) ? 0 : quarteirao.hashCode());
		return result;
	}

 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Face other = (Face) obj;
		if (codLogradouro == null) {
			if (other.codLogradouro != null)
				return false;
		} else if (!codLogradouro.equals(other.codLogradouro))
			return false;
		if (limiteFaceFinal == null) {
			if (other.limiteFaceFinal != null)
				return false;
		} else if (!limiteFaceFinal.equals(other.limiteFaceFinal))
			return false;
		if (limiteFaceInicial == null) {
			if (other.limiteFaceInicial != null)
				return false;
		} else if (!limiteFaceInicial.equals(other.limiteFaceInicial))
			return false;
		if (quarteirao == null) {
			if (other.quarteirao != null)
				return false;
		} else if (!quarteirao.equals(other.quarteirao))
			return false;
		return true;
	}

	@Transient
	public String getFaceAsText() {
		 
		StringBuffer string = new StringBuffer();
		string.append("Logr  "+codLogradouro);
		string.append("-"+ getSequenciaAsString());
		string.append("  Endere�o "+nomeLogradouro);
		if (limiteFaceInicial != null) {
			string.append("  Lim Inicial "+ limiteFaceInicial);
		}
		if (limiteFaceFinal != null) {
			string.append("  Lim Final "+ limiteFaceFinal);
		}
		return  string.toString();
	}
	
	/**
	 * M�todos para manter o atributo sequencia
	 */
	@Column(nullable=false)
	public Integer getSequencia() {
		return this.sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	@Transient
	public String getSequenciaAsString() {
		return (sequencia == null) ? "" : sequencia.toString();
	}

	@Transient
	public void setSequenciaAsString(String sequencia) {
		this.sequencia = (StringUtils.isEmpty(sequencia)) ? null : new Integer(sequencia);
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

	public void setPrediosRelacionados(String prediosRelacionados) {
		this.prediosRelacionados = prediosRelacionados;
	}

	@Column(name="PREDIOS_RELACIONADOS")
	public String getPrediosRelacionados() {
		return prediosRelacionados;
	}

	@Column(name="RECUO_VIARIO")
	public String getRecuoViario() {
		return recuoViario;
	}

	public void setRecuoViario(String recuoViario) {
		this.recuoViario = recuoViario;
	}

}
