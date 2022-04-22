# 예약상품권 어드민 API

## 인턴십 프로젝트 진행 결과

### 김대용
- 엔티티 생성
  - 완료
- 주문
  - 주문 검색 및 리스팅
    - 완료
  - 환불 API
    - 완료
- 기타 사항(미흡한 부분 및 궁금한 사항)
  - voucherorder pagenation 리팩토링 필요한 것 같습니다.(카운트 쿼리 직접 사용으로 인한 비효율적인 코드)
  - 네이티브 쿼리 없이 코드 구현은 어떻게 하는지 궁급합니다.

### 유진환
- 계정
    - 고객 계정 검색 및 리스팅 (w. 전화번호, 브랜드, columns=id, 브랜드 ID, 전화번호, 이름)
      - 완료
    - 고객 계정 정보 변경
      - 완료(?)
- 구매한 상품권
  - 구매한 상품권 검색 및 리스팅 (w. 전화번호, 브랜드, 구매일자, columns=id, 구매일자, 브랜드 ID, 상품권 이름, 유효기간, 구매자 전화번호)
    - 완료
  - 구매한 상품권 유효기간 연장
    - 완료(?)
- 기타 사항(미흡한 부분 및 궁금한 사항)
  - 구매한 상품권 검색 및 리스팅 기능에서 pagenation 기능구현 미흡
    - 결과 items가 page size 만큼 잘라서 나오는게 아닌 전체 결과값을 리스팅
  - 각 기능의 update 작동 test를 못했습니다.
  - 전체 적인 코드를 간결화 하고 싶은데 어떤 키워드를 검색해보면 좋을지 궁금합니다.


## Functional Requirement
### 계정
- 고객 계정 검색 및 리스팅 (w. 전화번호, 브랜드, columns=id, 브랜드 ID, 전화번호, 이름)
- 고객 계정 정보 변경

### 구매한 상품권
- 구매한 상품권 검색 및 리스팅 (w. 전화번호, 브랜드, 구매일자, columns=id, 구매일자, 브랜드 ID, 상품권 이름, 유효기간, 구매자 전화번호)
- 구매한 상품권 유효기간 연장

### 주문
- 주문 검색 및 리스팅 (w. 전화번호, 주문상태, columns=id, 상태, 총 구매액)
- 선택한 주문 환불하기 (우선 기존 환불 API를 사용 후, 직접 API 로직을 구현 예정입니다)

## Non-Functional Requirement

- Octa, NCP workplace 등 외부 인증 시스템을 통한 인증 시스템을 구축해야합니다.
- 환불을 진행하는 경우, 일련의 과정(결제 환불, 상품권 취소, 주문 취소)이 하나의 트랜잭션으로 이루어져야합니다. (API를 사용하는 경우 고려 X)

## Resource
### Mongodb
고객의 계정을 검색/변경할때 사용합니다. (customerpointaccounts)
조회한 주문 ID로 생성된 포인트를 조회할 때 사용합니다. (customerpoints)

### MYSQL
고객이 주문한 내역을 조회할 때 사용합니다. (voucher_order)


### 상품권 환불 API
주문 ID로 상품권을 환불 요청할 때 사용합니다.

**Request Body**
| Name     | Type         | Description    |
| -------- | ------------ | -------------- |
| orderIds | List<String> | 취소할 주문 ID |

**Response Body**
| Name   | Type                                  | Description      |
| ------ | ------------------------------------- | ---------------- |
| result | Map<String, VoucherOrderCancelResult> | 주문별 처리 결과 |

**VoucherOrderCancelResult**
- COMPLETE
- ALREADY_CANCELED
- POINT_NOT_FOUND
- PAYMENT_NOT_FOUND
- PAYMENT_ALREADY_CANCELED
- NOT_CANCELABLE_POINT
- ORDER_NOT_FOUND
- PAYMENT_CANCEL_FAILED
- ORDER_CANCEL_FAILED
- POINT_REMOVE_FAILED
