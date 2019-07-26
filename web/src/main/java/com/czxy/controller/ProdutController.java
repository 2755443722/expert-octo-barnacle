package com.czxy.controller;

import com.czxy.domain.Cart;
import com.czxy.domain.CartItem;
import com.czxy.domain.Product;
import com.czxy.scrvice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("product")
public class ProdutController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        try {
            List<Product> list = productService.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> findById(@PathVariable Integer id, HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
        }
        Product product = productService.findById(id);
        cart.addCartItem(product,1);
        request.getSession().setAttribute("cart",cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("addCart")
    public ResponseEntity<List<CartItem>> addCart(HttpServletRequest request){
        try {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart==null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            List<CartItem> list=new ArrayList<>();
            Map<Integer, CartItem> map = cart.getMap();
            Set<Integer> integers = map.keySet();
            for (Integer integer : integers) {
                CartItem cartItem = map.get(integer);
                list.add(cartItem);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id,HttpServletRequest request){

        try {
            Cart cart = (Cart) request.getSession().getAttribute("cart"); //遍历 删除key值为id的购物项
            Map<Integer, CartItem> map = cart.getMap();
            Set<Integer> integers = map.keySet();
            for (Integer integer : integers) {
                CartItem ct = map.get(integer);
                if(ct.getProduct().getId() == id){
                    ct.setCount(ct.getCount()-1);
                }
                if(ct.getCount() == 0){
                    map.remove(ct.getProduct().getId());
                }
            }
            request.getSession().setAttribute("cart",cart);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
