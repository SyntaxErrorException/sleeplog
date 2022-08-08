<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<title>ログイン Sleep log</title>
</head>

<body>
	<h1>Sleep log</h1>
	<div class="container">
		<form action="" method="post">
			<c:if test="${not empty error}">
				<p class="errorMessage">IDまたはパスワードが間違っています。</p>
				<br>
			</c:if>

			<p>
				ID<br> <input type="text" name="login_id">
			</p>
			<p>
				パスワード<br> <input type="password" name="login_pass"><br>
			</p>
			<input type="submit" value="ログイン"><br>
			<p>
				<a href="addUser">新規登録</a>
			</p>
		</form>
	</div>
</body>

</html>