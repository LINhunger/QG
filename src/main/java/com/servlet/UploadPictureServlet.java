package com.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.model.User;
import com.service.UserService;
import com.util.UploadUtils;

public class UploadPictureServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("user");
		//步骤一：构造工厂
		DiskFileItemFactory factory= new DiskFileItemFactory();
		//步骤二：获得解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名 乱码问题
		upload.setHeaderEncoding("utf-8");
		//步骤三：对请求内容进行解析
		try {
			List<FileItem> list = upload.parseRequest(request);
			//遍历集合
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){
					String name = fileItem.getFieldName();
					String value =fileItem.getString("utf-8");
					System.out.println("普通form项："+name+"..."+value);
				}
				else{
					String filename =fileItem.getName();
					InputStream in = new BufferedInputStream(fileItem.getInputStream());
					// 解决老版本浏览器IE6 文件路径存在问题
					if (filename.contains("\\")) {
						filename = filename.substring(filename
								.lastIndexOf("\\") + 1);
					}
					System.out.println("文件上传项："+filename);
					String regex = ".jpg";
					if(filename!=null&&!filename.contains(regex)){
						request.setAttribute("message", "上传格式不符");
						request.getRequestDispatcher("/upload.jsp").forward(request, response);
					}
					else{
					// 保证上传文件名唯一
					filename = user.getId()+".jpg";
					//获取数据库服务对象
					UserService service = new UserService();
					
									
					File path = new File(getServletContext().getRealPath("/jpg" ) +"\\"+user.getUsername());
					path.mkdirs();
					System.out.println(getServletContext().getRealPath("/jpg" ));
					user.setPicture("/"+user.getUsername()+"/"+filename);
					//将图片路径保存到用户数据库
					service.savePicture(user.getUsername(), user.getPicture());
					// 将文件内容输出/jpg 目录
					File targetFile = new File(path, filename);
					OutputStream out = new BufferedOutputStream(
							new FileOutputStream(targetFile));
					int temp;
					while((temp=in.read())!=-1){
						out.write(temp);
					}
					out.close();
					in.close();
				response.sendRedirect("/QG/personal/myarticle.jsp");
				}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	}

}
