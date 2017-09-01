package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.db.sql;
import com.entity.Teacher;

public class TeacherDao {
	public int insertTeacher(String number,String name,String tel,String major,String pwd){
		try{
			sql result = new sql();
			String temp = "insert into teacher  values('"+number+"','"+name+"','"+tel+"','"+major+"','"+pwd+"');";
			result.setSqlStr(temp);
			int flag = result.executeUpdate();
			result.close();
			if(flag==1){
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
	
	public int login(String number,String pwd){
		ResultSet rs = null;
		try{
			sql result = new sql();
			String temp = "select * from teacher where( number='"+number+"');";
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
	
	
	public ArrayList<Teacher> selectTeacher(String number){
		ResultSet rs = null;
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
		try {
			sql result = new sql();
			String temp = "SELECT * FROM teacher where number="+number;
			result.setSqlStr(temp);
			result.executeQuery();
			rs = result.getRs();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setMajorString(rs.getString("major"));
				teacher.setNameString(rs.getString("name"));
				teacher.setNumberString(rs.getString("number"));
				teacher.setPwdString(rs.getString("pwd"));
				teacher.setTelString(rs.getString("tel"));
				teacherList.add(teacher);
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacherList;
	}
	
	
	public int updateTeacher(String number,String name,String tel,String major,String pwd){
		int flag = 0;
		try {
			sql result = new sql();
			String temp = "UPDATE teacher  SET name='"+name+"' ,tel='"+tel+"',major='"+major+"',pwd='"+pwd+"' WHERE number='"+number+"'";
			result.setSqlStr(temp);
			flag = result.executeUpdate();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static void main(String[] args) {
		TeacherDao teacherDao = new TeacherDao();
		/*
//		����insertTeacher������
		int flag = teacherDao.insertTeacher("10002", "�ŷ�", "1881011982", "���ѧԺ", "000000");
		if (flag==1) {
			System.out.println("����ɹ�");
		}else {
			System.out.println("����ʧ��");
		}
		*/
//		����login����
		int flag = teacherDao.login("10001", "000000");
		if (flag==1) {
			System.out.println("��¼�ɹ�");
		}else {
			System.out.println("��¼ʧ��");
		}
		/*
//		����selectTeacher����
		ArrayList<Teacher> teacherList = teacherDao.selectTeacher("10001");
		Teacher teacher = teacherList.get(0);
		System.out.println(teacher.getMajorString());
		System.out.println(teacher.getNameString());
		System.out.println(teacher.getNumberString());
		*/
		/*
//		����updateTeacher����
		int flag = teacherDao.updateTeacher("10001", "��ǿ", "1892819721", "����ѧԺ", "000000");
		if (flag==1) {
			System.out.println("���³ɹ�");
		}else {
			System.out.println("����ʧ��");
		}
		*/
	}
}
