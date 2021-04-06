package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductsController {

    private final int SIZE_OF_PAGE = 4;

    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public ProductsController(ProductService productService, CategoryRepository categoryRepository,
                              BrandRepository brandRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("minPrice") Optional<Double> minPrice,
                           @RequestParam("maxPrice") Optional<Double> maxPrice,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("sortBy") Optional<String> sortBy,
                           @RequestParam("categoryId") Optional<Long> categoryId) {

        logger.info("Страница списка запрошена");

        Page<ProductRepr> productReprPage = productService.findWithFilter(
                categoryId.orElse(null),
                nameFilter.orElse(null),
                minPrice.orElse(null),
                maxPrice.orElse(null),
                page.orElse(1) - 1,
                SIZE_OF_PAGE,
                sortBy.filter(s -> !s.isBlank()).orElse("name")
        );
        // нумерация страниц page начинается с 0: page.orElse(1) - 1

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("products", productReprPage);

        return "categories-left-sidebar";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Страница редактирования id {} запрошена", id);
        model.addAttribute("product", productService.findById(id));
//                .orElseThrow(NotFoundException::new));
        return "product-details";
    }

}
