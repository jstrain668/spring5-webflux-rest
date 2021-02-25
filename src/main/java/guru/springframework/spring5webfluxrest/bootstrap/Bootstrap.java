package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRespository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRespository, VendorRepository vendorRepository) {
        this.categoryRespository = categoryRespository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRespository.count().block() == 0) {
            //load data
            System.out.println("#### LOADING DATA ON BOOTSTRAP #####");
            loadCategories();
            loadVendors();
        }
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setFirstName("Fruit");
        vendor1.setLastName("Machina");
        vendorRepository.save(vendor1).block();

        Vendor vendor2 = new Vendor();
        vendor2.setFirstName("Exotica");
        vendor2.setLastName("Fruits");
        vendorRepository.save(vendor2).block();

        System.out.println("Number of Vendors Loaded: " + vendorRepository.count().block());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setDescription("Crude Fruits");

        Category dried = new Category();
        dried.setDescription("Dried Fruits");

        Category fresh = new Category();
        fresh.setDescription("Fresh Fruits");

        Category exotic = new Category();
        exotic.setDescription("Exotic");

        Category nuts = new Category();
        nuts.setDescription("Nuts");

        categoryRespository.save(fruits).block();
        categoryRespository.save(dried).block();
        categoryRespository.save(fresh).block();
        categoryRespository.save(exotic).block();
        categoryRespository.save(nuts).block();

        System.out.println("Number of Categories Loaded: " + categoryRespository.count().block());
    }
}
