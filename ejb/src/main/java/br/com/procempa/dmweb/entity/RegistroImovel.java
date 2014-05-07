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
	@AttributeOverride(name="id", column=@Column(name="idRegistroImovel"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmRegistroImovel", schema="SQLDBA")
public class RegistroImovel extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	private String matricula = null;
	private String zona = null;
	private String certidao = null;
	private String transcricao = null;
	private String outros = null;
	private Documento documento = null;

	private String zonaMatricula = null;
	private String zonaCertidao = null;
	private String zonaTranscricao = null;
	
	/**
	 * M�todos para manter o atributo matricula
	 */
	@Column(length=15)
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * M�todos para manter o atributo zona
	 */
	@Column(length=7)
	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * M�todos para manter o atributo certidao
	 */
	@Column(length=40)
	public String getCertidao() {
		return this.certidao;
	}

	public void setCertidao(String certidao) {
		this.certidao = certidao;
	}

	/**
	 * M�todos para manter o atributo documento
	 */
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="idDocumento")
	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@Transient
	public String getRegistroImovelAsText() {
		return 	StringUtils.isNotBlank(matricula) ? "Matric./Zona " + matricula + "/" + zona :
				StringUtils.isNotBlank(certidao) ?	"Cert./Zona " + certidao + "/" + zona :
				StringUtils.isNotBlank(transcricao) ?	"Transcr./Zona " + transcricao + "/" + zona : "Outros " + outros;
	}

	@Column(length=60)
	public String getTranscricao() {
		return transcricao;
	}

	public void setTranscricao(String transcricao) {
		this.transcricao = transcricao;
	}

	@Column(length=60)
	public String getOutros() {
		return outros;
	}

	public void setOutros(String outros) {
		this.outros = outros;
	}
	
	@Transient
	public String getZonaMatricula(){
		return zonaMatricula;
	}
	
	public void setZonaMatricula(String zonaMatricula){
		this.zonaMatricula = zonaMatricula;
	}
	
	@Transient
	public String getZonaCertidao(){
		return zonaCertidao;
	}
	
	public void setZonaCertidao(String zonaCertidao){
		this.zonaCertidao = zonaCertidao;
	}
	
	@Transient
	public String getZonaTranscricao(){
		return zonaTranscricao;
	}
	
	public void setZonaTranscricao(String zonaTranscricao){
		this.zonaTranscricao = zonaTranscricao;
	}
	
}
