package br.com.procempa.dmweb.action;

import java.io.IOException;
import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;



@Name("inicializacaoAction" )
@Scope(ScopeType.SESSION)
@Startup
public class InicializacaoAction implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 
	@Out(scope=ScopeType.SESSION )
	private String urlBaseMapServer;

	@Out(scope=ScopeType.SESSION )
	private String urlBaseMap5000;
	
	@Out(scope=ScopeType.SESSION )
	private String urlAlinhamentos;
	
	@Out(scope=ScopeType.SESSION )
	private String urlAeroDep;
	
	@Out(scope=ScopeType.SESSION )
	private String urlAeroDmae;

	@Out(scope=ScopeType.SESSION )
	private Integer mosaico_dep;
	
	@Out(scope=ScopeType.SESSION )
	private Integer camada_dmae;

	@Out(scope=ScopeType.SESSION )
	private Integer mosaico_1_1000; // alinhamento
	
	@Out(scope=ScopeType.SESSION )
	private Integer camada_quarteirao;
	
	@Out(scope=ScopeType.SESSION )
	private Integer qtdCamadas;
	
	@Out(scope=ScopeType.SESSION )
	private String ambiente;
	
	private String urlServGeo;
	
	@Create
	public void init(){

		
		InitialContext jndiContext;
		try {
			jndiContext = new InitialContext();
			this.urlBaseMapServer = (String) jndiContext.lookup("urlBaseMapServer");
			
			this.urlBaseMap5000   = (String) jndiContext.lookup("urlBaseMap5000");
			
			this.urlAlinhamentos  = (String) jndiContext.lookup("urlAlinhamentos");
			
			this.urlAeroDep       = (String) jndiContext.lookup("urlAeroDep");
			
			this.urlAeroDmae       = (String) jndiContext.lookup("urlAeroDmae");			
			
			this.urlServGeo       = (String) jndiContext.lookup("urlServGeo");
			
			this.ambiente       = (String) jndiContext.lookup("ambiente");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
		
		if( StringUtils.isBlank(urlBaseMapServer )){
			
			this.urlBaseMapServer = "http://smurb_dm.procempa.com.br/arcgis/rest/services/SMURB_DM/Mapa_Base_de_Porto_Alegre_SMURB_Plano_Diretor_WEB_Final/MapServer" ;
			
		}
		if( StringUtils.isBlank(urlBaseMap5000 )){
			
			this.urlBaseMap5000 = "http://smurb_dm.procempa.com.br/arcgis/rest/services/SMURB_DM/Mapa_Base_de_Porto_Alegre_SMURB_Mapa_Base_5000_WEB/MapServer" ;
			
		}		
		
		
		HttpClient httpClient = new HttpClient();
		AuthScope auth = new AuthScope( AuthScope.ANY  );
		NTCredentials credential = new NTCredentials("user","senha" , "lproxy.procempa.com.br","pmpa" );
		httpClient.getState().setProxyCredentials(auth, credential);


		String urlTemp = urlBaseMapServer.substring(urlBaseMapServer.indexOf("/arcgis" ));
 
		 
		GetMethod get = new GetMethod();
		get.setPath( urlServGeo+urlTemp+"?f=pjson");

 
		String mapServerJson = null;
		
		try {
			int status = httpClient.executeMethod( get );
			if (status == HttpStatus.SC_OK) {
				mapServerJson = get.getResponseBodyAsString();
			}
		} catch (HttpException e) {

		} catch (IOException e) {

		}			
 
		try {
			JSONObject jsonObject = new JSONObject(mapServerJson);
			JSONArray jsonArray = jsonObject.getJSONArray("layers");
  
			JSONObject object;
			
			this.qtdCamadas = jsonArray.length();
			
			for (int i = 0; i < jsonArray.length(); i++) {
				object = (JSONObject) jsonArray.get( i );
				if( "Quarteirão".equals( object.get("name") ) ){
					camada_quarteirao = i;
				}
				if( "Mosaico de 1:1000".equals( object.get("name") ) ){
					mosaico_1_1000 = i;
				}
 
				if( "Mosaico da Rede de Esgoto".equals( object.get("name") ) ){
					mosaico_dep = i;
				}
				if( "Dutos de Esgoto".equals( object.get("name") ) ){
					camada_dmae = i;
				}
				
			}
 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 

}
