package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

import br.com.procempa.dmweb.entity.legado.Circulacao;
import br.com.procempa.dmweb.entity.legado.PredioRelacionado;

@Stateless
@Name(PredioRelacionadoService.SERVICE_NAME)
public class PredioRelacionadoService implements IPredioRelacionadoService{
	
	
		public static final String SERVICE_NAME = "predioRelacionadoService";
		
		private EntityManager entityManager;

		 
		@SuppressWarnings("unchecked")
		@Override
		public List<PredioRelacionado> findPredioRelacionado(String macrozona, String ueu, String quarteirao, BigDecimal codLogradouro, Integer sequencia ) {
	 
			Query query = getEntityManager().createNamedQuery(PredioRelacionado.FIND_PREDIO_RELACIONADO );

			query.setParameter("macrozona", Integer.parseInt(macrozona) );
			query.setParameter("ueu", Integer.parseInt(ueu) );
			query.setParameter("quarteirao",  Integer.parseInt(quarteirao) );			
			query.setParameter("codLogradouro",  codLogradouro.intValue() );
			query.setParameter("sequencia",  sequencia );
			
	 
			List<PredioRelacionado> predioRelacionadoList =  query.getResultList();
			
			return predioRelacionadoList;			

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
