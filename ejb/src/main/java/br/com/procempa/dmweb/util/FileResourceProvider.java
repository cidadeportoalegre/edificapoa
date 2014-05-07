package br.com.procempa.dmweb.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;

@Name("fileResourceProvider")
@Scope(ScopeType.EVENT)
public class FileResourceProvider
{

	private Long fileId;
	@RequestParameter
	private String fileName;
	@RequestParameter
	private String contentType;
	private byte[] data;

	@Create
	public void create()
	{
//		FileItem item = em.find(FileItem.class, fileId); // Get document representation
//		this.fileName = "manual.pdf";
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ext = context.getExternalContext();
        
        InputStream stream = ext.getResourceAsStream("/pdf/"+fileName);
        
        if (stream != null) {
            try {
            	ByteArrayOutputStream baos = new  ByteArrayOutputStream();
            	copy( stream, baos );
            	this.data = baos.toByteArray();
            } catch (IOException e) {
				e.printStackTrace();
			} finally {
                try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
//		this.contentType = "application/pdf";
	}

    private void copy(InputStream in, ByteArrayOutputStream out) throws IOException {
        byte[] buffer = new byte[2048];
        int read;
        
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
    
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
