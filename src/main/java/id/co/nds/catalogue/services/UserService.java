package id.co.nds.catalogue.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.entities.UserInfoEntity;
import id.co.nds.catalogue.exception.ClientException;
import id.co.nds.catalogue.exception.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstant;
import id.co.nds.catalogue.models.UserModel;
import id.co.nds.catalogue.repos.UserInfoRepo;
import id.co.nds.catalogue.repos.UserRepo;
import id.co.nds.catalogue.specs.UserSpec;
import id.co.nds.catalogue.validators.RoleValidator;
import id.co.nds.catalogue.validators.UserValidator;

@Service
public class UserService implements Serializable {
    @Autowired
    private UserRepo userRepo;

    @Autowired UserInfoRepo userInfoRepo;

     UserValidator userValidator = new UserValidator();
     RoleValidator roleValidator = new RoleValidator();

    public UserEntity add(UserModel userModel) throws ClientException {
        // validation
        // userValidator.notNullCheckUserId(userModel.getId());
        // userValidator.nullCheckFullname(userModel.getFullname());
        // userValidator.validateFullname(userModel.getFullname());
        // userValidator.nullCheckRoleId(userModel.getRoleId());
        // userValidator.validateRoleId(userModel.getRoleId());
        // userValidator.validateCallNumber(userModel.getCallNumber());
        // userValidator.nullCheckCreatorId(userModel.getActorId());

        if (userModel.getCallNumber() != null) {
            Long count = userRepo.countByCallNumber(userModel.getCallNumber());
            if (count > 0) {
                throw new ClientException("Call Number is already Existed");
            }
        }

        // process
        UserEntity user = new UserEntity();
        user.setFullname(userModel.getFullname());
        user.setRoleId(userModel.getRoleId());
        user.setCallNumber(userModel.getCallNumber());
        user.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        user.setCreatorId(userModel.getActorId() == null ? 0 : userModel.getActorId());

        return userRepo.save(user);
    }

    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);

        return users;
    }

    public List<UserEntity> findAllByCriteria(UserModel userModel) {
        List<UserEntity> users = new ArrayList<>();
        UserSpec specs = new UserSpec(userModel);
        userRepo.findAll(specs).forEach(users::add);

        return users;
    }

    public List<UserInfoEntity> findAllByRole(String roleName)
    throws ClientException, NotFoundException {
        //validation
        roleValidator.nullCheckName(roleName);
        roleValidator.validateName(roleName);

        //process
        List<UserInfoEntity> user = userInfoRepo.findAllByRole(roleName);
        roleValidator.nullCheckObject(user);

        return user;
    }


    public UserEntity findById(Integer id) throws ClientException, NotFoundException {

        // validation
        // userValidator.nullCheckUserId(id);
        // userValidator.validateUserId(id);

        // process
        UserEntity user = userRepo.findById(id).orElse(null);
        userValidator.nullCheckObject(user);

        return user;
    }

    public UserEntity edit(UserModel userModel)
            throws ClientException, NotFoundException {

        // validation
        // userValidator.nullCheckUserId(userModel.getId());
        // userValidator.validateUserId(userModel.getId());

        if (!userRepo.existsById(userModel.getId())) {
            throw new NotFoundException("Cannot find user with id " + userModel.getId());
        }

        // process
        UserEntity user = new UserEntity();
        user = findById(userModel.getId());

        if (userModel.getFullname() != null) {
            // userValidator.validateFullname(userModel.getFullname());

            user.setFullname(userModel.getFullname());
        }

        if (userModel.getRoleId() != null) {
            // userValidator.validateRoleId(userModel.getRoleId());
            user.setRoleId(userModel.getRoleId());
        }

        if (userModel.getCallNumber() != null) {
            // userValidator.validateCallNumber(userModel.getCallNumber());
            user.setCallNumber(userModel.getCallNumber());
        }

        user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        user.setUpdaterId(userModel.getActorId() == null ? 0 : userModel.getActorId());

        return userRepo.save(user);
    }

    public UserEntity delete(UserModel userModel)
            throws ClientException, NotFoundException {
        // Validation
        // userValidator.nullCheckUserId(userModel.getId());
        // userValidator.validateUserId(userModel.getId());

        if (!userRepo.existsById(userModel.getId())) {
            throw new NotFoundException("Cannot find User with id" + userModel.getId());
        }

        // process
        UserEntity user = new UserEntity();
        user = findById(userModel.getId());

        if (user.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("User id (" + userModel.getId() + ") is already been deleted.");
        }

        user.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        user.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        user.setDeleterId(userModel.getActorId() == null ? 0 : userModel.getActorId());

        userRepo.doDelete(user.getId(), user.getDeleterId());
        return user;

        
    }

}
