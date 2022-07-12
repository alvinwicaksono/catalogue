package id.co.nds.catalogue.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.globals.GlobalConstant;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<UserEntity, Integer>,
JpaSpecificationExecutor<UserEntity> {
    @Query(value = "SELECT COUNT(*) FROM ms_user WHERE rec_status = '"
    + GlobalConstant.REC_STATUS_ACTIVE +
    "' AND LOWER(fullname) = LOWER(:fullname)", nativeQuery = true)
    long countByName(@Param("fullname") String fullname);

    @Query(value = "SELECT COUNT(*) FROM ms_user WHERE rec_status = '"
    + GlobalConstant.REC_STATUS_ACTIVE +
    "' AND LOWER(call_number) = LOWER(:call_number)", nativeQuery = true)
    long countByCallNumber(@Param("call_number") String callNumber);

    @Modifying
    @Query(value = "UPDATE ms_user SET rec_status = '"
    + GlobalConstant.REC_STATUS_NON_ACTIVE + "', deleter_id = ?2 , deleted_date = NOW() " + "WHERE id = ?1", nativeQuery = true)
    Integer doDelete(Integer id, Integer deleterId);    // ?2 karna deleter Id ada diposisis 2

    @Query(value = "SELECT u.*, r.name AS role_name FROM  ms_user as u " + "JOIN ms_role AS r on u.role_id = r.id " + "WHERE LOWER(r.name) = LOWER(?1)", nativeQuery = true)
    List<UserEntity> findUsersByRole(String roleName);

    @Query(value = "SELECT u.role_id FROM ms_user AS u WHERE u.id = ?1", nativeQuery = true)
    String getUserRoleById(@Param("id") Integer id);
}
