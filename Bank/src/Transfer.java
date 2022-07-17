

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transfer
 */
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String nam1=request.getParameter("name1");
		String ac1=request.getParameter("act1");
		String nam2=request.getParameter("name2");
		String ac2=request.getParameter("act2");
		String am=request.getParameter("amt");
		int a=Integer.parseInt(ac1);
		int b=Integer.parseInt(ac2);
		int c=Integer.parseInt(am);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","9981");
			String qr="select * from customers where name=? and ActNo=?";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1,nam1);
			ps.setInt(2,a);
			ResultSet rs1=ps.executeQuery();
			if(rs1.next())
			{
				int cashs=rs1.getInt("Balance");
				if(cashs>=c)
				{
					cashs=cashs-c;
					ps.setString(1, nam2);
					ps.setInt(2,b);
					ResultSet rs2=ps.executeQuery();
					if(rs2.next())
					{
						int cashr=rs2.getInt("Balance");
						cashr=cashr+c;
						String qr1="update customers set Balance=? where ActNo=?";
						PreparedStatement ps1=con.prepareStatement(qr1);
						ps1.setInt(2,a);
						ps1.setInt(1,cashs);
						int i=ps1.executeUpdate();
						ps1.setInt(2,b);
						ps1.setInt(1,cashr);
						int j=ps1.executeUpdate();
						RequestDispatcher rd=request.getRequestDispatcher("Transac");
						rd.forward(request, response);
					}
					else
					{
						out.println("<p style='font-weight:bold; font-size:20px'>Invalid Name or Account Number of Receiver</p>");
					}
				}
				else
				{
					out.println("<p style='font-weight:bold; font-size:20px'>Insufficient</p>");
				}
				
			}
			else
			{
				out.println("<p style='font-weight:bold; font-size:20px'>Invalid Name or Account Number of Sender</p>");
			}
			con.close();
		}
		catch(Exception e)
		{
			out.println("ERROR");
		}
	}

}
