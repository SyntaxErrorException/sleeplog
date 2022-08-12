package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DailyRecord {
	//フィールド
	private Integer id;
	private Integer userId;
	private Date goingToBed;
	private Date getUp;
	private Integer fallAsleep;
	private String timeOfSleeping;
	private String mood;
	private Integer nightAwakenings;
	private String remarks;
}