package org.csu.mypetstore_spring.persistence;

import org.csu.mypetstore_spring.domain.Product;

import java.util.List;

public interface ProductMapper {
    //根据大类CategoryId来查询该类的Product
    List<Product> getProductListByCategory(String categoryId);

    //根据小类ProductId来查询product对象
    Product getProduct(String productId);

    //根据关键字查询所有符合条件的product
    List<Product> searchProductList(String keywords);
}
