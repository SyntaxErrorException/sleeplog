<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<p class="userName">
		<c:out value="${user.name}" />
		様
	</p>
	<p>
		平均睡眠時間 :
		<c:out value="${average}/日" />
	</p>
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
					<c:forEach var="record" items="${recordList}">
						<tr>
							<td><c:out value="${record.goingToBed}" /></td>
							<td><c:out value="${record.getUp}" /></td>
							<td><c:out value="${record.fallAsleep}分" /></td>
							<td><c:out value="${record.timeOfSleeping}" /></td>
							<td><c:out value="${record.nightAwakenings}" /></td>
							<td><c:out value="${record.mood}" /></td>
							<td><c:out value="${record.remarks}" /></td>
							<td><a href="editRecord?id=${record.id}" class="edit">編集</a></td>
							<td><a href="deleteRecordDone?id=${record.id}"
								class="delete">削除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</details>
	</div>
</body>

</html>