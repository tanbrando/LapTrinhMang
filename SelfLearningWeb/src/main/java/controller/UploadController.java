package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadController
 */
@WebServlet("/Upload")
@MultipartConfig
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		String type = request.getParameter("type");
		String uploadDir = getServletContext().getRealPath("/upload/");
		File uploadFolder = new File(uploadDir + type);
        Part videoPart = request.getPart("file");
        int chunkIndex = Integer.parseInt(request.getParameter("chunkIndex"));
        int totalChunks = Integer.parseInt(request.getParameter("totalChunks"));
        
        
        File finalFile = new File(uploadFolder, fileName);
        try (InputStream videoStream = videoPart.getInputStream();
             FileOutputStream fileOut = new FileOutputStream(finalFile, true)) {  // 'true' sẽ mở file ở chế độ append
             
            byte[] buffer = new byte[1024 * 1024];
            int bytesRead;
            while ((bytesRead = videoStream.read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
            }
        }
        if (chunkIndex == totalChunks - 1) {
        	response.setStatus(HttpServletResponse.SC_OK);
        } else {
        	response.setStatus(HttpServletResponse.SC_OK);
        }
        
	}

}
