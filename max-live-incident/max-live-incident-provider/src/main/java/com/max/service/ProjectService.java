package com.max.service;
import com.max.dto.CategoryDTO;
import com.max.dto.ProjectDTO;
import com.max.mapper.ProjectMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/27 16:30
 */
@Service
public class ProjectService {

    Logger log = org.slf4j.LoggerFactory.getLogger(ProjectService.class);
    @Resource
    private ProjectMapper projectMapper;
    public List<CategoryDTO> getCatagories() {
        return projectMapper.getCatagories();
    }

    public List<ProjectDTO> getProjectList(String projectName, String categoryType) {
        if (categoryType == null) {
            return getProjectListAll(projectName);
        }
        log.info("分类的类型是"+ categoryType);
        Integer categoryId = categoryId(categoryType);
        return projectMapper.getProjectList(projectName, categoryId);
    }

    private Integer categoryId(String categoryType){
        return projectMapper.getCategoryId(categoryType);
    }
    private List<ProjectDTO> getProjectListAll(String projectName) {
        return projectMapper.getProjectListAll(projectName);
    }
}
