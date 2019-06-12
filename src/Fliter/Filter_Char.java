package Fliter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/**
 * Servlet Filter implementation class FilterSample
 */
@WebFilter("/*")
public class Filter_Char implements Filter {
	public void init(FilterConfig fConfig) throws ServletException{ }
	public void doFilter(ServletRequest request,
				ServletResponse response, FilterChain chain)
				throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		System.out.println("フィルター起動");
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
}
