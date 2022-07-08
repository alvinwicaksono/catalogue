package id.co.nds.catalogue.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.nds.catalogue.entities.UserInfoEntity;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Integer> {
    
    @Query(value = "SELECT u.*, r.name AS role_name FROM  ms_user as u " + "JOIN ms_role AS r on u.role_id = r.id " + "WHERE r.name = ?1", nativeQuery = true)
    List<UserInfoEntity> findAllByRole(String roleName);
}
