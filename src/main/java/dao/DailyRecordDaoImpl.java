package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.DailyRecord;

public class DailyRecordDaoImpl implements DailyRecordDao {
	//フィールド
	private DataSource ds;

	//コンストラクタ
	public DailyRecordDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	//メソッド
	@Override
	public DailyRecord select(int id) throws Exception {
		DailyRecord  record = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM daily_records WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				record = mapToDailyRecord(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return record;
	}

	@Override
	public void insert(DailyRecord dailyRecord) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO daily_records "
					+ "(user_id,going_to_bed,get_up,fall_asleep,mood,night_awakenings,remarks)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, dailyRecord.getUserId(), Types.INTEGER);
			stmt.setObject(2, dailyRecord.getGoingToBed());
			stmt.setObject(3, dailyRecord.getGetUp());
			stmt.setObject(4, dailyRecord.getFallAsleep(), Types.INTEGER);
			stmt.setString(5, dailyRecord.getMood());
			stmt.setObject(6, dailyRecord.getNightAwakenings(), Types.INTEGER);
			stmt.setString(7, dailyRecord.getRemarks());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(DailyRecord record) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE daily_records SET "
					+ "going_to_bed = ?"
					+ ",get_up = ?"
					+ ",fall_asleep = ?"
					+ ",mood = ?"
					+ ",night_awakenings = ?"
					+ ",remarks = ?"
					+ " WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, record.getGoingToBed());
			stmt.setObject(2, record.getGetUp());
			stmt.setObject(3, record.getFallAsleep(),Types.INTEGER);
			stmt.setString(4, record.getMood());
			stmt.setObject(5, record.getNightAwakenings(),Types.INTEGER);
			stmt.setString(6, record.getRemarks());
			stmt.setObject(7, record.getId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(int id) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM daily_records WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	//ユーザIDによるdaily_recordsテーブルの検索
	public List<DailyRecord> findById(Integer userId) throws Exception {
		List<DailyRecord> recordList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM daily_records WHERE user_id = ? "
					+ "ORDER BY going_to_bed DESC";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				recordList.add(mapToDailyRecord(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return recordList;
	}

	private DailyRecord mapToDailyRecord(ResultSet rs) throws Exception {
		DailyRecord record = new DailyRecord();
		record.setId(rs.getInt("id"));
		record.setUserId(rs.getInt("user_id"));
		record.setGoingToBed(rs.getTimestamp("going_to_bed").toLocalDateTime());
		record.setGetUp(rs.getTimestamp("get_up").toLocalDateTime());
		record.setFallAsleep(rs.getInt("fall_asleep"));
		LocalDateTime ldt = record.getGoingToBed().plusMinutes(record.getFallAsleep());
		record.setTimeOfSleeping(Duration.between(ldt, record.getGetUp()));
		record.setMood(rs.getString("mood"));
		record.setNightAwakenings(rs.getInt("night_awakenings"));
		record.setRemarks(rs.getString("remarks"));
		/*
		 * 就寝・起床時刻の整形。
		 */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
		String gtb = dtf.format(record.getGoingToBed());
		String gu = dtf.format(record.getGetUp());
		record.setFormattedGoingToBed(gtb);
		record.setFormattedGetUp(gu);
		/*
		 *睡眠時間の整形。
		 */
		long tos = record.getTimeOfSleeping().toMinutes();
		long HH = tos/60;
		long mm = tos%60;
		DecimalFormat df = new DecimalFormat("#00");
		String s = df.format(HH) + ":" + df.format(mm);
		record.setFormattedTimeOfSleeping(s);

		return record;
	}
}
