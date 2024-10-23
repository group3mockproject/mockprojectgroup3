package com.example.apartmentmanagement.service.permission;
import com.example.apartmentmanagement.dto.request.AppUserRequest;
import com.example.apartmentmanagement.dto.respone.AuthenticationResponse;
import com.example.apartmentmanagement.entities.permission.AppUser;
import com.example.apartmentmanagement.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService extends IGeneralService<AppUser> {
    AppUser findByEmail(String email);
    AppUser findByUserCode(String userCode);
    Boolean existsByEmail(String email);

    Page<AppUser> searchAllCustomerByUserCodeOrFullName(String userCode, String fullName, Pageable pageable);

    Page<AppUser> searchAllEmployeeByUserCodeOrFullName(String userCode, String fullName, Pageable pageable);

    /**
     * Disable an entity by its ID.
     *
     * @param id the ID of the entity to disable
     */
    void disableUser(Long id);

    /**
     * Enable an entity by its ID.
     *
     * @param id the ID of the entity to enable
     */
    void enableUser(Long id);

    /**
     * Saves a new user based on the provided {@link AppUserRequest}.
     *
     * @param appUserRequest the request object containing user details to be saved
     * @return an {@link AuthenticationResponse} indicating the outcome of the save operation
     */
    AuthenticationResponse saveUser(AppUserRequest appUserRequest);

    /**
     * Update user based on the provided {@link AppUserRequest}.
     *
     * @param userId the id to find user details to be updated
     * @param appUserRequest the request object containing user details to be updated
     * @return an {@link AuthenticationResponse} indicating the outcome of the update operation
     */
    AuthenticationResponse updateUser(Long userId, AppUserRequest appUserRequest);

}
