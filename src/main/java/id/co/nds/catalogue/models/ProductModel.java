package id.co.nds.catalogue.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import id.co.nds.catalogue.controllers.ControllerGroup.DeletingById;
import id.co.nds.catalogue.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;
import id.co.nds.catalogue.controllers.ControllerGroup.RequestMethodById;
import id.co.nds.catalogue.controllers.ControllerGroup.UpdatingById;

public class ProductModel extends RecordModel{
    @Null(message = "Product id must be null", groups = { PostingNew.class})
    @NotNull (message = "Product Id cannot be null", groups= {UpdatingById.class, DeletingById.class})
    @PositiveOrZero (message = "Product Id must not be less than 0", groups = {GettingAllByCriteria.class, RequestMethodById.class})
    private Integer id;

    @NotBlank(message = "Product name cannot be blank", groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s][a-zA-Z]+)*$", message = "Product namee pattern must be words only", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    private String name;

    @NotNull (message = "Product Quantity cannot be null", groups= {PostingNew.class})
    @PositiveOrZero (message = "Product Quantity must not be less than 0", groups = {GettingAllByCriteria.class, UpdatingById.class})
    private Integer quantity;

    @NotNull (message = "Product Category cannot be null", groups= {PostingNew.class})
    @Pattern(regexp = "^(PC)[0-9]{4}$", message = "Product category pattern must be words only", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    private String categoryId;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    
}
