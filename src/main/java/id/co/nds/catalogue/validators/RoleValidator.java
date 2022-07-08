package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exception.ClientException;
import id.co.nds.catalogue.exception.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstant;

public class RoleValidator {
    public void nullCheckRoleId(String id) throws ClientException {
        if (id == null) {
            throw new ClientException("Role id is required");
        }
    }

    public void notNullCheckRoleId(String id) throws ClientException {
        if (id != null) {
            throw new ClientException("Role id is auto generated, do not input id");
        }
    }

    public void nullCheckName(String name) throws ClientException {
        if (name == null) {
            throw new ClientException("Role name is required");
        }
    }

    public void nullCheckObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("Role is not found");
        }
    }

    public void validateRoleId(String id) throws ClientException {
        if (id.startsWith("R")) {
            String number = id.substring(1);
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

    public void validateName(String name) throws ClientException {
        if (name.trim().equalsIgnoreCase("")) {
            throw new ClientException("Role name is null");
        }
    }

    public void validateRecStatus(String id, String recStatus) throws ClientException {
        if (recStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Product with id = " + id + "is already been deleted");
        }

    }
}
