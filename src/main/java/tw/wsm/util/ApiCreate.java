package tw.wsm.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(location = "D:/MyProject/website/KLweb/images")
@WebServlet("/ApiCreate")
public class ApiCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ApiCreate() {
		File dir = new File("D:/MyProject/website/KLweb/images");
		if (!dir.exists())
			dir.mkdir();
		File dir2 = new File("D:/MyProject/website/KLweb/video");
		if (!dir2.exists())
			dir2.mkdir();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String username = request.getParameter("username");
		username = username == null ? "" : username;
		String greeting = String.format("%s您好<br/>", username);
		request.setAttribute("greeting", greeting);
		
		String imgFileName = null;
		String videoFileName = null;
		ArrayList<HashMap<String, String>> messages = new ArrayList<HashMap<String, String>>();
		for (Part part : request.getParts()) { // request.getParts() 傳回Collection<Part>物件
			if ("uploadFile".equals(part.getName())) {
				// 取得上傳的檔案名稱
				imgFileName = part.getSubmittedFileName();
//				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
//				String extension = FilenameUtils.getExtension(filename);
//				filename = "images/thumbnail" + dtf.format(LocalDateTime.now())+"."+extension;
				// 取得上傳的檔案大小
				long filesize = part.getSize();
				HashMap<String, String> message = new HashMap<String, String>();
				if (imgFileName.equals("")) {// 當用戶端未上傳任何檔案時
					message.put("text", String.format("您上傳無效的檔案，請重新執行...", username));
					message.put("imgUrl", "");
				} else {
					// 將上傳檔案存檔
					part.write(imgFileName);
					message.put("text", String.format("檔案上傳成功(檔名:%s 大小:%,dbytes)...",imgFileName, filesize));
					message.put("imgUrl", String.format("videoImgUpload_uploadedImg?filename=%s", imgFileName));
				}
				messages.add(message);
			}else if ("uploadFile2".equals(part.getName())) {
				// 取得上傳的檔案名稱
				videoFileName = part.getSubmittedFileName();
				// 取得上傳的檔案大小
				long filesize = part.getSize();
				HashMap<String, String> message = new HashMap<String, String>();
				if (videoFileName.equals("")) {// 當用戶端未上傳任何檔案時
					message.put("text", String.format("您上傳無效的檔案，請重新執行...", username));
					message.put("imgUrl", "");
				} else {
					// 將上傳檔案存檔
					part.write("D:/MyProject/website/KLweb/video/"+videoFileName);
					message.put("text", String.format("檔案上傳成功(檔名:%s 大小:%,dbytes)...",videoFileName, filesize));
//					message.put("imgUrl", String.format("videoImgUpload_uploadedImg?filename=%s", videoFileName));
				}
				messages.add(message);
			}
		}
//		PrintWriter out = response.getWriter();
//		String videoName = request.getParameter("videoName");
//
//		String responseText = insertVideoPic(videoName, imgFileName,videoFileName);
//		out.print(responseText);
//		request.setAttribute("messages", messages);
//		request.getRequestDispatcher("/WEB-INF/views/uploadResponse.jsp").forward(request, response);
//		
	}

}
