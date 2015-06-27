<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>分析调查 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/mainPage.css">
</head>
<body>
  <!-- nav start -->
  <%@ include file="header.jsp" %>
  <!-- nav end -->

  <!-- body start -->
  <s:set var="sId" value="id" />
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div class="page-header">
          <h1>
            	<s:property value="title" />&nbsp;<small>分析调查</small>
          </h1>
        </div>
        
        <!-- 遍历调查的页面集合 -->
		<s:iterator var="p" value="pages" status="pst">
			<!-- 设置变量,对当前的Page的id属性进行保持 -->
			<s:set var="pId" value="#p.id" />

	        <!-- page one start -->
	        <div class="row">
	          <div class="col-sm-10 col-sm-offset-1">
	            <h2>
	              <span><s:property value="#p.title" /></span>
	            </h2>
              
	             <!-- 遍历问题集合 -->
	             <div class="form-horizontal">
				<s:iterator var="q" value="#p.questions" status="qst">
					<s:set var="qId" value="#q.id" />
					<s:set var="qt" value="#q.questionType" />
					<s:form action="MatrixStatisticsAction" namespace="/" method="post"  cssClass="form-group">
	                	<label class="col-sm-3 control-label"><s:property value="#qst.count+'.' + #q.title" /></label>
	                	<input type="hidden" name="qid" value='<s:property value="#qId" />'>
	                	<!-- 判断当前题型是否矩阵式题型 -->
						<s:if test='#qt > 5 '>
							<div class="col-sm-3"> </div>
							<!-- 提交给另外一个action,改变form的提交地址 -->
							<div class="col-sm-3 col-sm-offset-3">
								<s:submit action="MatrixStatisticsAction" value="查看矩阵式问题统计结果" cssClass="btn btn-success btn-block"/>
							</div>
						</s:if>
						<s:elseif test="#qt==5">
							<div class="col-sm-3"> </div>
							<div class="col-sm-3 col-sm-offset-3"></div>
						</s:elseif>
						<s:elseif test='#qt lt 5'>
							<s:set var="chartList" value="#{0:'平面饼图',
															1:'立体饼图',
															2:'横向平面柱状图',
															3:'纵向平面柱状图',
															4:'横向立体柱状图',
															5:'纵向立体柱状图',
															6:'平面折线图',
															7:'立体折线图'}"/>
							<div class="col-sm-3">
								<s:select name="chartType" list="#chartList" listKey="key" listValue="value" cssClass="form-control"/>
							</div>
							<div class="col-sm-3 col-sm-offset-3">
								<s:submit value="查看" cssClass="btn btn-success btn-block" />
							</div>
						</s:elseif>
		                
	                </s:form>
	             	
	             </s:iterator>
	             <!-- 遍历问题集合結束-->
				</div>
          	</div>
        </div>
        <!-- page one end -->
        <hr />
        </s:iterator>

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