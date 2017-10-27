//package com.epam.kinorating.servlet;
//
//import com.epam.kinorating.service.UserService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet(name = "checkmail", urlPatterns = "/checkmail")
//public class CheckEnterMail extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String mail = request.getParameter("mail");
//
//        PrintWriter printWriter = response.getWriter();
//
//        if (!UserService.isFree(null, mail)) {
//            printWriter.print("false");
//        }
//    }
//}
