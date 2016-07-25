<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Language Predictor</title>
</head>
<body style ="background-color:yellow;">
<h1>Enter or Copy the text you want to predict: </h1>
<form action="predictLanguage" method="post">  
<textarea name="inputText" cols=40 rows=6></textarea>
<p></p>
<input type="submit" value="Predict Language"/>  
</form>
</body>
</html>