package az.azerconnect.data.mappers

import az.azerconnect.data.models.dto.UserDto
import az.azerconnect.domain.models.UserModel


fun UserModel.toDto() = UserDto(
    fullName = "$firstName $lastName"
)