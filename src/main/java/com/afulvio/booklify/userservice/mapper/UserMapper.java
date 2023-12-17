package com.afulvio.booklify.userservice.mapper;

import com.afulvio.booklify.userservice.dto.UserDto;
import com.afulvio.booklify.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User mapUserDtoToUser(UserDto userDto);

    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserDto mapUserToUserDto(User user);
}
