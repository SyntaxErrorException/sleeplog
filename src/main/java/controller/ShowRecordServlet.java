package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
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
 * Servlet implementation class ShowRecordServlet
 */
@WebServlet("/showRecord")
public class ShowRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ID取得のためにユーザインスタンスを作成する
		User user = (User) request.getSession().getAttribute("user");

		//ユーザのレコードを抽出する
		try {
			DailyRecordDao recordDao = DaoFactory.createDailyRecordDao();
			List<DailyRecord> recordList = recordDao.findById(user.getId());
			//平均睡眠時間を算出する
			Duration d = Duration.ZERO;
			for (DailyRecord r : recordList) {
				d = r.getTimeOfSleeping().plus(d);
			}
			d = d.dividedBy(recordList.size());
			//平均睡眠時間を整形する
			long t = d.toMinutes();
			long HH = t / 60;
			long mm = t % 60;
			DecimalFormat df = new DecimalFormat("#00");
			String s = df.format(HH) + ":" + df.format(mm);
			
			request.setAttribute("averageTimeOfSleeping", s);
			request.setAttribute("recordList", recordList);
		} catch (Exception e) {
			throw new ServletException(e);
		}

		request.getRequestDispatcher("/WEB-INF/view/showRecord.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/addRecord");
	}

}
