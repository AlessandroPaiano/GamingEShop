package com.alessandropaiano.gamingeshop.dto;

public class OrderItemDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private double itemPrice;

    public OrderItemDTO() {}


    public OrderItemDTO(Long id, Long productId, int quantity, double itemPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }


}
