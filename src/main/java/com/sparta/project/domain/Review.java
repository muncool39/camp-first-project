package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity
@Table(name="p_review")
public class Review extends BaseEntity { // 리뷰
	@Id
	@Column(name="review_id", length=36, nullable=false, updatable=false)
	private String reviewId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Order order;

	@Column(name="content", length=1000) // 리뷰 내용
	private String content;

	@Column(name="score") // 평점
	private Integer score;

}