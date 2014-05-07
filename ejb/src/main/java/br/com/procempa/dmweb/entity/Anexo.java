package br.com.procempa.dmweb.entity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OptimisticLockType;

import br.com.procempa.dmweb.util.PropertyAsString;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverrides( {
	@AttributeOverride(name="id", column=@Column(name="idAnexo"))
} )
@org.hibernate.annotations.Entity (dynamicInsert=true, dynamicUpdate=true, selectBeforeUpdate=true, optimisticLock=OptimisticLockType.VERSION)
@Table(name="DmAnexo", schema="SQLDBA")
public class Anexo extends DmBaseDataObject implements Serializable {

	private static final long serialVersionUID = 6128608972681217828L;

	private Date data = null;
	private String descricao = null;
	private String mimeType = null;
	private String fileName = null;
	private Long fileSize = new Long(0);
	private Boolean participaDM = Boolean.FALSE;
	private Documento documento = null;
	private AnexoFile anexoFile = null;
	private Boolean digital = Boolean.TRUE;

	public  Anexo() {
	}

	public Date getData() {
		 return this.data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Transient
	public String getDataAsString() {
		return PropertyAsString.dateAsString(getData());
	}
	@Transient
	public void setDataAsString(String dataAsString) throws ParseException {
		setData(PropertyAsString.stringAsDate(dataAsString));
	}

	public String getDescricao() {
		 return this.descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return this.fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	@Transient
    public String getFileSizeAsString() {
		if (fileSize==null) {
			return "";
		}
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setGroupingUsed(true);
		Long fileSizeAux = this.fileSize / 1024;
		return nf.format(fileSizeAux) + "&nbsp;Kb";
	}
	
	public String getMimeType() {
		return this.mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	@Transient
	public String getMimeTypeAsString() {
		if (mimeType==null) {
			return "";
		}
		String saida = mimeType;
		if (saida.indexOf("text/plain") > -1) {
			saida = "Texto simples";
		}
		else if (saida.indexOf("pdf") > -1) {
			saida = "PDF";
		}
		else if (saida.indexOf("msword") > -1) {
			saida = "Texto Microsoft Office";
		}
		else if (saida.indexOf("excel") > -1) {
			saida = "Planilha Microsoft Office";
		}
		else if (saida.indexOf("powerpoint") > -1) {
			saida = "Apresenta��o Microsoft Office";
		}
		else if (saida.indexOf("vnd.oasis.opendocument.text") > -1) {
			saida = "Texto OpenOffice";
		}
		else if (saida.indexOf("vnd.oasis.opendocument.spreadsheet") > -1) {
			saida = "Planilha OpenOffice";
		}
		else if (saida.indexOf("vnd.oasis.opendocument.presentation") > -1) {
			saida = "Apresenta��o OpenOffice";
		}
		else if (saida.indexOf("vnd.sun.xml.writer") > -1) {
			saida = "OpenOffice Writer";
		}
		else if (saida.indexOf("vnd.sun.xml.calc") > -1) {
			saida = "OpenOffice Calc";
		}
		else if (saida.indexOf("vnd.sun.xml.impress") > -1) {
			saida = "OpenOffice Spreedsheet";
		}
		else if (saida.indexOf("jpeg") > -1) {
			saida = "Imagem JPG";
		}
		else if (saida.indexOf("gif") > -1) {
			saida = "Imagem GIF";
		}
		else if (saida.indexOf("bmp") > -1) {
			saida = "Imagem BMP";
		}
		else if (saida.indexOf("x-png") > -1) {
			saida = "Imagem PNG";
		}
		return saida;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idDocumento")
	public Documento getDocumento() {
		return this.documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name="idAnexoFile")
	public AnexoFile getAnexoFile() {
		return this.anexoFile;
	}
	public void setAnexoFile(AnexoFile anexoFile) {
		this.anexoFile = anexoFile;
	}

	public Boolean getParticipaDM() {
		return participaDM;
	}

	public void setParticipaDM(Boolean participaDM) {
		this.participaDM = participaDM;
	}

	@Transient
	public String getParticipaDMAsString() {
		return participaDM == null ? "" : participaDM.toString();
	}
	
	@Transient
	public void setParticipaDMAsString(String participaDM) {
		this.participaDM = participaDM == null ? null : new Boolean(participaDM);
	}

	@Transient
	public String getIconName() {
		String saida = mimeType;
		if (saida==null) {
			saida = "";
		}
		if ( ! getDigital() ) {
			saida = "ico_ndig";
		}
		else if (saida.indexOf("text/plain") > -1) {
			saida = "ico_txt";
		}
		else if (saida.indexOf("pdf") > -1) {
			saida = "ico_pdf";
		}
		else if (saida.indexOf("msword") > -1) {
			saida = "ico_doc";
		}
		else if (saida.indexOf("excel") > -1) {
			saida = "ico_xls";
		}
		else if (saida.indexOf("powerpoint") > -1) {
			saida = "ico_pwt";
		}
		else if (saida.indexOf("vnd.oasis.opendocument.text") > -1) {
			saida = "ico_writer";
		}
		else if (saida.indexOf("vnd.oasis.opendocument.spreadsheet") > -1) {
			saida = "ico_calc";
		}
		else if (saida.indexOf("vnd.oasis.opendocument.presentation") > -1) {
			saida = "ico_impress";
		}
		else if (saida.indexOf("vnd.sun.xml.writer") > -1) {
			saida = "ico_writer";
		}
		else if (saida.indexOf("vnd.sun.xml.calc") > -1) {
			saida = "ico_calc";
		}
		else if (saida.indexOf("vnd.sun.xml.impress") > -1) {
			saida = "ico_impress";
		}
		else if (saida.indexOf("jpeg") > -1) {
			saida = "ico_img";
		}
		else if (saida.indexOf("gif") > -1) {
			saida = "ico_img";
		}
		else if (saida.indexOf("bmp") > -1) {
			saida = "ico_img";
		}
		else if (saida.indexOf("png") > -1) {
			saida = "ico_img";
		}
		else {
			saida = "ico_file";
		}
		return saida;
	}

	public Boolean getDigital() {
		return digital;
	}

	public void setDigital(Boolean digital) {
		this.digital = digital;
	}
	
	@Transient
	public String getDigitalAsString() {
		return digital ? "true" : "false";
	}
	
	@Transient
	public void setDigitalAsString(String digitalAsString) {
		this.digital = new Boolean(digitalAsString);
	}
	
}
