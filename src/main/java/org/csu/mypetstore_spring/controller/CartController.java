package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Cart;
import org.csu.mypetstore_spring.domain.CartItem;
import org.csu.mypetstore_spring.domain.Item;
import org.csu.mypetstore_spring.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Iterator;

@Controller
public class CartController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/cart/addItemToCart")
    public String addItemToCart(@RequestParam("workingItemId") String workingItemId, HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            Boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
        session.setAttribute("cart", cart);
        return "cart/Cart";
    }

    @GetMapping("/cart/removeItemFromCart")
    public String removeItemFromCart(@RequestParam("workingItemId") String workingItemId, HttpSession session) {
        Cart cart=(Cart)session.getAttribute("cart");

        Item item = cart.removeItemById(workingItemId);

        if (item == null) {
            session.setAttribute("message","Attempted to remove null CartItem from Cart.");
            return "common/error";
        } else {
            session.setAttribute("cart",cart);
            return "cart/Cart";
        }
    }


    @GetMapping("/cart/viewCart")
    public String viewCart(HttpSession session){
        Cart cart=(Cart)session.getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            session.setAttribute("cart",cart);
        }
        return "cart/Cart";
    }

    @PostMapping("cart/updateCartQuantities")
    public String updateCartQuantities(HttpSession session){
        Cart cart=(Cart)session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();

        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                int quantity = Integer.parseInt(itemId);
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception e) {
                //ignore parse exceptions on purpose
            }
        }
        return "cart/Cart";
    }

    @GetMapping("/cart/checkOut")
    public String checkOut() {
        return "cart/CheckOut";
    }

}
