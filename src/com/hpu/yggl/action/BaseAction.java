package com.hpu.yggl.action;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, ServletContextAware {
	private static final long serialVersionUID = 745L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ServletContext application;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.application = context;
	}

	public void printOut(String str) {

		HttpServletResponse response = this.getResponse();
		// HttpServletRequest request = this.getRequest();
		try {
			response.setContentType("text/html;CHARSET=utf-8");
			response.setCharacterEncoding("UTF-8");
			// String callback = request.getParameter("callback");
			// response.getWriter().print(callback + "(" + str + ")");
			response.getWriter().print(str);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 得到HttpRequest
	 * 
	 * @return 获得的HttpRequest
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 得到HttpSession
	 * 
	 * @return 获得的HttpSession
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 得到HttpResponse
	 * 
	 * @return 获得的HttpResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 得到上下文路径，例如/portal
	 * 
	 * @return 上下文路
	 */
	public String getContext() {
		return this.getRequest().getContextPath();
	}

	/**
	 * 得到服务全路径，例如：http://127.0.0.1:8080/portal
	 * 
	 * @return 服务全路径
	 */
	public String getBasePath() {
		String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":"
				+ this.getRequest().getServerPort() + this.getContext();
		return basePath;
	}

	/**
	 * 获取真是的客户端的IP �?般情况直接用request.getRemoteAddr()方法
	 * 获取客户端的IP地址，但是如果客户端使用了代理服务器，该方法得到的是 代理服务器的地址，不是客户端的地�?�?
	 * 
	 * 代理服务器在转发客户端的请求时，会在HTTP的头消息中增加"x-forwarded-for"
	 * 信息，该信息中就保存有原客户端的地址。request的getHeader方法
	 * HTTP头中�?"x-forwarded-for"信息，便可以获得客户端的真实IP
	 * 
	 * 当客户端通过多级代理访问时，"x-forwarded-for"信息中的第一个非unknown字符集 即为客户端的真实IP
	 * 
	 * @return 返回真实的IP地址
	 */
	public String getRemoteAddr() {
		String remoteAddr = null;
		remoteAddr = this.getRequest().getHeader("x-forwarded-for");
		if (remoteAddr != null && remoteAddr.length() != 0) {
			while ((remoteAddr != null) && (remoteAddr.equals("unknown"))) {
				remoteAddr = this.getRequest().getHeader("x-forwarded-for");
			}
		}
		if (remoteAddr == null) {
			remoteAddr = this.getRequest().getHeader("Proxy-Client-IP");
		}
		if (remoteAddr == null) {
			remoteAddr = this.getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (remoteAddr == null) {
			remoteAddr = this.getRequest().getRemoteAddr();
		}
		return remoteAddr;
	}
}
