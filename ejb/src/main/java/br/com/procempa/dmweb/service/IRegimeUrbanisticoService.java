package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import br.com.procempa.dmweb.entity.legado.RegimeUrbanistico;

@Local
public interface IRegimeUrbanisticoService {
 
	List<RegimeUrbanistico> findRegimeUrbanistico( BigDecimal codLogradouro, BigDecimal numeroInicial, BigDecimal numeroFinal);

}
