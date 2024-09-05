package com.ecommerce.identityservice.repository;

import com.ecommerce.identityservice.entity.UserRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    /**
     * Handle fetch lazy by EntityGraph, optimise fetch eager to avoid create a lot of query
     * @param userId String
     * @return UserRole
     */
    @EntityGraph(attributePaths = {"user"})
    UserRole findByUserId(String userId);

    /**
     * Handle fetch lazy by EntityGraph, optimise fetch eager to avoid create a lot of query
     * @param roleId String
     * @return UserRole
     */
    @Query("SELECT ur FROM UserRole ur JOIN FETCH ur.role JOIN FETCH ur.user WHERE ur.role.roleId = :roleId ")
    UserRole findByRoleId(@Param("roleId") String roleId);
}
