package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

import br.com.procempa.dmweb.entity.Observacao;
import br.com.procempa.dmweb.entity.legado.Circulacao;
import br.com.procempa.dmweb.entity.legado.RegimeUrbanistico;
import br.com.procempa.dmweb.entity.legado.RegimeUrbanistico0300;



@Stateless
@Name(RegimeUrbanisticoService.SERVICE_NAME)
public class RegimeUrbanisticoService implements IRegimeUrbanisticoService {
	
	public static final String SERVICE_NAME = "regimeUrbanisticoService";
	
	private EntityManager entityManager;

	 
	@SuppressWarnings("unchecked")
	@Override
	public List<RegimeUrbanistico> findRegimeUrbanistico(BigDecimal codLogradouro, BigDecimal numeroInicial, BigDecimal numeroFinal ) {
 
		Query query = getEntityManager().createNamedQuery(RegimeUrbanistico.FIND_REGIME_URBANISTICO );
		
		query.setParameter("codLogradouro",  codLogradouro.intValue() );
		query.setParameter("numeroInicial", numeroInicial.intValue());
		query.setParameter("numeroFinal", numeroFinal.intValue() );
 
		List<RegimeUrbanistico> ruList =  query.getResultList();
		
		Integer indice = 0;
		
		for (Object object : ruList) {
			RegimeUrbanistico regimeUrbanistico = (RegimeUrbanistico) object;
			
			carregaObservacoes(regimeUrbanistico);
			carregaObservacoesSubunidade(regimeUrbanistico );			
			indice++;
			regimeUrbanistico.setIndice(indice);
		}

// Se tiver mais de um Regime Urbanístico, deve-se colocar o  Sistema de Circulação apenas após o último Regime, pois é o mesmo para todos RU e aparecerá na lista só uma vez, logo abaixo do último RU.	
// Se a face não tiver Regime Urbanístico, deve-se colocar o Sistema de Circulação na primeira ocorrência da lista.
//		if ( ruList.size() > 0 ) {
//			carregaCirculacao(ruList.get(ruList.size() - 1 ), codLogradouro.intValue(), numeroInicial.intValue(), numeroFinal.intValue());			
//		} else {
//			ruList.add(null);
//			carregaCirculacao(ruList.get(0), codLogradouro.intValue(), numeroInicial.intValue(), numeroFinal.intValue());			
//		}

		
		return ruList;

	}
 
	@SuppressWarnings("unchecked")	
	public List<RegimeUrbanistico0300> findRegimeUrbanistico0300(Integer macrozona, Integer ueu, Integer subUnidade) {

		Query query = getEntityManager().createNamedQuery(RegimeUrbanistico0300.FIND_REGIME_URBANISTICO_0300 );
		
		query.setParameter("macrozona",  macrozona );
		query.setParameter("ueu", ueu);
		query.setParameter("subunidade", subUnidade );
 
		List<RegimeUrbanistico0300> ru0300List = query.getResultList();
		
		return ru0300List;
		
		
	}
	
	public RegimeUrbanistico carregaObservacoesSubunidade(RegimeUrbanistico regimeUrbanistico) {
		
		Integer macrozona = regimeUrbanistico.getMacrozona();
		Integer ueu = regimeUrbanistico.getUeu();
		Integer subUnidade = regimeUrbanistico.getSubunidade();
		
		List<RegimeUrbanistico0300> ru0300List = findRegimeUrbanistico0300(macrozona, ueu, subUnidade);
		
		IObservacaoService observacaoService;
		
		observacaoService = (IObservacaoService) Component.getInstance(br.com.procempa.dmweb.service.ObservacaoService.class, true);	
		
		List<Observacao> observacaoList = new ArrayList<Observacao>(); 

		List<String> obsSubUnidadeListAux = new ArrayList<String>(0);
		
		for (Object object : ru0300List) {
			RegimeUrbanistico0300 regimeUrbanistico0300 = (RegimeUrbanistico0300) object;
			
			observacaoList = observacaoService.findObservacao(regimeUrbanistico0300.getObsReg());
			
			obsSubUnidadeListAux.add(observacaoList.get(0).getDescricao());
		}		

		regimeUrbanistico.setObservacoesSubUnidadeList(obsSubUnidadeListAux);
		
		return regimeUrbanistico;
		
	}

	public RegimeUrbanistico carregaObservacoes (RegimeUrbanistico regimeUrbanistico) {
		
		IObservacaoService observacaoService;
		
		observacaoService = (IObservacaoService) Component.getInstance(br.com.procempa.dmweb.service.ObservacaoService.class, true);	
		
		List<Observacao> observacaoList = new ArrayList<Observacao>(); 
		
		observacaoList = observacaoService.findObservacao(regimeUrbanistico.getObsReg1());
		
		if ( ! observacaoList.isEmpty()) {
			regimeUrbanistico.setObsReg1Descricao(observacaoList.get(0).getDescricao());			
		}
		
		observacaoList = observacaoService.findObservacao(regimeUrbanistico.getObsReg2());
		
		if ( ! observacaoList.isEmpty()) {
			regimeUrbanistico.setObsReg2Descricao(observacaoList.get(0).getDescricao());			
		}

		observacaoList = observacaoService.findObservacao(regimeUrbanistico.getObsReg3());
		
		if ( ! observacaoList.isEmpty()) {
			regimeUrbanistico.setObsReg3Descricao(observacaoList.get(0).getDescricao());			
		}

		observacaoList = observacaoService.findObservacao(regimeUrbanistico.getObsReg4());
		
		if ( ! observacaoList.isEmpty()) {
			regimeUrbanistico.setObsReg4Descricao(observacaoList.get(0).getDescricao());			
		}

		observacaoList = observacaoService.findObservacao(regimeUrbanistico.getObsReg5());
		
		if ( ! observacaoList.isEmpty()) {
			regimeUrbanistico.setObsReg5Descricao(observacaoList.get(0).getDescricao());			
		}

		observacaoList = observacaoService.findObservacao(regimeUrbanistico.getObsReg6());
		
		if ( ! observacaoList.isEmpty()) {
			regimeUrbanistico.setObsReg6Descricao(observacaoList.get(0).getDescricao());			
		}
		
		return regimeUrbanistico;
		
	}
	

//	public RegimeUrbanistico carregaCirculacao (RegimeUrbanistico regimeUrbanistico, Integer codLogradouro, Integer numeroInicial, Integer numeroFinal) {
//		
//		ICirculacaoService circulacaoService;
//		
//		circulacaoService = (ICirculacaoService) Component.getInstance(br.com.procempa.dmweb.service.CirculacaoService.class, true);	
//		
//		List<Circulacao> circulacaoList = new ArrayList<Circulacao>(); 
//		
//		circulacaoList = circulacaoService.findCirculacao(codLogradouro, numeroInicial, numeroFinal);
//		
//		if ( ! circulacaoList.isEmpty()) {
//			regimeUrbanistico.setCircAlinhamento(circulacaoList.get(0).getAlinhamento());			
//			regimeUrbanistico.setCircReferencia(circulacaoList.get(0).getReferencia().toLowerCase());
//			regimeUrbanistico.setCircLarguraLogr(circulacaoList.get(0).getLarguraLogradouro());			
//		}
//		
//		
//		return regimeUrbanistico;
//		
//	}
	
	
	
	public EntityManager getEntityManager() {
		
		if( null == entityManager ||  ! entityManager.isOpen() ) {
			entityManager = (EntityManager) Component.getInstance("entityManager" );
		}
		
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
