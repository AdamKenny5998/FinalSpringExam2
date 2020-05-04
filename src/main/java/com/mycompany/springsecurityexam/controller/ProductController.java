/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springsecurityexam.controller;

import com.mycompany.springsecurityexam.model.ProductService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
    
    @RequestMapping("deleteProduct")
    public ModelAndView addProduct(@PathVariable ("id") String id){
        service.deleteAProduct(id);
        return new ModelAndView("/viewAllProducts", "productList", service.getAllProducts());
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
