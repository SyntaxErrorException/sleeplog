<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div style="color: hsl(15, 90%, 45%);">※は必須項目</div>
		<div>
			<a href="login.html">ログアウト</a>
		</div>
	</div>
	<div class="container">
		<form action="" method="post">
			<div class="container">
				<div>
					<p>
						※就寝<br> <input type="datetime-local" name="goingToBed">
					</p>
				</div>
				<div>
					<p>
						※起床<br> <input type="datetime-local" name="getUp">
					</p>
				</div>
				<div>
					<p>
						※起床時の気分<br> <label><input type="radio" name="mood"
							value="1"> すっきり</label> <label><input type="radio"
							name="mood" value="2"> 普通</label> <label><input
							type="radio" name="mood" value="3"> やや悪い</label> <label><input
							type="radio" name="mood" value="4"> 悪い</label>
					</p>
				</div>
			</div>
			<p>
				寝付くまでの時間（分）<br> <input type="number" name="fallAsleep" min="0"
					value="0">
			</p>
			<p>
				夜間覚醒の回数<br> <input type="number" name="nightAwakenings" min="0"
					value="0">
			</p>
			<p>
				備考<br>
				<textarea name="remarks" cols="25" rows="3"></textarea>
			</p>
			<input type="submit" value="登録">
		</form>
	</div>
	<p>
		<a href="showRecord.html">キャンセル</a>
	</p>
</body>

</html>