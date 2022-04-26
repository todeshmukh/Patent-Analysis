package com.util;

//Modified

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

//import javax.servlet.ServletRequest;

//import com.db.ConnectionManager;

public class StringHelper {
	  public static StringBuffer connect2Server(String url) {
	        System.out.println(new Date());
	        URL u;
	        StringBuffer sb = new StringBuffer();
	        try {

	            u = new URL(url);
	            URLConnection uc = u.openConnection();
	            uc.setConnectTimeout(5000);
	            InputStream is = uc.getInputStream();
	            byte[] b = new byte[1024];
	            int i = 0;
	            while ((i = is.read(b)) != -1) {
	                String s = new String(b);
	                sb.append(s.trim());
	            }
	            u = null;
	            is.close();
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	        return sb;
	    }


	public static String n2s(Object d) {
		String dual = "";
		if (d == null) {
			dual = "";
		} else {
			dual = d.toString().trim();
		}
		return dual;
	}

	public static double n2s2() {
		double r = 20;
		return r;
	}

	public static double n2d(Object obj) {
		if (obj == null) {
			return 0.0;
		}
		String tmp = "" + obj;
		double ret = 0.0;
		try {
			ret = Double.parseDouble(tmp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0.0;
		}

		return ret;
	}

	public static char n2c(Object d) {
		String dual = n2s(d);
		if (dual.length() > 0)
			return dual.charAt(0);
		else
			return ' ';
	}

	public static String[] getFromDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		int d = c.get(Calendar.DATE);
		int m = c.get(Calendar.MONTH);// -1; //18Feb2016-->18Jan2016
		int y = c.get(Calendar.YEAR);
		// System.out.println(d + "/" + m + "/" + y);
		String[] a = { Integer.toString(m), Integer.toString(d),
				Integer.toString(y) };
		return a;
	}

	public static String[] getToDate() {
		Calendar c = Calendar.getInstance();
		int d = c.get(Calendar.DATE) - 1;
		int m = c.get(Calendar.MONTH);
		int y = c.get(Calendar.YEAR);
		// System.out.println(d + "/" + m + "/" + y);
		String[] a = { Integer.toString(m), Integer.toString(d),
				Integer.toString(y) };
		return a;
	}

	public static String[] getFromTenDate(int i, String dateTimeId)
			throws Exception {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(dateTimeId);
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -i);
		System.out.println(c.DAY_OF_WEEK + ":i=" + i);
		if ((c.get(Calendar.DAY_OF_WEEK) == 1)
				|| (c.get(Calendar.DAY_OF_WEEK) == 7)) {
			String[] a = { "-1" };
			return a;
		} else {
			int d = c.get(Calendar.DATE);
			int m = c.get(Calendar.MONTH) + 1;
			int y = c.get(Calendar.YEAR);
			// System.out.println(d + "/" + m + "/" + y);
			String[] a = { Integer.toString(m), Integer.toString(d),
					Integer.toString(y) };
			// System.out.println(d+":"+m+":"+y);
			return a;
		}
	}

	public static String[] getYearAgoDate(String dateTimeId) throws Exception {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(dateTimeId);
		c.setTime(date);
		c.add(Calendar.YEAR, -1);
		// System.out.println(c.DATE);
		/*
		 * if((c.get(Calendar.DAY_OF_WEEK)==1)||(c.get(Calendar.DAY_OF_WEEK)==7))
		 * { String[] a = {"-1"}; return a; }else{
		 */
		int d = c.get(Calendar.DATE);
		int m = c.get(Calendar.MONTH) + 1;
		int y = c.get(Calendar.YEAR);
		// System.out.println(d + "/" + m + "/" + y);
		String[] a = { Integer.toString(y), Integer.toString(m),
				Integer.toString(d) };
		// System.out.println(d+":"+m+":"+y);
		return a;
		// }
	}

	public static String getTMonth() {
		Calendar c = Calendar.getInstance();
		int d = c.get(Calendar.DATE);
		int m = c.get(Calendar.MONTH);// -1; //18Feb2016-->18Jan2016
		int y = c.get(Calendar.YEAR);
		// System.out.println(d + "/" + m + "/" + y);
		return m + "";
	}

	public static String getFMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		int d = c.get(Calendar.DATE);
		int m = c.get(Calendar.MONTH);// -1; //18Feb2016-->18Jan2016
		int y = c.get(Calendar.YEAR);
		// System.out.println(d + "/" + m + "/" + y);
		return m + "";
	}

	public static String getYear() {
		Calendar c = Calendar.getInstance();
		int d = c.get(Calendar.DATE);
		int m = c.get(Calendar.MONTH);
		int y = c.get(Calendar.YEAR);
		// System.out.println(d + "/" + m + "/" + y);
		return y + "";
	}

	public static int n2i(Object d) {
		int i = 0;
		if (d != null) {
			String dual = d.toString().trim();
			try {
				i = new Integer(dual).intValue();
			} catch (Exception e) {
				System.out.println("Unable to find integer value");
			}
		}
		return i;
	}

	public static int d2i(Object d) {
		int i = 0;
		if (d != null) {
			String dual = d.toString().trim();
			double v = n2d(dual);

			try {
				i = (int) v;
			} catch (Exception e) {
				System.out.println("Unable to find integer value");
			}
		}
		return i;
	}

	// public static double n2d(Object obj) {
	// if (obj == null) {
	// return 0.0;
	// }
	// String tmp = (String) obj;
	// double ret = 0.0;
	// try {
	// ret = Double.parseDouble(tmp);
	// } catch (NumberFormatException e) {
	// return 0.0;
	// }
	//
	// return ret;
	// }

	public static float n2f(Object d) {
		float i = 0;
		if (d != null) {
			String dual = d.toString().trim();
			try {
				i = new Float(dual).floatValue();
			} catch (Exception e) {
				System.out.println("Unable to find integer value");
			}
		}
		return i;
	}

	public static ArrayList n2List(Object d) {
		ArrayList dual = null;
		if (d == null) {
			dual = new ArrayList();
		} else {
			dual = (ArrayList) d;
		}
		return dual;
	}

	public static String dateToMmddyyyy(java.util.Date myDate) {

		if (myDate == null) {
			return "";
		}
		SimpleDateFormat simpledateformat = new SimpleDateFormat("MM/dd/yyyy");

		String dateString = "";

		dateString = simpledateformat.format(myDate);
		return dateString;

	}

	public static String MmddyyyyToYyyymmdd(String d) {
		java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.text.SimpleDateFormat formatter1 = new java.text.SimpleDateFormat(
				"MM/dd/yyyy");

		String dateString = null;
		try {
			java.util.Date myDate = formatter1.parse(d);
			dateString = formatter2.format(myDate);
			return dateString;
		} catch (ParseException e) {
			return null;
		}
	}

	public static String getNowAsDdmmyyyy() {

		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = "";
		java.util.Date myDate = rightNow.getTime();
		dateString = simpledateformat.format(myDate);
		return dateString;
	}

	public static String covertArray2String(int[] id_array) {
		if (id_array != null) {
			String inStr = "";
			for (int i = 0; i < id_array.length; i++) {
				inStr += id_array[i];
				if ((i + 1) < id_array.length) {
					inStr += ", ";
				}
			}
			return (inStr.equals("") ? null : inStr);
		}
		return null;
	}

	public static void main(String ar[]) throws Exception {
		/*
		 * Calendar c = Calendar.getInstance(); c.add(Calendar.DATE, 30);
		 * DateFormat dateFormat = new SimpleDateFormat("MMM d yyyy  hh:mm a");
		 * Date dateTrans = dateFormat.parse("Apr 25 2016  12:44 PM"); Date
		 * dateDB = dateFormat.parse("Apr 24 2016  12:44 PM");
		 * 
		 * if(dateTrans.after(dateDB)) System.out.println("yes"); else
		 * System.out.println("no");
		 */

//		System.out.println(ConnectionManager.getTransVdate("2"));
	}

	public static String hashMap2Query(HashMap myMap) {
		StringBuilder builder = new StringBuilder();

		Iterator entries = myMap.entrySet().iterator();
		while (entries.hasNext()) {
			try {
				java.util.Map.Entry entry = (java.util.Map.Entry) entries
						.next();
				String key = StringHelper.n2s(entry.getKey());
				String value = StringHelper.n2s(entry.getValue());

				builder.append(key);
				builder.append("=");
				builder.append(value);
				builder.append("&");
			} catch (Exception e) {

			}
		}

		return builder.toString();

	}

	public static HashMap getParameters(String str) {
		HashMap hmp = new HashMap();
		try {

			StringTokenizer st1 = new StringTokenizer(str, "&");

			// iterate through tokens
			while (st1.hasMoreTokens()) {
				String token = st1.nextToken();
				StringTokenizer st2 = new StringTokenizer(token, "=");
				try {
					String key = StringHelper.n2s(st2.nextToken());
					String value = StringHelper.n2s(st2.nextToken()) ;

					hmp.put(key, value);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hmp;
	}

//	public static HashMap displayRequest(ServletRequest request) {
//
//		Enumeration paraNames = request.getParameterNames();
//
//		System.out.println(" ------------------------------");
//
//		System.out.println("parameters:");
//
//		HashMap parameters = new HashMap();
//
//		String pname;
//
//		String pvalue;
//
//		while (paraNames.hasMoreElements()) {
//
//			pname = (String) paraNames.nextElement();
//
//			pvalue = request.getParameter(pname);
//
//			System.out.println(pname + " = " + pvalue + "");
//
//			parameters.put(pname, pvalue);
//		}
//
//		System.out.println(" ------------------------------");
//		return parameters;
//	}

	// public static HashMap processMultipart(HttpServletRequest request) {
	// HashMap param = new HashMap();
	// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	// System.out.println(isMultipart);
	// int success = 0;
	// String userid = "", selectedFileName = "";
	//
	// if (!isMultipart) {
	// System.out.println("File Not Uploaded");
	// } else {
	// FileItemFactory factory = new DiskFileItemFactory();
	// ServletFileUpload upload = new ServletFileUpload(factory);
	// List items = null;
	// try {
	// items = upload.parseRequest(request);
	// System.out.println("items: " + items);
	// } catch (FileUploadException e) {
	// e.printStackTrace();
	// }
	// String inputFile = "", outputFile = "";
	// String emailID = "";
	// Iterator itr = items.iterator();
	// while (itr.hasNext()) {
	// FileItem item = (FileItem) itr.next();
	// // textbox checkbox
	// if (item.isFormField()) {
	// String name = item.getFieldName();
	// System.out.println("name: " + name);
	// String value = item.getString();
	// System.out.println("value: " + value);
	// param.put(name, value);
	// } else {
	// // file
	// String itemName = item.getName();
	// String fieldName = item.getFieldName();
	// param.put(fieldName, itemName);
	// InputStream is;
	// try {
	// is = item.getInputStream();
	// BufferedImage keyBuffer = ImageIO.read(is);
	// param.put(fieldName + "_data", keyBuffer);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// }
	// System.out.println("Map is " + param);
	// return param;
	// }

	public static boolean checkConnectivityServer(String ip, int port) {
		boolean success = false;
		try {
			System.out.println("Checking Connectivity With " + ip + " " + port);
			Socket soc = new Socket();
			InetSocketAddress socketAddress = new InetSocketAddress(ip, port);
			soc.connect(socketAddress, 3000);
			success = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(" Connecting to server " + success);
		return success;

	}

}
