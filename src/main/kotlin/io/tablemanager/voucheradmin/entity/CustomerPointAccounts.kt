package io.tablemanager.voucheradmin.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Document(collection = "customerpointaccounts")
data class CustomerPointAccounts(

    @Id
    var _id: String?,

    @Field(name = "accountNumber")
    var accountNumber: String?,

    @Field(name = "createdAt")
    var accountCreatedAt: String?,

    @Field(name = "organizationId")
    var organizationId: String?,

    @Field(name = "name")
    var accountname: String?,

)