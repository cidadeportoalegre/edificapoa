package br.com.procempa.dmweb.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.procempa.dmweb.entity.legado.DivisaoTerritorial130;

public interface IDivisaoTerritorial130Service {
	List<DivisaoTerritorial130> findDivisaoTerritorial130(BigDecimal codLogradouro, BigDecimal macrozona, BigDecimal ueu, BigDecimal quarteirao, BigDecimal sequencia);
}
