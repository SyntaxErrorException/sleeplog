<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link href="css/style.css" rel="stylesheet" />
<title>睡眠日誌 Sleep log</title>
</head>

<body>
	<h1>Sleep log</h1>
	<h3>睡眠日誌</h3>
	<p class="userName">山田太郎 様</p>
	<p>平均睡眠時間 : 7時間30分/日</p>
	<div class="container">
		<div>
			<form action="" method="post">
				<input type="submit" value="記録を付ける">
			</form>
		</div>
		<div>
			<a href="login">ログアウト</a>
		</div>
	</div>
	<div class="container">
		<details>
			<summary>テーブル表示</summary>
			<table>
				<thead>
					<tr>
						<th class="fixed">年月日</th>
						<th class="fixed">曜日</th>
						<th class="fixed">就寝</th>
						<th class="fixed">起床</th>
						<th class="fixed">寝付くまでの時間</th>
						<th class="fixed">睡眠時間</th>
						<th class="fixed">夜間覚醒</th>
						<th class="fixed">起床時の気分</th>
						<th class="fixed">備考</th>
						<th class="fixed" colspan="2">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>22/08/01</td>
						<td>月</td>
						<td>22:00</td>
						<td>06:00</td>
						<td>15分</td>
						<td>08時間00分</td>
						<td>0回</td>
						<td>すっきり</td>
						<td>快眠だった。</td>
						<td><a href="editRecord.html" class="edit">編集</a></td>
						<td><a href="deleteRecordDone.html" class="delete">削除</a></td>
					</tr>
					<tr>
						<td>22/08/02</td>
						<td>火</td>
						<td>22:30</td>
						<td>06:00</td>
						<td>10分</td>
						<td>07時間30分</td>
						<td>0回</td>
						<td>すっきり</td>
						<td></td>
						<td><a href="editRecord.html" class="edit">編集</a></td>
						<td><a href="deleteRecordDone.html" class="delete">削除</a></td>
					</tr>
					<tr>
						<td>22/08/03</td>
						<td>水</td>
						<td>23:00</td>
						<td>06:00</td>
						<td>10分</td>
						<td>07時間00分</td>
						<td>0回</td>
						<td>すっきり</td>
						<td></td>
						<td><a href="editRecord.html" class="edit">編集</a></td>
						<td><a href="deleteRecordDone.html" class="delete">削除</a></td>
					</tr>
				</tbody>
			</table>
		</details>
	</div>
</body>

</html>