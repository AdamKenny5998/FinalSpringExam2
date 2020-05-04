/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsecurityexam.controller;

import com.mycompany.springsecurityexam.model.Product;
import com.mycompany.springsecurityexam.model.ProductService;
import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Adam
 */
@Controller
@RequestMapping("/product")
@SessionAttributes
public class ProductController {
    @Autowired
    ProductService service;
    
    @RequestMapping("")
    public ModelAndView getProducts(HttpSession sess){
        sess.setAttribute("uname", getLoggedInUserName());
        return new ModelAndView("/allProducts", "productList", service.getAllProducts());
    }
    
    @RequestMapping("/deleteProduct{code}")
    public ModelAndView deleteProduct(@PathVariable ("code") String code){
        service.deleteAProduct(code);
        return new ModelAndView("/allProducts", "productList", service.getAllProducts());
    }
    
    @GetMapping("/addProduct")
    public ModelAndView displayAgentAddForm(){
        return new ModelAndView("/addProduct", "product", new Product("","","",0.00,0.00,0));
    }
    
    
    private String getLoggedInUserName(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        
        return principal.toString();
    }
    
}
