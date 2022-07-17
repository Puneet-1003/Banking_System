

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transac
 */
@WebServlet("/Transac")
public class Transac extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String nam1=request.getParameter("name1");
		String ac1=request.getParameter("act1");
		String nam2=request.getParameter("name2");
		String ac2=request.getParameter("act2");
		String am=request.getParameter("amt");
		int c=Integer.parseInt(am);
		Date date=Calendar.getInstance().getTime();
		DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		String strDate=dateFormat.format(date);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","9981");
			String qr="insert into transaction values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(qr);
			ps.setString(1, nam1);
			ps.setString(2, nam2);
			ps.setInt(3, c);
			ps.setString(4, strDate);
			ps.executeUpdate();
			out.println("<head>");
			out.println("<link rel='stylesheet' href='transac.css'>");
			out.println("</head>");
			out.println("<table class='content-table'>");
			out.println("<tr>");
			out.println("<td style='font-weight:bold; font-size:20px'>Sender:</td>");
			out.println("<td>"+nam1+"</td>");
			out.println("<td style='font-weight:bold; font-size:20px'>Account Number:</td>");
			out.println("<td>"+ac1+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td style='font-weight:bold; font-size:20px'>Receiver:</td>");
			out.println("<td>"+nam2+"</td>");
			out.println("<td style='font-weight:bold; font-size:20px'>Account Number:</td>");
			out.println("<td>"+ac2+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td style='font-weight:bold; font-size:20px'>Money:</td>");
			out.println("<td>"+am+"Rs.</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td style='font-weight:bold; font-size:20px'>Date:</td>");
			out.println("<td>"+strDate+"</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<p style='font-weight:bold; font-size:20px'>Transaction has been done succesfully</p>");

		}
		catch(Exception e)
		{
			out.println("ERROR");
		}
	}

}
