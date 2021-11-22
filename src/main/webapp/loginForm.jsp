<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	*{
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    outline: none;
}

html, body{
    height: 100%;
}

body{
    display: flex;
    align-items: center;
    background: url(./image/background.png) repeat top left;
    font-family: 'Roboto', sans-serif;
}

form{
    background-color: #f1f2f4;
    width: 90%;
    max-width: 475px;
    height: 475px;
    margin: 0px auto;
    border-radius: 3px;
    padding: 0px 45px 10px 45px;
    box-shadow: 0px 1px 3px rgba(23, 16, 25, 0.75);
}

#clip{
    width: 100px;
    margin: 0px auto;
    margin-top: -70px;
    position: relative;
}

#clip img{
    display: block;
    margin: 0px auto;
}

.hole{
    position: absolute;
    height: 5px;
    width: 100%;
    bottom: 0px;
    background: url(./image/background.png) repeat top left;
    border-radius: 3px;
    box-shadow: 0px -2px 0px rgba(23, 16, 25, 0.2);
}

h2{
    text-align: center;
    font-size: 24px;
    padding-top: 45px;
    color: #36383c;
    font-weight: 510;
}

p{
    text-align: center;
    font-size: 15px;
    color: #7c818b;
    margin-top: 3px;
}

input, button{
    display: block;
    width: 100%;
}

#form-container{
    margin-bottom: 27px;
    border-radius: 3px;
    background-color: #e5e7e9;
    box-shadow: inset 0px 1px 3px rgba(23, 16, 25, 0.45), 0px 2px 0px #ffffff;
}

input[type = "text"], input[type = "password"]{
    width: calc(100% - 2px);
    margin: 0px auto;
    border: none;
    background-color: transparent;
    padding-left: 74px;
    padding-right: 24px;
    color: #6f7278;
    line-height: 45px;
    font-size: 17px;
}

input[type = "text"]{
    background: url(./image/mail.png) no-repeat 24px center;
    margin-top: 44px;
    height: 85px;
    border-bottom: 1px solid #6f7278;
    box-shadow: 0px 1px 0px #ffffff;
}

input[type = "password"]{
    background: url(./image/lock.png) no-repeat 24px center; 
    height: 84px;
}

#btn-login{
    height: 74px;
    width: 100%;
    border: 1px solid #2a8b7b;
    background: url(./image/btn-background.png) repeat-x;
    font-size: 25px;
    color: #ffffff;
    box-shadow: inset 0px 2px 0px  rgba(255, 255, 255, 0.4), 0px 1px 3px rgba(0, 0, 0, 0.3);
    border-radius: 3px;
    text-shadow: 0px 1px 3px rgba(23, 16, 25, 0.55);
    cursor: pointer;
}
</style>
<body>
    <form action="CheckLoginServlet" method="post">
        <div id="clip">
            <div class="hole"></div>
            <img src="./image/clip.png" alt="clip">
        </div>
        <h2>Let's get started</h2>
        <p>This will be an amazing experience</p>
        <div id="form-container">
            <input type="text" placeholder="Username" name="username"/>
            <input type="password" placeholder="Password" name="password"/>
        </div>
        <button type="submit" id="btn-login">Login</button>
    </form>
</body>
</html>