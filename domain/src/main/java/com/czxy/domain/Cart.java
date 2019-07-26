package com.czxy.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer,CartItem> map=new HashMap<>();
    private Integer totalmoney;

    //添加购物车
    public void addCartItem(Product product,Integer count){
        CartItem cartItem = map.get(product.getId());
        if (cartItem==null){
            cartItem=new CartItem(product,count);
        }else {
            cartItem.setCount(cartItem.getCount()+count);
        }
        map.put(product.getId(),cartItem);
    }

    //删除一条
    public void shanchu(Integer id){
        CartItem cartItem = map.get(id);
        if (cartItem.getCount()!=1){
            cartItem.setCount(cartItem.getCount()-1);
            map.put(cartItem.getProduct().getId(),cartItem);
        }else {
            map.remove(id);
        }
    }


    @Override
    public String toString() {
        return "Cart{" +
                "map=" + map +
                ", totalmoney=" + totalmoney +
                '}';
    }

    public Map<Integer, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<Integer, CartItem> map) {
        this.map = map;
    }

    public Integer getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Integer totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Cart(Map<Integer, CartItem> map, Integer totalmoney) {
        this.map = map;
        this.totalmoney = totalmoney;
    }

    public Cart() {
    }
}
