<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Logo上传 —— Survey Door 调查系统</title>

  <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

  <link rel="stylesheet" href="styles/bootstrap.css">
  <link rel="stylesheet" href="styles/login.css">

</head>
<body>
  <%@ include file="header1.jsp" %>

  <!-- body start -->
  <div class="container-fluid login-body">
    <div class="row">
      <div class="col-sm-8 col-md-6 col-lg-4 login-box">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>Logo上传</h3>
            <s:form action="SurveyAction_doAddLogo" method="post" enctype="multipart/form-data">
			<s:hidden name="sid" />
              <div class="form-group">
                <label>Select file</label>
                <input type="file" name='logoPhoto' placeholder="Password">
                <s:fielderror fieldName="logoPhoto"></s:fielderror>
              </div>
              <s:submit value="Upload" cssClass="btn btn-success pull-right" />
            </s:form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- body end -->

  <script type="text/javascript" src="scripts/jquery.js"></script>
  <script type="text/javascript" src="scripts/bootstrap.js"></script>

	<script type="text/javascript">
	$(function () {
	  $('.alert:empty').css('display', 'none');
	})
	</script>
	
	<%@ include file="modalboxfooter.jsp" %>
	
</body>
</html>