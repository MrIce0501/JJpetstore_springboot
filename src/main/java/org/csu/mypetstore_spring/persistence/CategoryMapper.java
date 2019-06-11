package org.csu.mypetstore_spring.persistence;

import org.csu.mypetstore_spring.domain.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
