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

public class UserModel extends RecordModel {
    
    @Null(message = "User id must be null", groups = { PostingNew.class})
    @NotNull (message = "User Id cannot be null", groups= {UpdatingById.class, DeletingById.class})
    @PositiveOrZero (message = "User Id must not be less than 0", groups = {GettingAllByCriteria.class, RequestMethodById.class})
    private Integer id;

    @NotBlank(message = "Fullname cannot be blank", groups = {PostingNew.class})
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s][a-zA-Z]+)*$", message = "Fullname pattern must be words only", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    private String fullname;

    @NotNull (message = "User Role cannot be null", groups= {PostingNew.class})
    @Pattern(regexp = "^(R)[0-9]{4}$", message = "User Role pattern must be words only", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class})
    private String roleId;

    @NotNull (message = "User Role cannot be null", groups= {PostingNew.class})
    @Pattern(regexp = "^(\\+62|0)[0-9]{9,12}$", message = "Call Numbers must start with 0 or + 62 and followed by 9-12 digits number", groups = {PostingNew.class, GettingAllByCriteria.class, UpdatingById.class}) 
    private String callNumber;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getCallNumber() {
        return callNumber;
    }
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    
}
