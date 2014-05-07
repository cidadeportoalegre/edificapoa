package br.com.procempa.dmweb.service;

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

@Stateless
@Name(CirculacaoService.SERVICE_NAME)
public class CirculacaoService implements ICirculacaoService{
	
	
		public static final String SERVICE_NAME = "circulacaoService";
		
		private EntityManager entityManager;

		 
		@SuppressWarnings("unchecked")
		@Override
		public List<Circulacao> findCirculacao(Integer codLogradouro, Integer imovelInicial, Integer imovelFinal ) {
	 
			Query query = getEntityManager().createNamedQuery(Circulacao.FIND_CIRCULACAO );
			
			query.setParameter("codLogradouro",  codLogradouro );
			query.setParameter("imovelInicial",  imovelInicial );
			query.setParameter("imovelFinal",  imovelFinal );			
	 
			List<Circulacao> circulacaoList =  query.getResultList();
			
			Integer indice = 0;
			for (Object object : circulacaoList) {
				Circulacao circulacao = (Circulacao) object;
				carregaObservacoes(circulacao);				
				indice++;
				circulacao.setIndice(indice);
			}
			
			
			return circulacaoList;			

		}
	 
		
		public Circulacao carregaObservacoes (Circulacao circulacao) {
			
			IObservacaoService observacaoService;
			
			observacaoService = (IObservacaoService) Component.getInstance(br.com.procempa.dmweb.service.ObservacaoService.class, true);	
			
			List<Observacao> observacaoList = new ArrayList<Observacao>(); 
			
			observacaoList = observacaoService.findObservacao(circulacao.getObservacao1(), "10");
			
			if ( ! observacaoList.isEmpty()) {
				circulacao.setObservacao1AsString(observacaoList.get(0).getDescricao());			
			}
			
			observacaoList = observacaoService.findObservacao(circulacao.getObservacao2(), "10");
			
			if ( ! observacaoList.isEmpty()) {
				circulacao.setObservacao2AsString(observacaoList.get(0).getDescricao());			
			}

			observacaoList = observacaoService.findObservacao(circulacao.getObservacao3(), "10");
			
			if ( ! observacaoList.isEmpty()) {
				circulacao.setObservacao3AsString(observacaoList.get(0).getDescricao());			
			}
			
			return circulacao;
			
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
