package org.csu.mypetstore_spring.persistence;

import org.csu.mypetstore_spring.domain.LineItem;
import org.csu.mypetstore_spring.domain.Sequence;

import java.util.List;

public interface LineItemMapper {
    List<LineItem> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItem lineItem);
}
