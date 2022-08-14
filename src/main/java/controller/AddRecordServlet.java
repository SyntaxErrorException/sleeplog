package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DailyRecordDao;
import dao.DaoFactory;
import domain.DailyRecord;
import domain.User;

/**
 * Servlet implementation class AddRecordServlet
 */
@WebServlet("/addRecord")
public class AddRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addRecord.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エラーメッセージ用リスト
		List<String> errorMsg = new ArrayList<>();
		//必須項目未入力チェック
		String goingToBed = request.getParameter("going_to_bed");
		String getUp = request.getParameter("get_up");
		String mood = request.getParameter("mood");
		if (goingToBed.isBlank() ||
				getUp.isBlank() ||
				mood.isBlank()) {
			errorMsg.add("必須項目が未入力です。");
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("error", true);
			request.getRequestDispatcher("/WEB-INF/view/addRecord.jsp").forward(request, response);
			return;
		}
		//就寝時刻チェック
		LocalDateTime gtb = LocalDateTime.parse(goingToBed);
		if (gtb.isAfter(LocalDateTime.now())) {
			errorMsg.add("就寝時刻が不正です。");
		}
		//起床時刻チェック
		LocalDateTime gu = LocalDateTime.parse(getUp);
		if (gu.isBefore(gtb)) {
			errorMsg.add("起床時刻が不正です。");
		}
		//チェック結果がNGならページを再表示する
		if (errorMsg.size() != 0) {
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("error", true);
			request.getRequestDispatcher("/WEB-INF/view/addRecord.jsp").forward(request, response);
			return;
		}

		//最新のレコードで睡眠時間の重複をチェック
		DailyRecordDao record = DaoFactory.createDailyRecordDao();
		User user = (User) request.getSession().getAttribute("user");
		try {
			List<DailyRecord> recordList = new ArrayList<>();
			recordList = record.findById(user.getId());
			gu = recordList.get(0).getGetUp();
			if (gu.isAfter(gtb)) {
				errorMsg.add("睡眠時間が既存の記録と重複しています。");
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("error", true);
				request.getRequestDispatcher("/WEB-INF/view/addRecord.jsp").forward(request, response);
				return;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//DBに登録
		try {
			DailyRecord r = new DailyRecord();
			r.setUserId(user.getId());
			r.setGoingToBed(gtb);
			r.setGetUp(gu);
			r.setFallAsleep(Integer.parseInt(request.getParameter("fall_asleep")));
			r.setMood(mood);
			r.setNightAwakenings(Integer.parseInt(request.getParameter("night_awakenings")));
			r.setRemarks(request.getParameter("remarks"));
			record.insert(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/showRecord");
	}

}
