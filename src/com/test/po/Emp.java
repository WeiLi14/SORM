package com.test.po;
import java.sql.*;
import java.util.*;

public class Emp {
	private String empname;
	private java.sql.Date brithday;
	private Integer id;
	private Double salary;
	private Integer age;
	public String getEmpname(){
		return empname;
	}
	public java.sql.Date getBrithday(){
		return brithday;
	}
	public Integer getId(){
		return id;
	}
	public Double getSalary(){
		return salary;
	}
	public Integer getAge(){
		return age;
	}
	public void setEmpname(String empname){
		this.empname=empname;
	}
	public void setBrithday(java.sql.Date brithday){
		this.brithday=brithday;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setSalary(Double salary){
		this.salary=salary;
	}
	public void setAge(Integer age){
		this.age=age;
	}
}
