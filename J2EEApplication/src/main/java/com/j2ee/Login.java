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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns="/login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		PrintWriter pw=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/userdata";
		String user="root";
		String Dpass="aruna@123";
		String login="SELECT * FROM USER WHERE EMAIL=? AND PASS=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,Dpass);
			ps=con.prepareStatement(login);
			ps.setString(1, email);
			ps.setString(2, pass);
			rs=ps.executeQuery();
			if(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				Long phone=rs.getLong(3);
				String mail=rs.getString(4);
			    pw.println("<html><h1>Hi "+name+" Login success</h1>"
				+"Id &nbsp: "+id+"<br>Phone &nbsp: "+phone+"<br>"
				+"Mail &nbsp: "+email+"<br>"+"<a href=\"demo.html\">Signup Form</a></html>");
			}
			else {
				pw.println("<html><h1>Login Failed</h1>"+"<br><a href=\"demo.html\">Signup Form</a></html>");
			}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
finally {
	pw.flush();
	pw.close();
		try {
			if(rs!=null) {
			rs.close();
		} 
			if(ps!=null) {
				ps.close();
			} 
			if(con!=null) {
				con.close();
			} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}

