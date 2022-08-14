package domain;

import java.time.Duration;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DailyRecord {
	/*
	 * フィールド
	 */
	private Integer id;
	private Integer userId;
	private Integer fallAsleep;
	private String mood;
	private Integer nightAwakenings;
	private String remarks;
	/*
	 * 計算用
	 */
	private LocalDateTime goingToBed;
	private LocalDateTime getUp;
	private Duration timeOfSleeping;
	/*
	 * 表示用
	 */
	private String formattedGoingToBed;
	private String formattedGetUp;
	private String formattedTimeOfSleeping;
}
