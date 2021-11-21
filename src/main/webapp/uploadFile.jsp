<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import = "java.util.ArrayList" %>
<!DOCTYPE >
<html>
<head>
<meta charset="UTF-8">
<title>Upload files</title>
</head>
<body>

    <div style="padding:5px; color:red;font-style:italic;">
       ${errorMessage}
    </div>
   
    <h2>Upload Files</h2>

    <form method="post" action="${pageContext.request.contextPath}/uploadFile"
        enctype="multipart/form-data">
       
        Select file to upload:
        <br />
        <input type="file" name="file"  />
        <br />
        Description:
        <br />
        <input type="text" name="description" size="100" />
        <br />
        <br />
        <input type="submit" value="Upload" />
    </form>
   
</body>
</html>