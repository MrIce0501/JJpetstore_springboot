package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Category;
import org.csu.mypetstore_spring.domain.Item;
import org.csu.mypetstore_spring.domain.Product;
import org.csu.mypetstore_spring.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            System.out.println(categoryId);
            Category category = catalogService.getCategory(categoryId);
            List<Product> productList = catalogService.getProductListByCategory(categoryId);

            model.addAttribute("category",category);
            model.addAttribute("productList",productList);
        }
        return "catalog/category";
    }

    @GetMapping("/catalog/product")
    public String viewProduct(@RequestParam("productId") String productId, Model model){
        if(productId!=null){
            System.out.println(productId);
            Product product = catalogService.getProduct(productId);
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            for(Item item:itemList){
                if(item.getAttribute1()==null){
                    item.setAttribute1(" ");
                }
                if(item.getAttribute2()==null){
                    item.setAttribute2(" ");
                }
                if(item.getAttribute3()==null){
                    item.setAttribute3(" ");
                }
                if(item.getAttribute4()==null){
                    item.setAttribute4(" ");
                }
                if(item.getAttribute5()==null){
                    item.setAttribute5(" ");
                }
            }
            model.addAttribute("product",product);
            model.addAttribute("itemList",itemList);
        }
        return "catalog/product";
    }

    @GetMapping("/catalog/item")
    public String viewItem(@RequestParam("itemId") String itemid,@RequestParam("productId") String productid, Model model){
        Item item=catalogService.getItem(itemid);

        if(item.getAttribute1()==null){
            item.setAttribute1(" ");
        }
        if(item.getAttribute2()==null){
            item.setAttribute2(" ");
        }
        if(item.getAttribute3()==null){
            item.setAttribute3(" ");
        }
        if(item.getAttribute4()==null){
            item.setAttribute4(" ");
        }
        if(item.getAttribute5()==null){
            item.setAttribute5(" ");
        }
        //  System.out.println("procreate");
        Product product=catalogService.getProduct(productid);

        model.addAttribute(item);
        model.addAttribute(product);

        return "/catalog/item";
    }

    @RequestMapping(value = "/catalog/SearchProducts",method = RequestMethod.POST)
    public String search(@RequestParam("keyword") String keyword,Model model){
        List<Product> productList=catalogService.searchProductList(keyword);
        model.addAttribute(productList);
        return "/catalog/SearchProducts";
    }
}
