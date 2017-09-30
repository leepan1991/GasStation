package com.volunteer.common;

import com.alibaba.fastjson.JSON;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Controller层 HTTP工具类。
 * 
 * @author liuyixin
 */
public final class HttpUtil {
	static final int BUFFER_SIZE = 32 * 1024;// 文件下载缓冲区
	public static final String IMAGE_JPEG = "image/jpeg";
	public static final String FORM_DATA = "multipart/form-data";

	// -- Content Type 定义 --//
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";

	// -- Header 定义 --//
	public static final String AUTHENTICATION_HEADER = "Authorization";

	// -- 常用数值定义 --//
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	private static ClientConfig clientCfg = null;

	public static String executePost(String url, Map<String, String> paramMap) throws URISyntaxException {
		url = url.replaceAll("\"", "");
		URI u = new URI(url);
		Client client = Client.create();
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			params.add(entry.getKey(), entry.getValue());
		}
		return resource.type(MediaType.APPLICATION_FORM_URLENCODED).entity(params).post(String.class);
	}

	/**
	 * 执行简单的GET请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String executeGet(String url) throws Exception {
		url = url.replaceAll("\"", "");
		Client client = Client.create(jerseryClientCfg());
		URI uri = new URI(url);
		WebResource resource = client.resource(uri);
		return resource.get(String.class);
	}

	/**
	 * 执行简单的GET请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static <T> T executeGet(String url, Class<T> clz) throws Exception {
		String result = executeGet(url);
		if (StringUtils.isNoneBlank(result)) {
			return JSON.parseObject(result, clz);
		}
		return null;
	}

	/**
	 * 执行简单的GET请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static byte[] executeGetForByteArray(String url) throws Exception {
		url = url.replaceAll("\"", "");
		Client client = Client.create(jerseryClientCfg());
		URI uri = new URI(url);
		WebResource resource = client.resource(uri);
		return resource.get(byte[].class);
	}

	/**
	 * 执行POST请求
	 * 
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static String executePost(String url, String body) throws Exception {
		Client client = Client.create(jerseryClientCfg());
		URI uri = new URI(url);
		WebResource resource = client.resource(uri);
		return resource.type(MediaType.APPLICATION_JSON).entity(body).post(String.class);
	}

	/**
	 * 获取请求IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (!checkIP(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!checkIP(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!checkIP(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	/**
	 * POST 二进制。
	 * 
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, byte[] body) throws Exception {
		Client client = Client.create(jerseryClientCfg());
		URI uri = new URI(url);
		WebResource resource = client.resource(uri);
		return resource.type(MediaType.APPLICATION_OCTET_STREAM).entity(body).post(String.class);
	}

	/**
	 * POST 二进制。
	 * 
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static String postJSON(String url, String body) throws Exception {
		Client client = Client.create(jerseryClientCfg());
		URI uri = new URI(url);
		WebResource resource = client.resource(uri);
		return resource.type(MediaType.APPLICATION_JSON).entity(body).post(String.class);
	}

	/**
	 * 上传文件。
	 * 
	 * @param url URL
	 * @param filePath 文件路径 。
	 * @param params 非File参数。
	 * @param fileFieldName 文件属性名。
	 * @return
	 * @throws Exception
	 */
	/*	public static String postFile(String url, Map<String, String> params, String fileFieldName, String filePath) throws Exception {
			Client client = Client.create(jerseryClientCfg());
			URI uri = new URI(url);
			WebResource resource = client.resource(uri);
			final File fileToUpload = new File(filePath);
			final FormDataMultiPart multiPart = new FormDataMultiPart();
			if (fileToUpload != null) {
				multiPart.bodyPart(new FileDataBodyPart(fileFieldName, fileToUpload, MediaType.APPLICATION_OCTET_STREAM_TYPE));
			}
			if (params != null) {
				for (Entry<String, String> param : params.entrySet()) {
					multiPart.field(param.getKey(), param.getValue());
				}
			}
			return resource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(String.class, multiPart);
		}*/

	/**
	 * 返回客户端JSON数据。
	 * 
	 * @param obj
	 * @param response
	 */
	public static void returnJson(Object obj, HttpServletResponse response) {
		try {
			if (obj == null) {
				response.setStatus(404);
				return;
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			IOUtils.write(JSON.toJSONString(obj), response.getOutputStream(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将二进制流返回客户端。
	 * 
	 * @param data
	 * @param response
	 */
	public static void returnByteArray(byte[] data, HttpServletResponse response) {
		if (ArrayUtils.isEmpty(data)) {
			return;
		}
		try {
			IOUtils.write(data, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取请求完整的URL。
	 * 
	 * @param request
	 * @return
	 */
	public static String getFullURL(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		if (request.getQueryString() != null) {
			url.append('?');
			url.append(request.getQueryString());
		}
		return url.toString();
	}

	/**
	 * 获取请求参数。
	 */
	public static String getParam(HttpServletRequest request, String name) {
		String header = request.getHeader(name);
		if (StringUtils.isNotBlank(header)) {
			return header.trim();
		}
		String parameter = request.getParameter(name);
		if (StringUtils.isNotBlank(parameter)) {
			return parameter.trim();
		}
		return null;
	}

	/**
	 * 获取bool参数。
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static boolean getBooleanParam(HttpServletRequest request, String name, boolean defaultValue) {
		String value = getParam(request, name);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return Boolean.parseBoolean(value);
	}

	/**
	 * 获取bool参数。
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static int getIntParam(HttpServletRequest request, String name, int defaultValue) {
		String value = getParam(request, name);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	 * @param obj
	 * @return
	 */
	public static String urlEncode(String obj) {
		try {
			return URLEncoder.encode(obj, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return obj;
		}
	}

	/**
	 * @param obj
	 * @return
	 */
	public static String urlDecode(String obj) {
		try {
			return URLDecoder.decode(obj, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return obj;
		}
	}

	public static String encodeFilePathToURL(String filePath) {
		String str = filePath.replaceAll("/", "{0}").replaceAll("\\.", "{1}");
		return urlEncode(str);
	}

	public static String decodeURLToFilePath(String fileUrl) {
		String str = urlDecode(fileUrl);
		return str.replaceAll("\\{0\\}", "/").replaceAll("\\{1\\}", ".");
	}

	/**
	 * 设置客户端缓存过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
	}

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified 内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag 内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName 下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName 下载后的文件名.
	 * @param userAgent 用到解决中文名乱码
	 */
	public static void setFileDownloadHeader(HttpServletResponse response, String fileName, String userAgent) {
		try {
			String encodedfileName = null;
			userAgent = userAgent.toUpperCase();
			if (userAgent.contains("MSIE")) {
				encodedfileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (userAgent.contains("FIREFOX")) {
				encodedfileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
			} else {// chrome两种都可以
				encodedfileName = URLEncoder.encode(fileName, "UTF-8");
			}
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		} catch (Exception e) {
		}
	}

	/**
	 * 取得带相同前缀的Request Parameters.
	 * 
	 * 返回的结果Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix) {
		Enumeration<?> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {// NOSONAR
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * 设置http响应文件
	 * 
	 * @param file 系统文件
	 * @param response http响应
	 * @param contentType 响应头类型
	 */
	public static void setHttpFile(File file, HttpServletResponse response, String contentType) {
		if (file.isFile()) {
			try {
				setHttpInputStream(new FileInputStream(file), response, contentType);
			} catch (IOException ignore) {
			}
		}
	}

	/**
	 * 设置http响应输入流
	 * 
	 * @param inputStream 输入流
	 * @param response http响应
	 * @param contentType 响应头类型
	 */
	public static void setHttpInputStream(InputStream inputStream, HttpServletResponse response, String contentType) {
		response.setContentType(contentType);
		BufferedInputStream in = null;
		ServletOutputStream out = null;
		try {
			response.setContentLength((int) inputStream.available());
			in = new BufferedInputStream(inputStream, BUFFER_SIZE);
			out = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
				out.flush();
			}
		} catch (IOException ignore) {
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.flush();
					out.close();
				}
			} catch (IOException ignore) {
			}
		}
	}

	/**
	 * 文件下载
	 * 
	 * @param request 请求
	 * @param response 响应
	 * @param fileName 文件名
	 * @param file 文件
	 * @param contentType 文件类型
	 */
	public static void fileDownload(HttpServletRequest request, HttpServletResponse response, String fileName, File file,
			String contentType) {
		String userAgent = request.getHeader("User-Agent");
		setFileDownloadHeader(response, fileName, userAgent);
		setHttpFile(file, response, contentType);
	}

	/**
	 * 获取请求头。
	 * 
	 * @param request
	 * @return
	 */
	public static String getHeaderStr(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String value = request.getHeader(headerName);
			sb.append(String.format("%s=%s\n", headerName, value));
		}
		return sb.toString();
	}

	public static String getBaseUrl(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}

	private static ClientConfig jerseryClientCfg() {
		if (clientCfg != null) {
			return clientCfg;
		}
		ClientConfig config = new DefaultClientConfig();
		SSLContext ctx = null;
		try {
			TrustManager[] trustManagers = new TrustManager[] { new MyX509TrustManager() };
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, trustManagers, null);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return clientCfg;
		} catch (KeyManagementException e) {
			e.printStackTrace();
			return clientCfg;
		}
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		}, ctx));
		clientCfg = config;
		return config;
	}

	private static boolean checkIP(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip) || ip.split(".").length != 4) {
			return false;
		}
		return true;
	}

	public static class MyX509TrustManager implements X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

		}
	}

	public static void main(String[] args) {
		/*String str = encodeFilePathToURL("C:/liuyixin/test.png");
		System.out.println(str);
		System.out.println(decodeURLToFilePath(str));*/

		// C:{0}liuyixin{0}test{1}png
		System.out.println("C:{0}liuyixin{0}test{1}png".replaceAll("\\{0\\}", "/"));
	}

}
