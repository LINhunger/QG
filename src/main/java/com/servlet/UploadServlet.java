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

import com.model.Resource;
import com.model.User;
import com.service.ResourceService;
import com.util.UploadUtils;

public class UploadServlet extends HttpServlet {

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
		//步骤一：构造工厂
		DiskFileItemFactory factory= new DiskFileItemFactory();
		//步骤二：获得解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名 乱码问题
		upload.setHeaderEncoding("utf-8");
		//步骤三：对请求内容进行解析
		try {
			List<FileItem> list = upload.parseRequest(request);
			ResourceService  service  = new ResourceService();
			//将资源信息保存在resource中
			Resource  resource = new Resource();
			//根据session获取用户名
			resource.setUsername(((User)request.getSession().getAttribute("user")).getUsername());
			//遍历集合
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){
					String name = fileItem.getFieldName();
					String value =fileItem.getString("utf-8");
					System.out.println("普通上传项："+name+"..."+value);
					//把文件描述存到resource中
					resource.setDescription(value);
				}
				else{
					String filename =fileItem.getName();
					InputStream in = new BufferedInputStream(fileItem.getInputStream());
					// 截取文件路径获取文件名
					if (filename.contains("\\")) {
						filename = filename.substring(filename
								.lastIndexOf("\\") + 1);
					}
					System.out.println("文件上传项："+filename);
					if(filename.equals("")){
						request.setAttribute("message", "上传文件项不能为空");
						request.getRequestDispatcher("/uploadfile.jsp").forward(request, response);
						return;
					}
					//将真实文件名realname保存到resource
					resource.setRealname(filename);
					// 保证上传文件名唯一
					filename = UUID.randomUUID().toString() + filename;
					// 生成随机目录
					String randomPath = UploadUtils
							.generateRandomPath(filename);// 生成目录不一定存在 ---创建
					//获取WEB-INF/resource 目录的路径
					//存在服务器
					File path = new File(getServletContext().getRealPath(
						"/WEB-INF/resource" + randomPath));
				//	File path = new File("D:\\JAVA\\MyEclipse\\workplace\\QG\\WebRoot\\WEB-INF\\resource" + randomPath);
					System.out.println(path);
					//保存uuidname,savepath到resource
					String uuidname = filename;
					resource.setUuidname(uuidname);
					resource.setSavepath(randomPath);
					path.mkdirs();
				//	HttpSession  session = request.getSession();//测试部分
					//session.setAttribute("path",randomPath+"/"+filename);
					// 将文件内容输出WEB-INF/resource 目录
					File targetFile = new File(path, filename);
					OutputStream out = new BufferedOutputStream(
							new FileOutputStream(targetFile));
					int temp;
					while((temp=in.read())!=-1){
						out.write(temp);
					}
					out.close();
					in.close();
				}
			}
			service.saveResource(resource);
			request.setAttribute("message", "文件上传成功");
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
