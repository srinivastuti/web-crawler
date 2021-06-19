<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<script src="/js/crawler.js"></script>
<meta charset="ISO-8859-1">


<style>
div.a {
	width: 100%;
	max-height: 140px;
}

.panel-body {
	background: lightblue;
}
</style>

</head>
<body bgcolor="lightblue">
	<form action="">
		<div class="container">
			<h2>Crawl</h2>
			<form action="">
				<div class="panel panel-primary">
					<div class="panel-body">
						<div class="form-group">
							<div class="col-lg-2 col-md-12 col-xs-12 col-sm-12"></div>
							<div class="col-lg-8 col-md-12 col-xs-12 col-sm-12">
								<input type="text" class="form-control" id="url"
									placeholder="Enter url">
							</div>

							<div class="btn btn-default">
								<button type="button"  onclick="webCrowler()" >Search</button>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-primary" id="crawledata">
					<div class="panel-body">
						<div class="col-lg-2 col-md-12 col-xs-12 col-sm-12"></div>
						<div id="other-top-level-element"></div>
					</div>
				</div>
			</form>
		</div>

	</form>
</body>

</html>