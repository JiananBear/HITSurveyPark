<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>矩阵式题型调查结果分析 —— Survey Door 调查系统</title>

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
      <div class="col-sm-12 table-responsive">
        <div class="page-header">
          <h1>
            矩阵式下拉列表题型调查结果分析
            <small><s:property value="qsm.question.title" /></small>
          </h1>
        </div>
        <table class="table">
          <thead>
            <tr>
              <th></th>
               <s:iterator value="qsm.question.matrixColTitleArr">
					<th><s:property /></th>
				</s:iterator>
            </tr>
          </thead>
          <tbody>
           <s:iterator var="row" value="qsm.question.matrixRowTitleArr" status="rst">
            <tr>
              <td><s:property/></td>
              <s:iterator var="col" value="qsm.question.matrixColTitleArr" status="cst">
	              <td>
					<!-- ognl不仅可以访问属性,还可以直接调方法 -->
					<s:property value='getScale(#rst.index,#cst.index)' />
				 </td>
			 </s:iterator>
            </tr>
           </s:iterator>
           <tr>
           		<td >共有&nbsp;<s:property value="qsm.count" />&nbsp;人参与问卷!</td>
           	</tr>
          </tbody>
        </table>
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

</body>
</html>


