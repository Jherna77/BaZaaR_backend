package com.jhernandez.backend.bazaar.application.service;

import java.time.LocalDateTime;
import java.util.List;

import com.jhernandez.backend.bazaar.application.port.ItemRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.MessageRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.OrderServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.Item;
import com.jhernandez.backend.bazaar.domain.model.Message;
import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.domain.model.OrderStatus;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.User;

public class OrderService implements OrderServicePort {

    private final UserRepositoryPort userRepository;
    private final OrderRepositoryPort orderRepository;
    private final ItemRepositoryPort itemRepository;
    private final ProductRepositoryPort productRepository;
    private final MessageRepositoryPort messageRepository;

    public OrderService(UserRepositoryPort userRepository, OrderRepositoryPort orderRepository,
                        ItemRepositoryPort itemRepository, ProductRepositoryPort productRepository,
                        MessageRepositoryPort messageRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void createOrderFromCart(Long userId) throws OrderException, UserException {
        if (userId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }

        User customer = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        if (customer.getCart().isEmpty()) {
            throw new UserException(ErrorCode.CART_EMPTY);
        }

        for (Item item : customer.getCart()) {
            User seller = userRepository.findUserById(item.getProduct().getShop().getId())
                    .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
            
            Product product = productRepository.findProductById(item.getProduct().getId())
                    .orElseThrow(() -> new UserException(ErrorCode.PRODUCT_NOT_FOUND));

            Integer quantity = item.getQuantity();
            
            if (quantity == null || quantity <= 0) {
                throw new ProductException(ErrorCode.PRODUCT_INVALID_QUANTITY);
            }
            
            product.addSold(quantity);
            productRepository.saveProduct(product);
                    
            customer.addCategoriesToFavourites(item.getProduct().getCategories());
            
            Item clonedItem = itemRepository.saveItem(item.clone())
                    .orElseThrow(() -> new UserException(ErrorCode.OPERATION_NOT_ALLOWED));
            orderRepository.saveOrder(new Order(null, clonedItem, customer, seller, LocalDateTime.now()))
                    .orElseThrow(() -> new UserException(ErrorCode.OPERATION_NOT_ALLOWED));

            
            // Notify seller and customer about the order creation
            messageRepository.saveMessage(new Message(seller, "New order created for " + item.getProduct().getName()));
            messageRepository.saveMessage(new Message(customer, "Order created for " + item.getProduct().getName()));
        }

        customer.clearCart();
        userRepository.saveUser(customer);
    }

    @Override
    public List<Order> findPurchaseOrdersByUserId(Long userId) throws UserException {
        if (userId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return orderRepository.findOrdersByCustomerId(userId);
    }

    @Override
    public List<Order> findSaleOrdersByUserId(Long userId) throws UserException {
        if (userId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return orderRepository.findOrdersByShopId(userId);
    }

    @Override
    public Order findOrderById(Long id) throws OrderException {
        return orderRepository.findOrderById(id)
                .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));
    }

    @Override
    public Order updateOrderStatus(Long id, OrderStatus status) throws OrderException {
        Order order = orderRepository.findOrderById(id)
                .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));

        if (order.getStatus().equals(status)) {
            throw new OrderException(ErrorCode.ORDER_STATUS_SAME);
        }

        switch (status) {
            // case PENDING -> order.setStatus(status);
            case CONFIRMED -> order.confirm();
            case SHIPPED -> order.ship();
            case DELIVERED -> order.deliver();
            case CANCELLED -> order.cancel();
            case RETURNED -> order.returnOrder();
            default -> throw new OrderException(ErrorCode.OPERATION_NOT_ALLOWED);
        }

        messageRepository.saveMessage(new Message(order.getCustomer(), "Order status updated to " + status));
        messageRepository.saveMessage(new Message(order.getShop(), "Order status updated to " + status));

        if (status == OrderStatus.CANCELLED || status == OrderStatus.RETURNED) {
            Item item = order.getItem();
            Product product = productRepository.findProductById(item.getProduct().getId())
                    .orElseThrow(() -> new UserException(ErrorCode.PRODUCT_NOT_FOUND));
            product.addSold(-item.getQuantity());
            productRepository.saveProduct(product);
        }

        return orderRepository.saveOrder(order)
                .orElseThrow(() -> new OrderException(ErrorCode.OPERATION_NOT_ALLOWED));
    }

}
