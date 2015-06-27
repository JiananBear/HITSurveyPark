<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>矩阵式下拉列表题型调查结果分析 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/colors.css">
  <link rel="stylesheet" href="styles/mainPage.css">
  <link rel="stylesheet" href="styles/progressBarStrengthen.css">
</head>
<body>
  <!-- nav start -->
  <%@ include file="header.jsp" %>
  <!-- nav end -->

  <!-- body start -->
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div class="page-header">
          <h1>
            矩阵式下拉列表题型调查结果分析
            <small><s:property value="qsm.question.title" /></small>
            <s:hidden id="qId" name="qsm.question.id"></s:hidden>
          </h1>
        </div>
        <div class="form-horizontal" id="chartContent">
        </div>
      </div>
    </div>
    <a href="javascript:history.go(-1);" style="width:10em"  class="btn btn-default pull-right">返回</a> 
  </div>
  <!-- body end -->
  
  <s:hidden id="qJson" value="%{qsmjson}" />

  <!-- footer start -->
 <%@ include file="footer.jsp" %>
  <!-- footer end -->

  <script type="text/javascript" src="scripts/jquery.js"></script>
  <script type="text/javascript" src="scripts/bootstrap.js"></script>
  <script type="text/javascript" src="scripts/analyzeTable.js"></script>
<script>
var data=$("#qJson").val();
var dataObj=eval("("+data+")");
printData(transformData(dataObj[0]));
</script>

</body>
</html>


