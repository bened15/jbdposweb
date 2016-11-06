package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestMenuItem;
import com.jbd.model.RestMenuItemComment;

public interface IRestMenuItemCommentManagement {

	public RestMenuItemComment insertRestMenuItemComment(RestMenuItemComment o);
	public RestMenuItemComment updateRestMenuItemComment(RestMenuItemComment o);
	public boolean deleteRestMenuItemComment(RestMenuItemComment o);
	public RestMenuItemComment findRestMenuItemComment(Integer oId);
	public List<RestMenuItemComment> findByMenuItem(RestMenuItem menuItem);

}
