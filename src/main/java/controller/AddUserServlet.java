package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import domain.User;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("login_id");
		String pass = request.getParameter("login_pass");
		String passConf = request.getParameter("login_pass_conf");
		String name = request.getParameter("name");
		List<String> errorMsgList = new ArrayList<>();
		//未入力の項目がないかチェックする
		if (loginId.isBlank() || 
				pass.isBlank() || 
				passConf.isBlank() || 
				name.isBlank()) {
			errorMsgList.add("未入力の項目があります。");
		}
		//IDに半角英数以外が入力されていないかチェックする
		Pattern pt = Pattern.compile("^[0-9a-zA-Z]+$");
		Matcher mc = pt.matcher(loginId);
		if (!mc.matches()) {
			errorMsgList.add("IDは半角英数のみ有効です");
		}
		//IDの文字数をチェックする
		if (loginId.length() > 20) {
			errorMsgList.add("IDは20文字以内です。");
		}
		//login_idの重複をチェックする
		try {
			User user = DaoFactory.createUserDao().findById(loginId);
			if (user != null) {
				errorMsgList.add("登録済みのIDです。");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//パスワードとパスワード確認の一致をチェックする
		if (!pass.equals(passConf)){
			errorMsgList.add("パスワードが一致しません。");
		}
		//パスワードの文字数をチェックする
		if (pass.length() < 4 || pass.length() > 255) {
			errorMsgList.add("パスワードは4～255文字です。");
		}
		//チェック結果がNGなら新規登録ページを再表示する
		if (errorMsgList.size() != 0) {
			request.setAttribute("errorMsgList", errorMsgList);
			request.setAttribute("error", true);
			request.getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(request, response);
			return ;
		}
		
		//DBに新規ユーザを登録する
		User user = new User();
		user.setLoginId(loginId);
		user.setLoginPass(pass);
		user.setName(name);
		try {
			DaoFactory.createUserDao().insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/view/addUserDone.jsp").forward(request, response);
	}
}
