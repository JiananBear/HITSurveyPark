package com.werun.surveypark.struts2.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Answer;
import com.werun.surveypark.domain.Question;
import com.werun.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class CollectSurveyAction extends BaseAction<Question>{

	private static final long serialVersionUID = -6557667776790081057L;
	
	private Integer sid;
	
	
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Resource
	private SurveyService surveyService;
	
	
	
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public InputStream getIs()
	{
		try {
			HSSFWorkbook wb=new HSSFWorkbook();
			HSSFSheet sheet=wb.createSheet("surveypark sheet");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null ;
			List<Question> questions = surveyService.getQuestions(sid);
			
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);
			
			Question q=null;
			Map<Integer, Integer> qidIndexMap = new HashMap<Integer, Integer>();
			
			for(int i=0;i<questions.size();i++)
			{
				q=questions.get(i);
				cell=row.createCell(i);
				cell.setCellValue(q.getTitle());
				cell.setCellStyle(style);
				sheet.setColumnWidth(i, 8000);
				qidIndexMap.put(q.getId(), i);
			}
			
			String oldUuid = "" ;
			String newUuid = "" ;
			int rowIndex = 0 ;
			List<Answer> answers = surveyService.findAnswers(sid);
			
			for(Answer a : answers)
			{
				newUuid=a.getUuid();
				if(!oldUuid.equals(newUuid))
				{
					oldUuid=newUuid;
					rowIndex++;
					row=sheet.createRow(rowIndex);
				}
				cell=row.createCell(qidIndexMap.get(a.getQuestionId()));
				cell.setCellStyle(style);
				cell.setCellValue(a.getAnswerIds());
			}
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
