<%@page import="com.service.*"%>
<%@page import="com.model.*"%>
<%@ page import="com.db.SplitPage" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>${article.a_title}</title>
	<link rel="stylesheet" type="text/css" href="/QG/css/article.css">
	<script type="text/javascript" src="/QG/js/savecomment.js"></script>
	<script type="text/javascript" src="/QG/js/saverecomment.js"></script>
	    <script type="text/javascript">
		function list(node){
			var oTdNode = node.parentNode;
		//	alert(oTdNode.nodeName);
			var oUlNode = oTdNode.getElementsByTagName("ul")[0];
		//	alert(oUlNode.nodeName);
			if(oUlNode.className=="open"){
				oUlNode.className="close";
			}
			else{
				oUlNode.className="open";
				}
		}
	</script>
  </head>
  <%
  		Article article =(Article)request.getAttribute("article");
  		UserService  userService = new UserService();
  		CommentService commentService = new CommentService();
  		RecommentService recommentService = new RecommentService();
  		//解决分页问题BUG(当跳转到下一页是，request的（article）对象消失)
  		if(article==null){
  			ArticleService  articleService = new ArticleService();
  			int a_id=Integer.parseInt(request.getParameter("a_id"));
  			article=articleService.getArticleById(a_id);
  			pageContext.setAttribute("article", article);
  		}
  		//
  		int a_id=article.getA_id();
  		//设置分页 
  			SplitPage sp = null;
  			if(session.getAttribute("csp"+a_id)==null){
  				sp=new SplitPage();
  				sp.setPageRows(6);//设置每页记录数
  			}else{
  				sp=(SplitPage)session.getAttribute("csp"+a_id);
  			}
  			sp.setTotalRows(commentService.getTotalRowsByA_id(a_id));
  			String flag=request.getParameter("flag");
  			int currentPage=sp.confirmPage(flag);
  			session.setAttribute("csp"+a_id, sp);
  		List<Comment> comments = commentService.getAllCommentByA_id(a_id, sp);
  		pageContext.setAttribute("comments", comments);
  %>
 <body>
<div class="article">
 <center><h1>${article.a_title}</h1></center>
<hr/>
<center><p class="head">作者：${article.username}   &nbsp;&nbsp;&nbsp;&nbsp;类别：${article.a_type}</p></center>
<center>
			${article.a_createtime} &nbsp;&nbsp;&nbsp;
			<a href="/QG/com/servlet/personal/SaveFavoriteServlet?a_id=${article.a_id}">收藏</a>
</center>
<hr></hr>
<div class="content">
		${article.a_content}
</div>
</div>

<c:if test="${empty comments}">
	<div class="box"><h2>还没有评论！</h2></div>
	</c:if>
<div class="box">
<table >
	<c:if test="${not empty comments}">
		<c:forEach items="${comments}" var="comment">	
			<tr>
			 		<td colspan="2"><hr/></td>
			</tr>	
			<tr>
			<c:set var="cu_id" value="${comment.cu_id}"></c:set>
				<td colspan="2">
					<!-- 根据评论id获取评论用户名 -->
			 		用户名 ：<%=userService.getUserById(Integer.parseInt(pageContext.findAttribute("cu_id").toString())).getUsername() %>
				</td>
			</tr>
			<tr>
				<td >
			 		发布时间 ：${comment.c_createtime}
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td >
					<c:if test="${pageScope.comment.cu_id==sessionScope.user.id}">
			 		<a href="/QG/com/servlet/comment/DeleteCommentServlet?a_id=${article.a_id}&c_id=${comment.c_id}">删除评论</a>
			 		</c:if>
				</td>
			</tr>
			<tr>
					<td colspan="2" style="width:700px">
					评论：${comment.c_content}
					</td>
			</tr>
			<tr>
					<td colspan="2">
					<a href="javascript:void(0)" onclick="list(this)">查看详情</a>		
		<% 
		//从pageContext取出Comment对象，获取其c_id,根据c_id找打全部评论recomments 
		int c_id=((Comment)pageContext.getAttribute("comment")).getC_id();
		List<Recomment> recomments = recommentService.getAllRecommentByC_id(c_id);
		pageContext.setAttribute("recomments", recomments);
		%>
						<ul class="close">
						<c:if test="${empty recomments}">
            					<h2>还没有人回复！</h2>
						</c:if>
						<c:if test="${not empty recomments}">
						<c:forEach items="${recomments}" var="recomment">
                			<li>
                					${recomment.r_username}：${recomment.r_content}
									&nbsp;&nbsp;&nbsp;&nbsp;
								  ${recomment.r_createtime}
								  &nbsp;&nbsp;&nbsp;&nbsp;
									<c:if test="${pageScope.recomment.publishid==sessionScope.user.id}">
			 								<a href="/QG/com/servlet/recomment/DeleteRecommentServlet?a_id=${article.a_id}&r_id=${recomment.r_id}">删除</a>
			 					</c:if>
                			</li>
						</c:forEach>
						</c:if>
						<form action="/QG/com/servlet/recomment/SaveRecommentServlet"  onsubmit="return checkform(this);" method="post">
							<input name="r_content"  value=""  id="r_content"/>
							<input type="hidden" name="c_id" value="${comment.c_id}"/>
							<input type="hidden" name="a_id" value="${article.a_id}"/>
							<input type="submit" value="回复" />
						</form>
            			</ul>
					</td>
			</tr>

			</c:forEach>
	</c:if>
</table>
<table>
		<tr>
		<td>第<%=currentPage %>页</td>
		<td><a href="/QG/article/article.jsp?a_id=${article.a_id}&flag=<%=SplitPage.FIRSTPAGE%>">首页</a></td>
    	<td><a href="/QG/article/article.jsp?a_id=${article.a_id}&flag=<%=SplitPage.PREVIOUSEPAGE %>">上一页</a></td>     
    	<td><a href="/QG/article/article.jsp?a_id=${article.a_id}&flag=<%=SplitPage.NEXTPAGE %>">下一页</a></td>
    	<td><a href="/QG/article/article.jsp?a_id=${article.a_id}&flag=<%=SplitPage.LASTPAGE %>">最后页</a></td>
    </tr>
  </table>
</div>
<div class="comment">
	<form action="/QG/com/servlet/comment/SavaCommentServlet"  onsubmit="return checkformC(this);" method="post">
					<p><textarea name="c_content" id="c_content"></textarea></p>
					<input type="hidden" name="a_id" value="${article.a_id}"/>
					<input type="submit" value="发表评论" class="submit"/>
	</form>
</div>
  </body>
</html>
