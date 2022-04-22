package io.tablemanager.voucheradmin.model

data class OrderCancelResult (
    var result: List<CancelResult>
)

data class CancelResult (
    var orderId: String?,
    var code: VoucherOrderCancelResult?
)

enum class VoucherOrderCancelResult{
    COMPLETE,
    ALREADY_CANCELED,
    POINT_NOT_FOUND,
    PAYMENT_NOT_FOUND,
    PAYMENT_ALREADY_CANCELED,
    NOT_CANCELABLE_POINT,
    ORDER_NOT_FOUND,
    PAYMENT_CANCEL_FAILED,
    ORDER_CANCEL_FAILED,
    POINT_REMOVE_FAILED
}