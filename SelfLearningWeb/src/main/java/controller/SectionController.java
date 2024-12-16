package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.sectionBO;

/**
 * Servlet implementation class SessionController
 */
@WebServlet("/Section/*")
public class SectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo(); 
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		if (pathInfo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Invalid endpoint\"}");
            return;
        }
		
		switch (pathInfo) {
        case "/create":
            createSection(req, resp);
            break;
        case "/update":
            updateSection(req, resp);
            break;
        case "/delete":
            deleteSection(req, resp);
            break;
        default:
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\": \"Endpoint not found\"}");
		}
	}
	
	private void createSection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int lesson_id = Integer.valueOf(req.getParameter("lesson_id"));
		String heading = req.getParameter("heading");
		sectionBO.addSection(lesson_id, heading);
		resp.sendRedirect("../Lesson/edit?id="+lesson_id);
	}
	
	private void updateSection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int lesson_id = Integer.valueOf(req.getParameter("lesson_id"));
		int id = Integer.valueOf(req.getParameter("id"));
		String heading = req.getParameter("heading");
		sectionBO.editSection(id, heading);
		resp.sendRedirect("../Lesson/edit?id="+lesson_id);
	}
	
	private void deleteSection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int lesson_id = Integer.valueOf(req.getParameter("lesson_id"));
		int id = Integer.valueOf(req.getParameter("id"));
		sectionBO.deleteSection(id);
		resp.sendRedirect("../Lesson/edit?id="+lesson_id);
	}

}
