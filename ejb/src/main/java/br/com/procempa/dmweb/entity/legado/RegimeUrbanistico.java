package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

import br.com.procempa.dmweb.util.PropertyAsString;



/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DMCTM200100", schema="SQLDBA")

@NamedQueries({
	@NamedQuery(name= "countReg", 
			query="select count( r ) from RegimeUrbanistico as r " +
					"where r.regimeUrbanisticoId.codLogradouro = :codLogradouro  " +
					"  and r.regimeUrbanisticoId.faceLimiteInicial = :numeroInicial " +
					"  and r.regimeUrbanisticoId.faceLimiteFinal   = :numeroFinal   "),
	@NamedQuery(name= RegimeUrbanistico.FIND_REGIME_URBANISTICO, 
			query="select r from RegimeUrbanistico as r " +
					"where r.regimeUrbanisticoId.codLogradouro = :codLogradouro  " +
					"  and r.regimeUrbanisticoId.faceLimiteInicial = :numeroInicial " +
					"  and r.regimeUrbanisticoId.faceLimiteFinal   = :numeroFinal   ")					
	})


public class RegimeUrbanistico  implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private RegimeUrbanisticoId regimeUrbanisticoId = null;
	
	public static final String COUNT_REGIME_URBANISTICO = "regimeUrbanistico.countRegimeUrbanistico";	
	public static final String FIND_REGIME_URBANISTICO = "regimeUrbanistico.findRegimeUrbanistico";
	
	
	private Integer macrozona = null;
	private Integer ueu = null;
	private Integer quarteirao = null;
	private Integer regUrbLimFinal = null;
	private Integer regUrbDistFinal = null;
	private Integer subunidade = null;
	private Integer densidade = null;
	private BigDecimal atividade = null;
	private String indiceAproveitamento = null;
	private String volumetria = null;
	private Integer obsReg1 = null;
	private Integer obsReg2 = null;
	private Integer obsReg3 = null;
	private Integer obsReg4 = null;
	private Integer obsReg5 = null;
	private Integer obsReg6 = null;

	/**
	 * M�todos para manter o atributo regimeUrbanisticoId
	 */
	@Id
	@AttributeOverrides({
		@AttributeOverride(name="codLogradouro", column=@Column(name="CODLOGR")),
		@AttributeOverride(name="faceLimiteInicial", column=@Column(name="IMOVEL_INI")),
		@AttributeOverride(name="regUrbLimInicial", column=@Column(name="RU_LIMITE_INI")),
		@AttributeOverride(name="regUrbDistInicial", column=@Column(name="RU_DIST_INI")),
		@AttributeOverride(name="faceLimiteFinal", column=@Column(name="IMOVEL_FIN"))
	})
	public RegimeUrbanisticoId getRegimeUrbanisticoId() {
		return this.regimeUrbanisticoId;
	}

	public void setRegimeUrbanisticoId(RegimeUrbanisticoId regimeUrbanisticoId) {
		this.regimeUrbanisticoId = regimeUrbanisticoId;
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
		return (macrozona == null) ? "" : ""+(macrozona-900);
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
	 * M�todos para manter o atributo regUrbLimFinal
	 */
	@Column(name="RU_LIMITE_FINI", length=5)
	public Integer getRegUrbLimFinal() {
		return this.regUrbLimFinal;
	}

	public void setRegUrbLimFinal(Integer regUrbLimFinal) {
		this.regUrbLimFinal = regUrbLimFinal;
	}

	@Transient
	public String getRegUrbLimFinalAsString() {
		return (regUrbLimFinal == null) ? "" : regUrbLimFinal.toString();
	}

	@Transient
	public void setRegUrbLimFinalAsString(String regUrbLimFinal) {
		this.regUrbLimFinal = (StringUtils.isEmpty(regUrbLimFinal)) ? null : new Integer(regUrbLimFinal);
	}

	/**
	 * M�todos para manter o atributo regUrbDistFinal
	 */
	@Column(name="RU_DIST_FIN", length=3)
	public Integer getRegUrbDistFinal() {
		return this.regUrbDistFinal;
	}

	public void setRegUrbDistFinal(Integer regUrbDistFinal) {
		this.regUrbDistFinal = regUrbDistFinal;
	}

	@Transient
	public String getRegUrbDistFinalAsString() {
		return (regUrbDistFinal == null) ? "" : regUrbDistFinal.toString();
	}

	@Transient
	public void setRegUrbDistFinalAsString(String regUrbDistFinal) {
		this.regUrbDistFinal = (StringUtils.isEmpty(regUrbDistFinal)) ? null : new Integer(regUrbDistFinal);
	}

	/**
	 * M�todos para manter o atributo subunidade
	 */
	@Column(name="SUBUNIDADE", length=2)
	public Integer getSubunidade() {
		return this.subunidade;
	}

	public void setSubunidade(Integer subunidade) {
		this.subunidade = subunidade;
	}

	@Transient
	public String getSubunidadeAsString() {
		return (subunidade == null) ? "" : subunidade.toString();
	}

	@Transient
	public void setSubunidadeAsString(String subunidade) {
		this.subunidade = (StringUtils.isEmpty(subunidade)) ? null : new Integer(subunidade);
	}

	/**
	 * M�todos para manter o atributo densidade
	 */
	@Column(name="densidade", length=3)
	public Integer getDensidade() {
		return this.densidade;
	}

	public void setDensidade(Integer densidade) {
		this.densidade = densidade;
	}

	@Transient
	public String getDensidadeAsString() {
		return (densidade == null) ? "" : (densidade == 999 )? "---" : densidade.toString();
	}

	@Transient
	public void setDensidadeAsString(String densidade) {
		this.densidade = (StringUtils.isEmpty(densidade)) ? null : new Integer(densidade);
	}

	/**
	 * M�todos para manter o atributo atividade
	 */
	@Column(name="atividade", length=2, scale=2, columnDefinition="decimal(4,2)")
	public BigDecimal getAtividade() {
		return this.atividade;
	}

	public void setAtividade(BigDecimal atividade) {
		if (atividade != null) {
			atividade.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.atividade = atividade;
	}

	@Transient
	public String getAtividadeAsString() {
		return atividade == null ? "" : PropertyAsString.prepareBigDecimalToString(atividade);
	}

	@Transient
	public void setAtividadeAsString(String atividade) {
		this.atividade = (StringUtils.isEmpty(atividade)) ? null : new BigDecimal(PropertyAsString.prepareStringToBigDecimal(atividade)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * M�todos para manter o atributo indiceAproveitamento
	 */
	@Column(name="aproveitamento", length=3)
	public String getIndiceAproveitamento() {
		return this.indiceAproveitamento;
	}

	public void setIndiceAproveitamento(String indiceAproveitamento) {
		this.indiceAproveitamento = indiceAproveitamento;
	}

	/**
	 * M�todos para manter o atributo volumetria
	 */
	@Column(name="volumetria", length=4)
 	public String getVolumetria() {
		return this.volumetria;
	}

	public void setVolumetria(String volumetria) {
		this.volumetria = volumetria;
	}

	/**
	 * M�todos para manter o atributo obsReg1
	 */
	@Column(name="OBS_REG1", length=5)
	public Integer getObsReg1() {
		return this.obsReg1;
	}

	public void setObsReg1(Integer obsReg1) {
		this.obsReg1 = obsReg1;
	}

	@Transient
	public String getObsReg1AsString() {
		return (obsReg1 == null) ? "" : obsReg1.toString();
	}

	@Transient
	public void setObsReg1AsString(String obsReg1) {
		this.obsReg1 = (StringUtils.isEmpty(obsReg1)) ? null : new Integer(obsReg1);
	}

	/**
	 * M�todos para manter o atributo obsReg2
	 */
	@Column(name="OBS_REG2", length=5)
	public Integer getObsReg2() {
		return this.obsReg2;
	}

	public void setObsReg2(Integer obsReg2) {
		this.obsReg2 = obsReg2;
	}

	@Transient
	public String getObsReg2AsString() {
		return (obsReg2 == null) ? "" : obsReg2.toString();
	}

	@Transient
	public void setObsReg2AsString(String obsReg2) {
		this.obsReg2 = (StringUtils.isEmpty(obsReg2)) ? null : new Integer(obsReg2);
	}

	/**
	 * M�todos para manter o atributo obsReg3
	 */
	@Column(name="OBS_REG3", length=5)
	public Integer getObsReg3() {
		return this.obsReg3;
	}

	public void setObsReg3(Integer obsReg3) {
		this.obsReg3 = obsReg3;
	}

	@Transient
	public String getObsReg3AsString() {
		return (obsReg3 == null) ? "" : obsReg3.toString();
	}

	@Transient
	public void setObsReg3AsString(String obsReg3) {
		this.obsReg3 = (StringUtils.isEmpty(obsReg3)) ? null : new Integer(obsReg3);
	}

	/**
	 * M�todos para manter o atributo obsReg4
	 */
	@Column(name="OBS_REG4", length=5)
	public Integer getObsReg4() {
		return this.obsReg4;
	}

	public void setObsReg4(Integer obsReg4) {
		this.obsReg4 = obsReg4;
	}

	@Transient
	public String getObsReg4AsString() {
		return (obsReg4 == null) ? "" : obsReg4.toString();
	}

	@Transient
	public void setObsReg4AsString(String obsReg4) {
		this.obsReg4 = (StringUtils.isEmpty(obsReg4)) ? null : new Integer(obsReg4);
	}

	/**
	 * M�todos para manter o atributo obsReg5
	 */
	@Column(name="OBS_REG5", length=5)
	public Integer getObsReg5() {
		return this.obsReg5;
	}

	public void setObsReg5(Integer obsReg5) {
		this.obsReg5 = obsReg5;
	}

	@Transient
	public String getObsReg5AsString() {
		return (obsReg5 == null) ? "" : obsReg5.toString();
	}

	@Transient
	public void setObsReg5AsString(String obsReg5) {
		this.obsReg5 = (StringUtils.isEmpty(obsReg5)) ? null : new Integer(obsReg5);
	}

	/**
	 * M�todos para manter o atributo obsReg6
	 */
	@Column(name="OBS_REG6", length=5)
	public Integer getObsReg6() {
		return this.obsReg6;
	}

	public void setObsReg6(Integer obsReg6) {
		this.obsReg6 = obsReg6;
	}

	@Transient
	public String getObsReg6AsString() {
		return (obsReg6 == null) ? "" : obsReg6.toString();
	}

	@Transient
	public void setObsReg6AsString(String obsReg6) {
		this.obsReg6 = (StringUtils.isEmpty(obsReg6)) ? null : new Integer(obsReg6);
	}
	

	private String obsReg1Descricao = null;
	@Transient
	public String getObsReg1Descricao() {
		return obsReg1Descricao;
	}
	public void setObsReg1Descricao(String obsReg1Descricao) {
		this.obsReg1Descricao = ( StringUtils.isEmpty(obsReg1Descricao) ) ? null : new String(obsReg1Descricao);
	}

	
	private String obsReg2Descricao = null;
	@Transient
	public String getObsReg2Descricao() {
		return obsReg2Descricao;
	}
	public void setObsReg2Descricao(String obsReg2Descricao) {
		this.obsReg2Descricao = ( StringUtils.isEmpty(obsReg2Descricao) ) ? null : new String(obsReg2Descricao);
	}

	
	private String obsReg3Descricao = null;
	@Transient
	public String getObsReg3Descricao() {
		return obsReg3Descricao;
	}
	public void setObsReg3Descricao(String obsReg3Descricao) {
		this.obsReg3Descricao = ( StringUtils.isEmpty(obsReg3Descricao) ) ? null : new String(obsReg3Descricao);
	}

	
	private String obsReg4Descricao = null;
	@Transient
	public String getObsReg4Descricao() {
		return obsReg4Descricao;
	}
	public void setObsReg4Descricao(String obsReg4Descricao) {
		this.obsReg4Descricao = ( StringUtils.isEmpty(obsReg4Descricao) ) ? null : new String(obsReg4Descricao);
	}

	
	private String obsReg5Descricao = null;
	@Transient
	public String getObsReg5Descricao() {
		return obsReg5Descricao;
	}
	public void setObsReg5Descricao(String obsReg5Descricao) {
		this.obsReg5Descricao = ( StringUtils.isEmpty(obsReg5Descricao) ) ? null : new String(obsReg5Descricao);
	}

	
	private String obsReg6Descricao = null;
	@Transient
	public String getObsReg6Descricao() {
		return obsReg6Descricao;
	}
	public void setObsReg6Descricao(String obsReg6Descricao) {
		this.obsReg6Descricao = ( StringUtils.isEmpty(obsReg6Descricao) ) ? null : new String(obsReg6Descricao);	}

	
	private String circAlinhamento = null;
	@Transient
	public String getCircAlinhamento() {
		return circAlinhamento;
	}
	public void setCircAlinhamento(String circAlinhamento) {
		this.circAlinhamento = circAlinhamento;
	}
	
	private String circReferencia = null;
	@Transient
	public String getCircReferencia() {
		return circReferencia;
	}
	public void setCircReferencia(String circReferencia) {
		this.circReferencia = circReferencia;
	}

	private String circLarguraLogr = null;
	@Transient
	public String getCircLarguraLogr() {
		return circLarguraLogr;
	}
	public void setCircLarguraLogr(String circLarguraLogr) {
		this.circLarguraLogr = circLarguraLogr;
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
	
	/**
	 * M�todos para manter o atributo ObservacoesSubUnidadePrediosRelList
	 */	
	private List<String> observacoesSubUnidadeList;
	@Transient
	public List<String> getObservacoesSubUnidadeList() {
		return observacoesSubUnidadeList;
	}

	public void setObservacoesSubUnidadeList(List<String> observacoesSubUnidadeList) {
		this.observacoesSubUnidadeList = observacoesSubUnidadeList;
	}
	
	
	@Transient
	public String getDadosSubunidade() {
		Set<String> set = new LinkedHashSet<String>();
		if (StringUtils.isNotBlank(getDensidadeAsString())) {
			set.add("Densidade: " + getDensidadeAsString());
		}
		if (StringUtils.isNotBlank(getAtividadeAsString())) {
			set.add("Atividade: " + getAtividadeAsString());
		}
		if (StringUtils.isNotBlank(getIndiceAproveitamento())) {
			set.add("Aproveitamento: " + getIndiceAproveitamento());
		}
		if (StringUtils.isNotBlank(getVolumetria())) {
			set.add("Volumetria: " + getVolumetria());
		}
		StringBuffer saida = new StringBuffer("");
		saida.append("Subunidade " + getSubunidadeAsString());
		if (set.size() > 0) {
			saida.append(" (");
			for (String string : set) {
				saida.append(string + " ");
			}
			saida.append(")");
		}
		return saida.toString();
	}
	
	@Transient
	public String getDadosSubunidadeId() {
		Set<String> set = new LinkedHashSet<String>();
		if (StringUtils.isNotBlank(getDensidadeAsString())) {
			set.add("|"+getDensidadeAsString());
		}
		if (StringUtils.isNotBlank(getAtividadeAsString())) {
			set.add("|"+getAtividadeAsString());
		}
		if (StringUtils.isNotBlank(getIndiceAproveitamento())) {
			set.add("|"+getIndiceAproveitamento());
		}
		if (StringUtils.isNotBlank(getVolumetria())) {
			set.add("|"+getVolumetria());
		}
		StringBuffer saida = new StringBuffer("");
		
		saida.append( getSubunidadeAsString() );
		
		if (set.size() > 0) {
			for (String string : set) {
				saida.append(string );
			}
		}
		return saida.toString();
	}
	
}
