package br.com.procempa.dmweb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.procempa.dmweb.entity.legado.Circulacao;

@Local
public interface ICirculacaoService {

	List<Circulacao> findCirculacao( Integer codLogradouro, Integer imovelInicial, Integer imovelFinal );
	
}
