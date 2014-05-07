package br.com.procempa.dmweb.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.InvalidValue;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.document.ByteArrayDocumentData;
import org.jboss.seam.document.DocumentData;
import org.jboss.seam.document.DocumentStore;
import org.jboss.seam.faces.FacesMessages;

import br.com.procempa.dmweb.entity.Observacao;
import br.com.procempa.dmweb.entity.legado.Circulacao;
import br.com.procempa.dmweb.entity.legado.DivisaoTerritorial;
import br.com.procempa.dmweb.entity.legado.DivisaoTerritorial130;
import br.com.procempa.dmweb.entity.legado.ExpedienteUnico;
import br.com.procempa.dmweb.entity.legado.PredioRelacionado;
import br.com.procempa.dmweb.entity.legado.RegimeUrbanistico;
import br.com.procempa.dmweb.service.ICirculacaoService;
import br.com.procempa.dmweb.service.IDivisaoTerritorial130Service;
import br.com.procempa.dmweb.service.IDivisaoTerritorialService;
import br.com.procempa.dmweb.service.IExpedienteUnicoService;
import br.com.procempa.dmweb.service.IObservacaoService;
import br.com.procempa.dmweb.service.IPredioRelacionadoService;
import br.com.procempa.dmweb.service.IRegimeUrbanisticoService;
import br.com.procempa.dmweb.util.ItemDM;


@Name("documentoAction")
@Scope(ScopeType.CONVERSATION)
public class DocumentoAction {

	private String criterioExpedienteUnico;
	private String criterioExpedienteUnicoDV;
	private String criterioAreaPrivativa;
	
	private String criterioCodigoCdl;
	
	
	private String criterioLogradouro;
	private String criterioEndereco;
	private String criterioNumero;
	
	private String criterioBairro;
	
	private String macrozona;
	private String ueu;
	private String quarteirao;
	
	/*
	 * valor de  urlAlinhamentos,  urlAeroDep e urlAeroDmae
	 * setado em InicializacaoAction
	 */
	@In(scope=ScopeType.SESSION, required=false )
	private String urlAlinhamentos;
	
	@In(scope=ScopeType.SESSION, required=false )
	private String urlAeroDep;

	@In(scope=ScopeType.SESSION, required=false )
	private String urlAeroDmae;
	
	@RequestParameter
	private String carta;
	
	@RequestParameter
	private String layerName;

	
	public String getLayerName() {
		return layerName;
	}


	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}


	@Out(required=false)
	@In(required=false)
	private DivisaoTerritorial divisaoTerritorial;
	
	@Out
	private List<ExpedienteUnico> expedienteUnicoList = new ArrayList<ExpedienteUnico>(0);
	
	@Out(required=false)
	@In(required=false)
	private List<DivisaoTerritorial> faceList = new ArrayList<DivisaoTerritorial>(0);
	
	@Out
	private List<RegimeUrbanistico> regimeUrbanisticoList = new ArrayList<RegimeUrbanistico>(0);

	@Out
	private List<ItemDM> itemDMList = new ArrayList<ItemDM>(0);
	
	@Out	
	private List<String> observacaoRegimeUrbanisticoList = new ArrayList<String>(0);

	@Out	
	private List<Circulacao> circulacaoList = new ArrayList<Circulacao>(0);

	@Out	
	private List<PredioRelacionado> predioRelacionadoList = new ArrayList<PredioRelacionado>(0);

	@Out	
	private List<DivisaoTerritorial130> divisaoTerritorial130List = new ArrayList<DivisaoTerritorial130>(0);
	
	@Out	
	private List<Observacao> observacaoFaceList = new ArrayList<Observacao>(0);

	@Out	
	private List<Observacao> observacoesFixasList = new ArrayList<Observacao>(0);
	
	public List<Circulacao> getCirculacaoList() {
		return circulacaoList;
	}



	@In 
	FacesMessages facesMessages;
	
	private  IExpedienteUnicoService expedienteUnicoService;
	
	private  IDivisaoTerritorialService divisaoTerritorialService;
	
	private  IRegimeUrbanisticoService regimeUrbanisticoService;	
	
	private  ICirculacaoService circulacaoService;	
	
	private  IPredioRelacionadoService predioRelacionadoService;
	
	private  IDivisaoTerritorial130Service divisaoTerritorial130Service;
	
	private  IObservacaoService observacaoService;
	

	
	@Begin(join=true)
	public void inicializa() {

		Conversation.instance().killAllOthers();

		expedienteUnicoList 			= new ArrayList<ExpedienteUnico>(0);
		faceList 						= new ArrayList<DivisaoTerritorial>(0);
		regimeUrbanisticoList 			= new ArrayList<RegimeUrbanistico>(0);
		itemDMList				 		= new ArrayList<ItemDM>(0);
		observacaoRegimeUrbanisticoList = new ArrayList<String>(0);
		circulacaoList 					= new ArrayList<Circulacao>(0);
		observacaoFaceList				= new ArrayList<Observacao>(0);

	}
	
	
	public String pesquisaExpedienteUnico() {
		
		
		if( "pesqEU".equals( definePesquisa() ) ) {
			if ( null == expedienteUnicoService ) {
				expedienteUnicoService = (IExpedienteUnicoService) Component.getInstance(br.com.procempa.dmweb.service.ExpedienteUnicoService.class, true);
			}
			expedienteUnicoList = 
			         expedienteUnicoService.findExpedienteUnico("002"+criterioExpedienteUnico+"00"+criterioExpedienteUnicoDV, getCriterioAreaPrivativa(), null);
			
			if( expedienteUnicoList.size() == 0 ) {
				
				facesMessages.add(new InvalidValue(String.format( "Expediente único %s não encontrado" , "002-"+criterioExpedienteUnico+"-00-"+criterioExpedienteUnicoDV ),
						            DocumentoAction.class, "criterioExpedienteUnico", getCriterioExpedienteUnico(), this));
				return "";
			}
			else {
				
				setCriterioBairro( ((ExpedienteUnico)expedienteUnicoList.get(0)).getBairro() );
				
				if ( null == divisaoTerritorialService ) {
					divisaoTerritorialService = (IDivisaoTerritorialService) Component.getInstance(br.com.procempa.dmweb.service.DivisaoTerritorialService.class, true);
				}
				
				faceList = divisaoTerritorialService.findDivisaoTerritorialcomExpedienteUnico( expedienteUnicoList.get(0) );
			}
			
			
		}
		if( "pesqLogradouro".equals( definePesquisa() ) ) {
			if ( null == divisaoTerritorialService ) {
				divisaoTerritorialService = (IDivisaoTerritorialService) Component.getInstance(br.com.procempa.dmweb.service.DivisaoTerritorialService.class, true);
			}
			
			faceList = divisaoTerritorialService.findDivisaoTerritorialcomLogradouro(new BigDecimal( criterioCodigoCdl ), new BigDecimal( criterioNumero ) );

		}		
		
		
		if (faceList != null && faceList.size() > 0) {
			for (DivisaoTerritorial dt : faceList) {
				dt.setFaceSelecionada( true );
			}
			
			montaDM();
		}	
		
		
		if( faceList.size() > 0 ){
			setDivisaoTerritorial( faceList.get(0) ); 
			setMacrozona( faceList.get(0).getDivisaoTerritorialId().getMacrozonaAsString());
			setUeu( faceList.get(0).getDivisaoTerritorialId().getUeuAsString());
			setQuarteirao( faceList.get(0).getDivisaoTerritorialId().getQuarteiraoAsString());
		} else {
			facesMessages.add(new InvalidValue("Endereço não cadastrado no PDDUA", DocumentoAction.class, "criterioNumero", getCriterioNumero(), this));
			return "";
		}
			
		 
		return "lista";
	}

	
	private String definePesquisa() {
		
		String entradaValida = "";
 
		
		// Logradouro
		if( StringUtils.isNotBlank( getCriterioCodigoCdl() )) {
			if( StringUtils.isNotBlank( getCriterioNumero() )) {
				return "pesqLogradouro";
			}
		}
		// Expediente Unico
		if( StringUtils.isNotBlank( getCriterioExpedienteUnico() )) {
			if( StringUtils.isNotBlank( getCriterioExpedienteUnicoDV() )) {
				return "pesqEU";
			}
		}
		// CTM
		if( StringUtils.isNotBlank( getCriterioCTM() )){
			return "pesqCTM";
		}
		
		return entradaValida;
	}
	
	public void montaDM( DivisaoTerritorial div ){
	//	setItemDMList(new ArrayList<ItemDM>());
 
			if (div.getFaceSelecionada() ) {
				
				BigDecimal codLogradouro = div.getDivisaoTerritorialId().getCodLogradouro();
				BigDecimal numeroInicial = div.getLimiteInicial();
				BigDecimal numeroFinal = div.getLimiteFinal();
				Integer sequencia = div.getSequenciaFinal();
				pesquisaPredioRelacionado(macrozona, ueu, quarteirao, codLogradouro, sequencia);
				pesquisaRegimeUrbanistico(codLogradouro, numeroInicial, numeroFinal);
				pesquisaCirculacao(codLogradouro, numeroInicial, numeroFinal);				
				ItemDM itemDM = new ItemDM( div.getLogradouro(), div.getSituacaoLogradouro(), "REGIME URBANÍSTICO",
						regimeUrbanisticoList, observacaoRegimeUrbanisticoList, "SISTEMA DE CIRCULAÇÃO", 
						circulacaoList, predioRelacionadoList, observacaoFaceList );
				if( !getItemDMList().contains(div)) {
					getItemDMList().add( itemDM ); 
				}

			}
			
			pesquisaObservacoesFixas();
	 
	} 	
	
	public void montaDM(){
		setItemDMList(new ArrayList<ItemDM>());
		for( int i = 0; i < faceList.size(); i++ ){
			if (faceList.get(i).getFaceSelecionada() ) {
				BigDecimal codLogradouro = faceList.get(i).getDivisaoTerritorialId().getCodLogradouro();
				BigDecimal numeroInicial = faceList.get(i).getLimiteInicial();
				BigDecimal numeroFinal = faceList.get(i).getLimiteFinal();			
				Integer sequencia = faceList.get(i).getSequenciaFinal();
				pesquisaPredioRelacionado(faceList.get(i).getDivisaoTerritorialId().getMacrozonaAsString(), faceList.get(i).getDivisaoTerritorialId().getUeuAsString(), faceList.get(i).getDivisaoTerritorialId().getQuarteiraoAsString(), codLogradouro, sequencia);
				pesquisaRegimeUrbanistico(codLogradouro, numeroInicial, numeroFinal);
				pesquisaCirculacao(codLogradouro, numeroInicial, numeroFinal);		
				pesquisaObservacoesFace(faceList.get(i).getDivisaoTerritorialId().getMacrozonaAsString(), faceList.get(i).getDivisaoTerritorialId().getUeuAsString(), faceList.get(i).getDivisaoTerritorialId().getQuarteiraoAsString(), codLogradouro, sequencia);
				ItemDM itemDM = new ItemDM(faceList.get(i).getLogradouro(), faceList.get(i).getSituacaoLogradouro(), "REGIME URBANÍSTICO",
						             regimeUrbanisticoList, observacaoRegimeUrbanisticoList, "SISTEMA DE CIRCULAÇÃO", 
						             circulacaoList, predioRelacionadoList, observacaoFaceList);
				getItemDMList().add(itemDM); 
				
				
			}
		}
		
		pesquisaObservacoesFixas();		
	} 	
	
	public void pesquisaRegimeUrbanistico (BigDecimal codLogradouro, BigDecimal numeroInicial, BigDecimal numeroFinal) {
		
		if( regimeUrbanisticoService == null ) {
			regimeUrbanisticoService = (IRegimeUrbanisticoService) Component.getInstance(br.com.procempa.dmweb.service.RegimeUrbanisticoService.class, true);		
		}
		
		regimeUrbanisticoList = regimeUrbanisticoService.findRegimeUrbanistico(codLogradouro, numeroInicial, numeroFinal);
		
	}

	public void pesquisaCirculacao (BigDecimal codLogradouro, BigDecimal numeroInicial, BigDecimal numeroFinal) {
		
		if( circulacaoService == null ) {
			circulacaoService = (ICirculacaoService) Component.getInstance(br.com.procempa.dmweb.service.CirculacaoService.class, true);		
		}
		
		circulacaoList = circulacaoService.findCirculacao(codLogradouro.intValue(), numeroInicial.intValue(), numeroFinal.intValue());
		
	}

	public void pesquisaPredioRelacionado (String macrozona, String ueu, String quarteirao, BigDecimal codLogradouro, Integer sequencia) {
		
		if( predioRelacionadoService == null ) {
			predioRelacionadoService = (IPredioRelacionadoService) Component.getInstance(br.com.procempa.dmweb.service.PredioRelacionadoService.class, true);		
		}
		
		predioRelacionadoList = predioRelacionadoService.findPredioRelacionado( macrozona, ueu, quarteirao, codLogradouro, sequencia);
		
	}

	public void pesquisaObservacoesFace (String macrozona, String ueu, String quarteirao, BigDecimal codLogradouro, Integer sequencia) {
		
		if( divisaoTerritorial130Service == null ){
			divisaoTerritorial130Service = (IDivisaoTerritorial130Service) Component.getInstance(br.com.procempa.dmweb.service.DivisaoTerritorial130Service.class, true);		
		}
		
		divisaoTerritorial130List = divisaoTerritorial130Service.findDivisaoTerritorial130(codLogradouro, new BigDecimal(Integer.parseInt(macrozona) + 900), new BigDecimal(ueu), new BigDecimal(quarteirao), new BigDecimal(sequencia));
		
		if( observacaoService == null ) {
			observacaoService = (IObservacaoService) Component.getInstance(br.com.procempa.dmweb.service.ObservacaoService.class, true);
		}
		
		observacaoFaceList = new ArrayList<Observacao>(0);
		
		for (Object object : divisaoTerritorial130List) {
			DivisaoTerritorial130 divisaoTerritorial130 = (DivisaoTerritorial130) object;
			List<Observacao> observacaoFaceListAux = new ArrayList<Observacao>();
			observacaoFaceListAux = observacaoService.findObservacao(divisaoTerritorial130.getDivisaoTerritorial130Id().getObservacao(), "1");			
			for (Object object1 : observacaoFaceListAux) {
				observacaoFaceList.add( (Observacao) object1 );
			}
		}
		
	}
	
	public void pesquisaObservacoesFixas() {
		observacoesFixasList = observacaoService.listObservacaoPorTipo("6");
	}
	
	public String abrePdf() throws NullPointerException, IOException {

	         String cartaId = getCarta();
	         ByteArrayOutputStream output = new ByteArrayOutputStream();
	         
	         String retorno = "";
	         
	         HttpClient httpClient = new HttpClient();
				AuthScope auth = new AuthScope( AuthScope.ANY  );
				NTCredentials credential = new NTCredentials("user","senha" , "lproxy.procempa.com.br","pmpa" );
				httpClient.getState().setProxyCredentials(auth, credential);
				
				InputStream is = getPdf(cartaId, httpClient);
	         
	        try {

				copy(is, output);
 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
				if (cartaId.length() > 3 ) {
					cartaId = "00000000";
				} else {
					cartaId = "000";					
				}
				
				is = getPdf(cartaId, httpClient);
				copy(is, output);				
			}

	         DocumentStore documentStore = DocumentStore.instance();

	         cartaId = documentStore.newId();
	         DocumentData data = new ByteArrayDocumentData("carta" + cartaId,
	                 new DocumentData.DocumentType("pdf", "application/pdf"), output.toByteArray());
	         data.setDisposition("inline");

	         documentStore.saveData(cartaId, data);
	         
	 		if (getLayerName().equalsIgnoreCase("alinhamentoResults") ) {			
				retorno = "/alinhamento.pdf?docId=";
			} else 
				if (getLayerName().equalsIgnoreCase("depResults") ) {
					retorno = "/aerodep.pdf?docId=";
			} else 
				if (getLayerName().equalsIgnoreCase("dmaeAguaResults") ) {
					retorno = "/aerodmae.pdf?docId=";
			}
	         
	         return  retorno + cartaId;	         
		
	}

	private String addZeros( String str ) {
		if ( str.length() < 3)  {
			str = "0".concat(str);
			addZeros(str);			
		}

		return str;
	}
	private InputStream getPdf ( String cartaId, HttpClient httpClient) {
		GetMethod get = new GetMethod();
		
		
		//		if (cartaId.length() > 3 ) {
		
		if (getLayerName().equalsIgnoreCase("alinhamentoResults") ) {			
			get.setPath( urlAlinhamentos + cartaId + ".pdf" );
//			get.setPath( "http://geopmpa.pmpa.ad/SPM/ALINHAMENTOS/"+cartaId+".pdf" );
		} else 
			if (getLayerName().equalsIgnoreCase("depResults") ) {
				get.setPath( urlAeroDep + cartaId + ".pdf" );
//				get.setPath( "http://geopmpa.pmpa.ad/DEP/RASTER/MAPA_2000/CADASTRO%20DEP/AERO%20"+cartaId+".pdf" );	
		} else 
			if (getLayerName().equalsIgnoreCase("dmaeAguaResults") ) {
				cartaId = addZeros(cartaId);				
				get.setPath( urlAeroDmae + cartaId + ".pdf" );
//				get.setPath( "http://geopmpa.pmpa.ad/DMAE/AGUA/PDF/AEROS/"+cartaId+".pdf" );	
		}

		
		InputStream is = null;
		try {
			int status = httpClient.executeMethod( get );
			if (status == HttpStatus.SC_OK) {
				is = get.getResponseBodyAsStream();

			}
		} catch (HttpException e) {

		} catch (IOException e) {
			
		}
		return is;

	}
	
    private void copy(InputStream in, ByteArrayOutputStream out) throws IOException, NullPointerException {
        byte[] buffer = new byte[2048];
        int read;
        
        	while ((read = in.read(buffer)) != -1) {
        		out.write(buffer, 0, read);
        	}

    }
	
    public void imprimeDM(){

    }
	
     
 	private String getCriterioCTM() {
		// TODO Auto-generated method stub
		return null;
	}
 	
 	public String voltaPraHome() {
 		return "voltar";
 	}


	public String getCriterioExpedienteUnico() {
		return criterioExpedienteUnico;
	}

	public void setCriterioExpedienteUnico(String criterioExpedienteUnico) {
		this.criterioExpedienteUnico = criterioExpedienteUnico;
	}

	public String getCriterioExpedienteUnicoDV() {
		return criterioExpedienteUnicoDV;
	}

	public void setCriterioExpedienteUnicoDV(String criterioExpedienteUnicoDV) {
		this.criterioExpedienteUnicoDV = criterioExpedienteUnicoDV;
	}

	public List<ExpedienteUnico> getExpedienteUnicoList() {
		return expedienteUnicoList;
	}

	public void setExpedienteUnicoList(List<ExpedienteUnico> expedienteUnicoList) {
		this.expedienteUnicoList = expedienteUnicoList;
	}

	public String getCriterioEndereco() {
		return criterioEndereco;
	}

	public void setCriterioEndereco(String criterioEndereco) {
		this.criterioEndereco = criterioEndereco;
	}

	public String getCriterioNumero() {
		return criterioNumero;
	}

	public void setCriterioNumero(String criterioNumero) {
		this.criterioNumero = criterioNumero;
	}


	public String getCriterioCodigoCdl() {
		return criterioCodigoCdl;
	}


	public void setCriterioCodigoCdl(String criterioCodigoCdl) {
		this.criterioCodigoCdl = criterioCodigoCdl;
	}


	public List<DivisaoTerritorial> getFaceList() {
		return faceList;
	}


	public void setFaceList(List<DivisaoTerritorial> faceList) {
		this.faceList = faceList;
	}

	public String getMacrozona() {
		return macrozona;
	}


	public void setMacrozona(String macrozona) {
		this.macrozona = macrozona;
	}


	public String getUeu() {
		return ueu;
	}


	public void setUeu(String ueu) {
		this.ueu = ueu;
	}


	public String getQuarteirao() {
		return quarteirao;
	}


	public void setQuarteirao(String quarteirao) {
		this.quarteirao = quarteirao;
	}


	public String getCriterioBairro() {
		return criterioBairro;
	}


	public void setCriterioBairro(String criterioBairro) {
		this.criterioBairro = criterioBairro;
	}


	public List<ItemDM> getItemDMList() {
		return itemDMList;
	}


	public void setItemDMList(List<ItemDM> itemDMList) {
		this.itemDMList = itemDMList;
	}
	
	private void setDivisaoTerritorial(DivisaoTerritorial divisaoTerritorial) {
		this.divisaoTerritorial = divisaoTerritorial;
	}
	
	public DivisaoTerritorial getDivisaoTerritorial() {
		return divisaoTerritorial;
	}


	public String getCriterioLogradouro() {
		return criterioLogradouro;
	}


	public void setCriterioLogradouro(String criterioLogradouro) {
		this.criterioLogradouro = criterioLogradouro;
	}


	public String getCriterioAreaPrivativa() {
		return criterioAreaPrivativa;
	}


	public void setCriterioAreaPrivativa(String criterioAreaPrivativa) {
		this.criterioAreaPrivativa = criterioAreaPrivativa;
	}


	public String getCarta() {
		return carta;
	}


	public void setCarta(String carta) {
		this.carta = carta;
	}


	public List<Observacao> getObservacoesFixasList() {
		return observacoesFixasList;
	}


	public void setObservacoesFixasList(List<Observacao> observacoesFixasList) {
		this.observacoesFixasList = observacoesFixasList;
	}

	public void setCirculacaoList(List<Circulacao> circulacaoList) {
		this.circulacaoList = circulacaoList;
	}


	public List<String> getObservacaoRegimeUrbanisticoList() {
		return observacaoRegimeUrbanisticoList;
	}


	public void setObservacaoRegimeUrbanisticoList(
			List<String> observacaoRegimeUrbanisticoList) {
		this.observacaoRegimeUrbanisticoList = observacaoRegimeUrbanisticoList;
	}

	
	
}
