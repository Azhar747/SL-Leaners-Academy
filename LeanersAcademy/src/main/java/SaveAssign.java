
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ClassesPackage.AssignSubject;
import ClassesPackage.Classes;

/**
 * Servlet implementation class SaveAssign
 */
public class SaveAssign extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveAssign() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String teachername = request.getParameter("TeacherName");
		String subjectname = request.getParameter("SubjectName");
		String classname = request.getParameter("id");
		System.out.println(classname);
		Connection con = null;
		Statement stmt = null;
		Statement stmt2=null;
		Statement stmt3=null;
		DBConnection connect = new DBConnection();
		AssignSubject assign = new AssignSubject();
		assign.setClassid(classname);
		assign.setSubject(subjectname);
		assign.setTeacher(teachername);
		String tname = assign.getTeacher();
		String sname = assign.getSubject();
	    String className = assign.getClassid();

		try {
			con = connect.Dbconnect();
			stmt = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
             int cID=0;
             int sID=0;
             int tID=0;
			if (( sname.equals("null") || sname.isEmpty() || tname.equals(subjectname))) {
				response.sendRedirect("Classnull.html");
			} else {
				ResultSet rs = stmt.executeQuery("select ID from Classes where ID='" + classname + "'");
				
				while (rs.next()) {
					 cID = rs.getInt(1);
				}
                    ResultSet rs2 = stmt.executeQuery("select ID from Subjects where Subject='" + subjectname + "'");
				
				while (rs2.next()) {
					 sID = rs2.getInt(1);
				}
                   ResultSet rs3 = stmt.executeQuery("select ID from Teacher where FirstName='" + teachername + "'");
				
				while (rs3.next()) {
					 tID = rs3.getInt(1);
				}
	                
				    stmt.executeUpdate("insert into Assign values ('" + sname + "','" + tname + "','" + cID+ "','" + sID+ "','" + tID+ "')");
					response.sendRedirect("Homepage");
				
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect("Classnull.html");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
