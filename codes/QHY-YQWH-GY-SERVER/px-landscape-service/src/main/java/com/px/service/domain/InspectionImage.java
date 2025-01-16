package com.px.service.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.px.common.annotation.Excel;
import com.px.common.core.domain.BaseEntity;

/**
 * 巡检问题图片对象 t_inspection_image
 * 
 * @author 品讯科技
 * @date 2024-08
 */
public class InspectionImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片编号 */
    private Long id;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String url;

    /** 巡检问题id */
    @Excel(name = "巡检问题id")
    private Long inspectionId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setInspectionId(Long inspectionId) 
    {
        this.inspectionId = inspectionId;
    }

    public Long getInspectionId() 
    {
        return inspectionId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("url", getUrl())
            .append("inspectionId", getInspectionId())
            .toString();
    }
}
