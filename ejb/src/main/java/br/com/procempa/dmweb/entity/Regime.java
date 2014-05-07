package br.com.procempa.dmweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@AttributeOverride(name="id", column=@Column(name="idRegime"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmRegime", schema="SQLDBA")
public class Regime extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	private String subUnidade = null;
	private Integer limiteInicial = null;
	private Integer limiteFinal = null;
	private Integer densidade = null;
	private String indiceAproveitamento = null;
	private String volumetria = null;
	private String grupAtividade = null;
	private Set<Observacao> observacaoList = new LinkedHashSet<Observacao>();
	private Face face = null;
	private Integer distInicial = 0;
	private Integer distFinal = 0;
	private Boolean legado = Boolean.FALSE;

	/**
	 * M�todos para manter o atributo subUnidade
	 */
	@Column(nullable=false)
	public String getSubUnidade() {
		return this.subUnidade;
	}

	public void setSubUnidade(String subUnidade) {
		this.subUnidade = subUnidade;
	}

	/**
	 * M�todos para manter o atributo limiteInicial
	 */
	@Column(nullable=true)
	public Integer getLimiteInicial() {
		return this.limiteInicial;
	}

	public void setLimiteInicial(Integer limiteInicial) {
		this.limiteInicial = limiteInicial;
	}

	@Transient
	public String getLimiteInicialAsString() {
		return (limiteInicial == null) ? "" : limiteInicial.toString();
	}

	@Transient
	public void setLimiteInicialAsString(String limiteInicial) {
		this.limiteInicial = (StringUtils.isEmpty(limiteInicial)) ? null : new Integer(limiteInicial);
	}

	/**
	 * M�todos para manter o atributo limiteFinal
	 */
	@Column(nullable=true)
	public Integer getLimiteFinal() {
		return this.limiteFinal;
	}

	public void setLimiteFinal(Integer limiteFinal) {
		this.limiteFinal = limiteFinal;
	}

	@Transient
	public String getLimiteFinalAsString() {
		return (limiteFinal == null) ? "" : limiteFinal.toString();
	}

	@Transient
	public void setLimiteFinalAsString(String limiteFinal) {
		this.limiteFinal = (StringUtils.isEmpty(limiteFinal)) ? null : new Integer(limiteFinal);
	}

	/**
	 * M�todos para manter o atributo densidade
	 */
	@Column(length=3, nullable=false)
	public Integer getDensidade() {
		return this.densidade;
	}

	public void setDensidade(Integer densidade) {
		this.densidade = densidade;
	}

	@Transient
	public String getDensidadeAsString() {
		return (densidade == null) ? "" : ""+densidade.intValue();
	}

	@Transient
	public void setDensidadeAsString(String densidade) {
		this.densidade = (StringUtils.isBlank( densidade )) ? null : new Integer( densidade );
	}

	/**
	 * M�todos para manter o atributo indiceAproveitamento
	 */
	@Column(length=3, nullable=false)
	public String getIndiceAproveitamento() {
		return this.indiceAproveitamento;
	}

	public void setIndiceAproveitamento(String indiceAproveitamento) {
		this.indiceAproveitamento = indiceAproveitamento;
	}

	/**
	 * M�todos para manter o atributo volumetria
	 */
	@Column(length=4, nullable=false)
	public String getVolumetria() {
		return this.volumetria;
	}

	public void setVolumetria(String volumetria) {
		this.volumetria = volumetria;
	}

	/**
	 * M�todos para manter o atributo grupAtividade
	 */
	@Column(length=5, nullable=false)
	public String getGrupAtividade() {
		return this.grupAtividade;
	}

	public void setGrupAtividade(String grupAtividade) {
		this.grupAtividade = grupAtividade;
	}

	/**
	 * M�todos para manter o atributo observacaoList
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinTable(name="DmObservacao_DmRegime", joinColumns={@JoinColumn(name="idRegimeDO")}, inverseJoinColumns={@JoinColumn(name="idObsDO")})
	public Set<Observacao> getObservacaoList() {
		return this.observacaoList;
	}

	public void setObservacaoList(Set<Observacao> observacaoList) {
		this.observacaoList = observacaoList;
	}

	
	/**
	 * M�todos para manter o atributo distInicial
	 */
	@Column(length=3)
	public Integer getDistInicial() {
		return this.distInicial;
	}

	public void setDistInicial(Integer distInicial) {
		this.distInicial = distInicial;
	}

	@Transient
	public String getDistInicialAsString() {
		return (distInicial == null) ? "0" : distInicial.toString();
	}

	@Transient
	public void setDistInicialAsString(String distInicial) {
		this.distInicial = (StringUtils.isEmpty(distInicial)) ? null : new Integer(distInicial);
	}
	
	/**
	 * M�todos para manter o atributo distFinal
	 */
	@Column(length=3)
	public Integer getDistFinal() {
		return this.distFinal;
	}
	
	public void setDistFinal(Integer distFinal) {
		this.distFinal = distFinal;
	}
	
	@Transient
	public String getDistFinalAsString() {
		return (distFinal == null) ? "0" : distFinal.toString();
	}
	
	@Transient
	public void setDistFinalAsString(String distFinal) {
		this.distFinal = (StringUtils.isEmpty(distFinal)) ? null : new Integer(distFinal);
	}
	
	/**
	 * M�todos para manter o atributo face
	 */
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="idFace")
	public Face getFace() {
		return this.face;
	}

	public void setFace(Face face) {
		this.face = face;
	}
	
	@Transient
	public String getRegimeAsText() {
		
		String aux = getGrupAtividade().replace(',', '.').replace(".00", "");
		BigDecimal b = new BigDecimal(aux);
		if(aux.endsWith("0")){
			if(aux.length()>2){
				b = b.stripTrailingZeros();
			}
		}
		
		return "Subunidade "+getSubUnidade()+" Dens. " + getDensidadeAsString()+
		       " Ativ. "+b+" Aprov. " + getIndiceAproveitamento()+
		       " Vol. " + getVolumetria()+
		       (getLimiteInicial()== null ? "" : getLimiteInicial() == 0 ? "" : " Lim. Inicial " + getLimiteInicialAsString())+
		       (getDistInicial() == null ? "" : getDistInicial() == 0 ? "" : " Dist. Inicial " + getDistInicialAsString())+
		       (getLimiteFinal() == null ? "" : getLimiteFinal() == 0 ? "" : " Lim. Final " + getLimiteFinalAsString())+		       
		       (getDistFinal() == null ? "" : getDistFinal() == 0 ? "" : " Dist. Final " + getDistFinalAsString());
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

}
