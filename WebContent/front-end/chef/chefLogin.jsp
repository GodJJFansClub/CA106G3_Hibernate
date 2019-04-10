<%@ page contentType="text/html; charset=Big5" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Login</title>
</head>
<body>
	<div class="container-fliud" style="margin-top: 20px;">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h2
					style="margin: 20px; color: gray; font-weight: bolder; text-align: center;">主廚登入</h2>
				<form method="post" action="<%=request.getContextPath()%>/ChefLogin">
					<div class="form-group">
						<label for="exampleInputEmail1">Account</label> <input type="text"
							class="form-control" name="account" placeholder="Account">
						<small class="form-text text-muted">account：tomcat</small>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" name="password"
							placeholder="Password"> <small
							class="form-text text-muted">password：123456</small>
					</div>
					<input type="hidden" name="action" value="login">
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
			<div class="col-3"></div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
</body>
</html>