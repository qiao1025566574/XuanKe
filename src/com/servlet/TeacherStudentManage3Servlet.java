package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.LabelDao;
import com.dao.Stu_LabDao;
import com.entity.Label;

public class TeacherStudentManage3Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");  
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        
        
        
        //type�����ͣ�1����¼ȡ��2����ܾ�
        String type = req.getParameter("type");
        //object1����stu_lab.getLabelnum()
        //object1����stu_lab.getStudentnumString()
        String labelnum = req.getParameter("object1");
        int labelnumber = Integer.parseInt(labelnum);
        String studentnum = req.getParameter("object2");
        if("1".equals(type)){ // ��ʾ¼ȡ
        	//��������1
        	LabelDao labelDao = new LabelDao();
        	ArrayList<Label> selectLabel = labelDao.selectLabel(null, labelnumber);
        	Label label = new Label();
        	for(int i=0;i<selectLabel.size();i++){
        		label = selectLabel.get(i);
        	}
        	int maxnum = label.getMaxnum();
        	int nownum = label.getNownum();
        	if(nownum>=maxnum){
        		//�����Ѿ����ˣ������ټ���
        		out.print("<script>alert('�����ﵽ���ƣ�¼ȡʧ�ܣ�');window.location.href='tea2_stumanage2.jsp'</script>");
        	}else {
        		//����Ǹĳ�1
        		Stu_LabDao stu_LabDao = new Stu_LabDao();
        		int flag = stu_LabDao.updateStu_lab1(Integer.parseInt(labelnum), studentnum, 1);
        		//��������1
        		nownum++;
                int flag2 = labelDao.updateLabelNowtime(Integer.parseInt(labelnum), nownum);
        		if (flag+flag2>=2) {
					//�ɹ�
        			out.print("<script>alert('¼ȡ�ɹ���');window.location.href='tea2_stumanage2.jsp'</script>");
				}else {
					//�޸����ݿ�ʧ��
					out.print("<script>alert('������æ��¼ȡʧ�ܣ�');window.location.href='tea2_stumanage2.jsp'</script>");
				}
				
			}
        	
        }
        else if("2".equals(type)){ // ��ʾ�ܾ�
    		//����Ǹĳ�2
    		Stu_LabDao stu_LabDao = new Stu_LabDao();
    		int flag = stu_LabDao.updateStu_lab1(Integer.parseInt(labelnum), studentnum, 2);
        	if (flag==1) {
        		out.print("<script>alert('�ܾ��ɹ���');window.location.href='tea2_stumanage2.jsp'</script>");
			}else {
				out.print("<script>alert('������æ���ܾ�ʧ�ܣ�');window.location.href='tea2_stumanage2.jsp'</script>");
			}
        }
		
	}
}
