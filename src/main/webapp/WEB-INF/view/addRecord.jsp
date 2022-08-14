<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css">
<title>記録を付ける Sleep log</title>
</head>

<body>
	<h1>Sleep log</h1>
	<h3>記録を付ける</h3>
	<div class="container">
		<div>
			<a href="logout">ログアウト</a>
		</div>
	</div>
	<div style="color: hsl(15, 90%, 45%);">必須項目</div>
		<c:if test="${not empty error}">
		<p class="errorMessage">
			<c:forEach var="msg" items="${errorMsg}">
				<c:out value="${msg}" /><br>
			</c:forEach>
		</p>
	</c:if>
	<div class="container">
		<form action="" method="post">
			<div class="require">
				<div>
					<p>
						就寝<br> <input type="datetime-local" name="going_to_bed">
					</p>
				</div>
				<div>
					<p>
						起床<br> <input type="datetime-local" name="get_up">
					</p>
				</div>
				<div>
					<p>
						起床時の気分<br> <label><input type="radio" name="mood"
							value="すっきり"> すっきり</label> <label><input type="radio"
							name="mood" value="普通" checked> 普通</label> <label><input
							type="radio" name="mood" value="やや悪い"> やや悪い</label> <label><input
							type="radio" name="mood" value="悪い"> 悪い</label>
					</p>
				</div>
			</div>
			<p>
				寝付くまでの時間（分）<br> <input type="number" name="fall_asleep" min="0"
					value="0" step="5">
			</p>
			<p>
				夜間覚醒の回数<br> <input type="number" name="night_awakenings"
					min="0" value="0">
			</p>
			<p>
				備考<br>
				<textarea name="remarks" cols="25" rows="3"></textarea>
			</p>
			<input type="submit" value="登録">
		</form>
	</div>
	<p>
		<a href="showRecord">キャンセル</a>
	</p>
</body>

</html>