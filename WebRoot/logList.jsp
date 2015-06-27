<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>日志管理 —— Survey Door 调查系统</title>

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
            <span>日志管理</span>
          </h1>
        </div>
        <table class="table">
          <thead>
            <th>操作人</th>
            <th>操作名称</th>
            <th>参数</th>
            <th>操作结果</th>
            <th>消息</th>
            <th>时间</th>
          </thead>
          <tbody>
          <s:iterator value="allLogs" status="st">
            <tr>
              <td><s:property value="operator" /></td>
              <td><s:property value="operName" /></td>
              <td><s:property value="@com.werun.surveypark.util.StringUtil@getDescString(operParams)"/></td>
              <td><s:property value="operResult"/></td>
              <td><s:property value="@com.werun.surveypark.util.StringUtil@getDescString(resultMsg)"/></td>
              <td><s:date name="operTime" format="yy/MM/dd hh:mm:ss"/></td>
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