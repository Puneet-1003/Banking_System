

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Customers
 */
@WebServlet("/Customers")
public class Customers extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String cus=request.getParameter("info");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","9981");
			String qr="select * from customers";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			out.println("<head>");
			out.println("<link rel='icon' href='favicon.ico' type='image/x-icon'>");
			out.println("<link rel='stylesheet' href='style.css'>");
			out.println("</head>");
			out.println("<body background='https://upload.wikimedia.org/wikipedia/commons/a/a8/London.bankofengland.arp.jpg'>");
			out.println("<h1>Customer List</h1>");
			out.println("<table class='content-table'>");
			out.println("<tr>");
			out.println("<td style='font-weight:bold; font-size:20px'>Name</td>");
			out.println("<td style='font-weight:bold; font-size:20px'>Phone No.</td>");
			out.println("<td style='font-weight:bold; font-size:20px'>Email id</td>");
			out.println("<td style='font-weight:bold; font-size:20px'>Account No.</td>");
			out.println("<td style='font-weight:bold; font-size:20px'>IFSC Code</td>");
			out.println("<td style='font-weight:bold; font-size:20px'>Current Balance</td>");
			out.println("</tr>");
			if(rs.next())
			{
				do
				{
					out.println("<tr>");
					out.println("<td>"+rs.getString("name")+"</td>");
					out.println("<td>"+rs.getString("phno")+"</td>");
					out.println("<td>"+rs.getString("email")+"</td>");
					out.println("<td>"+rs.getInt("ActNo")+"</td>");
					out.println("<td>"+rs.getString("IFSC")+"</td>");
					out.println("<td>"+rs.getInt("Balance")+"Rs.</td>");
					out.println("</tr>");
				}while(rs.next());
				out.println("</table>");
			}
			else
			{
				out.println("no record found");
			}
			con.close();
			
		}
		catch(Exception e)
		{
			out.println("Include database");
		}

	}

}
