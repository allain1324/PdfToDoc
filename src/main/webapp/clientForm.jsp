<%@ page import="model.bo.*"%>
<%@ page import="model.bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	crossorigin="anonymous" />
<title>Insert title here</title>
<style>
* {
	border: 0px;
	padding: 0px;
	box-sizing: border-box;
	text-decoration: none;
	list-style: none;
}
body, html {
	height: 100%;
	width: 100%;
}
body {
	height: 100%;
	width: 100%;
	background-image:
		url("./image/bg_clientForm.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	overflow-x: hidden;
	margin-bottom: 30px
}
.container {
	width: 1320px;
	margin: 0px auto;
}
header {
	width: 100%;
	height: 50px;
	border-bottom: 1px solid black;
	margin-top: -8px;
	justify-content: space-between;
	margin-bottom: 100px;
	background-color: #edf0f3;
}
header>div {
	display: flex;
	justify-content: space-between;
	height: 50px;
}
header div .fa-user {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	border: 1px solid black;
	padding-top: 9px;
	padding-left: 13px;
}
#userAccount {
	margin-top: 9px;
	position: relative;
}
#userAccountInfor {
	visibility: hidden;
	opacity: 0;
	transition: visibility 0s, opacity 0.5s linear;
	width: 150px;
	height: 100px;
	position: absolute;
	right: 0px;
	top: 24px;
	border: 1px solid black;
	border-radius: 3px;
}
#iconUser:hover {
	background-color: aliceblue;
	cursor: pointer;
}
#iconUser:hover>#userAccountInfor {
	visibility: visible;
	opacity: 1;
}
#userAccountInfor>li {
	height: 50px;
	padding: 5px 3px;
	text-align: center;
	border-bottom: 1px solid black;
}
#userAccountInfor>li:hover {
	background-color: #d8dbdd;
}
#userAccountInfor>li>a {
	display: block;
	height: 50px;
	width: 100%;
	font-size: 20px;
	line-height: 50px;
	color: black;
}
h2 {
	display: contents;
	text-align: center;
	line-height: 46px;
}
form {
	width: 900px;
	margin-left: auto;
	margin-right: auto;
	border: 1px solid black;
	display: flex;
	justify-content: space-between;
	margin-top: 15px;
}
form .importFile {
	padding: 0px 2px;
	margin: 3px;
	width: 400px;
	border: 1px solid black;
}
.exportDiv {
	margin: 3px;
	width: 400px;
	position: relative;
}
form .exportFile {
	padding: 0px 2px;
	width: 100%;
	border: 1px solid black;
	height: 100%;
}
.linkDownload {
	width: 23px;
	height: 23px;
	right: -2px;
	top: 4px;
	position: absolute;
}
.download {
	width: 100%;
	height: 100%;
	color: black;
	font-size: 22px;
}
form .execute {
	margin: 3px;
	padding-top: 3px;
	padding-bottom: 3px;
	padding-left: 22px;
	padding-right: 22px;
	cursor: pointer;
	border: 1px solid black;
}
#addNew {
	display: block;
	width: 100px;
	border: 1px solid black;
	margin-left: auto;
	margin-right: auto;
	cursor: pointer;
	margin-top: 20px;
	border-radius: 3px;
	padding-top: 3px;
	padding-bottom: 3px;
}
</style>
</head>
<body>
	<%
	account ac = (account) request.getSession().getAttribute("Account");
	%>

	<!-- <div style="padding: 5px; color: red; font-style: italic;">
		${errorMessage}</div> -->
	<header>
		<div class="container">
			<h2>
				Xin chào,
				<%=ac.getUsername()%>
				!
			</h2>
			<div id="userAccount">
				<div id="iconUser">
					<i class="fas fa-user"></i>
					<ul id="userAccountInfor">
						<li><a href="#">Account</a></li>
						<li><a href="logOutServlet">Logout</a></li>
					</ul>
				</div>
			</div>

		</div>
	</header>
	<!-- ${pageContext.request.contextPath}/uploadFile -->
	<div id="mainForm" class="container">
		<form method="post"
			action="${pageContext.request.contextPath}/UploadToDBServlet"
			enctype="multipart/form-data" id="form1">
			<input type="file" name="file" class="importFile"
				accept="application/pdf" title="Choose file to upload" id="input1"/>
			<div class="exportDiv">
				<input type="text" name="file" class="exportFile" disabled="true"
					accept=".docx" /> <a href="http://localhost:10000/PdfToDoc/downloadFile?idFile=" + request.getAttribute("idFile") class="linkDownload"> <i
					class="fas fa-download" class="download"></i>
				</a>
			</div>
			<input type="submit" value="Execute" class="execute" />
		</form>
	</div>
	<button id="addNew">Add new +</button>
</body>
<script>
    var addnew = document.getElementById("addNew");
    console.log(addNew)
    addnew.addEventListener('click', event =>{
        var form = document.querySelector("form")
        var formMain = document.getElementById("mainForm")
        var cln = form.cloneNode(true);
        var count = document.querySelectorAll("form").length
        cln.id = "form" + (count + 1);
        formMain.appendChild(cln);
        
    })
    
    var disable = true;
    var formSubmits = document.querySelectorAll("form")
    formSubmits.forEach(formSubmit =>{
        formSubmit.addEventListener("submit", event =>{
        	event.preventDefault();
        	var form = document.getElementById(event.target.id);
            var formImport = document.getElementById(event.target.id)[0];
            var linkDown = form.querySelector("a")
            var butExe = form[2]
            if(disable == true){
                formImport.setAttribute("disabled", disable);
                butExe.setAttribute("disabled", disable);
                butExe.setAttribute("value", "Processing");
                linkDown.setAttribute("href","#");
            }
        })
    })
    
    document.getElementById('input1').onchange = function () {
  		alert('Selected file: ' + this.value);
	};
</script>
</html>