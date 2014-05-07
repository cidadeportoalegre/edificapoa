package br.com.procempa.dmweb.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
	@AttributeOverride(name="id", column=@Column(name="idUsuario"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmUsuario", schema="SQLDBA")
public class Usuario extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	
	public static final String CARGO_ENGENHEIRO = "Engenheiro";
	public static final String CARGO_ARQUITETO = "Arquiteto";
	public static final String CARGO_ENGENHEIRA = "Engenheira";
	public static final String CARGO_ARQUITETA = "Arquiteta";

	public static final String SETOR_CIP = "SPM - CIP";
	public static final String SETOR_UAI2 = "SPM - CIP-UAI2";
	public static final String SETOR_URP2 = "SPM - CIP-URP2";
	
	private Integer matricula = null;
	private String cargo = null;
	private String setor = null;
	private String nomeReferencia = null;

	@Column(nullable=false) 
	public Integer getMatricula() { return matricula; }
	public void setMatricula(Integer matricula) { this.matricula = matricula; }
	@Transient
	public String getMatriculaAsString() { return (matricula == null) ? "" : matricula.toString(); }
	public void setMatriculaAsString(String matricula) { this.matricula = (StringUtils.isEmpty(matricula)) ? null : new Integer(matricula); }

	@Column(nullable=false) 
	public String getCargo() { return cargo; }
	public void setCargo(String cargo) { this.cargo = cargo; }

	@Column(nullable=false) 
	public String getSetor() { return setor; }
	public void setSetor(String setor) { this.setor = setor; }

	@Column(nullable=true) 
	public String getNomeReferencia() { return nomeReferencia; }
	public void setNomeReferencia(String nomeReferencia) { this.nomeReferencia = nomeReferencia; }

}
