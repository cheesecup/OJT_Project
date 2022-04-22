package io.tablemanager.voucheradmin.controller

import io.tablemanager.voucheradmin.entity.CustomerPointAccounts
import io.tablemanager.voucheradmin.model.PointAccountsResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import io.tablemanager.voucheradmin.service.CustomerAccountService
import org.bson.types.ObjectId
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.data.domain.Pageable

@RestController
class CustomerAccountController(
    private var customerAccountService: CustomerAccountService
) {


    // Get accounts by phoneNumber and brandId or by each others
    // (each parameter is 'organizationId' and 'accountNumber' field from 'customerpointaccounts' collection)
    // All parameter can be null
    // when all parameters are null this method find All items from 'customerpointaccounts' collection
    //
    // GET /point-accounts?phoneNumber='value'&brandId='value'
    @GetMapping("/point-accounts")
    fun getCustomerPointAccounts(
        @RequestParam("phoneNumber") phoneNumber: String?,
        @RequestParam("brandId") brandId: ObjectId?,
        @PageableDefault(size = 10,)pageable: Pageable,
    ): PointAccountsResponse<CustomerPointAccounts?> {
        return customerAccountService
            .getCustomerPointAccounts(phoneNumber,brandId,pageable)
    }


    // Find account by _id and update it's brandId or phoneNumber
    // (each parameter is 'organizationId' and 'accountNumber' field from 'customerpointaccounts' collection)
    // brandId or phongNumber can be null
    // when one parameter is null this method update non Null parameter's field
    // if both parameters are null, return "update failed"
    //
    // Patch /point-accounts/{pointAccountId}?brandId='value'&phoneNumber='value'
    @PatchMapping("/point-accounts/{pointAccountId}")
    fun updateCustomerPointAccount(
        @PathVariable("pointAccountId") pointAccountId: ObjectId,
        @RequestParam("brandId") brandId: String?,
        @RequestParam("phoneNumber") phoneNumber: String?
    ): String {
        return customerAccountService
            .updateCustomerAccounts(pointAccountId,brandId,phoneNumber)
    }

}