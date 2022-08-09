package domain;

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
	private String goingToBed;
	private String getUp;
	private Integer fallAsleep;
	private String mood;
	private Integer nightAwakenings = 0;
	private String remarks;
}