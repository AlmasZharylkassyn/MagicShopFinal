package MainBody;

import java.io.Serializable;

public class Product implements Serializable {

    private Long id;
    private String name;
    private Integer amount;
    private Integer price;

    public Product() {
    }

    public Product(Long id, String name, Integer amount, Integer price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }

    public String getStringData() {
        return "ID: " + id + ", name: " + name + ", amount: " + amount + ", price: " + price;
    }
}
