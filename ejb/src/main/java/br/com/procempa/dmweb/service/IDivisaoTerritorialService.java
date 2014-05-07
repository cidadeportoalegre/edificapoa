package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import br.com.procempa.dmweb.entity.legado.DivisaoTerritorial;
import br.com.procempa.dmweb.entity.legado.ExpedienteUnico;

@Local
public interface IDivisaoTerritorialService {
 
	List<DivisaoTerritorial> findDivisaoTerritorialcomExpedienteUnico( ExpedienteUnico eu);
	List<DivisaoTerritorial> findDivisaoTerritorialcomLogradouro(BigDecimal codLogradouro, BigDecimal nroImovel);
}
