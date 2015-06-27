<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>移动/复制页面 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/login.css">
  
<style>
.nav.nav-tabs>li.active>a { color: #f60; }
</style>
</head>
<body>
  <!-- nav start -->
  <%@ include file="header1.jsp" %>
  <!-- nav end -->

  <!-- body start -->
  <div class="container-fluid login-body">
    <div class="row">
      <div class="col-sm-10 col-md-8 login-box">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>移动/复制页面</h3>
            <span class="help-block">同一调查内是移动,不同调查间是复制</span>
			<s:set var="srcSID" value="srcSid" />
            <ul id="tabList" class="nav nav-tabs">
	            <s:iterator var="s" value="surveys">
	            	<s:if test="#srcSID==#s.id">
		            	<li class="active"><a data-toggle="tab" ><s:property value="title"/></a></li>
		            </s:if>
		            <s:else>
		            	<li ><a data-toggle="tab" ><s:property value="title"/></a></li>
		            </s:else>
	            </s:iterator>
            </ul>
            
            
            <div class="tab-content">
            
              <!-- page one start -->
              <s:iterator var="s" value="surveys">
				<s:set var="sId" value="#s.id" />
				
				<div class='tab-pane <s:property value='#srcSID==#sId?"active":""'/>'> 
					<s:iterator var="p" value="#s.pages" status="st">
					<s:set var="pId" value="#p.id"/>
					
					
					  <s:if test="#pId != srcPid">
			                <s:form name="form%{#pId}" action="MoveOrCopyPageAction_doMoveOrCopyPage" cssClass="form-horizontal" method="post">
			                  <s:hidden name="srcPid" />
			                  <s:hidden name="targPid" value="%{#pId}" />
							  <!-- 当移动/复制完成后,需要重定向到目标调查的设计页面 -->
							  <s:hidden name="sid" value="%{#sId}" />
			                  <div class="form-group">
			                    <label class="col-sm-3 control-label"><s:property value="#p.title" /></label>
			                    <div class="col-sm-5">
			                      <label class="radio-inline"><input type="radio" name="pos" value="0">之前</label>
			                      <label class="radio-inline"><input type="radio" name="pos" value="1">之后</label>
			                    </div>
			                    <div class="col-sm-3">
			                      <button class="btn btn-success btn-block">确定</button>
			                    </div>
			                  </div>
			                </s:form>
		               </s:if>
		               <s:else>
		               		 <s:form name="form%{#pId}" action="MoveOrCopyPageAction_doMoveOrCopyPage" cssClass="form-horizontal bg-info" method="post">
			                  <s:hidden name="srcPid" />
			                  <s:hidden name="targPid" value="%{#pId}" />
							  <!-- 当移动/复制完成后,需要重定向到目标调查的设计页面 -->
							  <s:hidden name="sid" value="%{#sId}" />
			                  <div class="form-group">
			                    <label class="col-sm-3 control-label"><s:property value="#p.title" /></label>
			                    <div class="col-sm-5">
			                      <label class="radio-inline"><input type="radio" name="pos" value="0">之前</label>
			                      <label class="radio-inline"><input type="radio" name="pos" value="1">之后</label>
			                    </div>
			                    <div class="col-sm-3">
			                      <button class="btn btn-success btn-block" disabled="disabled">确定</button>
			                    </div>
			                  </div>
			                </s:form>
		               </s:else>
		             </s:iterator>
		          </div>
		          <!-- page one end -->      
		          </s:iterator>
             </div>
             
   
             </div>
           </div>
          </div>
        </div>
   </div>
   
  <!-- body end -->

  <script type="text/javascript" src="scripts/jquery.js"></script>
  <script type="text/javascript" src="scripts/bootstrap.js"></script>

<script type="text/javascript">
$('.tab-content>.tab-pane').each(function (index, element) {
  element.setAttribute('id', 'tabPane' + index);
});
$('#tabList a[data-toggle=tab]').each(function (index, element) {
  element.setAttribute('href', '#tabPane' + index);
});
</script>
<%@ include file="modalboxfooter.jsp" %>
</body>
</html>