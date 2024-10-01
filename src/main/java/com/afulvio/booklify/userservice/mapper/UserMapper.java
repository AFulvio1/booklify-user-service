package com.afulvio.booklify.userservice.mapper;

import com.afulvio.booklify.userservice.dto.UserDTO;
import com.afulvio.booklify.userservice.dto.request.SaveUserRequest;
import com.afulvio.booklify.userservice.dto.request.UpdateUserRequest;
import com.afulvio.booklify.userservice.model.entity.UserEntity;
import com.afulvio.booklify.userservice.util.EncryptionUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        imports = {EncryptionUtil.class, LocalDateTime.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    UserDTO entityToDTO(UserEntity userEntity);

    @Mapping(target = "password", expression = "java(EncryptionUtil.encrypt(request.getPassword()))")
    @Mapping(target = "startValidity", expression = "java(LocalDateTime.now())")
    UserEntity requestToEntity(SaveUserRequest request) throws Exception;

    @Mapping(target = "password", expression = "java(EncryptionUtil.encrypt(request.getPassword()))")
    void updateFromRequest(UpdateUserRequest request, @MappingTarget UserEntity entity) throws Exception;

}
