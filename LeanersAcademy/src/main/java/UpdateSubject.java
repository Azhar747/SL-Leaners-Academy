

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ClassesPackage.Classes;
import ClassesPackage.SubjectPojo;
import ClassesPackage.UpdateSubjectPojo;

/**
 * Servlet implementation class SaveSubject
 */
public class UpdateSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String subId=request.getParameter("id");
		Connection con=null;
		Statement stmt=null;
		DBConnection connect=new DBConnection();
		UpdateSubjectPojo p=new UpdateSubjectPojo();
		p.setSubjectname(name);
		p.setSubjectId(subId);
		String ssname=p.getSubjectname();
		String subjId=p.getSubjectId();
		System.out.println(ssname);
		try {
			con=connect.Dbconnect();
			 stmt=con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try{
			
			if((ssname.equals("null")) || (ssname.isEmpty())){
				response.sendRedirect("UpdateSubjectnull.jsp");
					}
			else{
				stmt.executeUpdate("Update Subjects set  Subject='"+ssname+"'where ID='"+subjId+"'");
				response.sendRedirect("UpdateNewSubject.jsp");
				}
			}catch(NullPointerException | SQLException e){
				response.sendRedirect("UpdateSubjectnull.jsp");
				
			}
			
			
			} 
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
