package io.tablemanager.voucheradmin.repository

import io.tablemanager.voucheradmin.model.CustomerPointsAndAccounts
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.LookupOperation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

@Repository
interface CustomerPointsRepo{

        fun findByAccountNumberAndStartDate(
            accountNumber: String,
            startDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByAccountNumberAndEndDate(
            accountNumber: String,
            endDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByAccountNumberBetweenDate(
            accountNumber: String,
            startDate: LocalDateTime,
            endDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByAccountNumber(
            accountNumber: String,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByOrganizationIdAndStartDate(
            organizationId: ObjectId,
            startDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByOrganizationIdAndEndDate(
            organizationId: ObjectId,
            endDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByOrganizationIdBetweenDate(
            organizationId: ObjectId,
            startDate: LocalDateTime,
            endDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByOrganizationId(
            organizationId: ObjectId,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByStartDate(
            startDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByEndDate(
            endDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findBetweenDate(
            startDate: LocalDateTime,
            endDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByAccountNumberAndOrganizationIdAndStartDate(
            accountNumber: String,
            organizationId: ObjectId,
            startDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByAccountNumberAndOrganizationIdAndEndDate(
            accountNumber: String,
            organizationId: ObjectId,
            endDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByAccountNumberAndOrganizationIdBetweenDate(
            accountNumber: String,
            organizationId: ObjectId,
            startDate: LocalDateTime,
            endDate: LocalDateTime,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByAccountNumberAndOrganizationId(
            accountNumber: String,
            organizationId: ObjectId,
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun searchAll(
            pageable: Pageable
        ): Page<CustomerPointsAndAccounts?>

        fun findByPointId(
            _id: ObjectId,
        ): List<CustomerPointsAndAccounts?>

        fun updateExpireDate(
            _id: ObjectId,
            updateExpireDate: LocalDateTime,
        ): String
}

@Service
class CustomerPointsRepoImpl(
    private val mongoTemplate: MongoTemplate,
): CustomerPointsRepo {

    val lookupStage = LookupOperation
        .newLookup()
        .from("customerpointaccounts")
        .localField("accountId")
        .foreignField("_id")
        .`as`("customerPointAccounts")

    override fun findByAccountNumberAndStartDate(
        accountNumber: String,
        startDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchAccountNumberAndStartDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.accountNumber")
                    .`is`(accountNumber)
                    .and("createdAt")
                    .gte(startDate)
            )

        val aggregationAccountNumberAndStartDate = Aggregation
            .newAggregation(
                lookupStage,
                matchAccountNumberAndStartDate
            )

        val result = mongoTemplate.aggregate(
            aggregationAccountNumberAndStartDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findByAccountNumberAndEndDate(
        accountNumber: String,
        endDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchAccountNumberAndEndDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.accountNumber")
                    .`is`(accountNumber)
                    .and("createdAt")
                    .lte(endDate)
            )

        val aggregationAccountNumberAndEndDate = Aggregation
            .newAggregation(
                lookupStage,
                matchAccountNumberAndEndDate
            )

        val result = mongoTemplate.aggregate(
            aggregationAccountNumberAndEndDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findByAccountNumberBetweenDate(
        accountNumber: String,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchAccountNumberBetweenDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.accountNumber")
                    .`is`(accountNumber)
                    .and("createdAt")
                    .gte(startDate)
                    .lte(endDate)
            )

        val aggregationAccountNumberBetweenDate = Aggregation
            .newAggregation(
                lookupStage,
                matchAccountNumberBetweenDate
            )

        val result = mongoTemplate.aggregate(
            aggregationAccountNumberBetweenDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }


    override fun findByAccountNumber(
        accountNumber: String,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchAccountNumber = Aggregation
            .match(
                Criteria.where("customerPointAccounts.accountNumber")
                    .`is`(accountNumber)
            )

        val aggregationAccountNumber = Aggregation
            .newAggregation(
                lookupStage,
                matchAccountNumber
            )

        val result = mongoTemplate.aggregate(
                aggregationAccountNumber,
                "customerpoints",
                CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findByOrganizationIdAndStartDate(
        organizationId: ObjectId,
        startDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchOrganizationIdAndStartDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.organizationId")
                    .`is`(organizationId)
                    .and("createdAt")
                    .gte(startDate)
            )

        val aggregationOrganizationIdAndStartDate = Aggregation
            .newAggregation(
                lookupStage,
                matchOrganizationIdAndStartDate
            )

        val result = mongoTemplate.aggregate(
            aggregationOrganizationIdAndStartDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findByOrganizationIdAndEndDate(
        organizationId: ObjectId,
        endDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchOrganizationIdAndEndDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.organizationId")
                    .`is`(organizationId)
                    .and("createdAt")
                    .lte(endDate)
            )

        val aggregationOrganizationIdAndEndDate = Aggregation
            .newAggregation(
                lookupStage,
                matchOrganizationIdAndEndDate
            )

        val result = mongoTemplate.aggregate(
            aggregationOrganizationIdAndEndDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }



    override fun findByOrganizationIdBetweenDate(
        organizationId: ObjectId,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchOrganizationIdBetweenDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.organizationId")
                    .`is`(organizationId)
                    .and("createdAt")
                    .gte(startDate)
                    .lte(endDate)
            )

        val aggregationOrganizationIdBetweenDate = Aggregation
            .newAggregation(
                lookupStage,
                matchOrganizationIdBetweenDate
            )

        val result = mongoTemplate.aggregate(
            aggregationOrganizationIdBetweenDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findByOrganizationId(
        organizationId: ObjectId,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchOrganizationId = Aggregation
            .match(
                Criteria.where("customerPointAccounts.organizationId")
                    .`is`(organizationId)
            )

        val aggregationOrganizationId = Aggregation
            .newAggregation(
                lookupStage,
                matchOrganizationId
            )

        val result = mongoTemplate.aggregate(
                aggregationOrganizationId,
                "customerpoints",
                CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )
    }

    override fun findByStartDate(
        startDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchStartDate = Aggregation
            .match(
                Criteria.where("createdAt")
                    .gte(startDate)
            )

        val aggregationStartDate = Aggregation
            .newAggregation(
                lookupStage,
                matchStartDate
            )

        val result = mongoTemplate.aggregate(
            aggregationStartDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findByEndDate(
        endDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchEndDate = Aggregation
            .match(
                Criteria.where("createdAt")
                    .lte(endDate)
            )

        val aggregationEndDate = Aggregation
            .newAggregation(
                lookupStage,
                matchEndDate
            )

        val result = mongoTemplate.aggregate(
            aggregationEndDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findBetweenDate(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchBetweenDate = Aggregation
            .match(
                Criteria.where("createdAt")
                    .gte(startDate)
                    .lte(endDate)
            )

        val aggregationBetweenDate = Aggregation
            .newAggregation(
                lookupStage,
                matchBetweenDate
            )

        val result = mongoTemplate.aggregate(
            aggregationBetweenDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )
    }

    override fun findByAccountNumberAndOrganizationIdAndStartDate(
        accountNumber: String,
        organizationId: ObjectId,
        startDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchAccountNumberAndOrganizationIdAndStartDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.accountNumber")
                    .`is`(accountNumber)
                    .and("customerPointAccounts.organizationId")
                    .`is`(organizationId)
                    .and("createdAt")
                    .gte(startDate)
            )

        val aggregationAccountNumberAndOrganizationIdAndStartDate = Aggregation
            .newAggregation(
                lookupStage,
                matchAccountNumberAndOrganizationIdAndStartDate
            )

        val result = mongoTemplate.aggregate(
            aggregationAccountNumberAndOrganizationIdAndStartDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )
    }

    override fun findByAccountNumberAndOrganizationIdAndEndDate(
        accountNumber: String,
        organizationId: ObjectId,
        endDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchAccountNumberAndOrganizationIdAndEndDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.accountNumber")
                    .`is`(accountNumber)
                    .and("customerPointAccounts.organizationId")
                    .`is`(organizationId)
                    .and("createdAt")
                    .lte(endDate)
            )

        val aggregationAccountNumberAndOrganizationIdAndEndDate = Aggregation
            .newAggregation(
                lookupStage,
                matchAccountNumberAndOrganizationIdAndEndDate
            )

        val result = mongoTemplate.aggregate(
            aggregationAccountNumberAndOrganizationIdAndEndDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )
    }

    override fun findByAccountNumberAndOrganizationIdBetweenDate(
        accountNumber: String,
        organizationId: ObjectId,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchAccountNumberAndOrganizationIdBetweenDate = Aggregation
            .match(
                Criteria.where("customerPointAccounts.accountNumber")
                    .`is`(accountNumber)
                    .and("customerPointAccounts.organizationId")
                    .`is`(organizationId)
                    .and("createdAt")
                    .gte(startDate)
                    .lte(endDate)
            )

        val aggregationaccountNumberAndOrganizationIdBetweenDate = Aggregation
            .newAggregation(
                lookupStage,
                matchAccountNumberAndOrganizationIdBetweenDate
            )

        val result = mongoTemplate.aggregate(
            aggregationaccountNumberAndOrganizationIdBetweenDate,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findByAccountNumberAndOrganizationId(
        accountNumber: String,
        organizationId: ObjectId,
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val matchAccountNumberAndOrganizationId = Aggregation
            .match(
                Criteria.where("customerPointAccounts.accountNumber")
                    .`is`(accountNumber)
                    .and("customerPointAccounts.organizationId")
                    .`is`(organizationId)
            )

        val aggregationAccountNumberAndOrganizationId = Aggregation
            .newAggregation(
                lookupStage,
                matchAccountNumberAndOrganizationId,
        )

        val result = mongoTemplate.aggregate(
            aggregationAccountNumberAndOrganizationId,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun searchAll(
        pageable: Pageable
    ): Page<CustomerPointsAndAccounts?> {

        val aggregationAllPointsAndAccounts = Aggregation
            .newAggregation(
                lookupStage,
            )

        val result = mongoTemplate.aggregate(
            aggregationAllPointsAndAccounts,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

        return PageImpl(
            result,
            pageable,
            result.size.toLong()
        )

    }

    override fun findByPointId(
        pointId: ObjectId
    ): List<CustomerPointsAndAccounts?> {

        val matchPointId = Aggregation
            .match(
                Criteria.where("_id")
                    .`is`(pointId)
            )

        val aggregationByPointId = Aggregation
            .newAggregation(
                lookupStage,
                matchPointId
            )

        return mongoTemplate.aggregate(
            aggregationByPointId,
            "customerpoints",
            CustomerPointsAndAccounts::class.java
        ).mappedResults

    }

    override fun updateExpireDate(
        _id: ObjectId,
        updateExpireDate: LocalDateTime
    ): String {

        val pointToUpdate = Query(
            Criteria.where("_id")
                .`is`(_id)
        )

        val updateSet = Update.update(
            "expire",
            updateExpireDate
        )

        mongoTemplate.updateFirst(
            pointToUpdate,
            updateSet,
            "customerpointaccounts"
        )

        return "expireDate updated"
    }
}

