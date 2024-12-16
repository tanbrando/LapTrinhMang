package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BEAN.Lesson;
import model.BO.contentBO;
import model.BO.lessonBO;

/**
 * Servlet implementation class LessonController
 */
@WebServlet("/Lesson/*")
public class LessonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LessonController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String destination;
		ArrayList<Lesson> lessonList;
		Lesson lesson;
		RequestDispatcher rd;
		String pathInfo = req.getPathInfo(); 
		PrintWriter out = resp.getWriter();
		if (pathInfo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Invalid endpoint\"}");
            return;
        }
		
		switch (pathInfo) {
        case "/edit":
        	if (req.getParameter("id")!= null) {
        		destination="/WEB-INF/views/editLesson.jsp";
        		int id = Integer.valueOf(req.getParameter("id"));
        		lesson = lessonBO.getLesson(id);
        		req.setAttribute("lesson",lesson);
        		rd = getServletContext().getRequestDispatcher(destination);
    			rd.forward(req, resp);
        	} else if (req.getParameter("author_id")!=null) {
        		destination="/WEB-INF/views/editLessonList.jsp";
            	int author_id = Integer.valueOf(req.getParameter("author_id"));
            	lessonList = (ArrayList<Lesson>) lessonBO.getUserLessonList(author_id);
            	req.setAttribute("lessonList", lessonList);
            	rd = getServletContext().getRequestDispatcher(destination);
    			rd.forward(req, resp);
        	}
            break;
        case "/list":
        	destination="/WEB-INF/views/lessonList.jsp";
        	lessonList = (ArrayList<Lesson>) lessonBO.getLessonList();
        	req.setAttribute("lessonList", lessonList);
			rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(req, resp);
            break;
        case "/view":
        	destination="/WEB-INF/views/lesson.jsp";
        	int id = Integer.valueOf(req.getParameter("id"));
        	lesson = lessonBO.getLesson(id);
        	req.setAttribute("lesson",lesson);
    		rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(req, resp);
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
            createLesson(req, resp);
            break;
        case "/update":
        	updateLesson(req,resp);
        	break;
        case "/delete":
        	deleteLesson(req, resp);
        	break;
        default:
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\": \"Endpoint not found\"}");
		}
	}
	
	private void createLesson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int author_id = Integer.valueOf(req.getParameter("author_id"));
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String banner_file_path = req.getParameter("banner_file_path");
		Lesson lesson = lessonBO.addLesson(author_id, title, description, banner_file_path);
		resp.sendRedirect("../Lesson/edit?id="+lesson.getID());
	}
	
	private void updateLesson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String banner_file_path = req.getParameter("file_path");
		lessonBO.editLesson(id, title, description, banner_file_path);
		resp.sendRedirect("../Lesson/edit?id="+id);
	}
	
	private void deleteLesson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		int author_id = Integer.valueOf(req.getParameter("author_id"));
		lessonBO.deleteLesson(id);
		resp.sendRedirect("../Lesson/edit?author_id="+author_id);
	}

}
