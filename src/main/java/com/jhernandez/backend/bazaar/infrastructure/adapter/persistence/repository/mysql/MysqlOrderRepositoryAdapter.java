package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Order;
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

    // @Transactional
    // @Override
    // public Optional<Order> saveOrder(Order order) {
    //     log.info("Saving order {}", order.getId());
    //     OrderEntity orderEntity = orderEntityMapper.toEntity(order);
    //     return Optional.of(orderEntityMapper.toDomain(
    //             orderRepository.save(orderEntity)));
    // }

    // @Transactional(readOnly = true)
    // @Override
    // public List<Order> findAllOrders() {
    //     log.info("Finding all orders");
    //     return orderRepository.findAll().stream()
    //         .map(orderEntityMapper::toDomain)
    //         .collect(Collectors.toList());
    // }

    @Transactional(readOnly = true)
    @Override
    public Optional<Order> findOrderById(Long id) {
        log.info("Finding order with ID {}", id);
        return orderRepository.findById(id).map(orderEntityMapper::toDomain);
    }

    // @Transactional(readOnly = true)
    // @Override
    // public Boolean existsById(Long id) {
    //     log.info("Checking if order with ID {} exists", id);
    //     return orderRepository.existsById(id);
    // }

    // @Transactional
    // @Override
    // public void deleteOrderById(Long id) {
    //     log.info("Deleting order with ID {}", id);
    //     orderRepository.deleteById(id);
    // }

}
