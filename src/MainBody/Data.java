package MainBody;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Data implements Serializable {
    private String order;
    private User user;
    private Product product;
    private Boolean choiceOfServer;
    private ArrayList<Product> products;
    private Integer integer;


    public Data() {
    }

    public Data(String order, User user) {
        this.order = order;
        this.user = user;
    }

    public Data(String order, Product product) {
        this.order = order;
        this.product = product;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getChoiceOfServer() {
        return choiceOfServer;
    }

    public void setChoiceOfServer(Boolean choiceOfServer) {
        this.choiceOfServer = choiceOfServer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public String toString() {
        return "Data{" +
                "order='" + order + '\'' +
                ", user=" + user +
                ", choiceOfServer=" + choiceOfServer +
                '}';
    }
}
