package io.tablemanager.voucheradmin.repository

import io.tablemanager.voucheradmin.entity.VoucherOrder
import io.tablemanager.voucheradmin.entity.VoucherOrderItem
import io.tablemanager.voucheradmin.entity.VoucherProduct
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/* 주문 정보 Repo */
@Repository
interface VoucherOrderRepository : JpaRepository<VoucherOrder, String> {

    /* 휴대폰번호, 상태 둘 중 하나로 검색 시
    * 주문 검색 쿼리문 'or' 사용 */
    @Query(value = "select vo.id as orderId, vo.status, m.name, SUM(vp.sale_price * voi.purchase_count) as totalAmount" +
                   " from voucher_order vo" +
                   " left join MEMBER m on m.member_id = vo.user_id" +
                   " left join voucher_order_item voi on vo.id = voi.order_id" +
                   " left join voucher_product vp on voi.product_id = vp.id" +
                   " where m.phone = :phoneNumber or vo.status = :status group by vo.id",
            countQuery = "select count(*) " +
                    "from voucher_order vo " +
                    "left join MEMBER m on m.member_id = vo.user_id " +
                    "left join voucher_order_item voi on vo.id = voi.order_id " +
                    "left join voucher_product vp on voi.product_id = vp.id " +
                    "where m.phone = :phoneNumber or vo.status = :status", nativeQuery = true)
    fun voucherOrderListOr(@Param("phoneNumber") phoneNumber: String?, @Param("status") status: String?, pageable: Pageable): Page<OrderSearchInterface?>

    /* 휴대폰번호, 상태 둘 다 검색 시
    * 주문 검색 쿼리문 'and' 사용 */
    @Query(value = "select vo.id as orderId, vo.status, m.name, SUM(vp.sale_price * voi.purchase_count) as totalAmount" +
            " from voucher_order vo" +
            " join MEMBER m on m.member_id = vo.user_id" +
            " join voucher_order_item voi on vo.id = voi.order_id" +
            " join voucher_product vp on voi.product_id = vp.id" +
            " where m.phone = :phoneNumber and vo.status = :status group by vo.id",
        countQuery = "select count(*) " +
                "from voucher_order vo " +
                "left join MEMBER m on m.member_id = vo.user_id " +
                "left join voucher_order_item voi on vo.id = voi.order_id " +
                "left join voucher_product vp on voi.product_id = vp.id " +
                "where m.phone = :phoneNumber and vo.status = :status",nativeQuery = true)
    fun voucherOrderListAnd(@Param("phoneNumber") phoneNumber: String, @Param("status") status: String, pageable: Pageable): Page<OrderSearchInterface?>
}

@Repository
interface VoucherOrderItemRepository : JpaRepository<VoucherOrderItem?, Int?> {
}

@Repository
interface VoucherProductRepository : JpaRepository<VoucherProduct, String> {
}

/* 주문 검색 쿼리 결과 담는 인터페이스 */
interface OrderSearchInterface {
    var orderId: String?
    var status: String?
    var totalAmount: Int?
    var name: String?
}

