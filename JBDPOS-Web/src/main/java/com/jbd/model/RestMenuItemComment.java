package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rest_menu_item_product database table.
 *
 */
@Entity
@Table(name="rest_menu_item_comments")
@NamedQuery(name="RestMenuItemComment.findAll", query="SELECT r FROM RestMenuItemComment r")
public class RestMenuItemComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MENU_ITEM_COMMENT_ID")
	private int menuItemCommentId;

	@Column(name="COMMENT")
	private String comment;

	@Column(name="COMMENT_DESCRIPTION")
	private String commentDescription;


	//bi-directional many-to-one association to RestMenuItem
	@ManyToOne
	@JoinColumn(name="MENU_ITEM_ID")
	private RestMenuItem restMenuItem;


	public RestMenuItemComment() {
	}

	public int getMenuItemCommentId() {
		return this.menuItemCommentId;
	}

	public void setMenuItemCommentId(int menuItemCommentId) {
		this.menuItemCommentId = menuItemCommentId;
	}


	public RestMenuItem getRestMenuItem() {
		return this.restMenuItem;
	}

	public void setRestMenuItem(RestMenuItem restMenuItem) {
		this.restMenuItem = restMenuItem;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}
	
	
}