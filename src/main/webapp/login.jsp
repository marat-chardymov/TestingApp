<!DOCTYPE HTML>
<html>
<head>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"/>
    <title>login page</title>
</head>
<body>
<div class="container" style="margin-top: 120px;">
    <div align=center class="row">
        <div class="span4" style="float: none;">
            <div class="well">
                <legend>Please sign in</legend>
                <form method="POST"
                      action="<%=request.getContextPath()%>/login"
                      accept-charset="UTF-8">
                    <input name="username" class="span3" placeholder="Login"
                           type="text"> <input name="password" class="span3"
                                               placeholder="Password" type="password">
                    <button class="btn-info btn" type="submit">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
