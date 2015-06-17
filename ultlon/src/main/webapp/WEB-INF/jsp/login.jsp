<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8"/>
<title>登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
	<div class="span5">
		<div class="row">
			<h2 class="span4 offset2">Login</h2>
		</div>
		<div class="row">
			<form class="form-horizontal" method="post">
				<fieldset>
					<div class="control-group" style="padding-top: 20px">
						<label class="control-label" for="username">Username:</label>
						<div class="controls">
							<input class="input-block-level" type="text" id="username"
								name="username" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">Password:</label>
						<div class="controls">
							<input class="input-block-level" type="password" id="password"
								name="password" />
						</div>
					</div>
						<div class="control-group">
						<label class="control-label" for="password">记住我:</label>
						<div class="controls">
							<input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button class="btn btn-primary" type="submit">Login</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>

