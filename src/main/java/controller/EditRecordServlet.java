package controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DailyRecordDao;
import dao.DaoFactory;
import domain.DailyRecord;

/**
 * Servlet implementation class EditRecordServlet
 */
@WebServlet("/editRecord")
public class EditRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DailyRecordDao dailyRecord = DaoFactory.createDailyRecordDao();
		DailyRecord record = new DailyRecord();
		try {
			record = dailyRecord.select(Integer.parseInt(request.getParameter("id")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("record", record);
		request.getRequestDispatcher("/WEB-INF/view/editRecord.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DailyRecordDao dailyRecord = DaoFactory.createDailyRecordDao();
		DailyRecord record = new DailyRecord();
		record.setId(Integer.parseInt(request.getParameter("id")));
		record.setGoingToBed(LocalDateTime.parse(request.getParameter("goingToBed")));
		record.setGetUp(LocalDateTime.parse(request.getParameter("getUp")));
		record.setFallAsleep(Integer.parseInt(request.getParameter("fallAsleep")));
		record.setMood(request.getParameter("mood"));
		record.setNightAwakenings(Integer.parseInt(request.getParameter("nightAwakenings")));
		record.setRemarks(request.getParameter("remarks"));
		try {
			dailyRecord.update(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/showRecord");
	}
}
