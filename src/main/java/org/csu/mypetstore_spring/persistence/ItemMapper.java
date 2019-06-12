package org.csu.mypetstore_spring.persistence;

import org.csu.mypetstore_spring.domain.Item;

import java.util.List;
import java.util.Map;

public interface ItemMapper {
    //更新产品数量
    void updateInventoryQuantity(Map<String, Object> param);

    //获取产品数量
    int getInventoryQuantity(String itemId);

    //根据productId获取ItemList
    List<Item> getItemListByProduct(String productId);

    //根据ItemId获取Item
    Item getItem(String itemId);
}
