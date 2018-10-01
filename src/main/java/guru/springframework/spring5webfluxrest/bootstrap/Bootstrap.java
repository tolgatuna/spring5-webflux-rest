package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    VendorRepository vendorRepository;
    CategoryRepository categoryRepository;

    public Bootstrap(VendorRepository vendorRepository, CategoryRepository categoryRepository) {
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadVendors();
    }

    private void loadVendors() {
        if(vendorRepository.count().block() != 0) {
            log.info(" ## -- Vendors Count : " + vendorRepository.count().block());
            return;
        }

        Vendor vendor1 = new Vendor();
        vendor1.setFirstName("Tolga");
        vendor1.setLastName("TUNA");

        Vendor vendor2 = new Vendor();
        vendor2.setFirstName("Cem");
        vendor2.setLastName("YENICERI");

        vendorRepository.save(vendor1).block();
        vendorRepository.save(vendor2).block();
        System.out.println("Vendors Loaded!    Count : " + vendorRepository.count().block());
    }

    private void loadCategories() {
        if(categoryRepository.count().block() != 0) {
            log.info(" ## -- Categories Count : " + categoryRepository.count().block());
            return;
        }
        Category category1 = new Category();
        category1.setDescription("Fruits");

        Category category2 = new Category();
        category2.setDescription("Vegetables");

        Category category3 = new Category();
        category3.setDescription("Meats");

        categoryRepository.save(category1).block();
        categoryRepository.save(category2).block();
        categoryRepository.save(category3).block();

        System.out.println("Categories Loaded  Count : " + categoryRepository.count().block());
    }
}
