package com.sparta.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity
@Table(name="p_store_request")
public class StoreRequest extends BaseEntity { // 음식점 허가 요청
	@Id
	@Column(name="store_request_id", length=36, nullable=false, updatable=false)
	private String storeRequestId;

	@Column(name="is_approved", nullable=false) // 승인 여부
	@ColumnDefault("false")
	private Boolean isApproved;

	@Column(name="name", length=50, nullable=false) // 음식점 이름
	private String name;

	@Column(name="description", columnDefinition = "TEXT") // 음식점 설명
	private String description;

	@Column(name="address", length=255) // 음식점 주소
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User owner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Location location;

}