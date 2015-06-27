<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <script>
$(function () {
  var $items = $('.list-group>a.list-group-item');
  $items.on('click', function (e) {
    var $this = $(this);
    if ($this.hasClass('active')) {
      $items.removeClass('active');
    } else {
      $items.removeClass('active');
      $this.addClass('active');
    }
  });

  $('#selectTemplate').on('click', function (e) {
    var $selectedTemplate = $('.list-group>a.list-group-item.active');
    if ($selectedTemplate.length) {
      // do sth
    	var val=$selectedTemplate.find("input[type=hidden]").val();
      	var url="SurveyAction_newSurveyTemplate.action?lookup=0&tmpIndex="+val;
      	location.href=url;

    } else {
      $('#alertTemplate').show();
    }
  });
});
</script>
