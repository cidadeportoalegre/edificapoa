package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import br.com.procempa.dmweb.entity.legado.PredioRelacionado;
@Local
public interface IPredioRelacionadoService {
	
	List<PredioRelacionado> findPredioRelacionado(String macrozona, String ueu, String quarteirao, BigDecimal codLogradouro, Integer sequencia ) ;

}

