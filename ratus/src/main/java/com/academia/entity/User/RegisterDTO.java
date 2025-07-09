package com.academia.entity.User;

import com.academia.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {}
