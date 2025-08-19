package com.learning.spring_web_jpa.mapper;

import com.learning.spring_web_jpa.domain.dto.UserRequest;
import com.learning.spring_web_jpa.domain.dto.UserResponse;
import com.learning.spring_web_jpa.domain.entity.User;
import com.learning.spring_web_jpa.domain.model.UserPatchBody;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUserEntity(UserRequest userRequest);

    UserResponse toUserResponse(User user);

    UserPatchBody toUserPatchBody(User user);

    void updateUserFromUserPatchBody(UserPatchBody userPatchBody, @MappingTarget User user);

    List<UserResponse> toUserResponseList(List<User> userList);
}
