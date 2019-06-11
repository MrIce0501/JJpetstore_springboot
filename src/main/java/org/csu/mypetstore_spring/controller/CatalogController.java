package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Category;
import org.csu.mypetstore_spring.domain.Product;
import org.csu.mypetstore_spring.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog/main")
    public String viewMain(){
        return "catalog/main";
    }

    //如何从客户端网页获取值，@RequstParam;如何将服务端控制器中的值传递给客户端网页，Model
    @GetMapping("/catalog/category")
    public String viewCategory(@RequestParam("categoryId") String categoryId, Model model){
        if(categoryId!=null){
            Category category = catalogService.getCategory(categoryId);
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            model.addAttribute("category",category);
            model.addAttribute("productList",productList);
        }

        return "catalog/category";
    }

}
