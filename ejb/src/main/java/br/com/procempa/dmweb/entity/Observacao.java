package br.com.procempa.dmweb.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
	@AttributeOverride(name="id", column=@Column(name="idObservacao"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmObservacao", schema="SQLDBA")

@NamedQueries({
	@NamedQuery(name= Observacao.FIND_OBSERVACAO, 
			query="select o from Observacao as o " +
					"where o.tipoObservacao = :tipoObservacao  " +
					"  and o.codigo = :codigo "),
	@NamedQuery(name= Observacao.LIST_OBSERVACAO_POR_TIPO, 
					query="select o from Observacao as o " +
							"where o.tipoObservacao = :tipoObservacao  and o.id <> 1100")					
	})

public class Observacao extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	
	public static final String FIND_OBSERVACAO = "observacao.findObservacao";	
	public static final String LIST_OBSERVACAO_POR_TIPO = "observacao.listObservacaoPorTipo";	
	public static final String ORGAORESPONSAVEL_UAI2 = "UAI2";
	public static final String ORGAORESPONSAVEL_UAI1 = "UAI1";
	public static final Integer AREA_OCUPACAO_RAREFEITA = 79;
	private Integer codigo = null;
	private Integer relevancia = null;
	private String orgaoResponsavel = null;
	private String descricao = null;
	private String titulo = null;
	private TipoObservacao tipoObservacao = null;
	private Boolean analise = false;
	

	/**
	 * M�todos para manter o atributo codigo
	 */
	@Column(nullable=false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Transient
	public String getCodigoAsString() {
		return (codigo == null) ? "" : codigo.toString();
	}

	@Transient
	public void setCodigoAsString(String codigo) {
		this.codigo = (StringUtils.isEmpty(codigo)) ? null : new Integer(codigo);
	}

	/**
	 * M�todos para manter o atributo relevancia
	 */
	@Column(nullable=false)
	public Integer getRelevancia() {
		return this.relevancia;
	}

	public void setRelevancia(Integer relevancia) {
		this.relevancia = relevancia;
	}

	@Transient
	public String getRelevanciaAsString() {
		return (relevancia == null) ? "" : relevancia.toString();
	}

	@Transient
	public void setRelevanciaAsString(String relevancia) {
		this.relevancia = (StringUtils.isEmpty(relevancia)) ? null : new Integer(relevancia);
	}

	/**
	 * M�todos para manter o atributo orgaoResponsavel
	 */
	@Column(length=004, nullable=false)
	public String getOrgaoResponsavel() {
		return this.orgaoResponsavel;
	}

	public void setOrgaoResponsavel(String orgaoResponsavel) {
		this.orgaoResponsavel = orgaoResponsavel;
	}

	/**
	 * M�todos para manter o atributo descricao
	 */
	@Lob
	@Column(columnDefinition="CLOB(2G) NOT LOGGED ")
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	@Column(length=100, nullable=false)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setTipoObservacao(TipoObservacao tipoObservacao) {
		this.tipoObservacao = tipoObservacao;
	}

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="idTipoObservacao")
	public TipoObservacao getTipoObservacao() {
		return tipoObservacao;
	}
	
	@Transient
	public String getObservacaoAsText() {
		
		return StringUtils.leftPad(getCodigoAsString().trim(), 3, "0") + " - "+ getTitulo();
	}
	@Transient
	public String getDescricaoAbrev() {
		
		return StringUtils.left(getDescricao(),500);
	}
	
	@Transient
	public String getObsId() {
		return "obs"+getIdAsString();
	}

	public Boolean getAnalise() {
		return analise;
	}
	
//	public String getAnaliseAsString() {
//		return analise.toString();
//	}
//	
//	public void setAnaliseAsString(String analise) {
//		this.analise = Boolean.valueOf(analise);
//	}

	public void setAnalise(Boolean analise) {
		this.analise = analise;
	}

}
