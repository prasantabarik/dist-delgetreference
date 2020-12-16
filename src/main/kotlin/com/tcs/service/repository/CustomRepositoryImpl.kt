package com.tcs.service.repository

import com.tcs.service.configs.DataBaseConnectionConfig
import com.tcs.service.model.*
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class CustomRepositoryImpl : CustomRepository{


    override fun getAllByDesc(modDesc: String): List<BaseModel>{
        val query = Query()
        query.addCriteria(Criteria.where("modDesc").`is`(modDesc))


        return DataBaseConnectionConfig.mongoTemplate().find(query, BaseModel::class.java)

    }



fun getDeliveryScheduleSorted(storeNumber:Long?,deliveryStream:Int?,startDate:String,endDate:String?)
        : MutableList<DeliveryScheduleModel> {
        val query = Query()

        val criteria = Criteria()


    criteria.andOperator(
            Criteria.where("storeNumber").isEqualTo(storeNumber),
            Criteria.where("deliveryStreamNumber").isEqualTo(deliveryStream)
            , Criteria.where("startDate").lte(endDate.toString()).orOperator(
              Criteria.where("endDate").gte(startDate),
                Criteria.where("endDate").`is`(null))



    )

        val toPrint = query.addCriteria(criteria)


    return DataBaseConnectionConfig.mongoTemplate().find(toPrint, DeliveryScheduleModel::class.java)

    }


//del schedule for moment
    fun getDeliveryScheduleForMomentCustom(storeNumber:Long?,deliveryStream:Int?,startDate:String)
        :MutableList<DeliveryScheduleModel> {
    val query = Query()

    val criteria = Criteria()

    criteria.andOperator(
            Criteria.where("storeNumber").isEqualTo(storeNumber),
            Criteria.where("deliveryStreamNumber").isEqualTo(deliveryStream)
            , Criteria.where("startDate").lte(startDate).orOperator(
            Criteria.where("endDate").gte(startDate)
            ,Criteria.where("endDate").`is`(null)
    )
    )

    val toPrint = query.addCriteria(criteria)


    return DataBaseConnectionConfig.mongoTemplate().find(toPrint, DeliveryScheduleModel::class.java)


}




fun getDeliveryChannel(storeNumber:Long?,deliveryStream:Int?,startDate:String): MutableList<DeliveryChannel> {
    val query = Query()


    val criteria = Criteria()
    criteria.andOperator(
            Criteria.where("storeNumber").isEqualTo(storeNumber),
            Criteria.where("deliveryStream").isEqualTo(deliveryStream),
            Criteria.where("startDate").lte(startDate).orOperator(
                    Criteria.where("endDate").gte(startDate)
                    ,Criteria.where("endDate").`is`(null)
            )
    )



    val toPrint = query.addCriteria(criteria)


    return DataBaseConnectionConfig.mongoTemplate().find(toPrint, DeliveryChannel::class.java)


            }

    // for logistic one
    fun getLogisticChannelRepo(storeNumber: Long?,deliveryStream:Int?,startDate:String):
            MutableList<LogisticChannel> {
        val query = Query()

        val criteria = Criteria()


        criteria.andOperator(
                Criteria.where("storeNumber").isEqualTo(storeNumber),
                Criteria.where("deliveryStream").isEqualTo(deliveryStream),
                Criteria.where("startDate").lte(startDate)
                        .orOperator(
                        Criteria.where("endDate").gte(startDate),
                        Criteria.where("endDate").`is`(null) )

        )


        val toPrint = query.addCriteria(criteria)


        return DataBaseConnectionConfig.mongoTemplate().find(toPrint, LogisticChannel::class.java)

    }





}