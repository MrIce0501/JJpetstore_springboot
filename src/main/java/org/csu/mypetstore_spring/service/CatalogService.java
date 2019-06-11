package org.csu.mypetstore_spring.service;

import org.csu.mypetstore_spring.domain.Category;
import org.csu.mypetstore_spring.domain.Item;
import org.csu.mypetstore_spring.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CatalogService {
    List<Category> getCategoryList() ;

    Category getCategory(String categoryId) ;

    Product getProduct(String productId) ;

    List<Product> getProductListByCategory(String categoryId) ;

    List<Product> searchProductList(String keyword) ;

    List<Item> getItemListByProduct(String productId) ;

    Item getItem(String itemId) ;

    boolean isItemInStock(String itemId);
}
