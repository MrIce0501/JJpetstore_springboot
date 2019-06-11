package org.csu.mypetstore_spring.service.impl;

import org.csu.mypetstore_spring.domain.Category;
import org.csu.mypetstore_spring.domain.Item;
import org.csu.mypetstore_spring.domain.Product;
import org.csu.mypetstore_spring.persistence.CategoryMapper;
import org.csu.mypetstore_spring.persistence.ProductMapper;
import org.csu.mypetstore_spring.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public Category getCategory(String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    @Override
    public Product getProduct(String productId) {
        return productMapper.getProduct(productId);
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        return productMapper.getProductListByCategory(categoryId);
    }

    @Override
    public List<Product> searchProductList(String keyword) {
        return productMapper.searchProductList("%"+ keyword +"%" );
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        return null;
    }

    @Override
    public Item getItem(String itemId) {
        return null;
    }

    @Override
    public boolean isItemInStock(String itemId) {
        return false;
    }
}
