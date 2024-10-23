package com.example.apartmentmanagement.repository.permission;

import com.example.apartmentmanagement.entities.permission.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
    Boolean existsByEmail(String email);
    @Query(value = "select * from app_users where user_code=:userCode",nativeQuery = true)
    AppUser findByUserCode(String userCode);

    @Query(value = "SELECT u from AppUser u JOIN u.userRoles r WHERE r.appRole.roleId = 4")
    List<AppUser> findAllByRoleCustomer();

    @Query(value = "SELECT u from AppUser u JOIN u.userRoles r WHERE r.appRole.roleId != 4")
    List<AppUser> findAllByRoleEmployee();

    @Query("SELECT DISTINCT u FROM AppUser u JOIN u.userRoles r " +
            "WHERE (u.userCode LIKE %:userCode% or u.fullName LIKE %:fullName%) AND r.appRole.roleId = 4")
    Page<AppUser> searchAllByUserCodeOrFullNameAndRoleId(@Param("userCode") String userCode, @Param("fullName") String fullName, Pageable pageable);

    @Query("SELECT DISTINCT u FROM AppUser u JOIN u.userRoles r " +
            "WHERE (u.userCode LIKE %:userCode% or u.fullName LIKE %:fullName%) AND r.appRole.roleId != 4")
    Page<AppUser> searchAllEmployeeByUserCodeOrFullNameAndRoleId(@Param("userCode") String userCode, @Param("fullName") String fullName, Pageable pageable);

    /**
     * Disable an AppUser entity by user ID.
     * <p>
     * @param userId the user ID to disable
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE app_users SET account_non_expired = 0, account_non_locked = 0, credentials_non_expired = 0, " +
            "enabled = 0 WHERE user_id = :userId", nativeQuery = true)
    void disableUser(@Param("userId") Long userId);


    /**
     * Enable an AppUser entity by user ID.
     * <p>
     * @param userId the user ID to enable
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE app_users SET account_non_expired = 1, account_non_locked = 1, credentials_non_expired = 1, " +
            "enabled = 1 WHERE user_id = :userId", nativeQuery = true)
    void enableUser(@Param("userId") Long userId);


    /**
     * Updates background image and avatar for AppUser entity in the database.
     * <p>
     * @param avatar               the updated avatar URL of the AppUser
     * @param userId               the user ID of the AppUser to update
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE app_users set avatar = :avatar WHERE user_id = :userId", nativeQuery = true)
    void updateAvatarImage( @Param("avatar") String avatar, @Param("userId") Long userId);

    Boolean existsByUserCode(String newUserCode);

}