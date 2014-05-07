package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;

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
import org.hibernate.annotations.OptimisticLockType;

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="ZCTM0020", schema="SQLDBA")
@NamedQueries({
	@NamedQuery(name= Circulacao.FIND_CIRCULACAO, 
	query="select C from Circulacao as c " +
					"where c.circulacaoId.codLogradouro = :codLogradouro " +
					"  and c.circulacaoId.imovelInicial = :imovelInicial " +
					"  and c.circulacaoId.imovelFinal = :imovelFinal   " )					
	})

public class Circulacao implements Serializable {
	public static final long serialVersionUID = 1L;
	
	public static final String FIND_CIRCULACAO = "circulacao.findCirculacao";
	
	private CirculacaoId circulacaoId = null;
	private Integer macrozona = null;
	private Integer ueu = null;
	private Integer quarteirao = null;
	private String alinhamento = null;
	private String referencia = null;
	private String larguraLogradouro = null;
	private String larguraLogradouroPrev = null;
	private Integer observacao1 = null;
	private Integer observacao2 = null;
	private Integer observacao3 = null;
	private Integer dist1 = null;
	private String logr1 = null;

	private Integer codigo = null;
	
	private String recuoViario = null;
	
	/**
	 * M�todos para manter o atributo circulacaoId
	 */
	@Id
	@AttributeOverrides({
		@AttributeOverride(name="codLogradouro", column=@Column(name="codlogr")),
		@AttributeOverride(name="imovelInicial", column=@Column(name="IMV_INI")),
		@AttributeOverride(name="imovelFinal", column=@Column(name="IMV_FIN")),
		@AttributeOverride(name="alinhamInicial", column=@Column(name="ALI_INI")),
		@AttributeOverride(name="alinhamFinal", column=@Column(name="ALI_FIN"))
	})
	public CirculacaoId getCirculacaoId() {
		return this.circulacaoId;
	}

	public void setCirculacaoId(CirculacaoId circulacaoId) {
		this.circulacaoId = circulacaoId;
	}

	/**
	 * M�todos para manter o atributo macrozona
	 */
	@Column(name="MZ", length=3)
	public Integer getMacrozona() {
		return this.macrozona;
	}

	public void setMacrozona(Integer macrozona) {
		this.macrozona = macrozona;
	}
	
	@Transient
	public String getMacrozonaAsString() {
		return macrozona == null ? "" : ""+(macrozona.intValue()-900);
	}

	@Transient
	public void setMacrozonaAsString(String macrozona) {
		this.macrozona = (StringUtils.isEmpty(macrozona)) ? null : new Integer(macrozona);
	}

	/**
	 * M�todos para manter o atributo ueu
	 */
	@Column(name="UEU", length=3)
	public Integer getUeu() {
		return this.ueu;
	}

	public void setUeu(Integer ueu) {
		this.ueu = ueu;
	}

	@Transient
	public String getUeuAsString() {
		return (ueu == null) ? "" : ueu.toString();
	}

	@Transient
	public void setUeuAsString(String ueu) {
		this.ueu = (StringUtils.isEmpty(ueu)) ? null : new Integer(ueu);
	}

	/**
	 * M�todos para manter o atributo quarteirao
	 */
	@Column(name="QTR", length=3)
	public Integer getQuarteirao() {
		return this.quarteirao;
	}

	public void setQuarteirao(Integer quarteirao) {
		this.quarteirao = quarteirao;
	}

	@Transient
	public String getQuarteiraoAsString() {
		return (quarteirao == null) ? "" : quarteirao.toString();
	}

	@Transient
	public void setQuarteiraoAsString(String quarteirao) {
		this.quarteirao = (StringUtils.isEmpty(quarteirao)) ? null : new Integer(quarteirao);
	}

	/**
	 * M�todos para manter o atributo alinhamento
	 */
	@Column(name="ALI", length=5)
	public String getAlinhamento() {
		return this.alinhamento;
	}

	public void setAlinhamento(String alinhamento) {
		this.alinhamento = alinhamento;
	}

	/**
	 * M�todos para manter o atributo referencia
	 */
	@Column(name="REF", length=35)
	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * M�todos para manter o atributo larguraLogradouro
	 */
	@Column(name="GAB_ATU", length=5)
	public String getLarguraLogradouro() {
		if(! larguraLogradouroPrev.equals("00,00"))
			return this.larguraLogradouroPrev;
		else
			return this.larguraLogradouro;
	}

	public void setLarguraLogradouro(String larguraLogradouro) {
		this.larguraLogradouro = larguraLogradouro;
	}

	/**
	 * M�todos para manter o atributo observacao1
	 */
	@Column(name="OBS1", length=3)
	public Integer getObservacao1() {
		return this.observacao1;
	}

	public void setObservacao1(Integer observacao1) {
		this.observacao1 = observacao1;
	}

	private String observacao1AsString;
	@Transient
	public String getObservacao1AsString() {
		return (observacao1AsString == null) ? "" : observacao1AsString.toString();
	}

	@Transient
	public void setObservacao1AsString(String observacao1AsString) {
		this.observacao1AsString = (StringUtils.isEmpty(observacao1AsString)) ? null : new String(observacao1AsString);
	}

	/**
	 * M�todos para manter o atributo observacao2
	 */
	@Column(name="OBS2", length=3)
	public Integer getObservacao2() {
		return this.observacao2;
	}

	public void setObservacao2(Integer observacao2) {
		this.observacao2 = observacao2;
	}

	private String observacao2AsString;
	@Transient
	public String getObservacao2AsString() {
		return (observacao2AsString == null) ? "" : observacao2AsString.toString();
	}

	@Transient
	public void setObservacao2AsString(String observacao2AsString) {
		this.observacao2AsString = (StringUtils.isEmpty(observacao2AsString)) ? null : new String(observacao2AsString);
	}
	
	/**
	 * M�todos para manter o atributo observacao3
	 */
	@Column(name="OBS3", length=3)
	public Integer getObservacao3() {
		return this.observacao3;
	}

	public void setObservacao3(Integer observacao3) {
		this.observacao3 = observacao3;
	}

	private String observacao3AsString;
	@Transient
	public String getObservacao3AsString() {
		return (observacao3AsString == null) ? "" : observacao3AsString.toString();
	}

	@Transient
	public void setObservacao3AsString(String observacao3AsString) {
		this.observacao3AsString = (StringUtils.isEmpty(observacao3AsString)) ? null : new String(observacao3AsString);
	}

	/**
	 * M�todos para manter o atributo dist1
	 */
	@Column(name="DIST1", length=3)
	public Integer getDist1() {
		return this.dist1;
	}

	public void setDist1(Integer dist1) {
		this.dist1 = dist1;
	}

	@Transient
	public String getDist1AsString() {
		return (dist1 == null) ? "" : dist1.toString();
	}

	@Transient
	public void setDist1AsString(String dist1) {
		this.dist1 = (StringUtils.isEmpty(dist1)) ? null : new Integer(dist1);
	}
	
	@Transient
	public String getDescricao() {
		return getAlinhamento() + "|" + 
				getCirculacaoId().getAlinhamInicialAsString() + "|" +
				getCirculacaoId().getAlinhamFinalAsString() + "|" +
				getLarguraLogradouro() + "|" +
				getReferencia().trim() + "|" +
				getCodigo().toString();
	}

	/**
	 * C�digo referencia
	 * 
	 * @return
	 */
	@Column(name="CODREF", length=3)
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	
	@Column(name="GAB_PREV", length=5)
	public String getLarguraLogradouroPrev() {
		return larguraLogradouroPrev;
	}

	public void setLarguraLogradouroPrev(String larguraLogradouroPrev) {
		this.larguraLogradouroPrev = larguraLogradouroPrev;
	}

	public void setLogr1(String logr1) {
		this.logr1 = logr1;
	}

	@Column(name="LOGR1")
	public String getLogr1() {
		return logr1;
	}

	@Column(name="RECUO")
	public String getRecuoViario() {
		return recuoViario;
	}

	public void setRecuoViario(String recuoViario) {
		this.recuoViario = recuoViario;
	}
	
	/**
	 * M�todos para manter o atributo indice
	 */
	private Integer indice = null;
	@Transient	
	public Integer getIndice() {
		return this.indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}

}
