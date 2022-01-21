<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원가입</h3>
	<form method="post">
		<table border="1">
			<tr>
				<th>성명</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<label><input type="radio" name="gender" value="남" />남</label>
					<label><input type="radio" name="gender" value="여" checked />여</label>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" /></td>
			</tr>
		</table>
		<input type="submit" value="HttpServletRequest" onclick="action='joinRequest'">
		<input type="submit" value="@RequestParam" onclick="action='joinParam'">
		<input type="submit" value="데이터객체" onclick="action='joinDataObject'">
		<input type="submit" value="@PathVariable" onclick="go_submit(this.form)" >				
	</form>
	
	<script type="text/javascript">
	function go_submit(f) {
	//	f.action = 'joinPath/홍길동/남/test@hanul.com' 이런 형태로 값을 전달
		f.action = 'joinPath/' + f.name.value + '/' 
			+ f.gender.value + '/' + f.email.value;
	}
	</script>
	
</body>
</html>













