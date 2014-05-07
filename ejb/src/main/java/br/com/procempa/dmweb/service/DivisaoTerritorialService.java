package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

import br.com.procempa.cdl.client.ClienteCDL;
import br.com.procempa.dmweb.entity.SituacaoLogradouro;
import br.com.procempa.dmweb.entity.legado.DivisaoTerritorial;
import br.com.procempa.dmweb.entity.legado.ExpedienteUnico;



@Stateless
@Name(DivisaoTerritorialService.SERVICE_NAME)
public class DivisaoTerritorialService implements IDivisaoTerritorialService {
	
	public static final String SERVICE_NAME = "divisaoTerritorialService";
	
	private EntityManager entityManager;

	 
	@SuppressWarnings("unchecked")
	@Override
	public List<DivisaoTerritorial> findDivisaoTerritorialcomExpedienteUnico(ExpedienteUnico eu ) {
 
		getEntityManager().clear();
		Query query = getEntityManager().createNamedQuery(DivisaoTerritorial.FIND_QUARTEIRAO );
		
		query.setParameter("codLogradouro",  eu.getExpedienteUnicoId().getCodLogradouro()  );
		query.setParameter("limiteInicial", eu.getExpedienteUnicoId().getNumImovel()  );
		query.setParameter("limiteFinal", eu.getExpedienteUnicoId().getNumImovel() );
		query.setParameter("macrozona", new BigDecimal(900)  );
 
				
		DivisaoTerritorial dv = null;
		
		List<DivisaoTerritorial> dvlist = query.getResultList();
		// escolher o lado par/impar
		for (DivisaoTerritorial divisaoTerritorial : dvlist) {
			if(eu.getExpedienteUnicoId().getNumImovel().intValue()%2 - divisaoTerritorial.getLimiteInicial().intValue()%2 ==0){
				 dv = divisaoTerritorial;
				 break;
			}
		}
		
		List<DivisaoTerritorial> faces = new ArrayList<DivisaoTerritorial>(0);
		if( null != dv ){
			
			query = getEntityManager().createNamedQuery(DivisaoTerritorial.FACES_QUARTEIRAO );
			
			query.setParameter("ueu",  dv.getDivisaoTerritorialId().getUeu()   );
			query.setParameter("quarteirao", dv.getDivisaoTerritorialId().getQuarteirao()   );
			query.setParameter("macrozona", dv.getDivisaoTerritorialId().getMacrozona() );
			
			List<DivisaoTerritorial> facesDv = query.getResultList();
			
			faces = obtemFacesOrdenadas(eu.getExpedienteUnicoId().getCodLogradouro(), eu.getExpedienteUnicoId().getNumImovel(), facesDv);
		}
 
		return faces;

	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<DivisaoTerritorial> findDivisaoTerritorialcomLogradouro(BigDecimal codLogradouro, BigDecimal nroImovel ) {
		
		getEntityManager().clear();
		Query query = getEntityManager().createNamedQuery(DivisaoTerritorial.FIND_QUARTEIRAO );
		
		query.setParameter("codLogradouro",  codLogradouro   );
		query.setParameter("limiteInicial", nroImovel   );
		query.setParameter("limiteFinal", nroImovel  );
		query.setParameter("macrozona", new BigDecimal(900)  );
 
				
		DivisaoTerritorial dv = null;
		
		List<DivisaoTerritorial> dvlist = query.getResultList();
		// escolher o lado par/impar
		for (DivisaoTerritorial divisaoTerritorial : dvlist) {
			if(nroImovel.intValue()%2 - divisaoTerritorial.getLimiteInicial().intValue()%2 ==0){
				 dv = divisaoTerritorial;
				 break;
			}
		}
		
		List<DivisaoTerritorial> faces = new ArrayList<DivisaoTerritorial>(0);
				
		if( null != dv ){
			
			query = getEntityManager().createNamedQuery(DivisaoTerritorial.FACES_QUARTEIRAO );
			
			query.setParameter("ueu",  dv.getDivisaoTerritorialId().getUeu()   );
			query.setParameter("quarteirao", dv.getDivisaoTerritorialId().getQuarteirao()   );
			query.setParameter("macrozona", dv.getDivisaoTerritorialId().getMacrozona() );
			
			List<DivisaoTerritorial> facesDv = query.getResultList();
			
			faces = obtemFacesOrdenadas(codLogradouro, nroImovel, facesDv);
		}
 
		
		
		return faces;

	}
	
	private List<DivisaoTerritorial> obtemFacesOrdenadas(BigDecimal codLogradouroInicial, BigDecimal nroImovel, List<DivisaoTerritorial> divisaoTerritorialList) {
		
		Set<DivisaoTerritorial> set = new LinkedHashSet<DivisaoTerritorial>();
		
		if (divisaoTerritorialList != null && divisaoTerritorialList.size() > 0) {
			DivisaoTerritorial faceInicial = obtemFacePorCodLogradouro(divisaoTerritorialList, codLogradouroInicial, null, nroImovel.intValue());
			if(faceInicial != null){
				set.add(faceInicial);				
			}
			DivisaoTerritorial proximaFace = faceInicial;
			BigDecimal logradouro = null;
			Integer sequencia = null;
//			System.out.println(""+faceInicial.getCadastroDteId().getCodLogradouroAsString()+" # "+
//					faceInicial.getCadastroDteId().getMacrozonaAsString()+" # "+
//					faceInicial.getCadastroDteId().getUeuAsString()+" # "+
//					faceInicial.getCadastroDteId().getQuarteiraoAsString()+" # "+
//					faceInicial.getCadastroDteId().getSequenciaAsString()+" # "+
//					faceInicial.getCadastroDteId().getDataFace()+" # ");
			while (true) {
				if(proximaFace==null){
					break;
				}
				else{
					if ("S".equals(proximaFace.getNumeracaoInvertida())) {
						logradouro = proximaFace.getLogradouroFinal();
						sequencia = proximaFace.getSequenciaFinal();
					}
					else {
						logradouro = proximaFace.getLogradouroInicial();
						sequencia = proximaFace.getSequenciaInicial();
					}
					proximaFace = obtemFacePorCodLogradouro(divisaoTerritorialList, logradouro, sequencia, null);
//					System.out.println(""+proximaFace.getCadastroDteId().getCodLogradouroAsString()+" # "+
//							proximaFace.getCadastroDteId().getMacrozonaAsString()+" # "+
//							proximaFace.getCadastroDteId().getUeuAsString()+" # "+
//							proximaFace.getCadastroDteId().getQuarteiraoAsString()+" # "+
//							proximaFace.getCadastroDteId().getSequenciaAsString()+" # "+
//							proximaFace.getCadastroDteId().getDataFace()+" # ");
					
					if (proximaFace == null || proximaFace.equals(faceInicial)) {
						break;
					}
					else {
						if(set.contains(proximaFace)){
//							form.getValidateMessages().addException("Quarteirão provavelmente inconsistente!");
							break;
						}
						set.add(proximaFace);
					}
				}
			};
			for (Object object : divisaoTerritorialList) {
				DivisaoTerritorial divisaoTerritorial = (DivisaoTerritorial) object;
				
				carregaDadosCdl( divisaoTerritorial );
				
				carregaSituacaoLogradouro(divisaoTerritorial);
				
				if ( ! set.contains(divisaoTerritorial) ) {
					set.add( divisaoTerritorial );
				}
			}
		}
		return  new ArrayList<DivisaoTerritorial>(set);
	}

	
	private DivisaoTerritorial obtemFacePorCodLogradouro(List<DivisaoTerritorial> itemsCollection, BigDecimal codLogradouro, Integer sequencia, Integer nroImovel) {
		DivisaoTerritorial proximaFace = null;
		for (Object object : itemsCollection) {
			DivisaoTerritorial cdtedo = (DivisaoTerritorial) object;
			if (cdtedo.getDivisaoTerritorialId().getCodLogradouro().equals(codLogradouro)) {
				if(nroImovel!=null){
					if(nroImovel%2 - cdtedo.getLimiteInicial().intValue()%2 ==0){
						int limiteInicial;
						int limiteFinal;
						if(cdtedo.getLimiteInicial().intValue()>cdtedo.getLimiteFinal().intValue()){
							limiteInicial = cdtedo.getLimiteFinal().intValue();
							limiteFinal = cdtedo.getLimiteInicial().intValue();
						}
						else {
							limiteInicial = cdtedo.getLimiteInicial().intValue();
							limiteFinal = cdtedo.getLimiteFinal().intValue();
						}
						if(nroImovel>= limiteInicial && nroImovel<=limiteFinal){
							proximaFace = cdtedo;
							break;
						}
							
					}
				}
				else if(sequencia!=null){
					if(cdtedo.getDivisaoTerritorialId().getSequencia().intValue()==sequencia.intValue()){
						proximaFace = cdtedo;
						break;						
					}
				}
			}
		}
		return proximaFace;
	}
	
	public DivisaoTerritorial carregaDadosCdl(DivisaoTerritorial divisaoTerritorial) {
		try {
			ClienteCDL cdl = ClienteCDL.load(
					divisaoTerritorial.getDivisaoTerritorialId().getCodLogradouroAsString(), 
					divisaoTerritorial.getLimiteInicialAsString() );
			divisaoTerritorial.setLogradouro(cdl.getNomeLogr());
			 
//			inscricao.getEndereco().setBairro(cdl.getNomeBairro());
//			inscricao.getEndereco().setCepFormatado(cdl.getCep());
//			inscricao.getEndereco().setMunicipio("Porto Alegre");
//			inscricao.getEndereco().setUf(Uf.RS);
		} catch (Exception e) {
			e.printStackTrace();
			divisaoTerritorial.setLogradouro("Não encontrado ou serviço indisponível");
		}
		
		return divisaoTerritorial;
	}

	public DivisaoTerritorial carregaSituacaoLogradouro (DivisaoTerritorial divisaoTerritorial) {
		
		ISituacaoLogradouroService situacaoLogradouroService;
		
		situacaoLogradouroService = (ISituacaoLogradouroService) Component.getInstance(br.com.procempa.dmweb.service.SituacaoLogradouroService.class, true);
		
		String situacaoFinalLogradouro = "";
		
		List <SituacaoLogradouro> situacaoLogradouroList = situacaoLogradouroService.findSituacaoLogradouro(divisaoTerritorial.getSituacaoInicial().intValue());
		
		divisaoTerritorial.setSituacaoLogradouro(situacaoLogradouroList.get(0).getDescricao());
		
		
		if (divisaoTerritorial.getSituacaoFinal().intValue() > 0 ) {
			
			situacaoFinalLogradouro = situacaoLogradouroService.findSituacaoLogradouro(divisaoTerritorial.getSituacaoFinal().intValue()).get(0).getDescricao();
			
			if (divisaoTerritorial.getLimiteSituacao().intValue() > 0 ) {
				divisaoTerritorial.setSituacaoLogradouro(divisaoTerritorial.getSituacaoLogradouro() + " até número " + divisaoTerritorial.getLimiteSituacao().toString() + ", e após " + situacaoFinalLogradouro);
			} else {
				
				if (divisaoTerritorial.getDistanciaSituacao().intValue() > 0 ) {
					divisaoTerritorial.setSituacaoLogradouro(divisaoTerritorial.getSituacaoLogradouro() + " até distância " + divisaoTerritorial.getDistanciaSituacao().toString() + "m, e após " + situacaoFinalLogradouro);
				}

			}

			
		}
		
		return divisaoTerritorial;
		
	}

	
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
