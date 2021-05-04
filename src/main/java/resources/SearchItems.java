package resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchItems
 */
public class SearchItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TERMS_SIZE=4;
    private static final int ELEMENTS_SIZE = 7;
    private static final int MOLCOM_SIZE = 5;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("entering doGet.......");
		String[] termsArray = {"Term: ", "Definition: ", "Formula: ", "Example: "};
		String[] elementsArray = {"Atomic Number: ", "Element Name: ", "Atomic Symbol: ", "Mass: ", 
				"Number of Valence Electrons: ", "Electronegativity: ", "Group Name: "};
		String[] molcomArray = {"Name: ", "Molecular Formula: ", "Empirical Formula: ", "Bond Type: ", "Polar? "};
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String terms = request.getParameter("terms");
		String elements = request.getParameter("elements");
		String molcom = request.getParameter("molcom");
		try {
			if(terms!=null) {
				List<String> data = retrieveInfo("terms", terms);
				if(data.size()==TERMS_SIZE) {
					for(int i =0;i<termsArray.length;i++) {
						out.println(termsArray[i]+data.get(i)+"<br><br>");
					}
				}
				else {
					out.println(data.get(0));
				}
			}
			if(elements!=null) {
				List<String> data = retrieveInfo("elements", elements);
				if(data.size()==ELEMENTS_SIZE) {	
					for(int i =0;i<elementsArray.length;i++) {
						out.println(elementsArray[i]+data.get(i)+"<br><br>");
					}
				}
				else {
					out.println(data.get(0));
				}
			}
			if(molcom!=null) {
				List<String> data = retrieveInfo("molcom", molcom);
				if(data.size()==MOLCOM_SIZE) {
					for(int i =0;i<molcomArray.length;i++) {
						out.println(molcomArray[i]+data.get(i)+"<br><br>");
					}
				}
				else {
					out.println(data.get(0));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List<String> retrieveInfo(String id, String value){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<String> data = new ArrayList<String>();
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			int count = 0;
			if(id.equals("terms")) {
				count=TERMS_SIZE;
			}
			if(id.equals("elements")) {
				count = ELEMENTS_SIZE;
			}
			if(id.equals("molcom")) {
				count=MOLCOM_SIZE;
			}
			String sql = "SELECT * FROM "+id+" WHERE name = '"+value+"'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				for(int i =1; i<=count;i++) {
					if(rs.getString(i)!=null) {
						data.add(rs.getString(i));
					}
					else {
						data.add("None");
					}
				}
			}
			else {
				data.add("Sorry, that search result does not exist...");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtils.closeAll(conn, stmt, rs);
		}
		return data;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
