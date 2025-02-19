package com.seung.order_api.domain.controller;

import com.seung.order_api.common.api.ApiResponse;
import com.seung.order_api.domain.dto.OrderRequestDto;
import com.seung.order_api.domain.entity.Order;
import com.seung.order_api.domain.enums.Status;
import com.seung.order_api.domain.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
@Tag(name = "Order API", description = "주문 관련 API")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "주문리스트조회", description = "주문리스트조회")
    public ApiResponse<List<Order>> orderList() {
        return ApiResponse.success(orderService.findAll());
    }

    @GetMapping("{id}")
    public ApiResponse<Order> getOrder(@PathVariable("id") Long id) {
        return ApiResponse.success(orderService.getOrder(id));
    }

    @PostMapping
    public ApiResponse<Order> order(@RequestBody OrderRequestDto dto) {
        return ApiResponse.success(orderService.order(dto));
    }

    @PatchMapping("{id}")
    public ApiResponse<Boolean> updateStatus(@PathVariable("id") Long id, @RequestParam("status") Status status) {
        return ApiResponse.success(orderService.updateStatus(id, status));
    }
}
