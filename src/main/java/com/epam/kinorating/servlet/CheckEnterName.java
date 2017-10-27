//package com.epam.kinorating.servlet;
//
//import com.epam.kinorating.service.UserService;
//import org.apache.log4j.Logger;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.UnknownHostException;
//
//@WebServlet(name = "checkname", urlPatterns = "/checkname")
//public class CheckEnterName extends HttpServlet {
//    private static final Logger log = Logger.getLogger(CheckEnterName.class);
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userName = request.getParameter("username");
//        String mail = null;
//        PrintWriter printWriter;
//        try {
//            printWriter = response.getWriter();
//            if (!UserService.isFree(userName, mail)) {
//                printWriter.print("false");
//            }
//        } catch (UnknownHostException e) {
//            log.error("Got a problem with the host" ,e);
//        }
//
//    }
//}
