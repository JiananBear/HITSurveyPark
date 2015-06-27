<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>角色管理 —— Survey Door 调查系统</title>

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
          <h1>
            <span>角色管理</span>
            <s:a action="RoleAction_toAddRolePage" cssClass="btn btn-primary btn-lg pull-right" namespace="/">添加角色</s:a>
          </h1>
        </div>
        <table class="table">
          <thead>
            <tr>
              <th>序号</th>
              <th>ID</th>
              <th>角色名称</th>
              <th>修改</th>
              <th>删除</th>
            </tr>
          </thead>
          <tbody>
          <s:iterator value="allRoles" status="st">
			<s:set var="roleId" value="id" />
            <tr>
              <td><s:property value="#st.count" /></td>
              <td><s:property value="id" /></td>
              <td><s:property value="roleName" /></td>
              <td><s:a action="RoleAction_editRole?roleId=%{#roleId}">修改</s:a></td>
              <td><s:a action="RoleAction_deleteRole?roleId=%{#roleId}" cssClass="aList">删除</s:a></td>
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
  <%@ include file="modalboxfooter.jsp" %>
  

</body>
</html>