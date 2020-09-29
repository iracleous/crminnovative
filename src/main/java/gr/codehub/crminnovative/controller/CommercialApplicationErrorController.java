package gr.codehub.crminnovative.controller;


import gr.codehub.crminnovative.dto.ErrorDetails;
import gr.codehub.crminnovative.exception.CustomerNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class CommercialApplicationErrorController
        implements ErrorController {

    @RequestMapping("error")
    public ErrorDetails handleError(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return     new ErrorDetails( statusCode,exception==null? "N/A": exception.getMessage()  );

    }

    //    Error code= "  +
    //                + " Message= "+  ( ( exception==null)? "N/A": exception.getMessage() );

//        if (exception instanceof CustomerNotFoundException)
//        {
//            return exception.getMessage();
//        }
//
//        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
//                       + "<div>Exception Message: <b>%s</b></div><body></html>",
//              statusCode, exception==null? "N/A": exception.getMessage());


       // error below check operator priority
    //    return "Error code= "  +statusCode
    //            + " Message= "+    exception==null ? ""N/A": exception.getMessage()  ;



    @Override
    public String getErrorPath() {
        return "/error";
    }
}