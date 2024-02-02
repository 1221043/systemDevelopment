package jp.ac.isc.cloud;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class UserSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection users = null;
		try {
			users = DBConnection.openConnection();
			ArrayList<Member> list = new ArrayList<Member>();
			Statement state = users.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM user_table");
		
			while (result.next()) {
				String id = result.getString("id");
				String name = result.getString("name");
				String picture = result.getString("picture");
		
				list.add(new Member(id, name, picture));
			}
		
			result.close(); 
			DBConnection.closeConnection(users, state);
			request.setAttribute("list", list);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/select.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
