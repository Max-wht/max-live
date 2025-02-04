package com.max.inter;

import com.max.dto.CheckLoginDTO;
import com.max.dto.UserDTO;

public interface IUserRPCService {

    UserDTO getUserById(Long userId);

    boolean sendLoginCode(String mobile);

    CheckLoginDTO checkLoginCode(String moblie, int code);

    String createCookie(Long userId);
}
