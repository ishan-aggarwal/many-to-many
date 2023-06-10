package net.javaguides.springboot;

import net.javaguides.springboot.stock.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AppManyToManyWithExtraColumn implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockCategoryRepository stockCategoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(AppManyToManyWithExtraColumn.class, args);
    }

    @Override
    public void run(String... args) {
		System.out.println("Hibernate many to many - join table + extra column (Annotation)");

		Stock stock = new Stock();
        stock.setStockCode("7052");
        stock.setStockName("PADINI");
 
        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("CONSUMER2", "CONSUMER COMPANY2");
        //new category, need save to get the id first
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        // create StockCategory object
        StockCategory stockCategory1 = new StockCategory();
        
        stockCategory1.setStock(stock);
        stockCategory1.setCategory(category1);
        // extra columns in join table
        stockCategory1.setCreatedDate(new Date());
        stockCategory1.setCreatedBy("system-1");

        // add stockCategory1 to stock
        stock.getStockCategories().add(stockCategory1);

        // create StockCategory object
        StockCategory stockCategory2 = new StockCategory();

        stockCategory2.setStock(stock);
        stockCategory2.setCategory(category2);
        stockCategory2.setCreatedDate(new Date());
        stockCategory2.setCreatedBy("system-2");

        // add stockCategory2 to stock
        stock.getStockCategories().add(stockCategory2);
        
        stockRepository.save(stock);

        Set<StockCategory> stockCategories = stock.getStockCategories();
        List<Category> categories = stockCategories.stream()
                .map(sc -> sc.getCategory())
                .toList();

        System.out.println(categories.size());
        System.out.println(categories);

        System.out.println("Done");
	}
}
