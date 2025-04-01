package com.max.impl;

import com.max.dto.CategoryDTO;
import com.max.dto.ProjectDTO;
import com.max.inter.IProjectRPCService;
import com.max.service.ProjectService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/27 16:28
 */
@DubboService
public class ProjectRPCService implements IProjectRPCService {

    @Resource
    private ProjectService projectService;

    @Override
    public List<CategoryDTO> getCatagories() {
        return projectService.getCatagories();
    }

    @Override
    public List<ProjectDTO> getProjectList(String projectName, String categoryType) {
        return projectService.getProjectList(projectName, categoryType);
    }
}
