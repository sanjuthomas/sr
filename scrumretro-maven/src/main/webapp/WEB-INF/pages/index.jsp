<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Scrum Retro</title>
<meta name="description" content="An interactive getting started guide for Brackets.">
<%@include file="inc/headInclude.jspf"%>
<%@include file="inc/cssInclude.jspf"%>
<link href="<%=contextPath%>/css/int/home.css" rel="stylesheet">
</head>
<body class="home">
	<%@include file="inc/header.jspf"%>

	<div class="main-home col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
		<!-- start: LOGIN BOX -->
		<div class="box-login">
			<h3>Sign in to your account</h3>
			<form action="index.jspf" class="form-login" novalidate="novalidate">
				<div class="errorHandler alert alert-danger no-display">You have some form errors. Please check below.</div>
				<fieldset>
					<div class="form-group">
						<span class="input-icon"> <input type="text" placeholder="Email" name="username" class="form-control">
						</span>
						<!-- To mark the incorrectly filled input, you must add the class "error" to the input -->
						<!-- example: <input type="text" class="home error" name="home" value="Username" /> -->
					</div>
					<div class="form-group form-actions">
						<span class="input-icon"> <input type="password" placeholder="Password" name="password"
							class="form-control password"> <a href="#" class="forgot"> I forgot my password </a> </span>
					</div>
					<div class="form-actions">
						<label for="remember">
							<div style="position: relative;">
								<input type="checkbox" name="remember" id="remember" /> Keep me signed in
							</div> </label>
						<button class="btn btn-bricky pull-right" type="submit">Sign In</button>
					</div>
					<div class="new-account">
						Don't have an account yet? <a class="register" href="#"> Create an account </a>
					</div>
				</fieldset>
			</form>
		</div>
		<!-- end: LOGIN BOX -->
		<!-- start: FORGOT BOX -->
		<div class="box-forgot">
			<h3>Forgot Password?</h3>
			<p>Enter your e-mail address below to reset your password.</p>
			<form class="form-forgot" novalidate="novalidate">
				<div class="errorHandler alert alert-danger no-display">You have some form errors. Please check below.</div>
				<fieldset>
					<div class="form-group">
						<span class="input-icon"> <input type="email" placeholder="Email" name="username" class="form-control">
						</span>
					</div>
					<div class="form-actions">
						<button class="btn btn-light-grey go-back">Back</button>
						<button class="btn btn-bricky pull-right" type="submit">Submit</button>
					</div>
				</fieldset>
			</form>
		</div>
		<!-- end: FORGOT BOX -->
		<!-- start: REGISTER BOX -->
		<div class="box-register">
			<h3>Sign Up</h3>
			<form class="form-register" novalidate="novalidate">
				<div class="errorHandler alert alert-danger no-display">You have some form errors. Please check below.</div>
				<fieldset>

					<div class="form-group">
						<span class="input-icon"> <input type="email" placeholder="Email" name="username" class="form-control">
						</span>
					</div>
					<div class="form-group">
						<span class="input-icon"> <input type="password" placeholder="Password" name="password" id="password"
							class="form-control"> </span>
					</div>
					<div class="form-group">
						<span class="input-icon"> <input type="password" placeholder="Password Again" name="password_again"
							class="form-control"> </span>
					</div>
					<div class="form-group">
						<div>
							<label for="remember">
								<div style="position: relative;">
									<input type="checkbox" name="agree" id="agree" /> I agree to the Terms of Service and Privacy Policy
								</div> </label>
						</div>
					</div>
					<div class="form-actions">
						<button class="btn btn-light-grey go-back">Back</button>
						<button class="btn btn-bricky pull-right" type="submit">Submit</button>
					</div>
				</fieldset>
			</form>
		</div>
		<!-- end: REGISTER BOX -->
	</div>
	<%@include file="inc/footer.jspf"%>
	<%@include file="inc/jsInclude.jspf"%>
	<script src="<%=contextPath %>/js/int/home.js"></script>
</body>
</html>