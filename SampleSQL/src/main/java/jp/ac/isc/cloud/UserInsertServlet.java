package jp.ac.isc.cloud;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/UserInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection users = null;
		try {
			request.setCharacterEncoding("utf-8");//←これを忘れると入力データが文字化けする
			users = DBConnection.openConnection();
			String id = request.getParameter("insertId");
			String name = request.getParameter("insertName");
			String picture = request.getParameter("insertPicture");
			Statement state = users.createStatement();
			state.executeUpdate("INSERT INTO user_table VALUE('" + id + "','" + name + "','" + picture + "')");//←この部分は1行で入力する
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select"); // UserSelectServletを呼び出す
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
