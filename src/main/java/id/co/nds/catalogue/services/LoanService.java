package id.co.nds.catalogue.services;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.LoanEntity;
import id.co.nds.catalogue.exception.NotFoundException;
import id.co.nds.catalogue.models.LoanModel;
import id.co.nds.catalogue.repos.LoanRepo;
import id.co.nds.catalogue.repos.UserRepo;
import id.co.nds.catalogue.validators.LoanValidator;
import id.co.nds.catalogue.validators.UserValidator;

@Service
public class LoanService implements Serializable {
    @Autowired
    public LoanRepo loanRepo;

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public UserService userService;

    public LoanValidator loanValidator = new LoanValidator();
    public UserValidator userValidator = new UserValidator();

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public LoanEntity doLoan(LoanModel loanModel) throws Exception {
        if (!userRepo.existsById(loanModel.getUserId())) {
            throw new NotFoundException("Cannot find User with id: " + loanModel.getUserId());
        }

        LoanEntity loan = new LoanEntity();
        loan.setUserId(loanModel.getUserId());
        loan.setRoleId(userService.getUserRoleById(loanModel.getUserId()));
        loan.setLoanAmount(loanModel.getLoanAmount());
        loan.setLoanTerm(loanModel.getLoanTerm());
        loan.setInterestRate(loanModel.getInterestRate());
        loan.setTotalLoan(loanModel.getLoanAmount() / loanModel.getLoanTerm() * loanModel.getInterestRate() / 100);
        loan.setCustomerName(loanModel.getCustomerName().trim());
        loan.setStartDate(new Timestamp(System.currentTimeMillis()));
        
        return loanRepo.save(loan);
    }

}