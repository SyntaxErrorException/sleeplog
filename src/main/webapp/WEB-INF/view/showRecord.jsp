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
<link rel="icon" href="images/night.png" />
<title>睡眠日誌 Sleep logger</title>

<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/jquery.tablesorter.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/css/theme.default.min.css">
<script>
	$(function() {
	//削除処理
	function deleteRecord(id,row) {
			const bgc = $('#' + id + ' td').css('background-color');
			$('#' + id + ' td').css('background-color','hsl(330, 45%, 80%)');
	
			setTimeout(function(){
				const result = confirm('No.' + row + 'を削除します。\r\nよろしいですか？');
				if (result == true) {
					$('.' + row).slideUp(250,'swing',function(){
						window.location.href = '/Sleep_log/deleteRecordDone?id=' + id;
					})
				} else {
					$('#' + id + ' td').css('background-color',bgc);
				}
			},100);
		}
		
		$('.delete').click(function(){
			const id = $(this).data('id');
			const row = $(this).data('row');
			deleteRecord(id,row);
		});
		
		$('#table').tablesorter({
		  headers: {
		      0: { sorter: "digit"},
		      1: { sorter: "digit"},
		      2: { sorter: "digit"},
		      3: { sorter: "digit"},
		      4: { sorter: "digit"},
		      5: { sorter: "text"}
		  }
		});
	});//ready
	
</script>
</head>

<body>
	<h1>Sleep logger</h1>
	<h3>睡眠日誌</h3>
	<p class="userName">
		<c:out value="${user.name}" />
		様
	</p>
	<p>
		平均睡眠時間 :
		<c:out value="${averageTimeOfSleeping}" />
	</p>
	<div>
		<a href="logout">ログアウト</a>
	</div>
	<div class="container">
		<div>
			<form action="" method="post">
				<input type="submit" value="記録を付ける">
			</form>
		</div>
	</div>
	<div class="container">
		<table id="table">
			<thead>
				<tr>
					<th class="fixed">No.</th>
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
				<c:forEach var="record" items="${recordList}" varStatus="vs">
					<tr id="${record.id}" class="${vs.count}">
						<td><div class="${vs.count}">
								<c:out value="${vs.count}" />
							</div></td>
						<td><div class="${vs.count}">
								<c:out value="${record.formattedGoingToBed}" />
							</div></td>
						<td><div class="${vs.count}">
								<c:out value="${record.formattedGetUp}" />
							</div></td>
						<td><div class="${vs.count}">
								<c:out value="${record.fallAsleep}分" />
							</div></td>
						<td><div class="${vs.count}">
								<c:out value="${record.formattedTimeOfSleeping}" />
							</div></td>
						<td><div class="${vs.count}">
								<c:out value="${record.nightAwakenings}" />
							</div></td>
						<td><div class="${vs.count}">
								<c:out value="${record.mood}" />
							</div></td>
						<td><div class="${vs.count}">
								<c:out value="${record.remarks}" />
							</div></td>
						<td><div class="${vs.count}">
								<a href="editRecord?id=${record.id}" class="edit">編集</a>
							</div></td>
						<td><div class="${vs.count}">
								<button class="delete"
									data-id="${record.id}" data-row="${vs.count}">削除</button>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>