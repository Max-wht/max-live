package com.max.api.controller;

import com.max.api.DTO.WebResDTO;
import com.max.dto.CategoryDTO;
import com.max.dto.ProjectDTO;
import com.max.inter.IProjectRPCService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Max
 * @description
 * @date 2025/3/27 16:09
 */

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    Logger log = LoggerFactory.getLogger(ProjectController.class);

    @DubboReference
    private IProjectRPCService projectRPCService;

    /**
     * 获取项目分类
     * @return
     */
    @RequestMapping("/categories")
    public WebResDTO getCatagories(){
        try {
            List<CategoryDTO> catagoryDTOList = projectRPCService.getCatagories();
            return WebResDTO.success(catagoryDTOList);
        } catch (Exception e){
            log.error("获取项目分类失败");
            return WebResDTO.error("获取项目分类失败");
        }
    }

    /**
     * 获取项目列表
     * @param projectName
     * @param categoryType
     * @return
     */
    @RequestMapping("/list")
    public WebResDTO getProjectList(@RequestParam(required = false) String projectName, @RequestParam(required = false) String categoryType){
        try {
            List<ProjectDTO> projectList = projectRPCService.getProjectList(projectName, categoryType);
            return WebResDTO.success(projectList);
        } catch (Exception e) {
            log.error("获取项目列表失败");
            throw new RuntimeException(e);
        }
    }
}
