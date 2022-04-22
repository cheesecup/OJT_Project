package io.tablemanager.voucheradmin.repository

import io.tablemanager.voucheradmin.entity.CustomerPointAccounts
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.domain.Pageable


interface CustomerAccountsRepo
    : MongoRepository<CustomerPointAccounts?,String?> {
    // find customer account by accountNumber from customerpointaccounts(MongoDB.Posable)
    fun findAllByAccountNumber(
        accountNumber: String,
        pageable: Pageable
    ): Page<CustomerPointAccounts?>

    // find customer account by organizationId from customerpointaccounts(MongoDB.Posable)
    fun findAllByOrganizationId(
        organizationId: ObjectId,
        pageable: Pageable
    ): Page<CustomerPointAccounts?>

    fun findAllByOrganizationIdAndAccountNumber(
        organizationId: ObjectId,
        accountNumber: String,
        pageable: Pageable
    ): Page<CustomerPointAccounts?>

    fun findAllBy_id(
        _id: ObjectId
    ): List<CustomerPointAccounts>

}
