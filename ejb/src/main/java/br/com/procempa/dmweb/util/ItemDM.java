package br.com.procempa.dmweb.util;

import java.util.List;

import br.com.procempa.dmweb.entity.Observacao;
import br.com.procempa.dmweb.entity.legado.Circulacao;
import br.com.procempa.dmweb.entity.legado.PredioRelacionado;
import br.com.procempa.dmweb.entity.legado.RegimeUrbanistico;

public class ItemDM {

		private String logradouro;
		private String situacaoLogradouro;
		private String textoRegime ;
		private List<RegimeUrbanistico> regimeUrbanisticoList;
		private List<String> observacaoRegimeUrbanisticoList;
		private String textoCirculacao;
		private List<PredioRelacionado> predioRelacionadoList;		
		private List<Observacao> observacaoFaceList;
		
		public List<Observacao> getObservacaoFaceList() {
			return observacaoFaceList;
		}

		public void setObservacaoFaceList(List<Observacao> observacaoFaceList) {
			this.observacaoFaceList = observacaoFaceList;
		}

		public List<PredioRelacionado> getPredioRelacionadoList() {
			return predioRelacionadoList;
		}

		public void setPredioRelacionadoList(
				List<PredioRelacionado> predioRelacionadoList) {
			this.predioRelacionadoList = predioRelacionadoList;
		}

		private List<Circulacao> circulacaoList;

		
		public List<Circulacao> getCirculacaoList() {
			return circulacaoList;
		}

		public void setCirculacaoList(List<Circulacao> circulacaoList) {
			this.circulacaoList = circulacaoList;
		}

		public ItemDM(){}
		
		public ItemDM(String logradouro, String situacaoLogradouro, String textoRegime, List<RegimeUrbanistico> regimeUrbanisticoList,
					  List<String> observacaoRegimeUrbanisticoList, String textoCirculacao, List<Circulacao> circulacaoList, List<PredioRelacionado> predioRelacionadoList,
					  List<Observacao> observacaoFaceList) {
			super();
			this.logradouro = logradouro;
			this.situacaoLogradouro = situacaoLogradouro;
			this.textoRegime = textoRegime;
			this.regimeUrbanisticoList = regimeUrbanisticoList;
			this.observacaoRegimeUrbanisticoList = observacaoRegimeUrbanisticoList;
			this.textoCirculacao = textoCirculacao;
			this.circulacaoList = circulacaoList;
			this.predioRelacionadoList = predioRelacionadoList;	
			this.observacaoFaceList = observacaoFaceList;
		}

		public String getLogradouro() {
			return logradouro;
		}

		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}

		public String getSituacaoLogradouro() {
			return situacaoLogradouro;
		}

		public void setSituacaoLogradouro(String situacaoLogradouro) {
			this.situacaoLogradouro = situacaoLogradouro;
		}

		public String getTextoRegime() {
			return textoRegime;
		}

		public void setTextoRegime(String textoRegime) {
			this.textoRegime = textoRegime;
		}

		public List<RegimeUrbanistico> getRegimeUrbanisticoList() {
			return regimeUrbanisticoList;
		}

		public void setRegimeUrbanisticoList(
				List<RegimeUrbanistico> regimeUrbanisticoList) {
			this.regimeUrbanisticoList = regimeUrbanisticoList;
		}

		public List<String> getObservacaoRegimeUrbanisticoList() {
			return observacaoRegimeUrbanisticoList;
		}

		public void setObservacaoRegimeUrbanisticoList(
				List<String> observacaoRegimeUrbanisticoList) {
			this.observacaoRegimeUrbanisticoList = observacaoRegimeUrbanisticoList;
		}

		public String getTextoCirculacao() {
			return textoCirculacao;
		}

		public void setTextoCirculacao(String textoCirculacao) {
			this.textoCirculacao = textoCirculacao;
		}

	
}
