package com.dao;

import com.pojo.Student;

public interface StudentMapper {
	public Student getStuByNo(int sno);
	public int addStudent(Student stu);
	public int addStudent2(Student stu);
}
