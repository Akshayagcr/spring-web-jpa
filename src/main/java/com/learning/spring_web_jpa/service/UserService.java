package com.learning.spring_web_jpa.service;

import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.learning.spring_web_jpa.domain.dto.UserRequest;
import com.learning.spring_web_jpa.domain.dto.UserResponse;
import com.learning.spring_web_jpa.domain.dto.UsersResponse;
import com.learning.spring_web_jpa.domain.enums.Gender;
import com.learning.spring_web_jpa.domain.enums.SortDirection;
import com.learning.spring_web_jpa.domain.enums.SortProperty;
import com.learning.spring_web_jpa.domain.exceptions.UserNotFoundException;
import com.learning.spring_web_jpa.domain.model.UserPatchBody;
import com.learning.spring_web_jpa.mapper.UserMapper;
import com.learning.spring_web_jpa.repository.UserRepository;
import com.learning.spring_web_jpa.util.GenericUtils;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TransactionTemplate transactionTemplate;
    private final Validator validator;

    public UserResponse save(UserRequest userRequest) {
        var userEntity = UserMapper.INSTANCE.toUserEntity(userRequest);
        var savedUserEntity = transactionTemplate
                .execute( transactionStatus -> userRepository.save(userEntity));
        return UserMapper.INSTANCE.toUserResponse(savedUserEntity);
    }

    @Transactional
    public UsersResponse getAllUsers(SortProperty sortProperty, SortDirection sortDirection, int perPage, int page) {
        var order = switch(sortDirection){
            case ASC -> Sort.Order.asc(sortProperty.getSortPropertyName());
            case DESC -> Sort.Order.desc(sortProperty.getSortPropertyName());
        };
        var userPage = userRepository.findAll(
                PageRequest.of(page - 1,
                perPage,
                Sort.by(order.ignoreCase()))
        );
        return new UsersResponse(UserMapper.INSTANCE.toUserResponseList(userPage.getContent()));
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        return userRepository.fetchUserById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserResponse put(Long id, UserRequest userRequest) {
        var userEntity = transactionTemplate.execute(transactionStatus ->
                userRepository.findById(id).stream()
                .map(managedUserEntity -> {
                    managedUserEntity.setFirstName(userRequest.firstName());
                    managedUserEntity.setLastName(userRequest.lastName());
                    managedUserEntity.setEmail(userRequest.email());
                    managedUserEntity.setGender(Objects.isNull(userRequest.gender())
                            ? null : Gender.valueOf(userRequest.gender().toUpperCase()));
                    managedUserEntity.setCity(userRequest.city());
                    managedUserEntity.setCountry(userRequest.country());
                    return managedUserEntity;
                })
                .findFirst()
                .orElseGet(() -> {
                    var user = UserMapper.INSTANCE.toUserEntity(userRequest);
                    return userRepository.save(user);
                })
        );
        return UserMapper.INSTANCE.toUserResponse(userEntity);
    }

    public UserResponse patch(Long id, JsonMergePatch patch) {
        var updatedUserEntity = transactionTemplate.execute(transactionStatus -> {
            var userEntity = userRepository.findById(id)
                    .orElseThrow(UserNotFoundException::new);
            var userPatchBody = UserMapper.INSTANCE.toUserPatchBody(userEntity);
            var patchedUser = GenericUtils.applyMergePatch(patch, userPatchBody, UserPatchBody.class);
            var violations = validator.validate(patchedUser);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            UserMapper.INSTANCE.updateUserFromUserPatchBody(patchedUser, userEntity);
            return userEntity;
        });
        return UserMapper.INSTANCE.toUserResponse(updatedUserEntity);
    }

    @Transactional
    public void deleteUser(Long id) {
        var user = userRepository.findById(id)
                        .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
