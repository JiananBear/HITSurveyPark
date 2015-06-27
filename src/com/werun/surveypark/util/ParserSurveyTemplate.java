package com.werun.surveypark.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.Survey;

public class ParserSurveyTemplate {
	
	private static String path="/config/surveyTemplatesurveyTemplate.xml.xml";
	
	
	public static List<Survey> parserXml2SurveyTemplate(String path)
	{
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=factory.newDocumentBuilder();
			Document document= db.parse(path);
			
			NodeList templates=document.getElementsByTagName("template");
			
			List<Survey> re=new ArrayList<Survey>(); 
			for (int i=0;i<templates.getLength();i++)
			{
				Survey s=new Survey();
				Element template=(Element) templates.item(i);
				String surveyname=template.getAttributes().getNamedItem("surveyname").getNodeValue();
				String info=template.getAttribute("info");
				s.setTitle(surveyname);
				s.setInfo(info);
				//s.setClosed(false);
				//System.out.println(surveyname);
				
				
				NodeList pages=template.getElementsByTagName("page");
				for (int j=0;j<pages.getLength();j++)
				{
				
					Page p=new Page();
					Element pnode=(Element) pages.item(j);
					String titlename=pnode.getAttribute("pagename");
					//System.out.println(titlename);
					p.setTitle(titlename);
					p.setSurvey(s);
					p.setOrderno(j+1);
					
					NodeList questions=pnode.getElementsByTagName("question");
					
					for (int x=0;x<questions.getLength();x++)
					{
						Question q=new Question();
						q.setOptions("选项一\r\n选项二\r\n选项三\r\n选项四");
						q.setMatrixColTitles("列标题1\r\n列标题2\r\n列标题3\r\n列标题4");
						q.setMatrixRowTitles("行标题1\r\n行标题2\r\n行标题3\r\n行标题4");
						q.setMatrixSelectOptions("下拉1\r\n下拉2\r\n下拉3");
						q.setTitle("请输入对应的问题的题干！！");
						q.setOrderno(x);
						
						Element qnode=(Element) questions.item(x);
						Integer questionType=Integer.parseInt(qnode.getAttribute("questionType"));
						//System.out.println(questionType);
						q.setQuestionType(questionType);
						q.setPage(p);
						p.getQuestions().add(q);
					}
					
					s.getPages().add(p);
				}
				re.add(s);
			}
			
			return re;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Survey  parserXml2SurveyTemplate(String path,int tmpIndex)
	{
		List<Survey> re=ParserSurveyTemplate.parserXml2SurveyTemplate(path);
		return re.get(tmpIndex);
	}
	
	
	public static Survey  parserXml2SurveyTemplate(String path,String tmpname)
	{
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=factory.newDocumentBuilder();
			Document document= db.parse(path);
			
			NodeList templates=document.getElementsByTagName("template");
			
			for (int i=0;i<templates.getLength();i++)
			{
				
				Element template=(Element) templates.item(i);
				String surveyname=template.getAttributes().getNamedItem("surveyname").getNodeValue();
				if (surveyname.trim().equals(tmpname.trim()))
				{
					Survey s=new Survey();
					s.setTitle(surveyname);
					String info=template.getAttribute("info");
					s.setInfo(info);
					//s.setClosed(false);
					NodeList pages=template.getElementsByTagName("page");
					for (int j=0;j<pages.getLength();j++)
					{
					
						Page p=new Page();
						Element pnode=(Element) pages.item(j);
						String titlename=pnode.getAttribute("pagename");
						//System.out.println(titlename);
						p.setTitle(titlename);
						p.setSurvey(s);
						p.setOrderno(j+1);
						
						NodeList questions=pnode.getElementsByTagName("question");
						
						for (int x=0;x<questions.getLength();x++)
						{
							Question q=new Question();
							q.setOptions("选项一\r\n选项二\r\n选项三\r\n选项四");
							q.setMatrixColTitles("列标题1\r\n列标题2\r\n列标题3\r\n列标题4");
							q.setMatrixRowTitles("行标题1\r\n行标题2\r\n行标题3\r\n行标题4");
							q.setMatrixSelectOptions("下拉1\r\n下拉2\r\n下拉3");
							q.setTitle("请输入对应的问题的题干！！");
							q.setOrderno(x);
							
							Element qnode=(Element) questions.item(x);
							Integer questionType=Integer.parseInt(qnode.getAttribute("questionType"));
							//System.out.println(questionType);
							q.setQuestionType(questionType);
							q.setPage(p);
							p.getQuestions().add(q);
						}
						
						s.getPages().add(p);
					}
					return s;
				}

			}
			
			return null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	

}
