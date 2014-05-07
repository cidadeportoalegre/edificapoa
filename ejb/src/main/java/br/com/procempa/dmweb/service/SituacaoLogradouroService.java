package br.com.procempa.dmweb.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

import br.com.procempa.dmweb.entity.SituacaoLogradouro;

@Stateless
@Name(SituacaoLogradouroService.SERVICE_NAME)
public class SituacaoLogradouroService implements ISituacaoLogradouroService {
	public static final String SERVICE_NAME = "situacaoLogradouroService";
	
	private EntityManager entityManager;

	 
	@SuppressWarnings("unchecked")
	@Override
	public 	List<SituacaoLogradouro> findSituacaoLogradouro( Integer codigoSituacao ) {
 
		Query query = getEntityManager().createNamedQuery(SituacaoLogradouro.FIND_SITUACAO_LOGRADOURO );
		
		query.setParameter("codigoSituacao",  codigoSituacao );
 
		List<SituacaoLogradouro> situacaoLogradouroList = query.getResultList();
		
		return situacaoLogradouroList;

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

