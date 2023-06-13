package com.suivius.rest.dto;

import com.suivius.models.CableType;
import com.suivius.models.ConnectionType;
import com.suivius.models.EquipmentType;
import com.suivius.models.SiteType;

import java.util.List;

public class ReferenceDto {

    public  List<CableType> cableTypes;
    public List<EquipmentType> equipmentTypes;
    public  List<ConnectionType> connectionTypes;
    public List<SiteType> siteTypes;

    public List<StaffDto> staff;
    public List<ArticleDto> articles;
    public List<ToolDto> tools;
}
