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
<title>新規登録 Sleep log</title>
</head>

<body>
	<h1>Sleep log</h1>
	<h3>新規登録</h3>
	<div class="container">
		<form action="" method="post">
			<c:if test="${not empty error}">
				<p class="errorMessage">
					<c:forEach var="errorMsg" items="${errorMsgList }">
						<c:out value="${errorMsg}" />
						<br>
					</c:forEach>
				</p>
			</c:if>
			<p>
				ID<br> <input type="text" name="login_id" placeholder="半角英数のみ">
			</p>
			<p>
				パスワード<br> <input type="password" name="login_pass" placeholder="4文字以上">
			</p>
			<p>
				パスワード確認<br> <input type="password" name="login_pass_conf">
			</p>
			<p>
				お名前<br> <input type="text" name="name">
			</p>
			<input type="submit" value="登録">
		</form>
	</div>
	<P>
		<a href="login">ログイン画面に戻る</a>
	</P>
</body>

</html>