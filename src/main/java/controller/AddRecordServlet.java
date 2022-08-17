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
		//チェックのために訪問時刻を取得する
		LocalDateTime theVisitedTime = LocalDateTime.now();
		//就寝時刻チェック
		LocalDateTime goingToBed = null;
		try {
			goingToBed = LocalDateTime.parse(request.getParameter("going_to_bed"));
			if (goingToBed.isAfter(theVisitedTime)) {
				errorMsg.add("就寝時刻が不正です。");
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("error", true);
				request.getRequestDispatcher("/WEB-INF/view/addRecord.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.add("就寝時刻が未入力です。");
		}

		//起床時刻チェック
		LocalDateTime getUp = null;
		try {
			getUp = LocalDateTime.parse(request.getParameter("get_up"));
			if (getUp.isBefore(goingToBed) || getUp.isAfter(theVisitedTime)) {
				errorMsg.add("起床時刻が不正です。");
				request.setAttribute("errorMsg", errorMsg);
				request.setAttribute("error", true);
				request.getRequestDispatcher("/WEB-INF/view/addRecord.jsp").forward(request, response);
				return;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			errorMsg.add("起床時刻が未入力です。");
		}

		//睡眠時間の重複を過去のレコードと比較してチェックする
		DailyRecordDao recordDao = DaoFactory.createDailyRecordDao();
		User user = (User) request.getSession().getAttribute("user");
		try {
			List<DailyRecord> recordList = new ArrayList<>();
			recordList = recordDao.findById(user.getId());
			LocalDateTime[] userInput = {goingToBed,getUp};
			int n = 1;
			toExit://ループ脱出用ラベル
			for (DailyRecord r : recordList) {
				String msg = "睡眠時間が記録No." + n + "と重複しています。";
				for (int i = 0; i < 2; i++) {
					if (userInput[i].isBefore(r.getGetUp()) && 
						userInput[i].isAfter(r.getGoingToBed())) {
						errorMsg.add(msg);
						break toExit;
					}else if(r.getGoingToBed().isAfter(userInput[i]) && 
							r.getGetUp().isBefore(userInput[i+1])){
						errorMsg.add(msg);
						break toExit;
					}
				}
				n++;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		//備考欄の文字数をチェックする
		String remarks = request.getParameter("remarks");
		if (remarks.length() > 20) {
			errorMsg.add("備考は20文字以内です。");
		}
		
		//チェック結果がNGならページを再表示する
		if (errorMsg.size() != 0) {
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("error", true);
			request.getRequestDispatcher("/WEB-INF/view/addRecord.jsp").forward(request, response);
			return;
		}
		
		//DBに登録
		try {
			DailyRecord newRecord = new DailyRecord();
			newRecord.setUserId(user.getId());
			newRecord.setGoingToBed(goingToBed);
			newRecord.setGetUp(getUp);
			newRecord.setFallAsleep(Integer.parseInt(request.getParameter("fall_asleep")));
			newRecord.setMood(request.getParameter("mood"));
			newRecord.setNightAwakenings(Integer.parseInt(request.getParameter("night_awakenings")));
			newRecord.setRemarks(remarks);
			recordDao.insert(newRecord);
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/showRecord");
	}

}
