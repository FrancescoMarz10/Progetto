package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class TutorEsternoFilter
 */
@WebFilter("/TutorEsternoFilter")
public class TutorEsternoFilter implements Filter {
	FilterConfig filterConfig;
    /**
    /**
     * Default constructor. 
     */
    public TutorEsternoFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String ruolo=(String) ((HttpServletRequest)request).getSession().getAttribute("ruolo");
		if(ruolo==null || !ruolo.equals("TutorEsterno")) {
			//System.out.println("user");
			RequestDispatcher dispatcher=filterConfig.getServletContext().getRequestDispatcher("/Index.jsp");
			dispatcher.forward(request, response);
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		filterConfig=fConfig;
	}
}
