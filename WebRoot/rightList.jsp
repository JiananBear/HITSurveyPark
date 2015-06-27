<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>权限管理 —— Survey Door 调查系统</title>
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
            <span>权限管理</span>
            <a href="adminRightEdit.html" class="btn btn-primary btn-lg pull-right">添加权限</a>
          </h1>
        </div>
        
        <s:form action="RightAction_batchUpdateRights" namespace="/" method="post">
        <table class="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>权限名称</th>
              <th>
                <span>公共资源</span>
                <br>
                <label class="checkbox-inline">
                  <input type="checkbox" id="selectAll">全选
                </label>
                <a id="inverseSelectAll" href="#">反选</a>
              </th>
              <th>权限URL</th>
              <th>权限位</th>
              <th>权限码</th>
              <th>修改</th>
              <th>删除</th>
            </tr>
          </thead>
          <tbody>
          	<s:iterator value="allRights" status="st">
				<s:set var="rightId" value="id" />
				<s:hidden name="allRights[%{#st.index}].id"/>
	            <tr>
	              <td><s:property value="#rightId"/></td>
	              <td><s:textfield name="allRights[%{#st.index}].rightName"/></td>
	              <td><s:checkbox name="allRights[%{#st.index}].common" /></td>
	              <td><s:property value="rightUrl"/></td>
	              <td><s:property value="rightPos" /></td>
	              <td><s:property value="rightCode" /></td>
	              <td><s:a action="RightAction_editRight?rightId=%{#rightId}">修改</s:a></td>
	              <td><s:a action="RightAction_deleteRight?rightId=%{#rightId}" >删除</s:a></td>
	            </tr>
            </s:iterator>
            <tr>
            	<td colspan="10" style="height: 5px"><s:submit style='width:10em' cssClass="btn btn-default center-block" value="提交"/></td>
            </tr>
           </tbody>
        </table>
        
       </s:form>
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