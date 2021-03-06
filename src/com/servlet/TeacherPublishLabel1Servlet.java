package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LabelDao;
import com.dao.TeacherDao;
import com.entity.Teacher;


public class TeacherPublishLabel1Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			resp.setContentType("text/html");  
	        resp.setCharacterEncoding("utf-8");
	        req.setCharacterEncoding("utf-8");
	        
	        PrintWriter out = resp.getWriter();
	        HttpSession session = req.getSession();
	        String teachernum = (String)session.getAttribute("number");
	        TeacherDao teacherDao = new TeacherDao();
	        ArrayList<Teacher> selectTeacherList = teacherDao.selectTeacher(teachernum);
	        Teacher teacher = selectTeacherList.get(0);
	        String teachername = teacher.getNameString();
	        
	        
	        
	        String name = req.getParameter("name");
	        String place = req.getParameter("place");
	        String studytime = req.getParameter("studytime");
	        String maxnum = req.getParameter("maxnum");
	        String content = req.getParameter("content");
	        String term = req.getParameter("term");
	        String time = req.getParameter("time");
	        String score = req.getParameter("score");
	        if (name=="") {
	        	out.print("<script>alert('课题名称不能为空');window.location.href='tea1_publishlabel.jsp'</script>");
		   		return;
			}else if(place=="") {
				out.print("<script>alert('地点不能为空！');window.location.href='tea1_publishlabel.jsp'</script>");
		   		return;
			}else if(studytime=="") {
				out.print("<script>alert('上课时间不能为空！');window.location.href='tea1_publishlabel.jsp'</script>");
		   		return;
			}else if(maxnum==""){
				out.print("<script>alert('招生人数不能为空！');window.location.href='tea1_publishlabel.jsp'</script>");
		   		return;
			}else if(content=="") {
				out.print("<script>alert('课题简介不能为空！');window.location.href='tea1_publishlabel.jsp'</script>");
		   		return;
			}else if(term=="") {
				out.print("<script>alert('学期不能为空！');window.location.href='tea1_publishlabel.jsp'</script>");
		   		return;
			}else if(time==""){
				out.print("<script>alert('学时不能为空！');window.location.href='tea1_publishlabel.jsp'</script>");
		   		return;
			}else if (score=="") {
				out.print("<script>alert('学分不能为空！');window.location.href='tea1_publishlabel.jsp'</script>");
		   		return;
			}
	        int maxnum2 = Integer.parseInt(maxnum);
	        int score2 = Integer.parseInt(score);
	        LabelDao labelDao = new LabelDao();
	        int flag = labelDao.insertLabel(name, term, teachernum, teachername, place, maxnum2, content, time, score2, studytime);
			if (flag==1) {
				out.print("<script>alert('发布成功!');window.location.href='tea1_publishlabel.jsp'</script>");
			}else {
				out.print("<script>alert('发布失败!');window.location.href='tea1_publishlabel.jsp'</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
