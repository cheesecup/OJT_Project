package io.tablemanager.voucheradmin.model

import org.springframework.data.domain.Page

data class PointsAndAccountsResponse<CustomerPointsAndAccounts> (
    val totalPage: Int,
    val pageSize: Int,
    val resultCount: Int,
    val offset: Int,
    val items: List<CustomerPointsAndAccounts>,
) {
    companion object {
        fun<CustomerPointAndAccounts>of(page: Page<CustomerPointsAndAccounts?>):PointsAndAccountsResponse<CustomerPointsAndAccounts?> {
            return PointsAndAccountsResponse(
                totalPage = page.totalPages,
                pageSize = page.size,
                resultCount = page.totalElements.toInt(),
                offset = page.number+1,
                items = page.content
            )
        }
    }

}