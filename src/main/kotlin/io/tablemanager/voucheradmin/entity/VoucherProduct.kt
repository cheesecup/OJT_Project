package io.tablemanager.voucheradmin.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "voucher_product")
class VoucherProduct {

    @Id
    var id: String = ""

    var name: String = ""

    @Column(name = "sale_price")
    var salePrice: Int = 0

    var amount: Int = 0

    var quantity: Int = 0
}