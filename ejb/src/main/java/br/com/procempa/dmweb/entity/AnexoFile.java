package br.com.procempa.dmweb.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverrides( {
	@AttributeOverride(name="id", column=@Column(name="idAnexoFile"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmAnexoFile", schema="SQLDBA")
public class AnexoFile extends DmBaseDataObject implements Serializable {

	private static final long serialVersionUID = 4933445990540512458L;

	private Anexo anexo = null;
	private byte[] file = null;

	public  AnexoFile() {
	}

	@Lob
	@Column(columnDefinition="BLOB(2G) NOT LOGGED COMPACT")
	public byte[] getFile() {
		 return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@OneToOne(mappedBy="anexoFile")
	public Anexo getAnexo() {
		 return anexo;
	}

	public void setAnexo(Anexo anexo) {
		this.anexo = anexo;
	}

}
