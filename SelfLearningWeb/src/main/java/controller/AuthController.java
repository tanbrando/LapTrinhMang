package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BEAN.User;
import model.BO.userBO;

/**
 * Servlet implementation class AuthController
 */
@WebServlet("/Auth/*")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String destination;
		RequestDispatcher rd;
		String pathInfo = req.getPathInfo(); 
		PrintWriter out = resp.getWriter();
		if (pathInfo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Invalid endpoint\"}");
            return;
        }
		
		switch (pathInfo) {
        case "/login":
        	destination="/WEB-INF/views/login.jsp";
			rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(req, resp);
            break;
        case "/register":
        	destination="/WEB-INF/views/register.jsp";
			rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(req, resp);
        	break;
        case "/logout":
        	HttpSession session = req.getSession();
            session.setAttribute("currentUser", null);
        	destination="/WEB-INF/views/login.jsp";
        	rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(req, resp);
            break;
        default:
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\": \"Endpoint not found\"}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo(); 
		resp.setContentType("application/json");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		if (pathInfo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Invalid endpoint\"}");
            return;
        }
		
		switch (pathInfo) {
        case "/login":
            handleLogin(req, resp);
            break;
        case "/register":
            handleRegister(req, resp);
            break;
        default:
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\": \"Endpoint not found\"}");
		}
	}
	
		private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        PrintWriter out = resp.getWriter();
	      
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");
	        
	        

	        User user = userBO.login(username, password); // Gọi BO để kiểm tra thông tin đăng nhập

	        if (user != null) {
	            HttpSession session = req.getSession();
	            session.setAttribute("currentUser", user);

	            resp.setStatus(HttpServletResponse.SC_OK);
	            resp.sendRedirect("../Lesson/list");
	        } else {
	            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            resp.sendRedirect("login");
	        }
	    }
		
		private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			String username = req.getParameter("username");
	        String password = req.getParameter("password");
	        String fullname = req.getParameter("fullname");
	        
	        User user = userBO.addUser(username, password, fullname);
	        
	        if (user!= null) {
	        	HttpSession session = req.getSession();
	            session.setAttribute("currentUser", user);
	            resp.setStatus(HttpServletResponse.SC_OK);
	            resp.sendRedirect("../Lesson/list");
	        } else {
	        	resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        	resp.sendRedirect("register");
	        }
		}

}
