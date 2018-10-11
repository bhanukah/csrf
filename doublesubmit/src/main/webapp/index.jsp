<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
<body>
    <div>
        <h2>Double Submit Cookie Pattern</h2>
        <br/>
        <form action="login" method="POST" id="myform">
            <div>
                <label>User ID</label>
                <input type="text" class="form-control" id="userid"  name="userid" value="bhhilk">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" id="password"  name="password" value="admin">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success btn-block">Sign in</button>
            </div>
        </form>
    </div>
</body>
</html>