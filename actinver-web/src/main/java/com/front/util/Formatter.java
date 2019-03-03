package com.front.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.front.validator.Validator;
import com.sun.org.apache.regexp.internal.RE;

public class Formatter {

    private static final SimpleDateFormat formatYearMonthDay 	  = new SimpleDateFormat("dd MMMM yyyy");
    private static final SimpleDateFormat formatDateEtiqueta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat formatDayDMonthDYear	  = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "MX"));
    private static final SimpleDateFormat formatDay				  = new SimpleDateFormat("dd");
    private static final DecimalFormat 	  decimalFormatTaz    	  = new DecimalFormat("###,###,##0.00");

    private static DateFormatSymbols symbols = new DateFormatSymbols();
	private static DateFormat dateFormat = new SimpleDateFormat( "d MMM yyyy", symbols );
	private static DateFormat dateFormatEbank = null;
	private static Formatter formatter;
	private static DecimalFormat decimalFormat = null;
	private static DecimalFormat decimalFormatSimple = null;
	private static DecimalFormat decimalFormatSinDecimal = null;

    public static final Collection<String> DIAS = getDias();
	public static final Collection<String> MESES = getMeses();
	public static final Collection<String> ANIOS = getAnios();
	public static final String NBSP = "&nbsp;";
	public static final String[] AMPM = { "am", "pm" };
	public static final String[] MONTHS = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "" };
	public static final String[] WEEKDAYS = { "", "Domingo", "Lunes", "Martes", "Mi�rcoles", "Jueves", "Viernes", "S�bado" };
	public static final String[] SHORT_MONTHS = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic", "" };
	public static final String[] SHORT_WEEKDAYS = { "", "Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab" };

	public static String FECHA_ACTUAL = getFechaActual();
	private static SimpleDateFormat formaParserSeriehmssss = null;

	static {
		decimalFormat = new DecimalFormat( "###,###,##0.00" );
		decimalFormatSimple = new DecimalFormat( "########0.00" );
		decimalFormatSinDecimal = new DecimalFormat( "###,###,##0" );

		DateFormatSymbols symbols = new DateFormatSymbols();
		symbols.setAmPmStrings( AMPM );
		symbols.setMonths( MONTHS );
		symbols.setShortMonths( SHORT_MONTHS );
		symbols.setWeekdays( WEEKDAYS );
		symbols.setShortWeekdays( SHORT_WEEKDAYS );
		dateFormatEbank = new SimpleDateFormat( "d MMM yyyy", symbols );
		formaParserSeriehmssss=new SimpleDateFormat ("yyyyMMddhhmmss", symbols);
	}

	private Formatter(){
		decimalFormat = new DecimalFormat( "###,###,##0.00" );
		decimalFormatSimple = new DecimalFormat( "########0.00" );

		DateFormatSymbols symbols = new DateFormatSymbols();
		symbols.setAmPmStrings( AMPM );
		symbols.setMonths( MONTHS );
		symbols.setShortMonths( SHORT_MONTHS );
		symbols.setWeekdays( WEEKDAYS );
		symbols.setShortWeekdays( SHORT_WEEKDAYS );
		dateFormatEbank = new SimpleDateFormat( "d MMM yyyy", symbols );
	}

	public static Formatter getInstance(){
		return formatter==null? new Formatter(): formatter;
	}

	public String getFormatYearMonthDay(Date date){
		return formatYearMonthDay.format(date);
	}

	public String getFormatDay(Date date){
		return formatDay.format(date);
	}

	public String getDecimalFormatTaz(Double number){
		return decimalFormatTaz.format(number);
	}

	public Date getFormatDayDMonthDYear(String date){
		try {
			return formatDayDMonthDYear.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getFormatDayDMonthDYear(Date date){
		return formatDayDMonthDYear.format(date);
	}
	
	public static String getFormatDateEtiqueta(Date date){
		String aux = formatDateEtiqueta.format(date);
		aux = aux.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		return aux;
	}


	public static String formatFechaEbank( Date fecha ) {
		if( fecha == null ) return NBSP;
		return dateFormatEbank.format( fecha );
	}

	public static String formatFecha( Date fecha ) {
		if( fecha == null ) return NBSP;
		return dateFormat.format( fecha );
	}

	public static String formatNegativo( BigDecimal monto ) {
		double data = 0;
		BigDecimal uno = new BigDecimal(-1);
		if( monto != null ) {
			monto = monto.multiply(uno);
			data = monto.doubleValue();
		}
		return formatMonto( data );
	}

	private static Collection<String> getDias() {
		Collection<String> dias = new ArrayList<String>();
		for (int i = 0; i <= 31; i++) {
			dias.add(String.valueOf(i));
		}
		return dias;
	}

	private static final Collection<String> getMeses() {
		Collection<String> meses = new ArrayList<String>();
		String[] $meses = { "Enero", "Ferbero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
		for (String string : $meses) {
			meses.add(string);
		}
		return meses;
	}

	private static Collection<String> getAnios() {

		return null;
	}

	public static String formatDayMonthYear(Date fecha) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat sDF = new SimpleDateFormat(pattern);
		return sDF.format(fecha);
	}

	private static String getFechaActual() {
		return formatDayMonthYear(new Date());
	}

	public static double conversionMontoAlnovaJava(String montoAlnova){
		double montoConvertido = 0.0;

		if( ( montoAlnova != null ) && ( montoAlnova.length() > 1 ) ){
			int longitud = montoAlnova.length();
			String decimales = montoAlnova.substring(longitud-2, longitud);
			String enteros = montoAlnova.substring(0, longitud-2);
			String montoParceado = enteros + "." + decimales;
			montoConvertido = Double.parseDouble(montoParceado);
		}
		return montoConvertido;
	}

	public static String formatMonto( BigInteger monto ) {
		StringBuffer buffer = new StringBuffer(decimalFormat.format(monto));
		if (buffer.length() >= 4) {
			buffer.deleteCharAt(buffer.length() -1 );
			buffer.deleteCharAt(buffer.length() -1 );
			buffer.deleteCharAt(buffer.length() -1 );
		}
		if( monto != null ) {

			if( monto.compareTo(BigInteger.ZERO) < 0 ) {
				buffer.insert( 0, "<font color=\"red\">" );
				buffer.append( "</font>" );
			}
		}
		return buffer.toString();
	}

	public static String formatMontoPesos( double monto ) {
		StringBuffer buffer = new StringBuffer( "$ " + decimalFormat.format( monto ) );
		if( monto < 0 ) {
			buffer.insert( 0, "<font color=\"red\">" );
			buffer.append( "</font>" );
		}
		return buffer.toString();
	}

	public static String formatMontoPesos( String importe ) {
		String resultado="";
		try{
			if((importe!=null)&&(importe.length()>0)){
				Double monto=Double.parseDouble(importe);
				Locale LOCALE_MX = new Locale("es","mx");
				NumberFormat numberFormat=DecimalFormat.getInstance(LOCALE_MX);
				numberFormat.setMinimumFractionDigits(2);
	
				resultado = new String( "$" + numberFormat.format( monto ) );
					if( monto < 0 ) {
						resultado = new String( "$" + numberFormat.format( 0.00 ) );
					}
				}
		}catch(Exception e){
			e.printStackTrace();
			resultado=importe;
		}
		return resultado;
	}
	
	public static String formatMontoPesosSinSimbolo( String importe ) {
		String resultado="";
		try{
			if((importe!=null)&&(importe.length()>0)){
				Double monto=Double.parseDouble(importe);
				Locale LOCALE_MX = new Locale("es","mx");
				NumberFormat numberFormat=DecimalFormat.getInstance(LOCALE_MX);
				numberFormat.setMinimumFractionDigits(2);
	
				resultado = new String( numberFormat.format( monto ) );
					if( monto < 0 ) {
						resultado = new String( numberFormat.format( 0.00 ) );
					}
				}
		}catch(Exception e){
			e.printStackTrace();
			resultado=importe;
		}
		return resultado;
	}
	
	public static String formatMontoPesos( BigDecimal importe) {
		if( null == importe ){
			return "0.00";
		}
		return formatMonto(importe.doubleValue());
	}
	/**	Se agregan metodos de formato sin decimales		**/
	public static String formatMontoPesosSinDecimal( String importe ) {
		String resultado="";
		try{
			if((importe!=null)&&(importe.length()>0)){
				Double monto=Double.parseDouble(importe);
	
				resultado = new String( "$" + decimalFormatSinDecimal.format( monto ) );
					if( monto < 0 ) {
						resultado = new String( "$" + 0.00 );
					}
				}
		}catch(Exception e){
			e.printStackTrace();
			resultado=importe;
		}
		return resultado;
	}
	public static String formatMontoPesosSinDecimal( Double importe ) {
		String resultado="";
		try{
			if((importe!=null)){
				
				resultado = new String( "$" + decimalFormatSinDecimal.format( importe ) );
					if( importe < 0 ) {
						resultado = new String( "$" + 0);
					}
				}
		}catch(Exception e){
			e.printStackTrace();
			resultado=importe.toString();
		}
		return resultado;
	}
	
	public static String formatMonto( double monto ) {
		StringBuffer buffer = new StringBuffer( decimalFormat.format( monto ) );
		return buffer.toString();
	}

	public static String formatMonto( BigDecimal monto ) {
		StringBuffer buffer = new StringBuffer("0.00");
		if(monto!=null){
			buffer = new StringBuffer( decimalFormatSimple.format( monto ) );
		}

		return buffer.toString();
	}

	public static String formatMontoTruncado( String monto ) {
		StringBuffer buffer = new StringBuffer("0.00");
		if(!Validator.isEmptyData(monto)){
			if(Validator.checkNumericBalance(monto)){
//				Double importe = new Double(monto);
				BigDecimal importe = new BigDecimal(monto);
				buffer = new StringBuffer( decimalFormatSimple.format( importe ) );
			}
		}
		return buffer.toString();
	}
	
	public static String removeSpaces( String item ) {
		if (item == null)
			return "";
		char[] chs = item.toCharArray();
		StringBuffer cta = new StringBuffer();
		for ( int i=0;i<chs.length;i++ ){
			if ( !Character.isSpaceChar( chs[i] ) ){
				cta.append(chs[i]);
			}
		}
		return cta.toString();
	}

	public static String removeSpacesLeftRight( String item ) {
		if (item == null){
			return "";
		}
		item = item.replaceAll("^( )*", "");
		item = item.replaceAll("( )*$", "");

		return item;
	}

	public static boolean checkEMail(String data){
		boolean flag = false;
		if (data != null && data.trim().length()!=0){
			RE re = new RE("^[a-zA-Z](-|[a-zA-Z0-9\\._])*[a-zA-Z0-9]@[a-zA-Z0-9](-|[a-zA-Z0-9\\._])*[a-zA-Z0-9][\\.][a-zA-Z](-|[a-zA-Z0-9\\._])*[a-zA-Z]$");
			flag = re.match(data.trim());
		}
		return flag;
	}

	public static String formatDate(String date){

		try{

			if(date == null){
				return date;
			}else if(date.length() != 10 ){
				if(date.length()!=9){
					return date;
				}
			}
			String anio, dia;
			int mes;
			if(date.charAt(2) == '-'){
				dia = date.substring(0,2);
				mes = Integer.parseInt(date.substring(3,5));
				anio = date.substring(6,10);
			}else{
				anio = date.substring(0, 4);
				if(date.length()==10){
					mes = Integer.parseInt(date.substring(5, 7));
					dia = date.substring(8, 10);
				}else{
					mes = Integer.parseInt(date.substring(5, 6));
					dia = date.substring(8, 9);
				}

			}

			switch(mes){
			case 1:{
				return dia+" Ene "+anio;
			}case 2:{
				return dia+" Feb "+anio;
			}case 3:{
				return dia+" Mar "+anio;
			}case 4:{
				return dia+" Abr "+anio;
			}case 5:{
				return dia+" May "+anio;
			}case 6:{
				return dia+" Jun "+anio;
			}case 7:{
				return dia+" Jul "+anio;
			}case 8:{
				return dia+" Ago "+anio;
			}case 9:{
				return dia+" Sep "+anio;
			}case 10:{
				return dia+" Oct "+anio;
			}case 11:{
				return dia+" Nov "+anio;
			}case 12:{
				return dia+" Dic "+anio;
			}default:{
				return date;
			}
			}
		}catch(Exception e){
			return date;
		}
	}

	public static String formatoFechaHora(String fechaUltimoMovimiento){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String format = sdf.format(new Date(fechaUltimoMovimiento));
		String hora = fechaUltimoMovimiento.substring(11, 16);
        String meridiano = (Integer.parseInt(hora.substring(0,2))<= 12 ? "AM" : "PM");
        fechaUltimoMovimiento = formatDate(format.substring(0, 10))+ " " + hora + " " + meridiano;
		return fechaUltimoMovimiento;
	}

	public static String[] periodoMesActual(){
		String fechas[]= new String[2];
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/");
		String fechaHoy= formato.format(fechaActual);
		String pimerDiaMes = formato2.format(fechaActual)+"01";
		fechas[0]=pimerDiaMes;
		fechas[1]=fechaHoy;
		return fechas;
	}

	public static String formatMonto(String importe) {
		if ((importe != null) && (importe.length() > 0)) {

			String montoSeparado[] = importe.split("\\.");

			importe = montoSeparado[0];//= new String("$ " + numberFormat.format(entero));
			if(montoSeparado.length > 1){
				String decimales = montoSeparado[1];
				if (decimales.length() == 2) {
					importe += "." + decimales;
				}else if (decimales.length() == 1){
					importe += "." + decimales + "0";
				}else{
					importe += ".00";
				}
			}else{
				importe += ".00";
			}


		}
		return importe;
	}

	public static String formatName(String name)
	{
		String formatName = "";
		if(name == null || name.trim().length()<1)
			return formatName;
		else
		{
			String []words = name.trim().split(" ");
			for (String aux : words)
				if(aux.trim().length() > 0)
				formatName = formatName.concat(" "+aux);
			return formatName;
		}
	}

	public static String getDigits( String entity, String stdAcc ) throws NumberFormatException {

		stdAcc = stdAcc.trim();
		if( stdAcc.length() != 14 ) throw new IllegalArgumentException( "stdAcc.length() != 14" );

		StringBuffer buffer = new StringBuffer( entity );
		buffer.append( stdAcc.substring( 0, 4 ) );
		buffer.insert( 0, getDigit( buffer.toString().toCharArray(), 2, 0 ) ); // 1st digit
		buffer.setLength( 1 );
		buffer.append( getDigit( stdAcc.substring( 4, 14 ).toCharArray(), 0, 0 ) ); // 2nd digit
		return buffer.toString();
	}

	public static long getDigit( char[] subStr, int idx, int accum ) throws NumberFormatException {
		final int[] base = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };

		int total = subStr.length;
 		int iTemp = 0;
		for( int curr = 0; curr < total; curr++ ) {
			iTemp = Integer.parseInt( String.valueOf( subStr[ curr ] ) );
			iTemp *= base[ idx++ ];
			accum += iTemp;
		}
		BigDecimal bdAccum = BigDecimal.valueOf( accum );
		BigDecimal bd11 = BigDecimal.valueOf( 11 );
		BigDecimal bdTemp = bdAccum.divide( bd11, BigDecimal.ROUND_DOWN );
		bdTemp = bdTemp.multiply( bd11 );
		bdTemp = bdAccum.subtract( bdTemp );
		long lTemp = bdTemp.longValue();
		if( (lTemp != 0) && (lTemp != 1) ) lTemp = 11 - lTemp;
		return lTemp;
	}
	/**
	 * Formato para fecha ej: de 15  NOVIEMBRE 2009 A 15 Nov 2009
	 */public static String formatoFecha(String fecha){
		 	fecha = fecha.replaceAll("\t", " ");
		 	fecha = fecha.replaceAll(" ( )* ", " ");
			String fechaArreglo[]= fecha.split(" ");
			String mes=fechaArreglo[1].substring(0,3);
			fecha=fechaArreglo[0]+" "+mes.substring(0,1)+mes.substring(1,3).toLowerCase()+" "+fechaArreglo[2];
			return fecha;

		}

	 public static Date stringToDate(String strDate){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = null;
			try {

			    fecha = format.parse(strDate);

			} catch (Exception e) {
			    e.printStackTrace();
			}
			return fecha;
		}

	 	public static String formatFecha(String format,Date date){
	 		SimpleDateFormat sdf= new  SimpleDateFormat(format);
	 		return sdf.format(date);
	 	}

	 	public static boolean isNumeric(String data) {
			boolean flag = false;
			if(data!= null){
				RE re = new RE( "^(\\d+)$" );
				flag = re.match(data.trim());
			}
			return flag;
		}

	public static List<String> convertirMayusculas(List<String> lista){

		List<String> listaMayusculas = new ArrayList<String>();
		for(String string : lista){
			listaMayusculas.add(string.toUpperCase());
		}
		return listaMayusculas;
	}
	

//			return "&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;"+cuenta.substring((cuenta.length()-4),(cuenta.length()));

	
	
	public static String getErrorInput(String error){
		if( null != error ){
			return "<div class='textoError'>"+error+"</div>";
		}
		return error;
	}
	
	public static String formaParserSeriehmssss(Date fecha){
		return formaParserSeriehmssss.format(fecha);
	}


}