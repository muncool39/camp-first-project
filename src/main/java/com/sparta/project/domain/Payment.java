package com.sparta.project.domain;

import com.sparta.project.domain.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(name="p_payment")
public class Payment extends BaseEntity { // 결제
	@Id
	@Column(name="payment_id", length=36, nullable=false, updatable=false)
	private String paymentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Order order;

	@Column(name="type", nullable=false) // 결제 방식 (카드)
	@Enumerated(EnumType.STRING)
	private PaymentType type;

	@Column(name="payment_price", nullable=false) // 결제 가격
	private Integer paymentPrice;

	@Builder
	public Payment(String paymentId, Order order, PaymentType type, Integer paymentPrice) {
		this.paymentId = paymentId;
		this.order = order;
		this.type = type;
		this.paymentPrice = paymentPrice;
	}

}