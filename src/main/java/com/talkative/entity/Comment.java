package com.talkative.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {
	@XmlElement(required=false)
	private int id;
	@XmlElement(required=false)
	private int idAuthor;
	@XmlElement(required=false)
	private String content;
	@XmlElement(required=false)
	private Date date;
	@XmlElement(required=false)
	private static int lastId = 1;

	public int getID()
	{
		return this.id;
	}
	
	public int getIdAuthor()
	{
		return this.idAuthor;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public Date getDate()
	{
		return this.date;
	}
	
	public static int getLastId()
	{
		return Comment.lastId;
	}
	
	public void setContent(String newContent)
	{
		this.content = newContent;
	}
}
