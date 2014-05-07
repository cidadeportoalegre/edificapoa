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

import org.hibernate.annotations.OptimisticLockType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="ZCTM0400", schema="SQLDBA")

@NamedQueries({
	@NamedQuery(name= "countPrediosRel", 
			query="select count( p ) from PredioRelacionado as p " +
					"where p.predioRelacionadoId.macrozona = :macrozona  " +
					"  and p.predioRelacionadoId.ueu = :ueu " +
					"  and p.predioRelacionadoId.quarteirao = :quarteirao " +
					"  and p.predioRelacionadoId.codLogradouro = :codLogradouro " +					
					"  and p.predioRelacionadoId.sequencia   = :sequencia   "),
	@NamedQuery(name= PredioRelacionado.FIND_PREDIO_RELACIONADO, 
			query="select p from PredioRelacionado as p " +
					"where p.predioRelacionadoId.macrozona = :macrozona  " +
					"  and p.predioRelacionadoId.ueu = :ueu " +
					"  and p.predioRelacionadoId.quarteirao = :quarteirao " +
					"  and p.predioRelacionadoId.codLogradouro = :codLogradouro " +					
					"  and p.predioRelacionadoId.sequencia   = :sequencia   ")
	})


public class PredioRelacionado   implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String FIND_PREDIO_RELACIONADO = "regimeUrbanistico.findPredioRelacionado";	
	private PredioRelacionadoId predioRelacionadoId = null;
	private String descricao = null;
	
	public void setPredioRelacionadoId(PredioRelacionadoId predioRelacionadoId) {
		this.predioRelacionadoId = predioRelacionadoId;
	}
	
	@Id
	@AttributeOverrides({
		@AttributeOverride(name="macrozona", column=@Column(name="MZ")),
		@AttributeOverride(name="ueu", column=@Column(name="UEU")),
		@AttributeOverride(name="quarteirao", column=@Column(name="QTR")),
		@AttributeOverride(name="codLogradouro", column=@Column(name="CTM")),
		@AttributeOverride(name="sequencia", column=@Column(name="N"))
	})
	public PredioRelacionadoId getPredioRelacionadoId() {
		return predioRelacionadoId;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name="DESCRICAO")
	public String getDescricao() {
		return descricao;
	}
	 
}
