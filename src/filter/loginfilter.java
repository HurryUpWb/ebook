package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Me;
import utils.MyUtils;

public class loginfilter implements Filter {

    public loginfilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		Me me=(Me)req.getSession().getAttribute("me");
		String path=req.getServletPath();
		path=path.substring(1);
		if(!MyUtils.IsNull(me) || "getUsr.usr".equals(path)){
			chain.doFilter(request, response);
		}else{
			resp.sendRedirect(req.getContextPath()+"/view/login.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {	}

}
