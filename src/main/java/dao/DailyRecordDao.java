package dao;

import java.util.List;

import domain.DailyRecord;

public interface DailyRecordDao {
	List<DailyRecord> findById(Integer userId) throws Exception;
	
	DailyRecord select(int id) throws Exception;

	void insert(DailyRecord dailyRecord) throws Exception;

	void update(DailyRecord DailyRecord) throws Exception;

	void delete(int id) throws Exception;
}
