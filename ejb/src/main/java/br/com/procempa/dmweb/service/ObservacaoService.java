package br.com.procempa.dmweb.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

import br.com.procempa.dmweb.entity.Observacao;
import br.com.procempa.dmweb.entity.TipoObservacao;


@Stateless
@Name(ObservacaoService.SERVICE_NAME)
public class ObservacaoService implements IObservacaoService {
	
	public static final String SERVICE_NAME = "observacaoService";
	
	private EntityManager entityManager;

	


	@Override
	public List<Observacao> findObservacao(Integer codigo) {

		List<Observacao> observacaoList =  findObservacao(  codigo, getTipoObservacao("2") );
		
		return observacaoList;		
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<Observacao> findObservacao(Integer codigo, TipoObservacao tipoObservacao ) {
 
		
		Query query = getEntityManager().createNamedQuery(Observacao.FIND_OBSERVACAO );
		
		query.setParameter("codigo",  codigo );
		query.setParameter("tipoObservacao", tipoObservacao);
 
		List<Observacao> observacaoList =  query.getResultList();
		
		return observacaoList;			

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Observacao> findObservacao(Integer codigo, String tipoObservacao ) {
 
		
		Query query = getEntityManager().createNamedQuery(Observacao.FIND_OBSERVACAO );
		
		query.setParameter("codigo",  codigo );
		query.setParameter("tipoObservacao", getTipoObservacao(tipoObservacao));
 
		List<Observacao> observacaoList =  query.getResultList();
		
		return observacaoList;			

	}	
 
	@SuppressWarnings("unchecked")
	@Override
	public List<Observacao> listObservacaoPorTipo(String tipoObservacao ) {
 
		
		Query query = getEntityManager().createNamedQuery(Observacao.LIST_OBSERVACAO_POR_TIPO );
		
		query.setParameter("tipoObservacao", getTipoObservacao(tipoObservacao));
 
		List<Observacao> observacaoList =  query.getResultList();
		
		return observacaoList;			

	}	

	
	private TipoObservacao getTipoObservacao(String tipoObs) {
		
		String sql = "Select t from TipoObservacao t where t.id = " + tipoObs;
		
		Query query = getEntityManager().createQuery( sql );
		
		return (TipoObservacao) query.getSingleResult();
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
	
