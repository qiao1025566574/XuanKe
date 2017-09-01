package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.db.sql;
import com.entity.Student;

public class StudentDao {
	public int insertStudent(String number,String name, String className, String tel, String major, String pwd){
		/**
		 * ѧ�����в����µ�ѧ��
		 * @param number,name,className,tel,major,pwd ���Ǵ�������ֶ�
		 */
		int flag = 0;
		try{
			sql result = new sql();
			String temp = "INSERT INTO student VALUES('"+number+"','"+name+"','"+className+"','"+tel+"','"+major+"','"+pwd+"')";
			result.setSqlStr(temp);
			flag = result.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	public ArrayList<Student> selectStudent(String number){
		ResultSet rs = null;
		ArrayList<Student> studentList = new ArrayList<Student>();
		try {
			sql result = new sql();
			String temp = "SELECT * FROM student where number="+number;
			result.setSqlStr(temp);
			result.executeQuery();
			rs = result.getRs();
			while (rs.next()) {
				Student student = new Student();
				student.setMajorString(rs.getString("number"));
				student.setNameString(rs.getString("name"));
				student.setNumberString(rs.getString("class"));
				student.setNumberString(rs.getString("tel"));
				student.setPwdString(rs.getString("major"));
				student.setTelString(rs.getString("pwd"));
				studentList.add(student);
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}
	public int login(String number,String pwd){
		ResultSet rs = null;
		try{
			sql result = new sql();
			String temp = "select * from student   where( number='"+number+"');";
			result.setSqlStr(temp);
			result.executeQuery();
			rs = result.getRs();
			rs.next();
			String pwd2 = rs.getString("pwd");
			result.close();
			if(pwd2.equals(pwd)){
				return 1;
			}
			else{
				return 0;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int updateStudent(String number,String name, String className, String tel, String major, String pwd){
		/**
		 * ����ѧ����Ϣ
		 * @param number ������ѧ����ѧ��
		 * @param name ������ѧ��������...
		 */
		int flag = 0;
		try{
			sql result = new sql();
			String temp = "UPDATE student SET name = '"+name+"'," +
					"class = '"+className+"'," +
					"tel = '"+tel+"'," +
					"major = '"+major+"'," +
					"pwd ='"+pwd+"' " +
					"WHERE number = '"+number+"'";
			result.setSqlStr(temp);
			flag = result.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
		/*
//		����insertStudent����
		int flag = studentDao.insertStudent("1120142001", "����", "08311402", "18819182911", "�������", "000000");
		if (flag==1) {
			System.out.println("����ɹ�");
		}else {
			System.out.println("����ʧ��");
		}
		*/
		/*
//		����updatestudent����
		int flag = studentDao.updateStudent("1120142000", "��ǿ", "08311402", "1881018192", "�������", "000000");
		if (flag==1) {
			System.out.println("���³ɹ�");
		}else {
			System.out.println("����ʧ��");
		}
		*/
		/*
//		����login����
		int flag = studentDao.login("1120142000", "000000");
		if (flag==1) {
			System.out.println("��¼�ɹ�");
		}else {
			System.out.println("��¼ʧ��");
		}
		*/
	}
}