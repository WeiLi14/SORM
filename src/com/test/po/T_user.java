package com.test.po;
import java.sql.*;
import java.util.*;

public class T_user {
	private java.sql.Date regtime;
	private String myinfo;
	private String password;
	private java.sql.Timestamp lastlogtime;
	private Integer id;
	private String username;
	public java.sql.Date getRegtime(){
		return regtime;
	}
	public String getMyinfo(){
		return myinfo;
	}
	public String getPassword(){
		return password;
	}
	public java.sql.Timestamp getLastlogtime(){
		return lastlogtime;
	}
	public Integer getId(){
		return id;
	}
	public String getUsername(){
		return username;
	}
	public void setRegtime(java.sql.Date regtime){
		this.regtime=regtime;
	}
	public void setMyinfo(String myinfo){
		this.myinfo=myinfo;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setLastlogtime(java.sql.Timestamp lastlogtime){
		this.lastlogtime=lastlogtime;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setUsername(String username){
		this.username=username;
	}
}
