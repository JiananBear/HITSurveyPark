package com.werun.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.werun.surveypark.domain.security.Right;
import com.werun.surveypark.service.RightService;

@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right>{

	private static final long serialVersionUID = 4030840515774739462L;
	
	@Resource
	private RightService rightService;
	
	private List<Right> allRights;
	
	private Integer rightId;
	
	
	public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	public String findAllRights()
	{
		this.allRights=rightService.findAllEntities();
		//System.out.println(allRights.isEmpty());
		return "rightListPage";
	}
	
	public String toAddRightPage()
	{
		return "addRightPage";
	}
	
	public String saveOrUpdateRight()
	{
		rightService.saveOrUpdateRight(model);
		return "findAllRightsAction";
		
	}
	
	/**
	 * 编辑权限
	 */
	public String editRight(){
		this.model = rightService.getEntity(rightId);
		return "editRightPage" ;
	}
	
	/**
	 * 删除权限
	 */
	public String deleteRight(){
		Right r = new Right();
		r.setId(rightId);
		rightService.deleteEntity(r);
		return "findAllRightsAction" ;
	}
	
	/**
	 * 批量更新权限
	 */
	public String batchUpdateRights(){
		rightService.batchUpdateRights(allRights);
		return "findAllRightsAction" ;
	}

}
