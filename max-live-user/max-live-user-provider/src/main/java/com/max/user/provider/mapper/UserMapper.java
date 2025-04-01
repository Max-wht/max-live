package com.max.user.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.dto.ClassDTO;
import com.max.dto.StudentDTO;
import com.max.user.provider.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    List<StudentDTO> queryStudents(@Param("userName") String userName,
                                   @Param("sortBy") String sortBy,
                                   @Param("pageStart") Integer pageStart,
                                   @Param("pageSize") Integer pageSize);

    int queryStudentsTotal(@Param("userName") String userName);


    List<ClassDTO> getClassMap();
}
