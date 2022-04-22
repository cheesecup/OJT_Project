package io.tablemanager.voucheradmin.service

import io.tablemanager.voucheradmin.entity.CustomerPointAccounts
import io.tablemanager.voucheradmin.model.PointAccountsResponse
import io.tablemanager.voucheradmin.repository.CustomerAccountsRepo
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerAccountService (
    var customerAccountsRepo: CustomerAccountsRepo
        ){


    // Get accounts by phoneNumber and brandId or by each others
    // (each parameter is 'organizationId' and 'accountNumber' field from 'customerpointaccounts' collection)
    fun getCustomerPointAccounts(
        phoneNumber: String?,
        brandId: ObjectId?,
        pageable: Pageable
    ): PointAccountsResponse<CustomerPointAccounts?> {
        if(phoneNumber!=null&&brandId==null) {

            val listResponse = customerAccountsRepo.findAllByAccountNumber(phoneNumber,pageable)

            return PointAccountsResponse.of<CustomerPointAccounts>(
                listResponse
            )
        }
        else if(phoneNumber==null&&brandId!=null) {

            val listResponse = customerAccountsRepo.findAllByOrganizationId(brandId,pageable)

            return PointAccountsResponse.of<CustomerPointAccounts>(
                listResponse
            )
        }
        else if(phoneNumber!=null&&brandId!=null) {

            val listResponse = customerAccountsRepo.findAllByOrganizationIdAndAccountNumber(brandId,phoneNumber,pageable)

            return PointAccountsResponse.of<CustomerPointAccounts>(
                listResponse
            )
        }
        //when all parameters are null
        else {

            val listResponse = customerAccountsRepo.findAll(pageable)

            return PointAccountsResponse.of<CustomerPointAccounts>(
                listResponse
            )
        }

    }


    // Find account by _id and update it's brandId or phoneNumber
    // (each parameter is 'organizationId' and 'accountNumber' field from 'customerpointaccounts' collection)
    fun updateCustomerAccounts(
        pointAccountId: ObjectId,
        brandId: String?,
        phoneNumber: String?,
    ): String {

        val accountToUpdate = customerAccountsRepo.findAllBy_id(pointAccountId)

        if(brandId!=null&&phoneNumber==null) {
            customerAccountsRepo.saveAll(
                accountToUpdate.map {
                    it.apply {
                        organizationId = brandId
                    }
                }
            )
            return "organizationId updated"
        }
        if(brandId==null&&phoneNumber!=null) {
            customerAccountsRepo.saveAll(
                accountToUpdate.map {
                    it.apply {
                        accountNumber = phoneNumber
                    }
                }
            )
            return "accountNumber updated"
        }
        if(phoneNumber!=null&&brandId!=null) {
            customerAccountsRepo.saveAll(
                accountToUpdate.map {
                    it.apply {
                        accountNumber = phoneNumber
                        organizationId = brandId
                    }
                }
            )
            return "accountNumber&organizationId updated"
        }
        // if parameters are null
        return "update failed"
    }

}