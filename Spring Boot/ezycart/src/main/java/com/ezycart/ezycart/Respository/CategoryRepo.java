package com.ezycart.ezycart.Respository;

import com.ezycart.ezycart.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends JpaRepository<Category, Long> {
  public Category findByName(String name);

  @Query("SELECT c FROM Category c WHERE c.name = :name AND c.parentCategory.name = :parentCategoryName"
  )
  public Category findByNameAndParent(
    @Param("name") String name,
    @Param("parentCategoryName") String parentCategoryName
  );
}
