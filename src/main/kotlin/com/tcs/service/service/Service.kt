package com.tcs.service.service

import com.tcs.service.model.*
import com.tcs.service.repository.*
import org.springframework.stereotype.Service


@Service
class Service(private val deliverer: DelivererRepository,
              private val deliveryStream: DeliveryStreamRepository,
              private  val customQuery: CustomRepositoryImpl,
              private val deliveryschedule:DeliveryScheduleRepository ) {



    fun getDeliveryStreamService(deliveryStreamNumber: Int?) : MutableList<DeliveryStream> {

        return deliveryStream.findByDeliveryStreamNumber(deliveryStreamNumber)
    }
    fun getDeliveryScheduleSorted(storeNumber:Long?,deliveryStream:Int?,startDate:String,endDate:String?)
            : MutableList<DeliveryScheduleModel> {

        return customQuery.getDeliveryScheduleSorted(storeNumber,deliveryStream,startDate,endDate)



    }


    fun getDeliveryChannelService(storeNumber:Long?,deliveryStream:Int?,startDate:String): MutableList<DeliveryChannel> {

        return customQuery.getDeliveryChannel(storeNumber, deliveryStream, startDate)


    }

  fun  getLogisticChannelService(storeNumber:Long?,deliveryStream:Int?,startDate:String) :
          MutableList<LogisticChannel> {


      return customQuery.getLogisticChannelRepo(storeNumber, deliveryStream, startDate)
  }

    fun getDeliveryscheduleService(): MutableList<DeliveryScheduleModel> {

        val result = deliveryschedule.findAll()
        return result.toMutableList()

    }

    fun getDelivererAllService() : MutableList<Deliverer> {

        val result = deliverer.findAll()
        return result.toMutableList()
    }

    fun getDeliveryScheduleForMoment(storeNumber:Long?,deliveryStream:Int?,startDate:String)
            : MutableList<DeliveryScheduleModel> {

        return customQuery.getDeliveryScheduleForMomentCustom(storeNumber,deliveryStream,startDate)
    }

}