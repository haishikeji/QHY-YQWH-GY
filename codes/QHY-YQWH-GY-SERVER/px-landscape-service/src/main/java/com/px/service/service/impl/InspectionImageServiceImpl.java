package com.px.service.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.px.service.mapper.InspectionImageMapper;
import com.px.service.domain.InspectionImage;
import com.px.service.service.IInspectionImageService;

/**
 * 巡检问题图片Service业务层处理
 * 
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class InspectionImageServiceImpl implements IInspectionImageService 
{
    @Autowired
    private InspectionImageMapper inspectionImageMapper;

    /**
     * 查询巡检问题图片
     * 
     * @param id 巡检问题图片主键
     * @return 巡检问题图片
     */
    @Override
    public InspectionImage selectInspectionImageById(Long id)
    {
        return inspectionImageMapper.selectInspectionImageById(id);
    }

    /**
     * 查询巡检问题图片列表
     * 
     * @param inspectionImage 巡检问题图片
     * @return 巡检问题图片
     */
    @Override
    public List<InspectionImage> selectInspectionImageList(InspectionImage inspectionImage)
    {
        return inspectionImageMapper.selectInspectionImageList(inspectionImage);
    }

    /**
     * 新增巡检问题图片
     * 
     * @param inspectionImage 巡检问题图片
     * @return 结果
     */
    @Override
    public int insertInspectionImage(InspectionImage inspectionImage)
    {
        return inspectionImageMapper.insertInspectionImage(inspectionImage);
    }

    /**
     * 修改巡检问题图片
     * 
     * @param inspectionImage 巡检问题图片
     * @return 结果
     */
    @Override
    public int updateInspectionImage(InspectionImage inspectionImage)
    {
        return inspectionImageMapper.updateInspectionImage(inspectionImage);
    }

    /**
     * 批量删除巡检问题图片
     * 
     * @param ids 需要删除的巡检问题图片主键
     * @return 结果
     */
    @Override
    public int deleteInspectionImageByIds(Long[] ids)
    {
        return inspectionImageMapper.deleteInspectionImageByIds(ids);
    }

    /**
     * 删除巡检问题图片信息
     * 
     * @param id 巡检问题图片主键
     * @return 结果
     */
    @Override
    public int deleteInspectionImageById(Long id)
    {
        return inspectionImageMapper.deleteInspectionImageById(id);
    }
}
