/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

/**
 *
 * @author Michał
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);

            String reqURI = req.getRequestURI();
            if (reqURI.contains("/admin") && ses != null && ses.getAttribute("login") != null && ses.getAttribute("role").equals(1)) {
                chain.doFilter(request, response);
            } else if (!reqURI.contains("/admin") && (reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("login") != null) //                    || reqURI.indexOf("/faces/") >= 0 || reqURI.contains("javax.faces.resource") 
                    )) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
            }

        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
