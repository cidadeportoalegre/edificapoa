package br.com.procempa.dmweb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.procempa.dmweb.entity.Observacao;
import br.com.procempa.dmweb.entity.TipoObservacao;

@Local
public interface IObservacaoService {

 	List<Observacao> findObservacao( Integer codigo );

	List<Observacao> findObservacao(Integer codigo, TipoObservacao tipoObservacao);

	List<Observacao> findObservacao(Integer codigo, String tipoObservacao);

	List<Observacao> listObservacaoPorTipo(String tipoObservacao);	
}
