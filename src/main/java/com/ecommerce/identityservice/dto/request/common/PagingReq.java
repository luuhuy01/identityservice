package com.ecommerce.identityservice.dto.request.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PagingReq {
    public static final Integer DEFAULT_PAGE_NUMBER = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    private Integer pageNumber = DEFAULT_PAGE_NUMBER;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    /**
     * uri = ...&sorts=CreatedAt,ASC,UpdatedAt,ASC...
     * OR
     * uri = ...&sorts=CreatedAt,ASC&sorts=UpdatedAt,ASC
     */
    public String sorts;
    private LinkedHashMap<String, Sort.Direction> sortDirectionMaps = new LinkedHashMap<>();

    @JsonIgnore
    public Pageable buildPageable() {
        if (CollectionUtils.isEmpty(sortDirectionMaps) && StringUtils.isNotEmpty(sorts)) {
            List<String> sortList = Arrays.asList(sorts.split(","));
            for (int i = 0; i < sortList.size() - 1; i += 2) {
                sortDirectionMaps.put(sortList.get(i), Sort.Direction.valueOf(sortList.get(i + 1)));
            }
        }
        List<Sort.Order> orders = new ArrayList<>();
        for (Map.Entry<String, Sort.Direction> entry : sortDirectionMaps.entrySet()) {
            String property = entry.getKey();
            Sort.Direction direction = entry.getValue();
            orders.add(new Sort.Order(direction, property));
        }
        return PageRequest.of(pageNumber - 1, pageSize, Sort.by(orders));
    }
}
