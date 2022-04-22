
package io.tablemanager.voucheradmin.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Document(collection = "customerpoints")
class CustomerPoints(

    @Id
    var _id: String?,

    var accountId: String?,

    @Field(name = "createdAt")
    var pointcreatedAt: LocalDateTime?,

    var expire: LocalDateTime?,

    @Field(name = "name")
    var pointname: String?,

    var type: String?,

)


