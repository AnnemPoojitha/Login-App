package com.j2ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet(urlPatterns="/Signin")
public class Signup extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String email=request.getParameter("mail");
		String pass=request.getParameter("pass");
		String conPass=request.getParameter("conPass");
		long phoneNumber=Long.parseLong(phone);
		Connection con=null;
		PreparedStatement ps=null;
		String url="jdbc:mysql://localhost:3306/userdata";
		String user="root";
		String Dpass="aruna@123";
		String signup="INSERT INTO USER VALUES(0,?,?,?,?)";
		int res=0;
		PrintWriter p=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,Dpass);
			ps=con.prepareStatement(signup);
			ps.setString(1, name);
			ps.setLong(2, phoneNumber);
			ps.setString(3, email);
			ps.setString(4, pass);
			if(pass.equals(conPass)) {
				res=ps.executeUpdate();
				if(res>0) {
					p.println("<html><h1>Hi "+name+" Signup success</h1>"+"<h3>Phone number: "+phone+"<br>"+"Email: "+email+"</h3><br><a href=\"Login.html\">Login Form</a></html>");
				}
				else {
					p.println("<html><h3>Hi "+name+" Signup Failed</h3></html>");
				}
			}else {
				p.println("<html><h3>Hi "+name+" Mismatch of Password</h3><br><a href=\"Login.html\">Login Form</a></html>");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
