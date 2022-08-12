package test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DailyRecord;

/**
 * Servlet implementation class TestShowRecord
 */
@WebServlet("/testShowRecord")
public class TestShowRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDateTime time1 = LocalDateTime.of(2022,8,8,23,15);
		LocalDateTime time2 = LocalDateTime.of(2022,8,9,7,30);
		Integer i = 15;
		LocalDateTime time3 = time1.plusMinutes(i);
		
		/*
		 * 就寝時刻と起床時刻の差を睡眠時間とする。
		 * 「H時m分」の形式で表示するために、睡眠時間を「時」と「分」に分ける。
		 */
		Duration timeOfSleeping = Duration.between(time3, time2);

		
		/*
		 *0時0分にDurationを加えて睡眠時間を算出する。
		 */
		LocalTime t = LocalTime.MIDNIGHT.plus(timeOfSleeping);
		String s = DateTimeFormatter.ofPattern("H:mm").format(t);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/M/d H:m");
		String s1 = dtf.format(time1);
		String s2 = dtf.format(time2);
		
		DailyRecord r = new DailyRecord();
		r.setId(1);
//		r.setGoingToBed(s1);
//		r.setGetUp(s2);
		r.setFallAsleep(i);
		r.setMood("すっきり");
		List<DailyRecord> dr = new ArrayList<>();
		dr.add(r);
		
		request.setAttribute("records", dr);
		request.setAttribute("average", ave);
		request.setAttribute("timeOfSleeping", s);
		request.getRequestDispatcher("/WEB-INF/view/showRecord.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
