<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.entity.*"%>
<%@ page import="com.dao.*"%>
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
			<li><a href="stu1_readylabel.jsp">候选课题</a></li>
			<li><a href="stu2_mylabel.jsp">我的课题</a></li>
			<li class="now"><a href="stu3_mygrade.jsp">我的成绩</a></li>
			<li><a href="stu4_myinfo.jsp">个人资料</a></li>
		</ul>
	</div>
</div>
<!--头部-->
<div class="space_hx">&nbsp;</div>
<div class="space_hx">&nbsp;</div>
<div class="scd clearfix">
	<div class="scd_l">
		<div class="title">
			<span>我的成绩</span>
		</div>
		<div class="scd_lm">
			<ul class="s_nav s_nav_a">
				<li class="now">
					<div class="li_m">
						<a href="admin_1.report_lookup.jsp"><span>查看</span></a>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="scd_r">
		<div class="title">
			<span>查看</span>
		</div>
			<br>
			<div align = "center">
		<table border="1" cellspacing="0" cellpadding="0" ">
            <tr bgcolor="ff9900" style="font-weight:bold;">
                <td align="center" width="120px">课题编号</td>
                <td align="center" width="120px">课题名称</td>
                <td align="center" width="150px">学期</td>
                <td align="center" width="120px">学生学号</td>
                <td align="center" width="120px">学生姓名</td>
                <td align="center" width="120px">学生专业</td>
                <td align="center" width="120px">学生成绩</td>
            </tr>
            <%
	        String studentnum = (String)session.getAttribute("number");
	        
	        StudentDao studentDao = new StudentDao();
	        ArrayList<Student> selectStudent = studentDao.selectStudent(studentnum);
	        Student student = selectStudent.get(0);
            Stu_LabDao stu_LabDao = new Stu_LabDao();//根据学生学号查询学生课题表
	        ArrayList<Stu_lab> selectStu_lab1 = stu_LabDao.selectStu_lab1(studentnum);
            if(selectStu_lab1!= null && selectStu_lab1.size() > 0){
            for (int i = 0; i < selectStu_lab1.size(); i++) {
            	Stu_lab stu_lab = selectStu_lab1.get(i);
	        	int labelnum = stu_lab.getLabelnum();
	        	LabelDao labelDao = new LabelDao();
	        	ArrayList<Label> selectLabel = labelDao.selectLabel(null, labelnum);
	        	Label label = selectLabel.get(0);
				out.print("<tr>");
	            out.print("<td align=center>"+label.getNumber()+"</td>");
	            out.print("<td align=center>"+label.getNameString()+"</td>");
	            out.print("<td align=center>"+label.getTermString()+"</td>");
	            out.print("<td align=center>"+student.getNumberString()+"</td>");
	            out.print("<td align=center>"+student.getNameString()+"</td>");
	            out.print("<td align=center>"+student.getMajorString()+"</td>");
	            out.print("<td align=center>"+stu_lab.getGrade()+"</td>");
	            out.print("</tr>");
			}
            }
    		%>
        </table>
		</div>
		
		
		
	</div>
</div>

</html>