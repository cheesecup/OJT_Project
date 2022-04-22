package io.tablemanager.voucheradmin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "voucher_order")
class VoucherOrder(

    @Id
    var id: String,

    @Column(name = "inquiry_id")
    var inquiryId: String,

    var status: String,

    @Column(name = "create_date")
    var createDate: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var userId: Member,

    @Column(name = "service_id")
    var serviceId: String
)

