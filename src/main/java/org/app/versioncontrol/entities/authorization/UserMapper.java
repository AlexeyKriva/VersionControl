package org.app.versioncontrol.entities.authorization;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANSE = Mappers.getMapper(UserMapper.class);
    User fromUserAddDtoToUser(UserAddDto userAddDto);
}
