package com.cattsoft.emos.quality.delimiting.util;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringUtil {
	public static boolean checkStr(String str) {
		boolean bool = true;
		if ((str == null) || ("".equals(str.trim())))
			bool = false;
		return bool;
	}
	
	public static String checkObjString(Object obj) {
		String bool = "";
		if ((obj == null) || ("".equals(obj.toString().trim()))){
			bool = "";
			}else{
			bool = (String)obj;
			}
		return bool;
	}

	public static boolean checkObj(Object obj) {
		boolean bool = true;
		if ((obj == null) || ("".equals(obj.toString().trim())))
			bool = false;
		return bool;
	}

	public static String toString(Object obj) {
		return ((obj != null) ? obj.toString().trim() : "");
	}

	public static Integer toInteger(Object obj) {
		return Integer.valueOf((obj != null) ? Integer.parseInt(obj.toString()) : 0);
	}

	public static int toInt(String str) {
		return (("".equals(str)) ? -1 : Integer.parseInt(str));
	}

	public static String getISOToGBK(String str) {
		String strName = "";
		try {
			if (str != null)
				strName = new String(str.getBytes("ISO8859_1"), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strName;
	}

	public static String getISOToUTF8(String str) {
		String strName = "";
		try {
			if (str != null)
				strName = new String(str.getBytes("ISO8859_1"), "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strName;
	}

	public static String getNowDate() {
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String str = formatter.format(now.getTime());

		return str;
	}

	public static String getNowTimeLittleDate2() {
		Calendar c = Calendar.getInstance();
		c.add(5, -30);
		String time = c.get(1) + "-" + (c.get(2) + 1) + "-" + c.get(5) + " " + c.get(11) + ":" + c.get(12) + ":"
				+ c.get(13);
		String returnstr = "";
		try {
			Date d = parses(time, "yyyy-MM-dd HH:mm:ss");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			returnstr = formatter.format(d);
		} catch (Exception localException) {
		}
		return returnstr;
	}

	public static String getNowTime() {
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = formatter.format(now.getTime());
		return str;
	}

	public static long getTimeInMillis(String sDate, String eDate) {
		Timestamp sd = Timestamp.valueOf(sDate);
		Timestamp ed = Timestamp.valueOf(eDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sd);
		long timethis = calendar.getTimeInMillis();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(ed);
		long timeend = calendar2.getTimeInMillis();
		long thedaymillis = timeend - timethis;
		return thedaymillis;
	}

	public static String formatDateTime(String dTime) {
		String dateTime = "";
		if ((dTime != null) && (!("".equals(dTime))) && (!(dTime.startsWith("1900-01-01")))) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}

	public static String formatTime(String dTime) {
		String dateTime = "";
		if ((dTime != null) && (!("".equals(dTime)))) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}

	public static Date parses(String strDate, String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(strDate);
	}

	public static String getNowTimeLittle() {
		Calendar c = Calendar.getInstance();
		c.add(1, 1);
		String time = c.get(1) + "-" + (c.get(2) + 1) + "-" + c.get(5) + " " + c.get(11) + ":" + c.get(12) + ":"
				+ c.get(13);
		String returnstr = "";
		try {
			Date d = parses(time, "yyyy-MM-dd HH:mm:ss");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			returnstr = formatter.format(d);
		} catch (Exception localException) {
		}
		return returnstr;
	}

	public static String getNowTimeLittleDate() {
		Calendar c = Calendar.getInstance();
		c.add(5, 1);
		String time = c.get(1) + "-" + (c.get(2) + 1) + "-" + c.get(5) + " " + c.get(11) + ":" + c.get(12) + ":"
				+ c.get(13);
		String returnstr = "";
		try {
			Date d = parses(time, "yyyy-MM-dd HH:mm:ss");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			returnstr = formatter.format(d);
		} catch (Exception localException) {
		}
		return returnstr;
	}

	public static String getRandom(int num) {
		return String.valueOf(Math.random()).substring(2, num + 2);
	}

	public static String getTimeInMillis(Date sDate, Date eDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sDate);
		long timethis = calendar.getTimeInMillis();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(eDate);
		long timeend = calendar2.getTimeInMillis();
		long thedaymillis = timeend - timethis;
		return (thedaymillis / 1000L) + "????!";
	}

	public static String showTrace() {
		StackTraceElement[] ste = new Throwable().getStackTrace();
		StringBuffer CallStack = new StringBuffer();

		for (int i = 1; i < ste.length; ++i) {
			CallStack.append(ste[i].toString() + "\n");
			if (i > 4)
				break;
		}
		return CallStack.toString();
	}

	public static String checkTableDefKey(String[] key, String[] value, String name) {
		String str = "";
		for (int i = 0; i < key.length; ++i) {
			if (name.equals(key[i])) {
				str = value[i];
				break;
			}
		}
		return str;
	}

	public static boolean isChinese(String str) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static String getStrToGbk(String str) {
		String strName = "";
		try {
			if (str != null)
				strName = new String(str.getBytes("UTF-8"), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strName;
	}

	public static String getNextDate(String ts, int i) {
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts + " 00:00:00.000");
		now.setTime(t);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		now.add(5, i);
		String dt = formatter.format(now.getTime());
		return dt;
	}

	public static String getNextTime(String ts, int i) {
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts);
		now.setTime(t);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		now.add(12, i);
		String dt = formatter.format(now.getTime());
		return dt;
	}

	public static String getNextMonth(String ts, int i) {
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts);
		now.setTime(t);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM-dd HH:mm:ss");
		now.add(2, i);
		String dt = formatter.format(now.getTime());
		return dt;
	}

	public static long getUnixTime(String dateTime) {
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 08:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long l = (date1.getTime() - date2.getTime()) / 1000L;
		return l;
	}

	public static String toFirstUpperCase(String str) {
		if ((str == null) || ("".equals(str.trim())))
			return "";
		String firstChar = str.substring(0, 1).toUpperCase();
		String lastStr = str.substring(1);
		return firstChar + lastStr;
	}

	public static boolean isNum(String str) {
		boolean flg;
		try {
			Double.parseDouble(str);
			flg = true;
		} catch (Exception ex) {
			flg = false;
		}
		return flg;
	}

	public static String getIn300Ids(String ids, String alias) {
		String[] tempS = ids.split(",");
		int len = tempS.length;
		int which = 0;
		boolean isAnd = alias.indexOf("not") > 0;
		StringBuffer idsStr = new StringBuffer();
		idsStr.append("(");
		if (len > 300) {
			if (len % 300 > 0)
				which = len / 300 + 1;
			else {
				which = len / 300;
			}
			for (int i = 0; i < which; ++i) {
				idsStr.append(alias + " in (");
				for (int j = 300 * i; j < 300 * i + 300; ++j) {
					if (j >= len)
						break;
					idsStr.append(tempS[j] + ",");
				}

				idsStr = idsStr.replace(idsStr.lastIndexOf(","), idsStr.length(), "");
				if (i < which - 1)
					if (isAnd)
						idsStr.append(") and ");
					else
						idsStr.append(") or ");
				else
					idsStr.append(")");
			}
		} else {
			idsStr.append(alias + " in (");
			if (ids.lastIndexOf(",") == ids.length() - 1)
				idsStr.append(ids.substring(0, ids.length() - 1));
			else {
				idsStr.append(ids);
			}
			idsStr.append(" )");
		}
		idsStr.append(")");
		return idsStr.toString();
	}

	public static String getFormatString(String str) {
		String[] strArr = str.split(",");
		String retStr = "";
		for (int i = 0; i < strArr.length; ++i) {
			if (i > 0) {
				retStr = retStr + ",";
			}
			retStr = retStr + "'" + strArr[i] + "'";
		}
		return retStr;
	}

	public static String splitAndFilterString(String input) {
		if ((input == null) || (input.trim().equals(""))) {
			return "";
		}

		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");

		str = str.replaceAll("[(/>)<]", "");
		return str;
	}

	public static int getColType(String typeName, String colScale) {
		int type = 0;
		if (("varchar".equalsIgnoreCase(typeName)) || ("varchar2".equalsIgnoreCase(typeName))
				|| ("text".equalsIgnoreCase(typeName)))
			type = 1;
		else if ("char".equalsIgnoreCase(typeName))
			type = 2;
		else if ("datetime".equalsIgnoreCase(typeName))
			type = 6;
		else if ("date".equalsIgnoreCase(typeName))
			type = 7;
		else if ("time".equalsIgnoreCase(typeName)) {
			type = 8;
		}

		if (Integer.parseInt(colScale) > 0)
			type = 5;
		else if (("numeric".equalsIgnoreCase(typeName)) || ("long".equalsIgnoreCase(typeName)))
			type = 3;
		else if (("smallint".equalsIgnoreCase(typeName)) || ("int".equalsIgnoreCase(typeName))
				|| ("integer".equalsIgnoreCase(typeName))) {
			type = 4;
		}

		return type;
	}

	public static String getDBDefault(int type, String def) {
		String deft = "";
		if ((def != null) && (!("".equals(def)))) {
			if ((type == 1) || (type == 2) || (type == 6) || (type == 7) || (type == 8))
				deft = "default " + def;
			else
				deft = "default '" + def + "'";
		}
		return deft;
	}

	public static String getDBColType(int type, int len, int flt) {
		String typeName = "";
		if (type == 1)
			typeName = "varchar(" + len + ")";
		else if (type == 2)
			typeName = "char(" + len + ")";
		else if (type == 3)
			typeName = "numeric(" + len + ")";
		else if (type == 4)
			typeName = "int";
		else if (type == 5)
			typeName = "numeric(" + len + "," + flt + ")";
		else if (type == 6)
			typeName = "datetime";
		else if (type == 7)
			typeName = "date";
		else if (type == 8) {
			typeName = "time";
		}
		return typeName;
	}

	public static String getStrColType(int type) {
		String typeName = "";
		if (type == 1)
			typeName = "String";
		else if (type == 2)
			typeName = "Char";
		else if (type == 3)
			typeName = "Long";
		else if (type == 4)
			typeName = "Integer";
		else if (type == 5)
			typeName = "Double";
		else if (type == 6)
			typeName = "Date";
		else if (type == 7)
			typeName = "Date";
		else if (type == 8) {
			typeName = "Date";
		}
		return typeName;
	}

	public static String getGridColType(int type) {
		String typeName = "";
		if (type == 1)
			typeName = "String";
		else if (type == 2)
			typeName = "String";
		else if (type == 3)
			typeName = "Number";
		else if (type == 4)
			typeName = "Number";
		else if (type == 5)
			typeName = "Number";
		else if (type == 6)
			typeName = "Date";
		else if (type == 7)
			typeName = "Date";
		else if (type == 8) {
			typeName = "Date";
		}
		return typeName;
	}

	public static int getChineseCount(String str) {
		return (str.getBytes().length - str.length());
	}

	public static String[] split(String str, String[] array, String split, int length)
			throws ArrayIndexOutOfBoundsException {
		int index = 0;
		int offset = 0;
		int i = 0;
		while ((index = str.indexOf(split, offset)) != -1) {
			array[i] = str.substring(offset, index);
			offset = index + length;
			++i;
		}
		return array;
	}

	public static void split(String str, List<String> list, String split, int length)
			throws ArrayIndexOutOfBoundsException {
		int index = 0;
		int offset = 0;
		while ((index = str.indexOf(split, offset)) != -1) {
			list.add(str.substring(offset, index));
			offset = index + length;
		}
	}

	public static String checkHex(String lac) {
		int len = lac.length();
		for (int i = 0; i < 4 - len; ++i) {
			lac = "0" + lac;
		}
		return lac;
	}
	

	
	public static void main(String[] agrs){
		System.out.println(checkObjString("ss"));
		List<String> vEventCtList = new ArrayList<String>();
		vEventCtList.add("1");
		vEventCtList.add("2");
		vEventCtList.add("3");
		vEventCtList.add("4");
		List<String> tem = vEventCtList;
		Iterator<String> it = vEventCtList.iterator();
		while(it.hasNext()){
		    String x = it.next();
		    if(!"4".equals(x)){
		        it.remove();
		    }
		}
		for(String str :tem){
			System.out.println(str);
		}
	}
}
