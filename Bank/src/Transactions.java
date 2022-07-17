

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
 * Servlet implementation class Transactions
 */
@WebServlet("/Transactions")
public class Transactions extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","9981");
			String qr="select * from transaction";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qr);
			out.println("<head>");
			out.println("<link rel='icon' href='favicon.ico' type='image/x-icon'>");
			out.println("<link rel='stylesheet' href='style.css'>");
			out.println("</head>");
			out.println("<body background='https://upload.wikimedia.org/wikipedia/commons/a/a8/London.bankofengland.arp.jpg'>");
			if(rs.next())
			{
				
				
				out.println("<h1>Transaction History</h1>");
				out.println("<table class='content-table'>");
				out.println("<tr>");
				out.println("<td style='font-weight:bold; font-size:20px'>Sender</td>");
				out.println("<td style='font-weight:bold; font-size:20px'>Receiver</td>");
				out.println("<td style='font-weight:bold; font-size:20px'>Money</td>");
				out.println("<td style='font-weight:bold; font-size:20px'>Date</td>");
				out.println("</tr>");
				do
				{	
					
					out.println("<tr>");
					out.println("<td>"+rs.getString("Sender")+"</td>");
					out.println("<td>"+rs.getString("Receiver")+"</td>");
					out.println("<td>"+rs.getInt("Money")+"Rs.</td>");
					out.println("<td>"+rs.getString("Date")+"</td>");
					out.println("</tr>");
				}while(rs.next());
				out.println("</table>");
				
			}
			else
			{
				out.println("<h1>No records found</h1>");
			}
			out.println("</body>");
			con.close();
			
		}
		catch(Exception e)
		{
			out.println("Include database");
		}

	}

}
