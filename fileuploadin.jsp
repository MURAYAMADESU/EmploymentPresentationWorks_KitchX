<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
  <head>
  <meta HTTP-EQUIV = "content-type" CONTENT="text/html;charset=UFT-8">
   <title>ファイルのアップロード</title>
  </head>
  <body bgcolor="#FFFFFF">
    <form method="POST" enctype="multipart/form-data" 
       action="/employmentWorks/fileuploadout.jsp">
      画像：
      <input type="file" name="jpgdata" size="75" />
      <br><br>
      出力：
      <input type="text" name="out_name" size="75" value="" />.jpg
      <br><br>
      <input type="submit" value="画像のアップロード" />
    </form>
  </body>
</html>