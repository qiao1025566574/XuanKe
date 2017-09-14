package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LabelDao;
import com.dao.Stu_LabDao;
import com.entity.Label;
import com.entity.Stu_lab;

public class TeacherStudentManageServlet extends HttpServlet {
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
	        
	        //ȡ�õ�¼״̬�µ���ʦ�ı��
	        HttpSession session = req.getSession();
	        String teachernum = (String)session.getAttribute("number");
	        
	        //��ѯ����ʦ���µ����п���
	        LabelDao labelDao = new LabelDao();
	        ArrayList<Label> selectLabelList = labelDao.selectLabel(teachernum, -1);//������Ų�ѯ���������
	        
	        ArrayList<Integer> listsint = new ArrayList<Integer>();//��Ÿ���ʦ���µ����п�����
	        ArrayList<Stu_lab> stu_labList = new ArrayList<Stu_lab>();//������ղ�ѯ������ѧ�������
	        
	        //�ѿ����ŷŽ���
	        for(int i=0;i<selectLabelList.size();i++){
	        	Label label = selectLabelList.get(i);
	        	listsint.add(label.getNumber());
	        }
	        //��ѧ��������еĸ���ʦ�Ŀ����µ�ѧ��ȫ��ȡ����
	        Stu_LabDao stu_LabDao = new Stu_LabDao();
	        for(int i=0;i<listsint.size();i++){
	        	ArrayList<Stu_lab> selectStu_lab2 = stu_LabDao.selectStu_lab2(listsint.get(i), 1);//���ݸñ�����β�ѯ����
	        	for(int j =0 ;j<selectStu_lab2.size();j++){
	        		stu_labList.add(selectStu_lab2.get(j));
	        	}
	        }
			req.setAttribute("stu_labList", stu_labList);
			RequestDispatcher rd = req.getRequestDispatcher("tea2_stumanage.jsp");  
			try {  
			    rd.forward(req, resp);  
			         return;  
			}catch(Exception e){
				e.printStackTrace();
			} 
	        
	        
	        			
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
