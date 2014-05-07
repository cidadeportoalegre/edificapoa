package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

import br.com.procempa.dmweb.entity.legado.DivisaoTerritorial;
import br.com.procempa.dmweb.entity.legado.DivisaoTerritorial130;



@Stateless
@Name(DivisaoTerritorial130Service.SERVICE_NAME)
public class DivisaoTerritorial130Service implements IDivisaoTerritorial130Service {
	
	public static final String SERVICE_NAME = "divisaoTerritorial130Service";
	
	private EntityManager entityManager;


 
	@SuppressWarnings("unchecked")
	@Override
	public List<DivisaoTerritorial130> findDivisaoTerritorial130(BigDecimal codLogradouro, BigDecimal macrozona, BigDecimal ueu, BigDecimal quarteirao, BigDecimal sequencia ) {
 
		Query query = getEntityManager().createNamedQuery(DivisaoTerritorial130.FIND_QUARTEIRAO_130 );
		
		query.setParameter("codLogradouro",  codLogradouro   );
		query.setParameter("macrozona", macrozona   );
		query.setParameter("ueu", ueu  );
		query.setParameter("quarteirao", quarteirao  );
		query.setParameter("sequencia", sequencia  );		
		
		List<DivisaoTerritorial130> dvlist = query.getResultList();
		// escolher o lado par/impar
		
		
		return dvlist;

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
