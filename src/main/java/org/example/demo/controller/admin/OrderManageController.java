package org.example.demo.controller.admin;

import com.google.gson.Gson;
import org.example.demo.Services.*;
import org.example.demo.model.Account;
import org.example.demo.model.Order;
import org.example.demo.response.AccountResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "orderManageController", value = "/admin-order-manage")
public class OrderManageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null || !(account.getRole().getName().equals("ADMIN"))) {
            response.sendRedirect("login");
        } else {
            String base = request.getServletContext().getContextPath();
            request.setAttribute("base", base);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order-manage.jsp");
            //get list order
            List<Order> listOrder = OrderServices.getAll();
            request.setAttribute("subTabName", "manageOrder");
            request.setAttribute("tabName", "manage");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public static void main(String[] args) {
        //print base from doget method


    }
}
