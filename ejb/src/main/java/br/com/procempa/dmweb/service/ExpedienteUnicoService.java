package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

import br.com.procempa.cdl.client.ClienteCDL;
import br.com.procempa.dmweb.entity.legado.ExpedienteUnico;

@Stateless
@Name(ExpedienteUnicoService.SERVICE_NAME)
public class ExpedienteUnicoService implements IExpedienteUnicoService {
	
	public static final String SERVICE_NAME = "expedienteUnicoService";
	
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpedienteUnico> findExpedienteUnico(String expedienteUnico,
			String areaPrivativa, String tipoEndereco) {
 
		Query query = getEntityManager().createNamedQuery(ExpedienteUnico.FIND_EXPEDIENTE_UNICO );
		
		query.setParameter("expedienteUnico", new BigDecimal( expedienteUnico ) );
		
		if ( StringUtils.isBlank( areaPrivativa )) {
			query.setParameter("areaPrivativa", BigDecimal.ZERO);
		} else {
			query.setParameter("areaPrivativa", new BigDecimal( areaPrivativa.trim() ));
		}
		
		if (StringUtils.isBlank( tipoEndereco ) ) {
			query.setParameter("tipoEndereco", BigDecimal.ZERO);
		} else {
			query.setParameter("tipoEndereco", new BigDecimal( tipoEndereco.trim() ) );
		}
				
		List<ExpedienteUnico> eulist = query.getResultList();
		
		for (ExpedienteUnico eu : eulist) {
			carregaDadosCdl( eu );
		}
		
		return eulist;

	}


	public ExpedienteUnico carregaDadosCdl(ExpedienteUnico expedienteUnico) {
		try {
			ClienteCDL cdl = ClienteCDL.load(
					expedienteUnico.getExpedienteUnicoId().getCodLogradouroAsString(), 
					expedienteUnico.getExpedienteUnicoId().getNumImovelAsString() );
			expedienteUnico.setLogradouro(cdl.getNomeLogr());
			 
			expedienteUnico.setBairro(cdl.getNomeBairro());
//			inscricao.getEndereco().setCepFormatado(cdl.getCep());
//			inscricao.getEndereco().setMunicipio("Porto Alegre");
//			inscricao.getEndereco().setUf(Uf.RS);
		} catch (Exception e) {
			e.printStackTrace();
			expedienteUnico.setLogradouro("Não encontrado ou serviço indisponível");
		}
		
		return expedienteUnico;
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
