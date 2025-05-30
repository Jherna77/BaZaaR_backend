package com.jhernandez.backend.bazaar.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.UserEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntityMapperTest {

    @Autowired
    private UserEntityMapper userMapper;

    @Autowired
    private JpaUserRepository userRepository;

    @Transactional(readOnly = true)
    @Test
    void testUserEntityMapper() {
        Optional<UserEntity> userEntityOpt = userRepository.findByEmail("shop1@bazaar.es");
        assertTrue(userEntityOpt.isPresent());

        UserEntity entity = userEntityOpt.get();
        User user = userMapper.toDomain(entity);

        assertNotNull(user);
        assertEquals(entity.getId(), user.getId());
        assertEquals(entity.getEmail(), user.getEmail());
        assertNotNull(user.getShopProducts());
        assertEquals(entity.getShopProducts().size(), user.getShopProducts().size());
    }
}


// @Slf4j
// @DataJpaTest
// public class UserEntityMapperTest {

//     private final UserEntityMapper userMapper = new UserEntityMapperImpl(
//             new UserRoleEntityMapperImpl(), new ProductEntityMapperImpl(null, null),
//             new ItemEntityMapperImpl(), new OrderEntityMapperImpl());
//     @Autowired
//     private JpaUserRepository userRepository;

//     /**
//      * Test user entity mapper.
//      */
//     @Test
//     void testUserEntityMapper() {
//         final Optional<UserEntity> userEntity = userRepository.findByEmail("shop1@bazaar.es");
//         assertNotNull(userEntity);
//         log.info("User entity: {}", userEntity.get());
//         log.info("User entity id: {}", userEntity.get().getId());
//         log.info("User entity email: {}", userEntity.get().getEmail());
//         log.info("User entity password: {}", userEntity.get().getPassword());
//         log.info("User entity name: {}", userEntity.get().getName());
//         log.info("User entity surnames: {}", userEntity.get().getSurnames());
//         log.info("User entity address: {}", userEntity.get().getAddress());
//         log.info("User entity city: {}", userEntity.get().getCity());
//         log.info("User entity province: {}", userEntity.get().getProvince());
//         log.info("User entity zip code: {}", userEntity.get().getZipCode());
//         log.info("User entity role id: {}", userEntity.get().getRole().getId());
//         log.info("User entity role name: {}", userEntity.get().getRole().getName());
//         log.info("User entity shop: {}", userEntity.get().getShop());

//         final User user = this.userMapper.toDomain(userEntity.get());
//         assertNotNull(user);
//         assertNotNull(user.getId());
//         assertNotNull(user.getEmail());
//         assertNotNull(user.getPassword());
//         assertNotNull(user.getName());
//         assertNotNull(user.getSurnames());
//         assertNotNull(user.getAddress());
//         assertNotNull(user.getProvince());
//         assertNotNull(user.getCity());
//         assertNotNull(user.getZipCode());
//         assertNotNull(user.getRole().getId());
//         assertNotNull(user.getRole().getName());
//         assertNotNull(user.getShop());
//         assertEquals(user.getId(), userEntity.get().getId());
//         assertEquals(user.getEmail(), userEntity.get().getEmail());
//         assertEquals(user.getPassword(), userEntity.get().getPassword());
//         assertEquals(user.getName(), userEntity.get().getName());
//         assertEquals(user.getSurnames(), userEntity.get().getSurnames());
//         assertEquals(user.getAddress(), userEntity.get().getAddress());
//         assertEquals(user.getProvince(), userEntity.get().getProvince());
//         assertEquals(user.getCity(), userEntity.get().getCity());
//         assertEquals(user.getZipCode(), userEntity.get().getZipCode());
//         assertEquals(user.getRole().getId(), userEntity.get().getRole().getId());   
//         assertEquals(user.getRole().getName(), userEntity.get().getRole().getName());
//         // assertArrayEquals(user.getShop(), userEntity.get().getShop());
//         log.info("User: {}", user);

//     }
// }