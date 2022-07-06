package id.co.nds.catalogue.validators;

import java.sql.Timestamp;

import id.co.nds.catalogue.exception.ClientException;
import id.co.nds.catalogue.globals.GlobalConstant;

public class UserValidator {
    public void nullCheckUserId(Integer id) throws ClientException {
        if (id == null) {
            throw new ClientException("User id is required");
        }
    }

    public void notNullCheckUserId(Integer id) throws ClientException {
        if (id != null) {
            throw new ClientException("User id is auto generated, do not input id");
        }
    }

    public void nullCheckFullname(String fullname) throws ClientException {
        if (fullname == null) {
            throw new ClientException("Fullname is required");
        }
    }

    public void nullCheckRoleId(String roleId) throws ClientException {
        if (roleId == null) {
            throw new ClientException("Role id is required");
        }
    }

    public void nullCheckCreatedDate(Timestamp createdDate) throws ClientException {
        if (createdDate == null) {
            throw new ClientException("Creator date is required");
        }
    }

    public void nullCheckCreatorId(Integer creatorId) throws ClientException {
        if (creatorId == null) {
            throw new ClientException("Creator id is required");
        }
    }

    public void nullCheckObject(Object o) throws ClientException {
        if (o == null) {
            throw new ClientException("User is not found");
        }
    }

    public void validateUserId(Integer id) throws ClientException {
        if (id <= 0) {
            throw new ClientException("User id input is invalid");
        }
    }

    public void validateFullname(String fullname) throws ClientException {
        if (fullname.trim().equalsIgnoreCase("")) {
            throw new ClientException("Product name is required");
        }
    }

    public void validateRoleId(String roleId) throws ClientException {
        if (roleId.startsWith("R")) {
            String number = roleId.substring(1);
            try {
                Long.parseLong(number);
            } catch (NumberFormatException e) {
                throw new ClientException("Role id must be number");
            }
            if (number.length() != 4) {
                throw new ClientException("role id must followed by 4 number");

            }

        } else {
            throw new ClientException("Role id must starts with 'R' ");

        }
    }

    public void validateCallNumber(String callNumber) throws ClientException {
        if (callNumber.startsWith("+62") || callNumber.startsWith("0")) {

            if (callNumber.startsWith("+62")) {
                try {
                    Long.parseLong(callNumber.substring(3));
                } catch (NumberFormatException e) {
                    throw new ClientException("Call number must be number");
                }

                if (callNumber.substring(3).length() < 9 && callNumber.substring(3).length() > 12) {
                    throw new ClientException("Call Number must followed by 9-12 number ");
                }

                
            } else if (callNumber.startsWith("0")) {
                try {
                    Long.parseLong(callNumber.substring(1));
                } catch (NumberFormatException e) {
                    throw new ClientException("Call number must be number");
                }
                if (callNumber.substring(1).length() < 9 || callNumber.substring(1).length() > 12) {
                    throw new ClientException(
                            "Call Number must followed by 9-12 number " + callNumber.substring(1).length());
                }

                

            }

        } else {
            throw new ClientException("Call Number must starts with '+62' or '0' ");
        }
    }

    public void validateRecStatus(String id, String recStatus) throws ClientException {
        if (recStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("User with id = " + id + "is already been deleted");
        }

    }

}
