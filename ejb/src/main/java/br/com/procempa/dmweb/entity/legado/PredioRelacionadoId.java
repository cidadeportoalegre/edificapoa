package br.com.procempa.dmweb.entity.legado;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class PredioRelacionadoId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer macrozona = null;
	private Integer ueu = null;
	private Integer quarteirao = null;
	private Integer codLogradouro = null;
	private Integer sequencia = null;
	
//	@Column(name="MZ")
	public Integer getMacrozona() {
		return macrozona;
	}
	public void setMacrozona(Integer macrozona) {
		this.macrozona = macrozona;
	}
	
//	@Column(name="UEU")
	public Integer getUeu() {
		return ueu;
	}
	public void setUeu(Integer ueu) {
		this.ueu = ueu;
	}
	
//	@Column(name="QTR")
	public Integer getQuarteirao() {
		return quarteirao;
	}
	public void setQuarteirao(Integer quarteirao) {
		this.quarteirao = quarteirao;
	}
	
//	@Column(name="CTM")
	public Integer getCodLogradouro() {
		return codLogradouro;
	}
	public void setCodLogradouro(Integer codLogradouro) {
		this.codLogradouro = codLogradouro;
	}
	
//	@Column(name="N")
	public Integer getSequencia() {
		return sequencia;
	}
	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}
	
	@Transient
	public String getMacrozonaAsString() {
		return macrozona == null ? "" : ""+(macrozona);
	}
	
	@Transient
	public String getUeuAsString() {
		return ueu == null ? "" : ""+(ueu);
	}
	
	@Transient
	public String getQuarteiraoAsString() {
		return quarteirao == null ? "" : ""+(quarteirao);
	}
	
	@Transient
	public String getSequenciaAsString() {
		return sequencia == null ? "" : ""+(sequencia);
	}
	

}
