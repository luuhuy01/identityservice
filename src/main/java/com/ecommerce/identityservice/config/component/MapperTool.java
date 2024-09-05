package com.ecommerce.identityservice.config.component;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MapperTool {

    private final ModelMapper modelMapper;

    public <T> T map(Object source, Class<T> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public void map(Object source, Object destinationType) {
        modelMapper.map(source, destinationType);
    }

    public <T> Page<T> mapPage(Page<?> page, Class<T> destinationClass) {
        return page
                .map(o -> modelMapper.map(o, destinationClass));
    }

    public <T> List<T> mapList(List<?> sourceList, Class<T> destinationClass) {
        return sourceList
                .stream()
                .map(s -> modelMapper.map(s, destinationClass))
                .toList();
    }
}
