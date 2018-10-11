<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer Amount</title>
    </head>
    <body>
        <% 
        if (session.getAttribute("userid") == null) {
            response.sendRedirect("index.jsp");
        }
        String csrf = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                    if(cookie.getName().equals("csrf")){
                        csrf = cookie.getValue();
                    }
            }
        }
        %>
        
        <h1>Transfer Amount</h1>
        <form action="transfer" method="POST" id="myform">
            <div>
                <label>Account ID</label>
                <input type="text" class="form-control" id="accId" placeholder="Account ID" name="accid" value="ACC0101012">
            </div>
            <div class="form-group">
                <label>Amount</label>
                <input type="number" class="form-control" id="amount" placeholder="Amount" name="amount" value="2000.00">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="csrf" name="csrf" value=<%=csrf %>>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success btn-block">Transfer</button>
            </div>
        </form>
    </body>
</html>
