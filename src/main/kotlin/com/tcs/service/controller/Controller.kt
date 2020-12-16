package com.tcs.service.controller

import com.tcs.service.constant.ExceptionMessage.BAD_REQUEST
import com.tcs.service.constant.ExceptionMessage.NO_DATA_FOUND
import com.tcs.service.constant.ServiceLabels.API_TAG_DESC
import com.tcs.service.constant.ServiceLabels.API_TAG_NAME
import com.tcs.service.constant.ServiceLabels.DATA_FOUND
import com.tcs.service.constant.ServiceLabels.MEDIA_TYPE
import com.tcs.service.constant.ServiceLabels.OPENAPI_GET_DEF
import com.tcs.service.constant.URLPath.BASE_URI
import com.tcs.service.model.*
import com.tcs.service.repository.DeliveryChannelRepository
import com.tcs.service.repository.DeliveryStreamRepository
import com.tcs.service.repository.LogisticChannelRepository
import com.tcs.service.service.Service
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.logging.log4j.kotlin.logger
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(BASE_URI)
@Tag(name = API_TAG_NAME, description = API_TAG_DESC)
class Controller(private val service: Service,private  val repo: DeliveryChannelRepository,
                 private val repo1: DeliveryStreamRepository,
                 private val repo2: LogisticChannelRepository
                   ) {

    val logger = logger()

    /**
     *  GET Endpoints
     */

    /// Get deliverer All
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/delivererall"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getdelivererall(): ResponseEntity<ServiceResponse> {
        logger.info("Get all deliverer")

       val  records =  service.getDelivererAllService()
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", records))
    }

    //del schedule all
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/deliveryscheduleall"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getdeliveryscheduleall(): ResponseEntity<ServiceResponse> {
        logger.info("Get by query")
       val records =  service.getDeliveryscheduleService()
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", records))
    }
// delivery channel by query
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/deliveryChannel"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@RequestParam (required = false) storeNumber:Long?,
            @RequestParam(required = false) deliveryStream:Int?,
            @RequestParam(required = false) startDate:String): MutableList<DeliveryChannel> {
        logger.info("Get by query")

    return service.getDeliveryChannelService(storeNumber,deliveryStream,startDate)
}

    // delivery channel all
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/deliveryChannelAll"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllDelChannel(): ResponseEntity<ServiceResponse> {
        logger.info("Get all delivery Channel")

       val records = repo.findAll().toMutableList()
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", records))
    }

    //del schedule sorted
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/deliveryschedulesorted"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getdelschedulesorted(@RequestParam (required = false) storeNumber:Long?,
                             @RequestParam(required = false) deliveryStream:Int?,
                             @RequestParam(required = false) startDate:String,
                             @RequestParam(required = false) endDate:String?): ResponseEntity<ServiceResponse> {
        logger.info("Get from del schedule")

      val  records = service.getDeliveryScheduleSorted(storeNumber,deliveryStream,startDate,endDate).toMutableList()
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", records))
    }

    // del schedule crud for delivery moment validation
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/deliveryscheduleformoment"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getdelscheduleformoment(@RequestParam (required = false) storeNumber:Long?,
                             @RequestParam(required = false) deliveryStream:Int?,
                             @RequestParam(required = false) startDate:String): MutableList<DeliveryScheduleModel> {
        logger.info("Get from del schedule for moment")

        return service.getDeliveryScheduleForMoment(storeNumber,deliveryStream,startDate).toMutableList()

    }


    //Delivery Stream all
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/deliveryStreamAll"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllDelStream(): ResponseEntity<ServiceResponse> {
        logger.info("Get All")

      val  records = repo1.findAll().toMutableList()
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", records))
    }

    // Delivery Stream By Number

    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/deliveryStream/{deliveryStreamNumber}"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDeliveryStreamByNumber(@PathVariable deliveryStreamNumber: Int): ResponseEntity<ServiceResponse> {
        logger.info("Get by query in del stream")

       val records =  service.getDeliveryStreamService(deliveryStreamNumber)
        println(records)
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", records))
    }

    //Logistic Channel All
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/logisticChannelAll"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllLogisticChannel(): ResponseEntity<ServiceResponse> {
        logger.info("Get All Logistic channel")

       val records = repo2.findAll().toMutableList()
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", records))
    }

    // logistic channel by query
    @Operation(summary = OPENAPI_GET_DEF, description = OPENAPI_GET_DEF, tags = [API_TAG_NAME])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = DATA_FOUND, content = [
            (Content(mediaType = MEDIA_TYPE, array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = BAD_REQUEST, content = [Content()]),
        ApiResponse(responseCode = "404", description = NO_DATA_FOUND, content = [Content()])]
    )
    @RequestMapping(value = ["/logisticChannel"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getLogisticByQuery(@RequestParam (required = false) storeNumber:Long?,
                           @RequestParam(required = false) deliveryStream: Int?,
                           @RequestParam(required = false) startDate:String
                          ): MutableList<LogisticChannel> {
        logger.info("Get by query in logistic")

        return service.getLogisticChannelService(storeNumber,deliveryStream,startDate)
    }

}


