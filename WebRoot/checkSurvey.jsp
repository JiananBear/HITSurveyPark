<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>审批问卷 —— Survey Door 调查系统</title>
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
          <h1>审批调查</h1>
        </div>
        <s:if test="mySurveys.isEmpty() == true">目前您没有任何调查项!</s:if >
        <s:else>
	        <table class="table">
	          <thead>
	            <tr>
	              <th>ID</th>
	              <th>调查标题</th>
	              <th>创建时间</th>
	              <th>设计者</th>
	              <th>状态</th>
	              <th>详细信息</th>
	              <th>审核通过</th>
	              <th>审核不通过</th>
	            </tr>
	          </thead>
	          <tbody>
	            <s:iterator var="s" value="checkSurveys" >
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="title" /></td>
						<td><s:date name="createTime" format="MM/dd/yy HH:mm" /></td>
						<td>
							<s:property value="%{#s.user.nickName}"/>
						</td>
						<td>
						<s:if test="checked==2">
							<s:if test="closed==0">
								关闭
							</s:if>
							<s:elseif test="closed==1">开放</s:elseif>
						</s:if>	
						<s:elseif test="checked==1">
							审核中
						</s:elseif>
						<s:elseif test="checked==0">未通过</s:elseif>
						</td>
						<td><s:a action="SurveyAction_designSurvey?sid=%{#sId}&lookup=1" >详细信息</s:a></td>
						<td><s:a action="SurveyAction_checkSurvey?sid=%{#sId}&check=2" >审核通过</s:a></td>
						<td><s:a action="SurveyAction_checkSurvey?sid=%{#sId}&check=0" >审核不通过</s:a></td>
					</tr>
				</s:iterator>
	          </tbody>
	        </table>
       </s:else>
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