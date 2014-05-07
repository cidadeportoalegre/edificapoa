package br.com.procempa.dmweb.enumerator;

import java.io.Serializable;

/**
 * Enumeradores com as Roles do Sistema
 * A descrição deve coincidir exatamente com o que está cadastrado no banco de dados
 * 
 * @author bridi
 */
public enum RoleConstants implements Serializable {
	ADMINISTRADOR("Administrador"),
	ASSISTENTE("Assistente"),
	CONVIDADO("Convidado"),
	GERENTE("Gerente de Programa"),
	LIDER_ACAO("Líder de Ação"),
	LIDER_ETAPA("Líder de Etapa"),
	LIDER_SUBETAPA("Líder de Subetapa"),
	USUARIO("Usuário"),
	SECRETARIO("Secretário");
	
	private final String value;

	private RoleConstants(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public String toString(){
		return value;
	}	
		
}
