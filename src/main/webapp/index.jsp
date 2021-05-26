<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<style type="text/css">
  body {
    font-family: Calibri, Helvetica, sans-serif;  
    background-image: url('papa.jpeg');
    background-repeat: no-repeat;
    background-size: cover;
  }
  
  .container {
    position: fixed;
    top: 48%;
    left: 70%;
    transform: translate(-50%,-50%);
  }
  
  input[type=submit] {
    background-color: lightblue;
    width: 100%;
    color: black;
    padding: 15px;
    margin: 10px 0px;
    border: none;
    cursor: pointer;
  }
  
  input[type=text], input[type=password] {   
    width: 100%;   
    margin: 8px 0;  
    padding: 12px 20px;   
    display: inline-block;   
    border: 2px solid green;   
    box-sizing: border-box;   
  }
</style>
</head>
<body>
  <div class="container">
    <form action="LoginAuthentication" method="post">
      <h2>Welcome to Ideas2it</h2><br>
      <label>Email</label><br>
      <input type="text" name="email" required><br>
      <label>Password</label><br>
      <input type="password" name="password" required><br><br>
      <input type="hidden" name="action" value="loginSubmit">
      <input type="submit" value="Login">
    </form> 
  </div>
  
</body>
</html>