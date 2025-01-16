package com.px.service.service.impl;

import java.util.List;
import com.px.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.px.service.mapper.InspectionSolveLogImagePestsMapper;
import com.px.service.domain.InspectionSolveLogImagePests;
import com.px.service.service.IInspectionSolveLogImagePestsService;

/**
 * 巡检问题解决日志图片-病虫害Service业务层处理
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class InspectionSolveLogImagePestsServiceImpl implements IInspectionSolveLogImagePestsService 
{
    @Autowired
    private InspectionSolveLogImagePestsMapper inspectionSolveLogImagePestsMapper;

    /**
     * 查询巡检问题解决日志图片-病虫害
     * 
     * @param id 巡检问题解决日志图片-病虫害主键
     * @return 巡检问题解决日志图片-病虫害
     */
    @Override
    public InspectionSolveLogImagePests selectInspectionSolveLogImagePestsById(Long id)
    {
        return inspectionSolveLogImagePestsMapper.selectInspectionSolveLogImagePestsById(id);
    }

    /**
     * 查询巡检问题解决日志图片-病虫害列表
     * 
     * @param inspectionSolveLogImagePests 巡检问题解决日志图片-病虫害
     * @return 巡检问题解决日志图片-病虫害
     */
    @Override
    public List<InspectionSolveLogImagePests> selectInspectionSolveLogImagePestsList(InspectionSolveLogImagePests inspectionSolveLogImagePests)
    {
        return inspectionSolveLogImagePestsMapper.selectInspectionSolveLogImagePestsList(inspectionSolveLogImagePests);
    }

    /**
     * 新增巡检问题解决日志图片-病虫害
     * 
     * @param inspectionSolveLogImagePests 巡检问题解决日志图片-病虫害
     * @return 结果
     */
    @Override
    public int insertInspectionSolveLogImagePests(InspectionSolveLogImagePests inspectionSolveLogImagePests)
    {
        inspectionSolveLogImagePests.setCreateTime(DateUtils.getNowDate());
        return inspectionSolveLogImagePestsMapper.insertInspectionSolveLogImagePests(inspectionSolveLogImagePests);
    }

    /**
     * 修改巡检问题解决日志图片-病虫害
     * 
     * @param inspectionSolveLogImagePests 巡检问题解决日志图片-病虫害
     * @return 结果
     */
    @Override
    public int updateInspectionSolveLogImagePests(InspectionSolveLogImagePests inspectionSolveLogImagePests)
    {
        return inspectionSolveLogImagePestsMapper.updateInspectionSolveLogImagePests(inspectionSolveLogImagePests);
    }

    /**
     * 批量删除巡检问题解决日志图片-病虫害
     * 
     * @param ids 需要删除的巡检问题解决日志图片-病虫害主键
     * @return 结果
     */
    @Override
    public int deleteInspectionSolveLogImagePestsByIds(Long[] ids)
    {
        return inspectionSolveLogImagePestsMapper.deleteInspectionSolveLogImagePestsByIds(ids);
    }

    /**
     * 删除巡检问题解决日志图片-病虫害信息
     * 
     * @param id 巡检问题解决日志图片-病虫害主键
     * @return 结果
     */
    @Override
    public int deleteInspectionSolveLogImagePestsById(Long id)
    {
        return inspectionSolveLogImagePestsMapper.deleteInspectionSolveLogImagePestsById(id);
    }
}
