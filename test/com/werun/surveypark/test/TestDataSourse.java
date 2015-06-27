package com.werun.surveypark.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.Survey;
import com.werun.surveypark.domain.User;
import com.werun.surveypark.domain.statistics.QuestionStatisticsModel;
import com.werun.surveypark.service.StatisticsService;
import com.werun.surveypark.service.SurveyService;
import com.werun.surveypark.service.impl.SurveyServiceImpl;
import com.werun.surveypark.util.ParserSurveyTemplate;


public class TestDataSourse {
	
	@Test
	public void test()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		context.getBean("dataSourse");
		
	}
	@Test
	public void test1()
	{
		SurveyServiceImpl server=new SurveyServiceImpl();
		server.updateLogoPhotoPath(9, "/upload/205210495907841.png");
	}
	
	@Test
	public void test02()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		StatisticsService dao=(StatisticsService) context.getBean("statisticsService");
		QuestionStatisticsModel qsm=dao.statistics(10);
		qsm.getQuestion().setPage(null);
		JSONArray jsonArray=JSONArray.fromObject(qsm);
		System.out.println(jsonArray.toString());
//		Question q=dao.getQuestion(2);
//		q.setPage(null);
//		JSONArray jsonArray = new JSONArray();
//		JSONObject jsonObject = new JSONObject();
//		
//		jsonObject.put("question",q);
//		jsonArray.add(jsonObject);
//		
//		System.out.println(jsonArray.toString());
	}
	
	@Test
	public void test03() throws Exception
	{
		
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=factory.newDocumentBuilder();
			Document document= db.parse("config/surveyTemplate.xml");
			
			NodeList templates=document.getElementsByTagName("template");
			
			List<Survey> re=new ArrayList<Survey>(); 
			for (int i=0;i<templates.getLength();i++)
			{
				Survey s=new Survey();
				Element template=(Element) templates.item(i);
				String surveyname=template.getAttributes().getNamedItem("surveyname").getNodeValue();
				s.setTitle(surveyname);
				System.out.println(surveyname);
				
				
				NodeList pages=template.getElementsByTagName("page");
				for (int j=0;j<pages.getLength();j++)
				{
				
					Page p=new Page();
					Element pnode=(Element) pages.item(j);
					String titlename=pnode.getAttribute("pagename");
					System.out.println(titlename);
					p.setTitle(titlename);
					p.setSurvey(s);
					
					NodeList questions=pnode.getElementsByTagName("question");
					
					for (int x=0;x<questions.getLength();x++)
					{
						Question q=new Question();
						q.setOptions("选项一,选项二,选项三,选项四");
						q.setMatrixColTitles("列标题1,列标题2,列标题3,列标题4");
						q.setMatrixRowTitles("行标题1,行标题2,行标题3,行标题4");
						q.setMatrixSelectOptions("下拉1,下拉2,下拉3,");
						q.setTitle("请输入对应的问题的题干！！");
						
						Element qnode=(Element) questions.item(x);
						Integer questionType=Integer.parseInt(qnode.getAttribute("questionType"));
						System.out.println(questionType);
						q.setQuestionType(questionType);
						
						p.getQuestions().add(q);
					}
					
					s.getPages().add(p);
				}
				
			}
	}
	
	@Test
	public void test04()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		SurveyService service=(SurveyService) context.getBean("surveyService");
		User u=new User();
		u.setId(2);
		
		Survey sur=ParserSurveyTemplate.parserXml2SurveyTemplate("WebRoot/tmp/surveyTemplate.xml", 0);
		for(Page p: sur.getPages())
		{
			System.out.println(p.getId());
			System.out.println(p.getOrderno());
			
			for(Question q : p.getQueList())
			{
				System.out.println(q.getId());
				System.out.println(q.getOrderno());
			}
		}
		sur=service.saveSurveyTemplate(u, "WebRoot/tmp/surveyTemplate.xml", 0);
		for(Page p: sur.getPages())
		{
			System.out.println(p.getId());
			System.out.println(p.getOrderno());
			
			for(Question q : p.getQueList())
			{
				System.out.println(q.getId());
				System.out.println(q.getOrderno());
			}
		}
	}


}
