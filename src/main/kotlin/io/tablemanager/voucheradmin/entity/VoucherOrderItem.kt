package io.tablemanager.voucheradmin.entity

import javax.persistence.*

@Entity
@Table(name = "voucher_order_item")
class VoucherOrderItem(
    @ManyToOne
    @JoinColumn(name = "order_id")
    var orderId: VoucherOrder,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var productId: VoucherProduct
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "purchase_count")
    var purchaseCount: Int = 0
}