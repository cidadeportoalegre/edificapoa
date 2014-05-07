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

/**
 * Gerador Procempa3
 * @author: alexgv
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="ZDTE130", schema="SQLDBA")
@NamedQueries({
	@NamedQuery(name = DivisaoTerritorial130.FIND_QUARTEIRAO_130, query = "select b from DivisaoTerritorial130 as b "
			+ "where b.divisaoTerritorial130Id.faceCodLogradouro = :codLogradouro "
			+ "  and b.divisaoTerritorial130Id.macrozona = :macrozona "
			+ "  and b.divisaoTerritorial130Id.ueu = :ueu "
			+ "  and b.divisaoTerritorial130Id.quarteirao = :quarteirao"
			+ "  and b.divisaoTerritorial130Id.faceSequencia = :sequencia") })
public class DivisaoTerritorial130  implements Serializable {
	public static final long serialVersionUID = 1L;
	public static final String FIND_QUARTEIRAO_130 = "divisaoTerritorial130.find_quarteirao_130";	
	private DivisaoTerritorial130Id divisaoTerritorial130Id = null;

	/**
	 * M�todos para manter o atributo divisaoTerritorial130Id
	 */
	@Id
	@AttributeOverrides({
		@AttributeOverride(name="macrozona", column=@Column(name="UTS")),
		@AttributeOverride(name="ueu", column=@Column(name="UTP")),
		@AttributeOverride(name="quarteirao", column=@Column(name="QTR")),
		@AttributeOverride(name="faceCodLogradouro", column=@Column(name="CODLOGR")),
		@AttributeOverride(name="faceSequencia", column=@Column(name="SEQFACE")),
		@AttributeOverride(name="faceData", column=@Column(name="DATAFACE")),
		@AttributeOverride(name="tipoObs", column=@Column(name="TIPOBS")),
		@AttributeOverride(name="observacao", column=@Column(name="CODOBS"))
	})
	public DivisaoTerritorial130Id getDivisaoTerritorial130Id() {
		return this.divisaoTerritorial130Id;
	}

	public void setDivisaoTerritorial130Id(DivisaoTerritorial130Id divisaoTerritorial130Id) {
		this.divisaoTerritorial130Id = divisaoTerritorial130Id;
	}
}
