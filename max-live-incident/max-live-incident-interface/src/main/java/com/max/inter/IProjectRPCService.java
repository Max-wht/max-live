package com.max.inter;

import com.max.dto.CategoryDTO;
import com.max.dto.ProjectDTO;

import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/27 16:12
 */
public interface IProjectRPCService {
    List<CategoryDTO> getCatagories();

    List<ProjectDTO> getProjectList(String projectName, String categoryType);
}
