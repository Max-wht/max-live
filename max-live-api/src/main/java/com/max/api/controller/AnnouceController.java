package com.max.api.controller;

import com.max.api.DTO.WebResDTO;
import com.max.api.VO.AnnounceVO;
import com.max.dto.AnnounceDTO;
import com.max.inter.IAnnounceRPCService;
import com.max.live.page.PageBean;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * @author Max
 * @description
 * @date 2025/3/10 18:42
 */
@RestController
@RequestMapping("/notice")
@CrossOrigin
public class AnnouceController {
    Logger log = org.slf4j.LoggerFactory.getLogger(AnnouceController.class);

    @DubboReference
    private IAnnounceRPCService announceService;

    /**
     * 将 AnnounceDTO 转换为 AnnounceVO
     * @param dto
     * @return
     */
    private AnnounceVO convertToVO(AnnounceDTO dto) {
        AnnounceVO vo = new AnnounceVO();
        vo.setId(dto.getId());
        vo.setName(dto.getName());
        vo.setContent(dto.getContent());
        vo.setCreateTime(dto.getCreateTime());
        return vo;
    }

    /**
     * 查询
     * @param announceParam
     * @return
     */
    @RequestMapping("/Announcements")
    public WebResDTO getAnnounceList(@RequestBody AnnounceDTO announceParam){
        try {
            PageBean<AnnounceDTO> pageBean = announceService.getAnnounceList(announceParam);

            // 转换 PageBean 中的数据列表
            PageBean<AnnounceVO> page = new PageBean<>();
            page.setRow(pageBean.getRow().stream()
                    .map(this::convertToVO)
                    .collect(Collectors.toList()));
//            page.setTotal(pageBean.getTotal());

            return WebResDTO.success(page);
        } catch (Exception e) {
            log.error("查询失败", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * 修改公告
     * @param announceDTO
     * @return
     */
    @RequestMapping("/update")
    public WebResDTO updateAnnounce(@RequestBody AnnounceDTO announceDTO){
        try {
            announceService.updateAnnounce(announceDTO);
            return WebResDTO.success();
        } catch (Exception e) {
            log.error("修改失败", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * 公告删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public WebResDTO deleteAnnounce(@RequestParam("id") Integer id){
        log.info("删除公告id:{}", id);
        try {
            announceService.batchDelete(id);
            return WebResDTO.success();
        } catch (Exception e) {
            log.error("删除失败", e);
            return WebResDTO.error("删除失败");
        }
    }
    /**
     * 公告保存
     * @param announceDTO
     * @return
     */
    @PostMapping("/save")
    public WebResDTO save(@RequestBody AnnounceDTO announceDTO){
        log.info("保存公告:{}", announceDTO);
        try {
            announceService.save(announceDTO);
            return WebResDTO.success();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
