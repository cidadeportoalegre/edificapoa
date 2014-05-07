package br.com.procempa.dmweb.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.NumberConverter;

import org.apache.commons.lang.StringUtils;
 
/**
 * Converts a Double or Long value provided by standard jsf number
 * converter to a BigDecimal value
 *
 * To get a locale-sensitive converter, java.text.NumberFormat is used
 * (through javax.faces.convert.NumberConverter).
 * The parsing done by java.math.BigDecimal is not affected by locale.
 * See javax.faces.convert.BigDecimalConverter
 *
 */
public class BigDecimalConverter extends NumberConverter
{
 
	/*
	 * don't use BigDecimal ctor
	 * see java.math.BigDecimal
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String string) {

		if( StringUtils.isBlank( string )) {
			string = "0";
		}
		Object value = new BigDecimal( string.replace(".", "").replace(",", ".") );
 
		return value;
	}
 
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		} else if (value instanceof String) {
			return (String) value;
		} else {
			DecimalFormat df = new DecimalFormat();
			// Define duas casas decimais
			df.setMinimumFractionDigits(((BigDecimal)value).scale());        
			// Cria símbolos de formatação
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			// Define o separador de milhar
			simbolos.setGroupingSeparator('.');         
			// Define o separador decimal
			simbolos.setDecimalSeparator(',');          
			// Atribui símbolos ao formatador
			df.setDecimalFormatSymbols(simbolos);  
			// Aplica a formatação
			String numeroFormatado = df.format(value);    		
			
			return numeroFormatado;
		}	
	}
 

}
