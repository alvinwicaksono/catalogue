package id.co.nds.catalogue.controllers;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.catalogue.controllers.ControllerGroup.DeletingById;
import id.co.nds.catalogue.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.catalogue.controllers.ControllerGroup.PostingNew;
import id.co.nds.catalogue.controllers.ControllerGroup.UpdatingById;
import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.entities.UserInfoEntity;
import id.co.nds.catalogue.exception.ClientException;
import id.co.nds.catalogue.exception.NotFoundException;
import id.co.nds.catalogue.models.ResponseModel;
import id.co.nds.catalogue.models.UserModel;
import id.co.nds.catalogue.services.UserService;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postUserController(
           @Validated(PostingNew.class) @RequestBody UserModel userModel) {

        try {
            // request
            UserEntity user = userService.add(userModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setMsg("New User is successfully added");
            response.setData(user);
            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getAllUserController() {
        try {
            // request
            List<UserEntity> user = userService.findAll();

            // Response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> searchUserController(
        @Validated(GettingAllByCriteria.class) @RequestBody UserModel userModel) {
        try {
            // request
            List<UserEntity> user = userService.findAllByCriteria(userModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(user);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/get/info")
    public ResponseEntity<ResponseModel> getAllByRoleController(@RequestParam String roleName) {
        try {
            List<UserInfoEntity> user = userService.findAllByRole(roleName);

            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(user);
            return ResponseEntity.ok(response);
        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }

    }
    
    @GetMapping(value = "/get/role")
    public ResponseEntity<ResponseModel> getUsersByRoleController(@RequestParam String roleName) {
        try {
            List<UserEntity> user = userService.findUsersByRole(roleName);

            ResponseModel response = new ResponseModel();
            response.setMsg("Request successfully");
            response.setData(user);
            return ResponseEntity.ok(response);
        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }

    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseModel> getUserByIdController(@PathVariable @NotNull @PositiveOrZero Integer id) {
        try {
            // request
            UserEntity user = userService.findById(id);

            // response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(user);
            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putUserController(
           @Validated(UpdatingById.class) @RequestBody UserModel userModel) {
        try {
            // request
            UserEntity user = userService.edit(userModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setMsg("Request Successfully");
            response.setData(user);
            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseModel> deleteUserController(
            @Validated(DeletingById.class) @RequestBody UserModel userModel) {
        try {
            // request
            UserEntity user = userService.delete(userModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setMsg("User is successfully deleted");
            response.setData(user);
            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (NotFoundException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg("Sorry, there is a failure on our server");
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }

    }

}
