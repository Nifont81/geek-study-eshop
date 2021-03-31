package ru.geekbrains.shopadminui.service;

// Data Transfer Object

import ru.geekbrains.persist.model.Product;

public class ProductDTO {

    //@JsonIgnore
    private Long id;

//    @Size(min = 3, max = 100)
    private String title;

    private String description;

//    @NotNull
//    @Min(10)
    private double price;
//    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public BigDecimal getPrice() {
//        return price;
//    }
    public double getPrice() {
        return price;
    }

//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
