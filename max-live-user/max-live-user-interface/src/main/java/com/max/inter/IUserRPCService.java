package com.max.inter;

import com.max.dto.CheckLoginDTO;
import com.max.dto.StudentDTO;
import com.max.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface IUserRPCService {

    UserDTO getUserById(Long userId);

    boolean sendLoginCode(String mobile);

    CheckLoginDTO checkLoginCode(String moblie, int code);

    String createCookies(Long userId);

    Boolean checkToken(String token);

    List<StudentDTO> queryStudents(String userName, String sortBy, Integer page, Integer pageSize);

    int queryStudentsTotal(String userName);

    Map<Integer, String> getClassMap();
}
