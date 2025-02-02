package com.max.inter;

import com.max.dto.UserDTO;

public interface IUserRPCService {

    UserDTO getUserById(Long userId);

    boolean sendLoginCode(String mobile);
}
