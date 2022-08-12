package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
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
	public void insert(DailyRecord dailyRecord) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO daily_record "
					+ "(user_id,going_to_bed,get_up,fall_asleep,mood,night_awakenings,remarks)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, dailyRecord.getUserId(), Types.INTEGER);
			stmt.setObject(2, dailyRecord.getGoingToBed(), Types.DATE);
			stmt.setObject(3, dailyRecord.getGetUp(), Types.DATE);
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
	public List<DailyRecord> findById(Integer userId) throws Exception {
		List<DailyRecord> recordList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "select * from daily_records WHERE user_id = ?";
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

	@Override
	public DailyRecord update(DailyRecord dailyRecord) throws Exception {

		return null;
	}

	@Override
	public void delete(DailyRecord dailyRecord) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "delete from daily_records WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, dailyRecord.getUserId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	private DailyRecord mapToDailyRecord(ResultSet rs) throws Exception {
		
		DailyRecord record = new DailyRecord();
		record.setId(rs.getInt("id"));
		record.setUserId(rs.getInt("user_id"));
		record.setGoingToBed(rs.getDate("going_to_bed"));
		record.setGetUp(rs.getDate("get_up"));
		record.setFallAsleep(rs.getInt("fall_asleep"));
		record.setTimeOfSleeping(null);
		record.setMood(rs.getString("mood"));
		record.setNightAwakenings(rs.getInt("night_awakenings"));
		record.setRemarks(rs.getString("remarks"));
		return record;
	}
}
