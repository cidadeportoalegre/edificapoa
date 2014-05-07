package br.com.procempa.dmweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	@AttributeOverride(name="id", column=@Column(name="idDocumento"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmDocumento", schema="SQLDBA")
public class Documento extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	public static final String SITUACAO_EMITIDA = "Emitida";
	public static final String SITUACAO_ATUALIZADA = "Atualizada";
	public static final String SITUACAO_EM_ANALISE = "Em an�lise";
	public static final String SITUACAO_EM_ATUALIZACAO = "Em atualiza��o";
	public static final String ESGOTOPLUVIAL_NA_FRENTE = "Na frente";
	public static final String ESGOTOPLUVIAL_NA_FRENTE_LADO_OPOSTO = "Na frente lado oposto";
	public static final String ESGOTOPLUVIAL_FAIXA_NAO_EDIFICAVEL = "Faixa n�o edific�vel";
	public static final String ESGOTOPLUVIAL_NAO = "Nao";
	public static final String ESGOTOPLUVIAL_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String ESGOTOCLOACAL_NA_FRENTE = "Na frente";
	public static final String ESGOTOCLOACAL_NA_FRENTE_LADO_OPOSTO = "Na frente lado oposto";
	public static final String ESGOTOCLOACAL_NAO = "Nao";
	public static final String ESGOTOCLOACAL_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String ABASTECIMENTOAGUA_NA_FRENTE = "Na frente";
	public static final String ABASTECIMENTOAGUA_NA_FRENTE_LADO_OPOSTO = "Na frente lado oposto";
	public static final String ABASTECIMENTOAGUA_NAO = "Nao";
	public static final String ABASTECIMENTOAGUA_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String VALESPROTEGIDOS_NAO = "Nao";
	public static final String VALESPROTEGIDOS_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String VIAPROJETADA_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String VIAPROJETADA_NAO = "Nao";
	public static final String PASSAGEMPEDESTRE_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String PASSAGEMPEDESTRE_NAO = "Nao";
	public static final String TRACADOOUTROS_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String TRACADOOUTROS_NAO = "Nao";
	public static final String AREAVERDE_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String AREAVERDE_NAO = "Nao";
	public static final String ESCOLA_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String ESCOLA_NAO = "Nao";
	public static final String CEEE_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String CEEE_NAO = "Nao";
	public static final String COMUNITARIOSOUTROS_CONFORME_GRAFICADO = "Conforme anexo";
	public static final String COMUNITARIOSOUTROS_NAO = "Nao";
	public static final String TIPODOCUMENTO_DM = "DM";
	public static final String TIPODOCUMENTO_BOLETIM_ORGAOS_PUBLICOS = "Boletim �rg�os P�blicos";
	public static final String TIPODOCUMENTO_BOLETIM_COMISSOES = "Boletim Comiss�es";
	public static final String TIPODOCUMENTO_CERTIDAO = "Certid�o";
	public static final String TIPODOCUMENTO_BOLETIM_PDDUA = "Boletim PDDUA";
	public static final String TIPODOCUMENTO_DM_ANTIGA = "DM Antiga";

	private String tipoDocumento = null;
	private String expedienteUnico = null;
	private String expedienteSimplificado = null;
	private String areaPrivativa = null;
	private String situacao = null;
	private Date dataEmissao = null;
	private Date dataAtualizacao = null;
	private String viaProjetada = null;
	private String passagemPedestre = null;
	private String tracadoOutros = null;
	private String tracadoOutrosDescricao = null;
	private String areaVerde = null;
	private String escola = null;
	private String ceee = null;
	private String comunitariosOutros = null;
	private String comunitariosOutrosDescricao = null;
	private String esgotoPluvial  = null;
	private String esgotoCloacal = null;
	private String abastecimentoAgua = null;
	private String valesProtegidos = null;
	private BigDecimal crt = null;
	private BigDecimal dgceaAltitude = null;
	private String textoLivre = null;
	private String textoLivreAtualizacao = null;
	private Set<CurvaNivelRuido> curvaNivelRuidoList = new LinkedHashSet<CurvaNivelRuido>();
	private Set<Observacao> obsCondicionantesList = new LinkedHashSet<Observacao>();
	private Set<Observacao> obsGeraisList = new LinkedHashSet<Observacao>();
	private Set<RegistroImovel> registroImovelList = new LinkedHashSet<RegistroImovel>();
	private Set<Quarteirao> quarteiraoList = new LinkedHashSet<Quarteirao>();
	private String codLogradouro;
	private Integer nroImovel = null;
	private String nomeEmitente = null;
	
	private Boolean legado = Boolean.FALSE;
	
	@Transient public String validar = null;
	
	private Set<Anexo> anexoList = new LinkedHashSet<Anexo>();
	
	public Documento() {
		super();
	}
	
	public Documento( String expedienteUnico, String areaPrivativa) {
		super();
		this.tipoDocumento = TIPODOCUMENTO_DM;
		this.expedienteUnico = expedienteUnico;
		this.areaPrivativa = areaPrivativa;
		this.situacao = SITUACAO_EM_ANALISE;
	}

	/**
	 * M�todos para manter o atributo tipoDocumento
	 */
	@Column(nullable=false)
	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * M�todos para manter o atributo expedienteUnico
	 */
	@Column(length=12)
	public String getExpedienteUnico() {
		return this.expedienteUnico;
	}

	public void setExpedienteUnico(String expedienteUnico) {
		this.expedienteUnico = expedienteUnico;
	}

	/**
	 * M�todos para manter o atributo expedienteSimplificado
	 */
	@Column(length=12, nullable=true)
	public String getExpedienteSimplificado() {
		return this.expedienteSimplificado;
	}
	
	public void setExpedienteSimplificado(String expedienteSimplificado) {
		this.expedienteSimplificado = expedienteSimplificado;
	}
	
	/**
	 * M�todos para manter o atributo areaPrivativa
	 */
	@Column(length=5)
	public String getAreaPrivativa() {
		return this.areaPrivativa;
	}

	public void setAreaPrivativa(String areaPrivativa) {
		this.areaPrivativa = areaPrivativa;
	}

	/**
	 * M�todos para manter o atributo situacao
	 */
	@Column(nullable=false)
	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * M�todos para manter o atributo dataEmissao
	 */
	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	@Transient
	public String getDataEmissaoAsString() {
		if(getTipoDocumento().equals(TIPODOCUMENTO_DM_ANTIGA))
			return (this.dataEmissao == null) ? null : new SimpleDateFormat("dd/MM/yyyy").format(this.dataEmissao);
		else
			return (this.dataEmissao == null) ? null : new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.dataEmissao); 
	}

	@Transient
	public void setDataEmissaoAsString(String dataEmissao) throws ParseException {
		this.dataEmissao = PropertyAsString.stringAsDate(dataEmissao);
	}

	/**
	 * M�todos para manter o atributo dataAtualizacao
	 */
	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Transient
	public String getDataAtualizacaoAsString() {
		return (this.dataAtualizacao == null) ? null : new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.dataAtualizacao); 
	}

	@Transient
	public void setDataAtualizacaoAsString(String dataAtualizacao) throws ParseException {
		this.dataAtualizacao = PropertyAsString.stringAsDate(dataAtualizacao);
	}

	/**
	 * M�todos para manter o atributo viaProjetada
	 */
	@Column(length=18)
	public String getViaProjetada() {
		return this.viaProjetada;
	}

	public void setViaProjetada(String viaProjetada) {
		this.viaProjetada = viaProjetada;
	}

	/**
	 * M�todos para manter o atributo passagemPedestre
	 */
	@Column(length=18)
	public String getPassagemPedestre() {
		return this.passagemPedestre;
	}

	public void setPassagemPedestre(String passagemPedestre) {
		this.passagemPedestre = passagemPedestre;
	}

	/**
	 * M�todos para manter o atributo tracadoOutros
	 */
	@Column(length=18)
	public String getTracadoOutros() {
		return this.tracadoOutros;
	}

	public void setTracadoOutros(String tracadoOutros) {
		this.tracadoOutros = tracadoOutros;
	}

	/**
	 * M�todos para manter o atributo tracadoOutrosDescricao
	 */
	@Column(length=30)
	public String getTracadoOutrosDescricao() {
		return this.tracadoOutrosDescricao;
	}

	public void setTracadoOutrosDescricao(String tracadoOutrosDescricao) {
		this.tracadoOutrosDescricao = tracadoOutrosDescricao;
	}

	/**
	 * M�todos para manter o atributo areaVerde
	 */
	@Column(length=18)
	public String getAreaVerde() {
		return this.areaVerde;
	}

	public void setAreaVerde(String areaVerde) {
		this.areaVerde = areaVerde;
	}

	/**
	 * M�todos para manter o atributo escola
	 */
	@Column(length=18)
	public String getEscola() {
		return this.escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	/**
	 * M�todos para manter o atributo ceee
	 */
	@Column(length=18)
	public String getCeee() {
		return this.ceee;
	}

	public void setCeee(String ceee) {
		this.ceee = ceee;
	}

	/**
	 * M�todos para manter o atributo comunitariosOutros
	 */
	@Column(length=18)
	public String getComunitariosOutros() {
		return this.comunitariosOutros;
	}

	public void setComunitariosOutros(String comunitariosOutros) {
		this.comunitariosOutros = comunitariosOutros;
	}

	/**
	 * M�todos para manter o atributo comunitariosOutrosDescricao
	 */
	@Column(length=30)
	public String getComunitariosOutrosDescricao() {
		return this.comunitariosOutrosDescricao;
	}

	public void setComunitariosOutrosDescricao(String comunitariosOutrosDescricao) {
		this.comunitariosOutrosDescricao = comunitariosOutrosDescricao;
	}

	/**
	 * M�todos para manter o atributo esgotoPluvial 
	 */
	@Column(length=25)
	public String getEsgotoPluvial () {
		return this.esgotoPluvial ;
	}

	public void setEsgotoPluvial (String esgotoPluvial ) {
		this.esgotoPluvial  = esgotoPluvial ;
	}

	/**
	 * M�todos para manter o atributo esgotoCloacal
	 */
	@Column(length=25)
	public String getEsgotoCloacal() {
		return this.esgotoCloacal;
	}

	public void setEsgotoCloacal(String esgotoCloacal) {
		this.esgotoCloacal = esgotoCloacal;
	}

	/**
	 * M�todos para manter o atributo abastecimentoAgua
	 */
	@Column(length=25)
	public String getAbastecimentoAgua() {
		return this.abastecimentoAgua;
	}

	public void setAbastecimentoAgua(String abastecimentoAgua) {
		this.abastecimentoAgua = abastecimentoAgua;
	}

	/**
	 * M�todos para manter o atributo valesProtegidos
	 */
	@Column(length=18)
	public String getValesProtegidos() {
		return this.valesProtegidos;
	}

	public void setValesProtegidos(String valesProtegidos) {
		this.valesProtegidos = valesProtegidos;
	}

	/**
	 * M�todos para manter o atributo crt
	 */
	@Column(length=2, scale=2)
	public BigDecimal getCrt() {
		return this.crt;
	}

	public void setCrt(BigDecimal crt) {
		if (crt != null) {
			crt.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.crt = crt;
	}

	@Transient
	public String getCrtAsString() {
		return crt == null ? "" : PropertyAsString.prepareBigDecimalToString(crt);
	}

	@Transient
	public void setCrtAsString(String crt) {
		this.crt = (StringUtils.isEmpty(crt)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(crt)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo dgceaAltitude
	 */
	@Column(length=4, scale=2)
	public BigDecimal getDgceaAltitude() {
		return this.dgceaAltitude;
	}

	public void setDgceaAltitude(BigDecimal dgceaAltitude) {
		if (dgceaAltitude != null) {
			dgceaAltitude.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.dgceaAltitude = dgceaAltitude;
	}

	@Transient
	public String getDgceaAltitudeAsString() {
		return dgceaAltitude == null ? "" : PropertyAsString.prepareBigDecimalToString(dgceaAltitude);
	}

	@Transient
	public void setDgceaAltitudeAsString(String dgceaAltitude) {
		this.dgceaAltitude = (StringUtils.isEmpty(dgceaAltitude)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(dgceaAltitude)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo textoLivre
	 */
	@Column(length=1500)
	public String getTextoLivre() {
		return this.textoLivre;
	}

	public void setTextoLivre(String textoLivre) {
		this.textoLivre = textoLivre;
	}

	/**
	 * M�todos para manter o atributo textoLivreAtualizacao
	 */
	@Column(length=250)
	public String getTextoLivreAtualizacao() {
		return this.textoLivreAtualizacao;
	}

	public void setTextoLivreAtualizacao(String textoLivreAtualizacao) {
		this.textoLivreAtualizacao = textoLivreAtualizacao;
	}

	/**
	 * M�todos para manter o atributo curvaNivelRuidoList
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinTable(
			name="DmCurvaNivelRuido_DmDocumento", 
			joinColumns={@JoinColumn(name="idDocumentoDO")}, 
			inverseJoinColumns={@JoinColumn(name="idCurvaNivelRuidoDO")})
	public Set<CurvaNivelRuido> getCurvaNivelRuidoList() {
		return this.curvaNivelRuidoList;
	}

	public void setCurvaNivelRuidoList(Set<CurvaNivelRuido> curvaNivelRuidoList) {
		this.curvaNivelRuidoList = curvaNivelRuidoList;
	}

	/**
	 * M�todos para manter o atributo obsCondicionantesList
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinTable(
			name="DmObservacao_DmDocumento_Condicionantes", 
			joinColumns={@JoinColumn(name="idDocumentoDO")}, 
			inverseJoinColumns={@JoinColumn(name="idObsDO")})
			public Set<Observacao> getObsCondicionantesList() {
		return this.obsCondicionantesList;
	}
	
	public void setObsCondicionantesList(Set<Observacao> obsCondicionantesList) {
		this.obsCondicionantesList = obsCondicionantesList;
	}
	
	/**
	 * M�todos para manter o atributo obsGeraisList
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinTable(
			name="DmObservacao_DmDocumento", 
			joinColumns={@JoinColumn(name="idDocumentoDO")}, 
			inverseJoinColumns={@JoinColumn(name="idObsDO")})
	public Set<Observacao> getObsGeraisList() {
		return this.obsGeraisList;
	}

	public void setObsGeraisList(Set<Observacao> obsGeraisList) {
		this.obsGeraisList = obsGeraisList;
	}

	/**
	 * M�todos para manter o atributo registroImovelList
	 */
	@OneToMany(mappedBy="documento", fetch=FetchType.LAZY, cascade={ CascadeType.ALL } )
	public Set<RegistroImovel> getRegistroImovelList() {
		return this.registroImovelList;
	}

	public void setRegistroImovelList(Set<RegistroImovel> registroImovelList) {
		this.registroImovelList = registroImovelList;
	}

	/**
	 * M�todos para manter o atributo quarteiraoList
	 */
	@OneToMany(mappedBy="documento", fetch=FetchType.LAZY, cascade={ CascadeType.ALL })
	public Set<Quarteirao> getQuarteiraoList() {
		return this.quarteiraoList;
	}

	public void setQuarteiraoList(Set<Quarteirao> quarteiraoList) {
		this.quarteiraoList = quarteiraoList;
	}

	@OneToMany(mappedBy="documento", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<Anexo> getAnexoList() {
		return this.anexoList;
	}
	
	public void setAnexoList(Set<Anexo> anexoList) {
		this.anexoList = anexoList;
	}
	
	public void setCodLogradouro(String codLogradouro) {
		this.codLogradouro = codLogradouro;
		
	}
	public String getCodLogradouro(){
		return codLogradouro ;
		
	}

	/**
	 * M�todos para manter o atributo nroImovel
	 */
	public Integer getNroImovel() {
		return this.nroImovel;
	}

	public void setNroImovel(Integer nroImovel) {
		this.nroImovel = nroImovel;
	}

	@Transient
	public String getNroImovelAsString() {
		return (nroImovel == null) ? "" : nroImovel.toString();
	}

	@Transient
	public void setNroImovelAsString(String nroImovel) {
		this.nroImovel = (StringUtils.isEmpty(nroImovel)) ? null : new Integer(nroImovel);
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

	@Column(length=60)
	public String getNomeEmitente() {
		return nomeEmitente;
	}

	public void setNomeEmitente(String nomeEmitente) {
		this.nomeEmitente = nomeEmitente;
	}


}
