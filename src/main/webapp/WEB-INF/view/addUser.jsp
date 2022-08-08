<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
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
		<form action="addUserDone.html">
			<p>
				ID<br> <input type="text" name="loginId">
			</p>
			<p>
				パスワード<br> <input type="password" name="loginPass">
			</p>
			<p>
				パスワード再入力<br> <input type="password" name="loginPassConf">
			</p>
			<p>
				お名前<br> <input type="text" name="userName">
			</p>
			<input type="submit" value="登録">
		</form>
	</div>
	<P>
		<a href="login.html">ログイン画面に戻る</a>
	</P>
</body>

</html>