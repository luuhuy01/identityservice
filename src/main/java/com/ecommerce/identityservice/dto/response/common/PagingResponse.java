package com.ecommerce.identityservice.dto.response.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagingResponse {
    private Integer pageNumber;
    private Integer pageSize;
    private Long total;
    private List<?> data;

    public PagingResponse(Page<?> page) {
        this.pageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.data = page.getContent();
        this.total = page.getTotalElements();
    }

    public static PagingResponse of(Page<?> page) {
        return new PagingResponse(page);
    }
}
