package dao;

import java.util.List;

import domain.DailyRecord;

public interface DailyRecordDao {
	void insert(DailyRecord dailyRecord) throws Exception;
	List<DailyRecord> findById(Integer userId) throws Exception;
	DailyRecord update(DailyRecord dailyRecord) throws Exception;
	 void delete(DailyRecord dailyRecord) throws Exception;
}
