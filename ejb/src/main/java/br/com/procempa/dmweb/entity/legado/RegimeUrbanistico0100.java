package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.OptimisticLockType;

import br.com.procempa.dmweb.util.PropertyAsString;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="ZCTM0100", schema="SQLDBA")
public class RegimeUrbanistico0100   implements Serializable {
        public static final long serialVersionUID = 1L;
        private Integer idZCTM0100 = null;
        private Integer macrozona = null;
        private Integer ueu = null;
        private Integer subunidade = null;
        private Integer densidade = null;
        private BigDecimal atividade = null;
    	private String indiceAproveitamento = null;
    	private String volumetria = null;

        @Id
        public Integer getIdZCTM0100() {
			return idZCTM0100;
		}
		public void setIdZCTM0100(Integer idZCTM0100) {
			this.idZCTM0100 = idZCTM0100;
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
