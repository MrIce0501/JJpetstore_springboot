package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Account;
import org.csu.mypetstore_spring.domain.Cart;
import org.csu.mypetstore_spring.domain.Order;
import org.csu.mypetstore_spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/listOrder")
    public String listOrders(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
            model.addAttribute("listOrder", orderList);
            return "order/ListOrders";
        } else {
            session.setAttribute("message", "You haven't login in");
            return "account/SignOnForm";
        }
    }

    @GetMapping("/order/toNewOrder")
    public String toNewOrder() {
        return "order/NewOrder";
    }

    @PostMapping("/order/shippingAddress")
    public String shippingAddress(Order order,HttpSession session){
        Order order1=(Order)session.getAttribute("order");
        if (!order.getShipAddress1().equals(order1.getShipAddress1())){
            order1.setShipAddress1(order.getShipAddress1());
            order1.setShipAddress2(order.getShipAddress2());
            order1.setShipCity(order.getShipCity());
            order1.setShipCountry(order.getShipCountry());
            order1.setShipState(order.getShipState());
            order1.setShipToFirstName(order.getShipToFirstName());
            order1.setShipToLastName(order.getShipToLastName());
            order1.setShipZip(order.getShipZip());
        }
        session.setAttribute("order",order1);
        return "order/confirmOrder";
    }

    @PostMapping("/order/newOrder")
    public String newOrder(@RequestParam(value = "shippingAddressRequired",required = false) boolean shippingAddressRequired, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");
        if (account == null) {
            session.setAttribute("message", "You must sign on before attempting to check out.  Please sign on and try checking out again.");
            return "account/SignOnForm";
        }
        else if (cart != null) {
            Order order=new Order();
            order.initOrder(account, cart);
            session.setAttribute("order", order);
            if(shippingAddressRequired) {
                return "order/ShippingForm";
            }
            else {
                return "order/ConfirmOrder";
            }
        }
        else {
            session.setAttribute("message", "An order could not be created because a cart could not be found.");
            return "common/error";
        }
    }

    @PostMapping("/order/confirmOrder")
    public String confirmOrder(HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        orderService.insertOrder(order);
        session.removeAttribute("cart");
        return "order/ViewOrder";
    }

    @GetMapping("/order/viewOrder")
    public String viewOrder(@RequestParam("orderId") int orderId, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        Order order = orderService.getOrder(orderId);
        if (account.getUsername().equals(order.getUsername())) {
            session.setAttribute("order",order);
            return "order/ViewOrder";
        } else {
            order = null;
            session.setAttribute("message", "You may only view your own orders.");
            return "common/error";
        }
    }
}
