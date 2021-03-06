package com.dyz.commentservice.domain.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Data
@Builder
@ToString(exclude = { "content" })
@EqualsAndHashCode(of = { "id", "type", "targetResourceId" })
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "create_time", nullable = false)
	private Date createTime;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", columnDefinition = "longtext", nullable = false)
	private String content;
	
	@Column(name = "type", nullable = false)
	private String type;

	/**
	 * 父评论ID，该评论针对另一个评论
	 * 值为0表示，该评论为某一资源的第一级评论
	 */
	@Column(name = "parent_id", nullable = false)
	private int parentId;

	/**
	 * 该评论是哪种资源下的评论
	 * Record的评论
	 * 或者
	 * File的评论
	 */
	@Column(name = "target_resource_id", nullable = false)
	private int targetResourceId;
	
	@Column(name = "publisher_id", nullable = false)
	private int publisherId;
}








