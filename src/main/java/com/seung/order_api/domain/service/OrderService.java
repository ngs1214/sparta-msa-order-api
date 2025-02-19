package com.seung.order_api.domain.service;

import com.seung.order_api.common.api.ServiceException;
import com.seung.order_api.common.api.ServiceExceptionCode;
import com.seung.order_api.common.service.KafkaProducerService;
import com.seung.order_api.domain.dto.OrderRequestDto;
import com.seung.order_api.domain.entity.Order;
import com.seung.order_api.domain.enums.Status;
import com.seung.order_api.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(()->new ServiceException(ServiceExceptionCode.NOT_FOUND_ORDER));
    }

    public Order order(OrderRequestDto dto) {
        Order order = Order.builder()
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .status(Status.ORDERED)
                .build();

        Order saveOrder = orderRepository.save(order);
        log.info("saveOrderId : {}", saveOrder.getId());
        kafkaProducerService.sendMessage(saveOrder.getId());

        return saveOrder;
    }

    @Transactional
    public Boolean updateStatus(Long id, Status status) {
        Order order = orderRepository.findById(id).orElseThrow(()->new ServiceException(ServiceExceptionCode.NOT_FOUND_ORDER));
        order.setStatus(status);

        kafkaProducerService.sendMessage(order.getId());
        return true;
    }


}
