<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html lang="en">
  <head>
     <meta charset="UTF-8">
  <title>我的调查 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/mainPage.css">
  </head>
  
  <body>
  <%@ include file="header.jsp" %>
   
   
  <!-- body start -->
  <div class="container">
    <div class="row">
      <div class="col-sm-12 table-responsive">
        <div class="page-header">
          <h1>我的调查</h1>
        </div>
        <s:if test="mySurveys.isEmpty() == true">目前您没有任何调查项!</s:if >
        <s:else>
	        <table class="table">
	          <thead>
	            <tr>
	              <th>ID</th>
	              <th>调查标题</th>
	              <th>创建时间</th>
	              <th>状态</th>
	              <th>设置</th>
	              <th>收集信息</th>
	              <th>分析</th>
	              <th>打开/关闭</th>
	              <th>清除调查</th>
	              <th>删除</th>
	            </tr>
	          </thead>
	          <tbody>
	            <s:iterator value="mySurveys" >
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="title" /></td>
						<td><s:date name="createTime" format="MM/dd/yy HH:mm" /></td>
						<td>
						<s:if test="checked==2">
							<s:if test="closed==0">
								开放
							</s:if>
							<s:elseif test="closed==1">关闭</s:elseif>
						</s:if>	
						<s:elseif test="checked==1">
							审核中
						</s:elseif>
						<s:elseif test="checked==0">未通过</s:elseif>
						</td>
						<td><s:a action="SurveyAction_designSurvey?sid=%{#sId}&lookup=0" >设计</s:a></td>
						<td><s:a action="CollectSurveyAction?sid=%{#sId}" >收集信息</s:a></td>
						<td><s:a action="SurveyAction_analyzeSurvey?sid=%{#sId}" >分析</s:a></td>
						<td>
							<s:if test="checked==2">
								<s:a action="SurveyAction_toggleStatus?sid=%{#sId}" namespace="/" >打开/关闭</s:a>
							</s:if>
							<s:else>
								打开/关闭
							</s:else>
						</td>
						<td><s:a action="SurveyAction_clearAnswers?sid=%{#sId}" >清除调查</s:a></td>
						<td><s:a action="SurveyAction_deleteSurvey?sid=%{#sId}" >删除</s:a></td>
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
