package br.com.procempa.dmweb.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverrides( {
	@AttributeOverride(name="id", column=@Column(name="idTipoObservacao"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmTipoObservacao", schema="SQLDBA")
public class TipoObservacao extends DmBaseDataObject implements Serializable {
	public static final long serialVersionUID = 1L;
	public static final String DESCRICAO_SUBUNIDADE = "Subunidade";
	public static final String DESCRICAO_REGIME = "Regime";
	public static final String DESCRICAO_FACE = "Face";
	public static final String DESCRICAO_GERAIS = "Gerais";
	public static final String DESCRICAO_FIXA = "Fixa";
	public static final String DESCRICAO_FIXAS_DM = "Fixas DM";
	public static final String DESCRICAO_FIXAS_BOLETIM = "Fixas Boletim";
	public static final String DESCRICAO_FIXAS_CERTIDAO = "Fixas Certidão";
	public static final String DESCRICAO_ALINHAMENTO = "Alinhamento";
	public static final String DESCRICAO_CONDICIONANTES = "Condicionantes";
	private String descricao = null;

	/**
	 * M�todos para manter o atributo descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
