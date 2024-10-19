package com.pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root", "password");
		PreparedStatementps = con.prepareStatement("SELECT * FROM book WHERE bname LIKE ?");
		ps.setString(1, "%" + keyword + "%");
		ResultSetrs = ps.executeQuery();

		List<Book> bookList = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book();
			book.setBid(rs.getInt(1));
			book.setBname(rs.getString(2));
			book.setBprice(rs.getDouble(3));
			bookList.add(book);
		}
		request.setAttribute("bookList", bookList);
		RequestDispatcherrd = request.getRequestDispatcher("bookSearch.jsp");
		rd.forward(request, response);
	}

}
