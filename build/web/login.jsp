<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <meta charset="utf-8">
        <style>
            .container{
                text-align: center;
            }
            .form-group{
                padding-left: 300px;
                text-align: left;
                color: #3366ff;
                font-weight: bold;
            }
            
            h2{
                padding-top: 30px;
                padding-bottom: 20px;
                color: #3366ff;
            }
            #username{
                width: 500px; 
            }
            #pwd{ 
                width: 500px;
            }
        </style>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h2>Login Page</h2>
            <form action="ServletDispatcher" method="POST">
              <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" placeholder="Enter username" name="txtUsername">
              </div>
              <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="txtPassword">
              </div>
              <button type="submit" class="btn btn-primary" value="Login" name="btAction">Login</button>
              &emsp;
              <input type="reset" value="Reset" class="btn btn-primary" />
            </form>
            <br/>
            <c:if test="${not empty requestScope.ERRORLOGIN}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.ERRORLOGIN}
                </div>
            </c:if> 
            <a href="InitSearchPageServlet">Back to Search Page</a>
          </div>
    </body>
</html>