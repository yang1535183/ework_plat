package com.diiwon.mis.plat.base.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

/****
 * 自定义访问对象工具类
 * 
 * @author sunshine
 *
 */
public class CusAccessObjectUtil {

	/** 获取物理的真实地址 **/
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取操作系统,浏览器及浏览器版本信息
	 */
	public static String getOsAndBrowserInfo(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;
		String user = userAgent.toLowerCase();

		String os = "";
		String browser = "";

		// =================OS Info=======================
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}
		// ===============Browser===========================
		if (user.contains("edge")) {
			browser = (userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera")) {
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			} else if (user.contains("opr")) {
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
			}

		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
			browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}

		return "操作系统：" + os + "/浏览器：" + browser;
	}

	public static void getIpCity(HttpServletRequest request) {
		String ip = getIpAddress(request);
		try {
			// 创建 GeoLite2 数据库
			File database = new File("/diiwon/mis/plat/GeoLite2-City.mmdb");
			// 读取数据库内容
			DatabaseReader reader = new DatabaseReader.Builder(database).build();
			InetAddress ipAddress = InetAddress.getByName(ip);

			// 获取查询结果
			CityResponse response = reader.city(ipAddress);

			// 获取国家信息
			Country country = response.getCountry();
			System.out.println("country.getIsoCode()"+country.getIsoCode()); // 'CN'
			System.out.println("country.getIsoCode()"+country.getName()); // 'China'
			System.out.println("country.getIsoCode()"+country.getNames().get("zh-CN")); // '中国'

			// 获取省份
			Subdivision subdivision = response.getMostSpecificSubdivision();
			System.out.println(subdivision.getName());
			System.out.println(subdivision.getIsoCode()); // '45'
			System.out.println(subdivision.getNames().get("zh-CN")); // '广西壮族自治区'

			// 获取城市
			City city = response.getCity();
			System.out.println(city.getName()); // 'Nanning'
			Postal postal = response.getPostal();
			System.out.println(postal.getCode()); // 'null'
			System.out.println(city.getNames().get("zh-CN")); // '南宁'
			Location location = response.getLocation();
			System.out.println(location.getLatitude()); // 22.8167
			System.out.println(location.getLongitude()); // 108.3167
		} catch (Exception e) {
			System.out.println("无法通过"+ip+"获取到所在的城市。");
		}
	}

	/*** 获取物理地址 **/
	public static String getMACAddress(HttpServletRequest request) {
		try {
			String ip = getIpAddress(request);
			String line = "";
			String macAddress = "";
			final String MAC_ADDRESS_PREFIX = "MAC Address = ";
			final String LOOPBACK_ADDRESS = "127.0.0.1";
			// 如果为127.0.0.1,则获取本地MAC地址。
			if (LOOPBACK_ADDRESS.equals(ip)) {
				InetAddress inetAddress = InetAddress.getLocalHost();
				// 此方法需要JDK1.6。
				byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
				// 下面代码是把mac地址拼装成String
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					if (i != 0) {
						sb.append("-");
					}
					// mac[i] & 0xFF 是为了把byte转化为正整数
					String s = Integer.toHexString(mac[i] & 0xFF);
					sb.append(s.length() == 1 ? 0 + s : s);
				}
				// 把字符串所有小写字母改为大写成为正规的mac地址并返回
				macAddress = sb.toString().trim().toUpperCase();
				return macAddress;
			}
			// 获取非本地IP的MAC地址
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader isr = new InputStreamReader(p.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				if (line != null) {
					int index = line.indexOf(MAC_ADDRESS_PREFIX);
					if (index != -1) {
						macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();
					}
				}
			}
			br.close();
			return macAddress;
		} catch (Exception e) {
			e.printStackTrace();
			return "获取失败";
		}
	}

	/** 获取资源 **/
	public void doGetAll(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();// 返回请求行中的资源名称
		String url = request.getRequestURL().toString();// 获得客户端发送请求的完整url
		String ip = request.getRemoteAddr();// 返回发出请求的IP地址
		String params = request.getQueryString();// 返回请求行中的参数部分
		String host = request.getRemoteHost();// 返回发出请求的客户机的主机名
		int port = request.getRemotePort();// 返回发出请求的客户机的端口号。
		System.out.println("ip:" + ip);
		System.out.println("url:" + url);
		System.out.println("uri:" + uri);
		System.out.println("params:" + params);
		System.out.println("host:" + host);
		System.out.println("port:" + port);
	}
}
