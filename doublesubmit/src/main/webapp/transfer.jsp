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
                <input type="text" id="accId" placeholder="Account ID" name="accid" value="ACC0101012">
            </div>
            <div>
                <label>Amount</label>
                <input type="number" id="amount" placeholder="Amount" name="amount" value="2000.00">
            </div>
            <div>
                <input type="hidden" id="csrf" name="csrf" value=<%=csrf %>>
            </div>
            <div>
                <button type="submit">Transfer</button>
            </div>
        </form>
    </body>
</html>
