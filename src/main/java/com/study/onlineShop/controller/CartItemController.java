package com.study.onlineShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.study.onlineShop.entity.Cart;
import com.study.onlineShop.entity.CartItem;
import com.study.onlineShop.entity.Customer;
import com.study.onlineShop.entity.Product;
import com.study.onlineShop.service.CartItemService;
import com.study.onlineShop.service.CartService;
import com.study.onlineShop.service.CustomerService;
import com.study.onlineShop.service.ProductService;

@Controller
public class CartItemController {
  @Autowired
  private CartService cartService;

  @Autowired
  private CartItemService cartItemService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private ProductService productService;

  @RequestMapping("/cart/add/{productId}")
  @ResponseStatus(value = HttpStatus.CREATED)
  public void addCartItem(@PathVariable(value = "productId") int productId) {
    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    String username = loggedInUser.getName();
    Customer customer = customerService.getCustomerByUserName(username);

    Cart cart = customer.getCart();
    List<CartItem> cartItems = cart.getCartItem();
    Product product = productService.getProductById(productId);

    // 修改现有商品的quantity,进入的条件是 cartItems.size() > 0
    for (int i = 0; i < cartItems.size(); i++) {
      CartItem cartItem = cartItems.get(i);
      if (product.getId() == (cartItem.getProduct().getId())) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getProductPrice());
        cartItemService.addCartItem(cartItem);
        return;
      }
    }

    // 添加新商品
    CartItem cartItem = new CartItem();
    cartItem.setQuantity(1);
    cartItem.setProduct(product);
    cartItem.setPrice(product.getProductPrice());
    cartItem.setCart(cart);
    cartItemService.addCartItem(cartItem);
  }

  @RequestMapping(value = "/cart/removeCartItem/{cartItemId}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void removeCartItem(@PathVariable(value = "cartItemId") int cartItemId) {
    cartItemService.removeCartItem(cartItemId);
  }

  @RequestMapping(value = "/cart/removeAllItems/{cartId}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void removeAllCartItems(@PathVariable(value = "cartId") int cartId) {
    Cart cart = cartService.getCartById(cartId);
    cartItemService.removeAllCartItems(cart);
  }
}

