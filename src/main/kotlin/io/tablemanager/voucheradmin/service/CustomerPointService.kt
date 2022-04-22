package io.tablemanager.voucheradmin.service

import io.tablemanager.voucheradmin.model.CustomerPointsAndAccounts
import io.tablemanager.voucheradmin.model.PointsAndAccountsResponse
import io.tablemanager.voucheradmin.repository.CustomerPointsRepo
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CustomerPointService (
    var customerPointsRepo: CustomerPointsRepo
        ) {

    // Get Points by phoneNumber and brandId and Date or by each others
    // (each parameter is 'organizationId' and 'accountNumber' field from 'customerpointaccounts' collection and 'cratedAt' field from 'customerpoints' collection)
    fun getPointsAndAccounts(
        phoneNumber: String?,
        brandId: ObjectId?,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        pageable: Pageable,
    ): PointsAndAccountsResponse<CustomerPointsAndAccounts?> {


        if (phoneNumber != null && brandId == null) {

            if (startDate != null && endDate == null) {

                val listResponse = customerPointsRepo
                    .findByAccountNumberAndStartDate(phoneNumber, startDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else if (startDate == null && endDate != null) {

                val listResponse = customerPointsRepo
                    .findByAccountNumberAndEndDate(phoneNumber, endDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else if (startDate != null && endDate != null) {

                val listResponse = customerPointsRepo
                    .findByAccountNumberBetweenDate(phoneNumber, startDate, endDate,pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else {

                val listResponse = customerPointsRepo
                    .findByAccountNumber(phoneNumber,pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            }

        } else if (phoneNumber == null && brandId != null) {

            if (startDate != null && endDate == null) {

                val listResponse = customerPointsRepo
                    .findByOrganizationIdAndStartDate(brandId, startDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else if (startDate == null && endDate != null) {

                val listResponse = customerPointsRepo
                    .findByOrganizationIdAndEndDate(brandId, endDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else if (startDate != null && endDate != null) {

                val listResponse = customerPointsRepo
                    .findByOrganizationIdBetweenDate(brandId, startDate, endDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else {

                val listResponse = customerPointsRepo
                    .findByOrganizationId(brandId, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            }

        } else if (phoneNumber != null && brandId != null) {

            if (startDate != null && endDate == null) {

                val listResponse = customerPointsRepo
                    .findByAccountNumberAndOrganizationIdAndStartDate(phoneNumber, brandId, startDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else if (startDate == null && endDate != null) {

                val listResponse = customerPointsRepo
                    .findByAccountNumberAndOrganizationIdAndEndDate(phoneNumber, brandId, endDate,pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else if (startDate != null && endDate != null) {

                val listResponse = customerPointsRepo
                    .findByAccountNumberAndOrganizationIdBetweenDate(phoneNumber, brandId, startDate, endDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else {

                val listResponse = customerPointsRepo
                    .findByAccountNumberAndOrganizationId(phoneNumber, brandId, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            }

        } else if (phoneNumber == null && brandId == null) {

            if (startDate != null && endDate == null) {

                val listResponse = customerPointsRepo
                    .findByStartDate(startDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            } else if (startDate == null && endDate != null) {

                val listResponse = customerPointsRepo
                    .findByEndDate(endDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts?>(
                    listResponse
                )

            } else if (startDate != null && endDate != null) {

                val listResponse = customerPointsRepo
                    .findBetweenDate(startDate, endDate, pageable)

                return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
                    listResponse
                )

            }

        }

        //when all parameters are null
        val listResponse = customerPointsRepo
            .searchAll(pageable)

        return PointsAndAccountsResponse.of<CustomerPointsAndAccounts>(
            listResponse
        )

    }

    // Find point by _id update it's expireDate('expire' field from 'customerpoints' collection)
    fun updateCustomerPoints(
        pointId: ObjectId,
        expireDate: LocalDateTime,
    ): String {

        return customerPointsRepo.updateExpireDate(pointId,expireDate)

    }



}