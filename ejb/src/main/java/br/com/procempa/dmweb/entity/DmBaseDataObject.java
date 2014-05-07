package br.com.procempa.dmweb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.StringUtils;

import br.com.procempa.dmweb.util.PropertyAsString;

 
@MappedSuperclass
public abstract class DmBaseDataObject  implements Serializable {
	public static final long serialVersionUID = 1L;
	
	private Long id = null;
	private Timestamp timestamp = null;

	/**
	 * M�todos para manter o atributo id
	 */
	@Column(nullable=false)
	@Id
	@GeneratedValue
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public String getIdAsString() {
		return (id == null) ? "" : id.toString();
	}

	@Transient
	public void setIdAsString(String id) {
		this.id = (StringUtils.isEmpty(id)) ? null : new Long(id);
	}

	/**
	 * M�todos para manter o atributo timestamp
	 */
	@Column(name="timestamp", nullable=false)
	@Version
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Transient
	public String getTimestampAsString() {
		return PropertyAsString.dateAsString(this.timestamp);
	}

 
}
