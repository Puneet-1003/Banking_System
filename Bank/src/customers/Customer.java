package customers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Customer
 */
@WebServlet("/Customer")
public class Customer extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String cus=request.getParameter("info");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","9981");
			String qr="select * from customers where name=?";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1,cus);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				out.println(rs.getString("name")+" "+rs.getInt("ActNo"));
			}
			else
			{
				out.println("no record found");
			}
			con.close();
			
		}
		catch(Exception e)
		{
			out.println("invalid customer");
		}
	}

}
