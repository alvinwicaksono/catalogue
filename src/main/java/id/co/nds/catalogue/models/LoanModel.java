package id.co.nds.catalogue.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;

public class LoanModel {
    @Null(message = "User id must be null", groups = { PostingNew.class})
    @PositiveOrZero (message = "User Id must not be less than 0", groups = {PostingNew.class})
    private String id;

    @NotNull (message = "User Id cannot be null", groups= {PostingNew.class})
    @PositiveOrZero (message = "User Id must not be less than 0", groups = {PostingNew.class})
    private Integer userId;

    @Null (message = "User Role must be null", groups= {PostingNew.class})
    @Pattern(regexp = "^(R)[0-9]{4}$", message = "User Role pattern must be words only", groups = {PostingNew.class})
    private String roleId;

    @NotNull (message = "Loan Amount cannot be null", groups= {PostingNew.class})
    @Positive (message = "loan amount must be positive integer", groups= {PostingNew.class})
    private Double loanAmount;

    @NotNull (message = "Loan Term cannot be null", groups= {PostingNew.class})
    @Positive (message = "loan Term must be positive integer", groups= {PostingNew.class})
    private Integer loanTerm;

    @NotNull (message = "Interest Rate cannot be null", groups= {PostingNew.class})
    @Positive (message = "Interest Rate must be positive integer", groups= {PostingNew.class})
    private Double interestRate;

    @NotBlank (message = "Customer name cannot be Blank", groups= {PostingNew.class})
    @Pattern(regexp = "^\\S+$", message = "Customer name cannot using whitespaces", groups= {PostingNew.class}) 
    private String customerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
