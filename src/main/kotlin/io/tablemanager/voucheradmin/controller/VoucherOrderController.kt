package io.tablemanager.voucheradmin.controller

import io.tablemanager.voucheradmin.entity.VoucherOrder
import io.tablemanager.voucheradmin.model.CancelIds
import io.tablemanager.voucheradmin.model.OrderCancelResult
import io.tablemanager.voucheradmin.repository.*
import io.tablemanager.voucheradmin.service.VoucherOrderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/order")
class VoucherOrderController(private var voucherOrderService: VoucherOrderService,
                             private var voucherOrderRepository: VoucherOrderRepository) {

    private var logger: Logger = LoggerFactory.getLogger(VoucherOrderController::class.java)

    /* 주문 목록 전체 조회 */
    @GetMapping("/list")
    fun getList(pageable: Pageable): Page<VoucherOrder> {
        return voucherOrderRepository.findAll(pageable)
    }

    /* 주문 검색 및 리스팅 */
    @GetMapping("/search")
    fun getOrderSearch(@RequestParam("phoneNumber") phoneNumber: String,
                       @RequestParam("status") status: String,
                       pageable: Pageable): Page<OrderSearchInterface?> {

//        logger.info("phoneNumber= {}", phoneNumber)
//        logger.info("status= {}", status)

        return voucherOrderService.searchList(phoneNumber, status, pageable)
    }

    /* 선택한 주문 환불하기 */
    @PostMapping("/cancel")
    fun postCancel(@RequestBody cancelIds: CancelIds): OrderCancelResult? {

//        for (i in 0 until cancelIds.orderIds.size) {
//            logger.info("{}= {}",i, cancelIds.orderIds[i])
//        }

        var restTemplate = RestTemplate()

        var url = "외부 API 입력" // 외부 api

        return restTemplate.postForObject(url, cancelIds, OrderCancelResult::class.java)
    }
}
