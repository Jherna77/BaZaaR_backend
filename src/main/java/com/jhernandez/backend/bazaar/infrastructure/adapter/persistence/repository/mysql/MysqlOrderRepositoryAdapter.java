package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.OrderEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.OrderEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaOrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlOrderRepositoryAdapter implements OrderRepositoryPort {

    private final JpaOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Optional<Order> saveOrder(Order order) {
        log.info("Saving order {}", order.getId());
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        return Optional.of(orderEntityMapper.toDomain(
                orderRepository.save(orderEntity)));
    }

    @Override
    public List<Order> findAllOrders() {
        log.info("Finding all orders");
        return orderRepository.findAll().stream()
            .map(orderEntityMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        log.info("Finding user with ID {}", id);
        return orderRepository.findById(id).map(orderEntityMapper::toDomain);
    }

}
