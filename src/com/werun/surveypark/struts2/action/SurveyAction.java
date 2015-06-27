package com.werun.surveypark.struts2.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.Page;
import com.werun.surveypark.domain.Question;
import com.werun.surveypark.domain.Survey;
import com.werun.surveypark.domain.User;
import com.werun.surveypark.service.SurveyService;
import com.werun.surveypark.util.ValidateUtil;

@Controller("surveyAction")
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware{

	private static final long serialVersionUID = -5740692445093858396L;
	
	private List<Survey> mySurveys;
	
	private String inputPage;
	
	private Integer sid;
	
	private Integer tmpIndex;
	
	private List<Survey> checkSurveys;
	
	private Integer check;
	
	private Integer lookup=0;
	
	
	
	
	

	public Integer getLookup() {
		return lookup;
	}

	public void setLookup(Integer lookup) {
		this.lookup = lookup;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
	}

	public List<Survey> getCheckSurveys() {
		return checkSurveys;
	}

	public Integer getTmpIndex() {
		return tmpIndex;
	}

	public void setTmpIndex(Integer tmpIndex) {
		this.tmpIndex = tmpIndex;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
	

	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}



	@Resource
	private SurveyService surService;
	

	private User user;
	
	
	public String findCheckingSurveys()
	{
		this.checkSurveys=surService.getALLCheckingSurveys();
		return "checkSurvey";
	}
	
	public String checkSurvey()
	{
		surService.checkSurvey(sid,check);
		return "findCheckingSurvey";
		
	}
	
	public String blockSurvey()
	{
		this.model=surService.getBlockSurvey(user);
		return "designSurveyPage" ;
	}
	
	public String templateSurvey()
	{
		String path=getXmlurl();
		this.model=surService.getSurveyTemplate(user, path, tmpIndex-1);
		return "designSurveyPage" ;
	}
	

	public String newSurvey()
	{
		this.model=surService.newSurvey(user);
		return "designSurveyPage" ;
	}
	
	public String newSurveyTemplate()
	{
		String path=getXmlurl();
		this.model=surService.saveSurveyTemplate(user,path,tmpIndex-1);
		/*for(Page p: model.getPages())
		{
			System.out.println(p.getTitle());
			System.out.println(p.getId());
			for (Question q : p.getQuestions())
			{
				System.out.println(q.getTitle());
				System.out.println(q.getId());
			}
		}*/
		return "designSurveyPage" ;
	}
	
	public String mySurveys(){
		mySurveys=surService.findMySurveys(user);
		return "mySurveyListPage";
	}
	
	public String designSurvey()
	{
		this.model=surService.getSurveyWithChildren(sid);
		return "designSurveyPage";
	}
	
	public String editSurvey()
	{
		this.model=surService.getSurvey(sid);
		return "editSurveyPage" ;
	}
	
	
	public String updateSurvey()
	{
		sid=this.model.getId();
		this.model.setUser(user);
		surService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	
	public String deleteSurvey()
	{
		surService.deleteSurvey(sid);
		return "findMySurveysAction";
	}
	
	public String clearAnswers()
	{
		surService.clearAnswers(sid);
		return "findMySurveysAction";
	}
	
	public String toggleStatus()
	{
		surService.toggleStatus(sid);
		return "findMySurveysAction";
	}
	
	
	public String toAddLogoPage()
	{
		return "addLogoPage";
	}
	
	private File logoPhoto;
	private String logoPhotoFileName;
	
	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}
	
	public void prepareDoAddLogo()
	{
		inputPage="/addLogo.jsp";
	}
	
	public String doAddLogo()
	{
		if(ValidateUtil.isValid(logoPhotoFileName))
		{
			String dir=ServletActionContext.getServletContext().getRealPath("/upload");
			long i=System.nanoTime();
			String ext=logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			File file=new File(dir,i+ext);
			logoPhoto.renameTo(file);
			surService.updateLogoPhotoPath(sid,"/upload/" + i + ext);
		}
		return "designSurveyAction";
	}
	
	public boolean logoPhotoExists()
	{
		String path=this.model.getLogoPhotoPath();
		if(ValidateUtil.isValid(path))
		{
			String dir=ServletActionContext.getServletContext().getRealPath(path);
			return new File(dir).exists();
		}
		return false;
	}
	
	public String analyzeSurvey()
	{
		this.model=surService.getSurveyWithChildren(sid);
		return "analyzeSurveyListPage";
	}

	@Override
	public void setUser(User u) {
		user=u;
	}
	
	
	
	

}
