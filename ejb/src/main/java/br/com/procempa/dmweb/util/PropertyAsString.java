package br.com.procempa.dmweb.util;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;



/**
 * @author petrillo
 *
 * Classe que cont�m m�todos para convers�o de tipos primitivos
 * de dados para Strings formatas
 * 
 */
public class PropertyAsString { 
    private static final String RETISCENCIAS_STYLE = "style='text-decoration : none; cursor : help; color: black; font-weight : normal; font-family: Verdana, Sans-Serif, Sans, Geneva, Arial, Helvetica;'";

	/**
	 * Converte uma Data para String formatada
	 * @param date data a ser formatada
	 * @return retorna a string no formato "dd/MM/yyyy"
	 */
	public static String dateAsString(Date date) {
		return (date == null) ? null : new SimpleDateFormat("dd/MM/yyyy").format(date); 
	}

	/**
	 * Converte uma Data/Hora para String formatada
	 * @param date data a ser formatada
	 * @return retorna a string no formato "dd/MM/yyyy"
	 */
	public static String dateTimeAsString(Date date) {
		return (date == null) ? null : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date); 
	}
	
	/** ---------------- beto&vin�cius ---------------*/

	/**
	 * Converte uma Hora para String formatada
	 * @param time hora a ser formatada
	 * @return retorna a string no formato "hh:mm"
	 */
	public static String timeAsString(Time hora)  throws ParseException {
		return (hora == null) ? null : new SimpleDateFormat("HH:mm").format(hora); 
	}

	/**
	 * Converte um String no formato hh:mm para uma Hora
	 * @param string string a ser convertida
	 * @return retorna a hora convertida
	 */
	public static Time stringAsTime(String string) throws ParseException {
		Time hora = null;
		if (!StringUtils.isEmpty(string)) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			sdf.setLenient(false);
			hora = new Time(sdf.parse(string).getTime());
		}
		return hora;
	}

	/**
	 * Converte um String no formato dd/MM/yyyy para uma Data
	 * @param string string a ser convertida
	 * @return retorna a data convertida
	 */
	public static Date stringAsDate(String string) throws ParseException {
		Date date = null;
		if (!StringUtils.isEmpty(string)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			try {
				date = sdf.parse(string);
			} catch (ParseException e) {
				date = sdf.parse("");
			}
		}
		return date;
	}
	
	/**
	 * Converte um Float para String formatado
	 * @param f Float a ser convertido
	 * @return retorna um string no formato "9999,99"
	 */
	public static String floatAsString(float f) {
		return (new String(Float.toString(f))).replace('.',','); 
	}

	/**
	 * Converte uma String no formato 9999,99 para uma Float
	 * @param string string a ser convertida
	 * @return retorna o float convertido
	 */
	public static float stringAsFloat(String string) {
		float f = 0;

		if (!StringUtils.isEmpty(string))
			f = Float.parseFloat(string.replace(',','.'));

		return f;
	}

	/**
	 * Converte um Double para String formatado
	 * @param d Double a ser convertido
	 * @return retorna um string no formato "9999,99"
	 */
	public static String doubleAsString(BigDecimal bd, int precisao, int arredondamento) {
		return (bd==null) ? 
			null :
			bd.setScale(precisao, arredondamento).toString().replace('.', ','); 
	}
	
	public static String doubleAsString(BigDecimal bd, int precisao) {
		return (bd==null) ? 
			null :
			doubleAsString(bd, precisao, BigDecimal.ROUND_HALF_UP);
	}

	public static String doubleAsString(BigDecimal bd) {
		return (bd==null) ? 
			null :
			doubleAsString(bd, 2, BigDecimal.ROUND_HALF_UP);
	}
/*
		Fun��o substitu�da pela acima que tamb�m arredonda o valor para duas decimais antes de devolver
		public static String doubleAsString(BigDecimal bd) {
		return (bd==null) ? 
			null :
			doubleAsString(bd.doubleValue()); 
	}
*/
	public static String doubleAsString(double d) {
		return (new String(Double.toString(d))).replace('.',','); 
	}

	/**
	 * Converte uma String no formato 9999,99 para uma Double
	 * @param string string a ser convertida
	 * @return retorna o double convertido
	 */
	public static double stringAsDouble(String string) {
		double d = 0;

		if (!StringUtils.isEmpty(string))
			d = Double.parseDouble(string.replace(',','.'));

		return d;
	}
	
	/**
	 * Prepara o string que veio da tela para ser setado no contrutor de um BigDecimal para setar no DataObject
	 * @param string string a ser preparado
	 * @return string preparado
	 */
	public static String prepareStringToBigDecimal(String string) {
		if ( ! StringUtils.isEmpty(string) ) {
			string = string.replace(',','.');
		}
		else {
			string = "0";
		}
		return string;
	}
	
	/**
	 * Prepara o BigDecimal que veio do banco para ser apresentado na tela
	 * @param valor O BigDecimal a ser preparado para apresenta��o
	 * @return string preparado
	 * 
	 * @deprecated N�o deve ser usado o setScale (precis�o e arredondamento), ser� responsabilidade do SGBD truncar os valores
	 * @see prepareBigDecimalToString(BigDecimal valor)
	 */
	public static String prepareBigDecimalToString(BigDecimal valor, int precisao, int arredondamento) {
		String saida = "";
		if (valor != null) {
			saida = valor.setScale(precisao, arredondamento).toString();
			if ( ! StringUtils.isEmpty(saida) ) {
				saida = saida.replace('.', ',');
			}
		}
		return saida;
	}
	
	/**
	 * @param valor
	 * @param precisao
	 * @return
	 * 
	 * @deprecated N�o deve ser usado o setScale (precis�o e arredondamento), ser� responsabilidade do SGBD truncar os valores
	 * @see prepareBigDecimalToString(BigDecimal valor)
	 */
	public static String prepareBigDecimalToString(BigDecimal valor, int precisao) {
		return prepareBigDecimalToString(valor, precisao, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	/**
	 * Prepara o BigDecimal que veio do banco para ser apresentado na tela
	 * @param valor
	 * @return
	 */
	public static String prepareBigDecimalToString(BigDecimal valor) {
		String saida = "";
		if (valor != null) {
			saida = valor.toString();
			if ( ! StringUtils.isEmpty(saida) ) {
				saida = saida.replace('.', ',');
			}
		}
		return saida;
	}
	
	/**
	 * Devolve a data atual por escrito
	 * @return retorna um String ex:"12 de setembro de 2004"
	 */
	public static  String dataAtual() {
		String output = new String("");
		Calendar calendar = new GregorianCalendar();

		output = ""+calendar.get(Calendar.DATE);
		switch(calendar.get(Calendar.MONTH))
		{
			case 0 :
				output += " de janeiro de ";
				break;
			case 1 :
				output += " de fevereiro de ";
				break;
			case 2 :
				output += " de mar�o de ";
				break;
			case 3 :
				output += " de abril de ";
				break;
			case 4 :
				output += " de maio de ";
				break;
			case 5 :
				output += " de junho de ";
				break;
			case 6 :
				output += " de julho de ";
				break;
			case 7 :
				output += " de agosto de ";
				break;
			case 8 :
				output +=  " de setembro de ";
				break;
			case 9 :
				output +=  " de outubro de ";
				break;
			case 10 :
				output +=  " de novembro de ";
				break;
			case 11 :
				output +=  " de dezembro de ";
				break;
		}
		output += ""+calendar.get(Calendar.YEAR);
		return(output);
	}   
	
	/**
	 * Coloca uma mascara no string com o CNPJ 
	 * @param string sem mascara ex.:"92902303000118"
	 * @return retorna o string "92.902.303/0001-18"
	 */
	public static String cnpjMask (String cnpj) {
		cnpj = "00000000000000" + cnpj.trim();
		cnpj = cnpj.substring(cnpj.length() -14);
		String output = new String("");
		int max = cnpj.length();
		char cnpjvet[] = cnpj.toCharArray();
		if(max <= 14) {
			for(int i = (max-1); i > (max- 3); i--) {
				output = cnpjvet[i] + output;
			}
			output = "-"+output;
			if((max-3) <= 12) {
				for(int i = (max-3); i > (max- 7); i--) {
					output = cnpjvet[i] + output;
				}
				output = "/"+output;
			}
			int j=0;
			for(int i = (max- 7); i >= 0; i--) {
				if(j==3) {
					output = "."+output;
					j = 0;
				}
				j++;
				output = cnpjvet[i] + output;
			}
		}
		else {
			output = cnpj;
		}
		return(output);
	}

	/**
	 * Recebe um dataAsString e o devolve por extenso  
	 * @param string "dd/MM/YYYY"
	 * @return retorna o string ex.:"26 de novembro de 2004"
	 */
	public static String dataExtenso(String data) {
		String output, aux;
		char temp[];
		output = new String("");
		aux = new String("");
		temp = new char[4];
		int i;
	    //copia para output o **dia**
		data.getChars(0,3,temp,0);
		for(int cc = 0; cc < 2; cc++)
		output += temp[cc];
	
	    //copia para aux o numero do **mes**
		data.getChars(3,6,temp,0);
		for(int cc = 0; cc < 2; cc++)
		aux += temp[cc];
	    //converte o string **mes** para um inteiro 
		i = Integer.parseInt(aux);
	 
		switch(i) { //troca o numero do mes pelo seu nome
			case 1 :
				output += " de janeiro de ";
				break;
			case 2 :
				output += " de fevereiro de ";
				break;
			case 3 :
				output += " de mar?o de ";
				break;
			case 4 :
				output += " de abril de ";
				break;
			case 5 :
				output += " de maio de ";
				break;
			case 6 :
				output += " de junho de ";
				break;
			case 7 :
				output += " de julho de ";
				break;
			case 8 :
				output += " de agosto de ";
				break;
			case 9 :
				output +=  " de setembro de ";
				break;
			case 10 :
				output +=  " de outubro de ";
				break;
			case 11 :
				output +=  " de novembro de ";
				break;
			case 12 :
				output +=  " de dezembro de ";
				break;
		}
        
		//pega o **ano**
		data.getChars(6,10,temp,0);
		for(int cc = 0; cc < 4; cc++)
		output += temp[cc];

		return(output);
	}
	
	/**
	 * Coloca uma mascara no string com o CPF
	 * @param string sem mascara ex.:"11111111111"
	 * @return retorna o string "111.111.111-11"
	 */
	public static String cpfMask(String cpf) {
		String output = new String("");
		int max = cpf.length();
		char cpfvet[] = cpf.toCharArray();

		if (max <= 11) {
			for (int i = (max - 1); i > (max - 3); i--) {
				output = cpfvet[i] + output;
			}
			output = "-" + output;
			if ((max - 3) <= 12) {
				for (int i = (max - 3); i > (max - 6); i--) {
					output = cpfvet[i] + output;
				}
				output = "." + output;
			}
			int j = 0;
			for (int i = (max - 6); i >= 0; i--) {
				if (j == 3) {
					output = "." + output;
					j = 0;
				}
				j++;
				output = cpfvet[i] + output;
			}
		} else
			return (cpf);

		return (output);
	}

	/**
	 * Coloca uma mascara numerica (com separador de milhares) 
	 * @param string sem mascara ex.:"11111111111"
	 * @return retorna o string "11.111.111.111"
	 */
	public static String numberMask (String number) {
		String output = new String("");
		int contador = 1;
		String separador = "";
		char numbervet[] = number.trim().toCharArray();
		 for(int i = (number.length()-1); i >= 0; i--) {
		 	if (contador == 3 && i>0) {
		 		separador = ".";
		 		contador = 0;
		 	}
		 	else {
		 		separador = "";
		 	}
	 		contador++;
		 	output = separador + numbervet[i] + output;
		 }
		 return(output);
	}	

	/**
	 * Apresenta uma lista de n�mero certo de elementos, seguido por retiscencias, se necess�rio.
	 * @param lista Resultado da pesquisa
	 * @param limite Numero de itens a ser apresentados antes das retiscencias
	 * @param property Propriedade a ser apresentada, separada por v�rgulas
	 * @return A lista de 'property', com 'limite' ocorr�ncias, seguido, se necess�rio, de um '...';
	 * 				Parando-se o mouse sobre o retorno, ser�o apresentados os �tens remanescentes.
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String montaListaRetiscencias(Set lista, int limite, final String property) throws Exception {
		return montaListaRetiscencias(new ArrayList(lista), limite, property, null);
	}

	public static String montaListaRetiscencias(@SuppressWarnings("rawtypes") List lista, int limite, final String property) throws Exception {
		return montaListaRetiscencias(lista, limite, property, null);
	}

	/**
	 * Apresenta uma lista de n�mero certo de elementos, seguido por retiscencias, se necess�rio.
	 * @param lista Resultado da pesquisa
	 * @param limite Numero de itens a ser apresentados antes das retiscencias
	 * @param property Propriedade a ser apresentada, separada por v�rgulas
	 * 		  agora permite null. Neste caso, o pr�prio objeto da lista ser� usado como property 
	 * 		  (para contemplar Set<String> ou <Long>, etc).
	 * @param restricao property que s� deve aparecer nas retiscencias (n�o na lista principal)
	 * @return A lista de 'property', com 'limite' ocorr�ncias, seguido, se necess�rio, de um '...';
	 * 				Parando-se o mouse sobre o retorno, ser�o apresentados os �tens remanescentes.
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String montaListaRetiscencias(Set lista, int limite, final String property, String restricao) throws Exception {
		return montaListaRetiscencias(new ArrayList(lista), limite, property, restricao);
	}
	
	@SuppressWarnings("rawtypes")
	public static String montaListaRetiscencias(List lista, int limite, final String property, String restricao) throws Exception {
		restricao = (restricao==null) ? "" : restricao;
		String saida = "";
		String retiscencias = "";
		int contador = 0;
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			Object objectProperty = null;
			if (StringUtils.isNotBlank(property)) {
				objectProperty = PropertyUtils.getProperty(iter.next(), property);
			}
			else {
				objectProperty = iter.next();
			}
			String propriedade = objectProperty==null ? "" : objectProperty.toString();
		    if (contador < limite && ! restricao.equals(propriedade)) {
			    contador++;
				saida += propriedade + ", ";
		    }
		    else {
		    	retiscencias += propriedade + ", ";
		    }
		}
		saida = StringUtils.chomp(saida, ", ");
		retiscencias = StringUtils.chomp(retiscencias, ", ");
		if (StringUtils.isEmpty(saida) && StringUtils.isNotEmpty(retiscencias)) {
			saida = retiscencias;
			retiscencias = "";
		}
		if (StringUtils.isNotEmpty(retiscencias)) {
		    saida = "<a href=# title='..., " + retiscencias + "' " + RETISCENCIAS_STYLE + " onclick=\"alert('" + saida + ", " + retiscencias + "');\">" + saida + ", ...</a>";
		}
		return saida;
	}

}

