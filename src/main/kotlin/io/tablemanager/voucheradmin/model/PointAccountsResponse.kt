package io.tablemanager.voucheradmin.model

import io.tablemanager.voucheradmin.entity.CustomerPointAccounts
import org.springframework.data.domain.Page

data class PointAccountsResponse<CustomerPointAccounts>(
    val totalPage: Int,
    val pageSize: Int,
    val resultCount: Int,
    val offset: Int,
    val items: List<CustomerPointAccounts>,
) {
    companion object {
        fun <T>of(page: Page<CustomerPointAccounts?>): PointAccountsResponse<CustomerPointAccounts?> {
            return PointAccountsResponse(
                totalPage = page.totalPages,
                pageSize = page.size,
                resultCount = page.totalElements.toInt(),
                offset = page.number+1,
                items = page.content
            )
        }
    }
}