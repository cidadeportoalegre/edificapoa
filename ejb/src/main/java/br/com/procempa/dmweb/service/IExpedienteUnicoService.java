package br.com.procempa.dmweb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.procempa.dmweb.entity.legado.ExpedienteUnico;

@Local
public interface IExpedienteUnicoService {

	List<ExpedienteUnico> findExpedienteUnico( String expedienteUnico, String areaPrivativa, String tipoEndereco );
}
