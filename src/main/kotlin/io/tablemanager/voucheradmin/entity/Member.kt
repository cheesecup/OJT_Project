package io.tablemanager.voucheradmin.entity

import javax.persistence.*

@Entity
@Table(name = "MEMBER")
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var memberId: Int = 0

    var phone: String = ""
}