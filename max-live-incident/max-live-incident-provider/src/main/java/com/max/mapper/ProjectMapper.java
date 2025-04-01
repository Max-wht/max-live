package com.max.mapper;

import com.max.dto.CategoryDTO;
import com.max.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/27 17:04
 */

@Mapper
public interface ProjectMapper {
    List<CategoryDTO> getCatagories();

    List<ProjectDTO> getProjectList(@Param("projectName")String projectName, @Param("categoryId")Integer categoryId);

    Integer getCategoryId(@Param("categoryType") String categoryType);

    List<ProjectDTO> getProjectListAll(@Param("projectName")String projectName);
}
