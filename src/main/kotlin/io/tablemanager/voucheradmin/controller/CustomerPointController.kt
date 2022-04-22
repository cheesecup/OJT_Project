package io.tablemanager.voucheradmin.controller

import io.tablemanager.voucheradmin.model.CustomerPointsAndAccounts
import io.tablemanager.voucheradmin.model.PointsAndAccountsResponse
import io.tablemanager.voucheradmin.service.CustomerPointService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
class CustomerPointController (
    @Autowired
    private var customerPointsService: CustomerPointService
    ){

    // Get Points by phoneNumber and brandId and Date or by each others
    // (each parameter is 'organizationId' and 'accountNumber' field from 'customerpointaccounts' collection and 'cratedAt' field from 'customerpoints' collection)
    // All parameter can be null
    // when all parameter is null this method find All items from 'customerpoints' and 'customerpointaccounts' collection
    // DateTime Format is yyyy-mm-dd'T'hh:mm:ss (ex: 2022-04-11T12:00:00)
    //
    // Get /points?phoneNumber='value'&brandId='value'&startDate='value'&endDate='value'
    @GetMapping("/points")
    fun getCustomerPoints (
        @RequestParam("phoneNumber") phoneNumber: String?,
        @RequestParam("brandId") brandId: ObjectId?,
        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startDate: LocalDateTime?,
        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endDate: LocalDateTime?,
        @PageableDefault(size = 10) pageable: Pageable,
    ): PointsAndAccountsResponse<CustomerPointsAndAccounts?> {
        return customerPointsService
            .getPointsAndAccounts(phoneNumber,brandId,startDate,endDate,pageable)
    }


    // Find point by _id update it's expireDate('expire' field from 'customerpoints' collection)
    //
    // Patch /points/{pointId}?expireDate='value'
    @PatchMapping("/points/{pointId}")
    fun updateCustomerPoint (
        @PathVariable("pointId") pointId: ObjectId,
        @RequestParam("expireDate") expireDate: LocalDateTime,
    ): String {
        return customerPointsService
            .updateCustomerPoints(pointId,expireDate)
    }


}