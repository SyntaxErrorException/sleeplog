package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import domain.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// リクエストスコープから変数へ代入
			String loginId = request.getParameter("login_id");
			String loginPass = request.getParameter("login_pass");

			// Daoを用いてDBにログイン情報を渡す
			UserDao userDao = DaoFactory.createUserDao();
			User user = userDao.findByIdAndPass(loginId, loginPass);
			if (user != null) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/showRecord");
			} else {
				request.setAttribute("error", true);
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
