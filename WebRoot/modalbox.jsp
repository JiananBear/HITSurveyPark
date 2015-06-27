<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!-- modal box start -->
  <div class="modal fade modal-confirm">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Hello!</h4>
        </div>
        <div class="modal-body">
          <p>这里有很多有意思的问题模板，希望你能喜欢！</p>
        </div>
        <div class="list-group">
        <s:iterator status="status" var="st" value="@com.werun.surveypark.util.ParserSurveyTemplate@parserXml2SurveyTemplate(xmlurl)">
          <a href="#" class="list-group-item">
          	<input type="hidden" value='<s:property value="#status.count"/>'/>
            <h4 class="list-group-item-heading"><s:property value="#st.title"/></h4>
            <p class="list-group-item-text"><s:property value="#st.info"/></p>
          </a>
         </s:iterator>
        </div>
        <div class="modal-footer">
          <p id="alertTemplate" class="text-danger" style="display: none;">
                  	未选中任何模板！
          </p>
          <button class="btn btn-default" data-dismiss="modal">Close</button>
          <a href="${pageContext.request.contextPath}/SurveyAction_newSurvey.action?lookup=0" class="btn btn-success">仍然自行创建</a>
          <a href="#" id="selectTemplate" class="btn btn-primary">使用该模板</a>
        </div>
      </div>
    </div>
  </div>
  <!-- modal box end -->
  