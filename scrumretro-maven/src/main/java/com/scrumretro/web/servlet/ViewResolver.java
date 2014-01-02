package com.scrumretro.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scrumretro.exception.InvalidPathException;

@WebServlet(
		name = "view", 
		urlPatterns = { 
				"/index.html",
				"/vw/*"
		})
public class ViewResolver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		System.out.println("contextPath.."+contextPath);
		System.out.println("requestURI.."+requestURI);
		String dispatchURL = getDispatchPath(contextPath, requestURI);
		System.out.println(dispatchURL);
		request.getRequestDispatcher(dispatchURL).forward(request, response);
	}
	protected String getDispatchPath(String contextPath, String requestURI){
		String path = requestURI.replaceFirst(contextPath, "").replaceFirst("/", "");
		String view = "";
		if("".equals(path)||path.startsWith("index.")||path.startsWith("default."))
			view = "index";
		else if(path.startsWith("vw/")){
			view = path.replace("vw/","").replace(".html","");
		}
		else
			throw new InvalidPathException(requestURI+" not supported.");
		return "/WEB-INF/pages/"+view+".jsp";
	}
}
