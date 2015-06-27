package com.werun.surveypark.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;

/**
 * Survey
 */
public class Survey extends BaseEntry{
	
	private static final long serialVersionUID = 3558084902611139686L;
	
	
	public static Integer CHECKED=2;
	public static Integer UNCHECKED=0;
	public static Integer CHECKING=1;
	
	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createTime = new Date();
	
	private String info;
	
	private boolean closed;
	
	private String logoPhotoPath;
	
	private Integer checked;

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	//建立从Survey到User之间多对一关联关系
	private User user ;
	
	//建立从Survey到Page之间一对多关联关系
	private Set<Page> pages = new HashSet<Page>();
	
	private List<Page> pageList=new LinkedList<Page>();
	
	private float maxOrderno ;
	private float minOrderno ;
	
	

	public List<Page> getPageList() {
		return pageList;
	}


	public float getMaxOrderno() {
		return maxOrderno;
	}

	public void setMaxOrderno(float maxOrderno) {
		this.maxOrderno = maxOrderno;
	}

	public float getMinOrderno() {
		return minOrderno;
	}

	public void setMinOrderno(float minOrderno) {
		this.minOrderno = minOrderno;
	}

	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
		pageList=new LinkedList<Page>(pages);
		Collections.sort(pageList,new Comparator<Page>() {

			@Override
			public int compare(Page o1, Page o2) {
				// TODO Auto-generated method stub
				return (int) (o1.getOrderno()-o2.getOrderno());
			}
		});
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreText() {
		return preText;
	}

	public void setPreText(String preText) {
		this.preText = preText;
	}

	public String getNextText() {
		return nextText;
	}

	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	public String getExitText() {
		return exitText;
	}

	public void setExitText(String exitText) {
		this.exitText = exitText;
	}

	public String getDoneText() {
		return doneText;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getLogoPhotoPath() {
		return logoPhotoPath;
	}

	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
	

	
}
