<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.*"%>
<%@page import="com.entity.Student"%>
<%@page import="com.dao.StudentDao"%>
<%@page import="com.entity.Stu_lab"%>
<%
	response.setContentType("text/html;charset=utf-8");
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>开放实验选课系统</title>
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/thems.css">
<link rel="stylesheet" type="text/css" href="css/responsive.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
p.leftmargin {
	margin-left: 35%
}
</style>
</head>

<!--头部-->
<div class="header">
	<div class="space_hx">&nbsp;</div>
	<div class="head_td"><a href="index.jsp">开放实验选课系统</a></div>
	<div class="space_hx">&nbsp;</div>
	<div class="nav_m">
		<div class="n_icon">&nbsp;</div>
		<ul class="nav clearfix">
			<li><a href="tea1_publishlabel.jsp">发布课题</a></li>
			<li><a href="tea2_stumanage.jsp">学生管理</a></li>
			<li class="now"><a href="tea3_inputgrade.jsp">成绩录入</a></li>
			<li><a href="tea4_myinfo.jsp">个人资料</a></li>
		</ul>
	</div>
</div>
<!--头部-->
<div class="space_hx">&nbsp;</div>
<div class="space_hx">&nbsp;</div>
<div class="scd clearfix">
	<div class="scd_l">
		<div class="title">
			<span>成绩录入</span>
		</div>
		<div class="scd_lm">
			<ul class="s_nav s_nav_a">
				<li>
					<div class="li_m">
						<a href="tea3_inputgrade.jsp"><span>录入</span></a>
					</div>
				</li>
				<li class="now">
					<div class="li_m">
						<a href="tea3_searchgrade.jsp"><span>查询</span></a>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="scd_r">
		<div class="title">
			<span>查询</span>
		</div>
		<form id="input" name="input" method ="post" action = "TeacherSearchGradeServlet">  
        		<input id="input"  name ="input" type="submit" value="查询已录入成绩的学生" />
		</form>
		<br>
		<div align = "center">
		<table border="1" cellspacing="0" cellpadding="0" ">
            <tr bgcolor="ff9900" style="font-weight:bold;">
                <td align="center" width="120px">课题编号</td>
                <td align="center" width="120px">学生学号</td>
                <td align="center" width="120px">学生姓名</td>
                <td align="center" width="120px">学生班级</td>
                <td align="center" width="120px">学生电话</td>
                <td align="center" width="120px">学生专业</td>
                <td align="center" width="120px">学生成绩</td>
            </tr>
            <%
    		ArrayList<Stu_lab> stu_labList = (ArrayList<Stu_lab>)request.getAttribute("stu_labList");
            if(stu_labList!= null && stu_labList.size() > 0){
            for (int i = 0; i < stu_labList.size(); i++) {
            		Stu_lab stu_lab = stu_labList.get(i);
            		StudentDao studentDao = new StudentDao();
            		ArrayList<Student> students = studentDao.selectStudent(stu_lab.getStudentnumString());
            		Student student = students.get(0);
					out.print("<tr>");
		            out.print("<td >"+stu_lab.getLabelnum()+"</td>");
		            out.print("<td >"+stu_lab.getStudentnumString()+"</td>");
		            out.print("<td >"+student.getNameString()+"</td>");
		            out.print("<td >"+student.getClassString()+"</td>");
		            out.print("<td >"+student.getTelString()+"</td>");
		            out.print("<td >"+student.getMajorString()+"</td>");
		            out.print("<td >"+stu_lab.getGrade()+"</td>");
		            out.print("</tr>");
			}
            }
    		%>
        </table>
		</div>
	</div>
</div>

</html>