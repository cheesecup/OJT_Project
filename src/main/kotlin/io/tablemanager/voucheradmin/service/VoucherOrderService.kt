package io.tablemanager.voucheradmin.service

import io.tablemanager.voucheradmin.repository.OrderSearchInterface
import io.tablemanager.voucheradmin.repository.VoucherOrderRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class VoucherOrderService(private var voucherOrderRepository: VoucherOrderRepository) {
    private var logger: Logger = LoggerFactory.getLogger(VoucherOrderService::class.java)

    /* 주문 검색 서비스 */
    fun searchList(phoneNumber: String, status: String, pageable: Pageable): Page<OrderSearchInterface?> {

        var newStatus = status.toUpperCase() // status 값 대문자로 변환

        if (phoneNumber != "" && newStatus != "") { // 휴대폰, 상태 둘 다 검색 시
            return voucherOrderRepository.voucherOrderListAnd(phoneNumber, newStatus, pageable)
        } else if (phoneNumber == "") { // status로 검색 시
            return voucherOrderRepository.voucherOrderListOr(null, newStatus, pageable)
        } else { // phoneNumber로 검색 시
            return voucherOrderRepository.voucherOrderListOr(phoneNumber, null, pageable)
        }
    }

    /* 상품권 환불 */


}