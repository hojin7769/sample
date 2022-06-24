package com.example.sample.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice(basePackages = {"com.example.sample.web"})
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError(HttpServletRequest req , Model model, Exception ex){
        LOGGER.error("Request: " + req.getRequestURI() + "raised" + ex);

        String msg = ex.getMessage();
        if(ex.getCause() != null){
            msg = ex.getCause().getMessage();
        }
        model.addAttribute("error",msg);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Model model, Exception ex) {
        LOGGER.error("Request: " + req.getRequestURL() + " raised " + ex);

        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
