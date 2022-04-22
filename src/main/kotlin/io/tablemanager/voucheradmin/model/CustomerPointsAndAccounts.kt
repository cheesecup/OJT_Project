package io.tablemanager.voucheradmin.model

import io.tablemanager.voucheradmin.entity.CustomerPointAccounts
import org.springframework.data.mongodb.core.mapping.Field


data class CustomerPointsAndAccounts (

    var _id: String?,

    var accountId: String?,

    var createdAt: String?,

    var expire: String?,

    @Field(name = "name")
    var pointsname: String?,

    var type: String?,

    var customerPointAccounts: List<CustomerPointAccounts>?
    
)