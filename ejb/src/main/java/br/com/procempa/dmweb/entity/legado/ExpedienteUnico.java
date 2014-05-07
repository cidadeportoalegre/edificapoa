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

import org.hibernate.annotations.OptimisticLockType;

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="ZCEU110", schema="SQLDBA")


@NamedQueries({
	@NamedQuery(name="countCEU", 
			query="select count( b ) from ExpedienteUnico as b " +
					"where b.expedienteUnicoId.multiusu > 0 " +
					"  and b.expedienteUnicoId.expedienteUnico = :expedienteUnico " +
					"  and b.expedienteUnicoId.areaPrivativa   = :areaPrivativa   " +
					"  and b.expedienteUnicoId.tipoEndereco    = :tipoEndereco    "),
	@NamedQuery(name= ExpedienteUnico.FIND_EXPEDIENTE_UNICO, 
			query="select b from ExpedienteUnico as b " +
							"where b.expedienteUnicoId.multiusu > 0 " +
							"  and b.expedienteUnicoId.expedienteUnico = :expedienteUnico " +
							"  and b.expedienteUnicoId.areaPrivativa   = :areaPrivativa   " +
							"  and b.expedienteUnicoId.tipoEndereco    = :tipoEndereco    ")					
	})


public class ExpedienteUnico  implements Serializable {
	public static final long serialVersionUID = 1L;
	private ExpedienteUnicoId expedienteUnicoId = null;

	public static final String FIND_EXPEDIENTE_UNICO = "expedienteUnico.findExpedienteUnico";
	/**
	 * M�todos para manter o atributo expedienteUnicoId
	 */
	@Id
	@AttributeOverrides({
		@AttributeOverride(name="multiusu", column=@Column(name="MULTIUSU")),
		@AttributeOverride(name="expedienteUnico", column=@Column(name="CODEXUNI")),
		@AttributeOverride(name="areaPrivativa", column=@Column(name="ARPEXUNI")),
		@AttributeOverride(name="cdCadast", column=@Column(name="CDCADAST")),
		@AttributeOverride(name="tipoEndereco", column=@Column(name="TPENDER")),
		@AttributeOverride(name="dtOcoEnd", column=@Column(name="DTOCOEND")),
		@AttributeOverride(name="codLogradouro", column=@Column(name="CODLOGR")),
		@AttributeOverride(name="numImovel", column=@Column(name="NIMOVEL"))
	})
	public ExpedienteUnicoId getExpedienteUnicoId() {
		return this.expedienteUnicoId;
	}

	public void setExpedienteUnicoId(ExpedienteUnicoId expedienteUnicoId) {
		this.expedienteUnicoId = expedienteUnicoId;
	}

	private String logradouro = null;
	private String bairro     = null;

	@Transient
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Transient
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
}
