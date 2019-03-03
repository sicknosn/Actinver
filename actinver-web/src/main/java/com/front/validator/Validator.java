package com.front.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import com.front.util.Formatter;

public class Validator {

	/**
	 * Valida si es vac�o o nulo es String recibido.
	 * @param data
	 * @return
	 */
	public static boolean isEmptyData(String data){
		boolean flag = true;
		if (data!=null && data.trim().length()>0){
			flag = false;
		}
		return flag;
	}

	public static boolean isCantidadMinima(String data){
		boolean flag = false;
		double aDouble = Double.parseDouble(data);
		if(aDouble < 1000){
			return true;
		}
		return flag;
	}
	public static boolean isDecimalCuentaSaldosAlias(String data){
		boolean flag = true;
		if(!data.matches("(\\d){14}")){
			flag = false;
		}
		return flag;
	}

	public static boolean checkEMail(String data){
		boolean flag = false;
		if (data != null && data.trim().length()!=0){
			try {
				RE re = new RE("^[a-zA-Z](-|[a-zA-Z0-9\\._])*[a-zA-Z0-9]@[a-zA-Z0-9](-|[a-zA-Z0-9\\._])*[a-zA-Z0-9][\\.][a-zA-Z](-|[a-zA-Z0-9\\._])*[a-zA-Z]$");
				flag = re.match(data.trim());
			} catch (RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static boolean checkNumeric(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				RE re = new RE( "^([0-9]+)$" );
				flag = re.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static boolean checkNumeric(Integer data) {
		boolean flag = false;
		if (data != null && data.toString().trim().length()>=0){
			try {
				RE re = new RE( "^([0-9]+)$" );
				flag = re.match(data.toString().trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static boolean checkNumeric(Long data) {
		boolean flag = false;
		if (data != null && data.toString().trim().length()>=0){
			try {
				RE re = new RE( "^([0-9]+)$" );
				flag = re.match(data.toString().trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static boolean checkNip(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				RE re = new RE( "^([0-9]+){4}" );
				flag = re.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static boolean checkNumericBalance(String data) {
		boolean flag = false;
		data = data.replace(".", "");
		flag = checkNumeric(data);
		return flag;
	}

	public static boolean checkDecimal(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>0){
			try {
				RE re = new RE( "^(([0-9]+)|([0-9]+\\.[0-9]+))|^(.[0-9]+)$" );
				flag = re.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/*valido .00  invalido 10.00*/
	public static boolean checkDecimals(String data) {
		System.out.println("checkDecimals - data::"+data);
		boolean flag = false;
		if (data != null && data.trim().length()>0){
			try {
				RE re = new RE( "^(.[0-9]+)$" );
				flag = re.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static boolean checkOnlyText(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				RE re = new RE( "^([A-Za-z\\s]+)$" );
				flag = re.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static boolean checkAlphanumeric(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				RE re = new RE( "^([A-Za-z0-9\\s]+)$" );
				flag = re.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static  boolean isEmptyCuentaCargo(String cuentaCargo) {
		StringTokenizer tokenizer = new StringTokenizer(cuentaCargo, "$");
		tokenizer.nextToken();
		String cantidadString = tokenizer.nextToken().trim();
		cantidadString = cantidadString.replace(',', ' ');
		cantidadString = Formatter.removeSpaces(cantidadString);
		double cantidad;
		try {
			cantidad = Double.parseDouble(cantidadString);
		} catch (NumberFormatException e) {
			cantidad=0d;
		}
		if (cantidad <= 0) {
			return true;
		}
		return false;
	}

	public static boolean containsOnlyNumbers(String str) {

        if (str == null || str.length() == 0)
            return false;

        for (int i = 0; i < str.length(); i++) {

            if (!Character.isDigit(str.charAt(i)))
                return false;
        }

        return true;
    }
	public static boolean checkFecha(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				// Formato valido: dd/mm/yyyy; d/mm/yyyy;
				RE re = new RE("(0[1-9]|[12][0-9]|3[01])[// /.](0[1-9]|1[012])[// /.]((20)\\d\\d)");
				flag = re.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;

	}

	public static boolean checkFecha(String data, String format) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				SimpleDateFormat formato = new SimpleDateFormat(format);
				formato.parse(data);
				flag = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return flag;

	}
	public static boolean checkFecha(String data, String format, Locale localidad) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				SimpleDateFormat formato = new SimpleDateFormat(format,localidad);
				formato.parse(data);
				flag = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return flag;

	}
	
	public static boolean formatHour(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				// formato valido: hh:mm:ss; h:mm:ss
				RE filtro=new RE("^([0-9]{2}[/: /.][0-9]{2}[/: /.][0-9]{2})$");
				flag =filtro.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;

	}
	
	public static boolean isValidFormatOnlyClient(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				// formato valido: 01-15-5502-11834
				RE filtro=new RE("^([0-9]{2}-[0-9]{2}-[0-9]{4}-[0-9]{5})$");
				flag =filtro.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;

	}

	public static boolean isValidHour(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				// Datos validos: [00:00:00 - 23:59:60]
				String hora="^(0[0-9]{1}|[1-9]{1}|1[0-9]{1}|2[0-3]{1})";
				String comodin="[/: /.]";
				String min="(0[0-9]{1}|[1-5]{1}[0-9]{1})";
				String seg="(0[0-9]{1}|[1-5]{1}[0-9]{1}|60)$";
				RE re = new RE(hora+comodin+min+comodin+seg);
				flag = re.match(data.trim());
			} catch(RESyntaxException e) {
				e.printStackTrace();
			}
		}
		return flag;

	}

	public static boolean containsInvalidCharacters(String data) {
		boolean flag = false;
		if (data != null && data.trim().length()>=0){
			try {
				// Datos invalidos: [�������������������� ����������]
				flag = data.trim().matches( "^(.*[�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�].*)$" );
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static void main(String[] args) {
		System.out.println("result::"+checkDecimal("10"));
	}
	
}
