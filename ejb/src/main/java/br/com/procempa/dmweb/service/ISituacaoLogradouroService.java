package br.com.procempa.dmweb.service;

import java.util.List;

import br.com.procempa.dmweb.entity.SituacaoLogradouro;

public interface ISituacaoLogradouroService {

	List<SituacaoLogradouro> findSituacaoLogradouro( Integer codigoSituacao );
	
}
