package controllers.admin

import java.util

import akka.stream.Materializer
import javax.inject.Inject
import play.api.Logging
import play.api.mvc.{AnyContent, EssentialFilter, Filter, Request, RequestHeader, Result}
import play.http.HttpFilters
import play.mvc

import scala.Option.WithFilter
import scala.concurrent.{ExecutionContext, Future}

class FilterReq @Inject() (implicit val mat: Materializer, ec: ExecutionContext) extends Filter with Logging{
  def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis

    nextFilter(requestHeader).map { result =>
      val endTime     = System.currentTimeMillis
      val requestTime = endTime - startTime

      logger.info(
        s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}" + "Filter"
      )

      println("xxxx");

      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }
}