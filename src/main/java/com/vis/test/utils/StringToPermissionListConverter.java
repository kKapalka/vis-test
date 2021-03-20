package com.vis.test.utils;

import com.vis.test.model.Permission;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringToPermissionListConverter implements AttributeConverter<List<Permission>, String> {

    @Override
    public String convertToDatabaseColumn(List<Permission> list) {
        if(list == null) return "";
        return list.stream().map(Permission::name).collect(Collectors.joining(";"));
    }

    @Override
    public List<Permission> convertToEntityAttribute(String joined) {
        if(joined == null) return new ArrayList<>();
        return Arrays.stream(joined.split(";")).map(Permission::valueOf).collect(Collectors.toList());
    }
}