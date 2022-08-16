<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css">
<link rel="icon" href="images/night.png" />
<title>編集 Sleep logger</title>
</head>

<body>
	<h1>Sleep logger</h1>
	<h3>編集</h3>
	<div class="container">
		<div>
			<a href="logout">ログアウト</a>
		</div>
	</div>
	<c:if test="${not empty error }">
		<p class="errorMessage">必須項目が未入力です。</p>
	</c:if>
	<div style="color: #ffdd00;">必須項目</div>
	<div class="container">
		<form action="" method="post">
			<div class="require">
				<div>
					<p>
						就寝<br> <input type="datetime-local" name="goingToBed"
							value="${record.goingToBed}">
					</p>
				</div>
				<div>
					<p>
						起床<br> <input type="datetime-local" name="getUp"
							value="${record.getUp}">
					</p>
				</div>
				<div>
					<p>
						起床時の気分<br> <label><input type="radio" name="mood"
							value="すっきり"
							<c:out value="${record.mood=='すっきり'? 'checked':''}" />>すっきり</label>
						<label><input type="radio" name="mood" value="普通"
							<c:out value="${record.mood=='普通'? 'checked':''}" />>普通</label> <label><input
							type="radio" name="mood" value="やや悪い"
							<c:out value="${record.mood=='やや悪い'? 'checked':''}" />>やや悪い</label>
						<label><input type="radio" name="mood" value="悪い"
							<c:out value="${record.mood=='悪い'? 'checked':''}" />>悪い</label>
					</p>
				</div>
			</div>
			<p>
				寝付くまでの時間（分）<br> <input type="number" name="fallAsleep" min="0"
					value="${record.fallAsleep}" step="5">
			</p>
			<p>
				夜間覚醒の回数<br> <input type="number" name="nightAwakenings" min="0"
					value="${record.nightAwakenings}">
			</p>
			<p>
				備考<br>
				<textarea name="remarks" cols="25" rows="3">
					<c:out value="${record.remarks}" />
				</textarea>
			</p>
			<input type="submit" value="更新">
		</form>
	</div>
	<p>
		<a href="showRecord">キャンセル</a>
	</p>
</body>

</html>