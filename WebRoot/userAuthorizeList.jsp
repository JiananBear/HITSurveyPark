<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>用户授权管理 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/mainPage.css">
</head>
<body>
  <!-- nav start -->
  <%@ include file="header.jsp" %>
  <!-- nav end -->

  <!-- body start -->
  <div class="container">
    <div class="row">
      <div class="col-sm-12 table-responsive">
        <div class="page-header">
          <h1>用户授权管理</h1>
        </div>
        <table class="table">
          <thead>
            <tr>
              <th>序号</th>
              <th>ID</th>
              <th>email</th>
              <th>昵称</th>
              <th>修改授权</th>
              <th>清除授权</th>
            </tr>
          </thead>
          <tbody>
          <s:iterator value="allUsers" status="st">
			<s:set var="userId" value="id" />
            <tr>
              <td><s:property value="#st.count" /></td>
              <td><s:property value="id" /></td>
              <td><s:property value="email" /></td>
              <td><s:property value="nickName" /></td>
              <td><s:a action="UserAuthorizeAction_editAuthorize?userId=%{#userId}" >修改授权</s:a></td>
              <td><s:a action="UserAuthorizeAction_clearAuthorize?userId=%{#userId}" >清除授权</s:a></td>
            </tr>
           </s:iterator>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <!-- body end -->
  
  <!-- footer start -->
  <%@ include file="footer.jsp" %>
  <!-- footer end -->

  <script type="text/javascript" src="scripts/jquery.js"></script>
  <script type="text/javascript" src="scripts/bootstrap.js"></script>
  
<script>
$(function () {
  $('#confirmAddSurvey').on('click', function (e) {
    location.href = 'designSurvey.html';
  });
});
</script>
<%@ include file="modalboxfooter.jsp" %>
</body>
</html>