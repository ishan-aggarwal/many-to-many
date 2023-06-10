package net.javaguides.springboot.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCategoryRepository extends JpaRepository<Category, Long>{

}
