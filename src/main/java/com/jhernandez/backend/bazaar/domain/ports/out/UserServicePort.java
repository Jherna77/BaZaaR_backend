package com.jhernandez.backend.bazaar.domain.ports.out;

import com.jhernandez.backend.bazaar.domain.ports.in.CreateUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.DeleteUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.RetrieveUserUseCase;
import com.jhernandez.backend.bazaar.domain.ports.in.UpdateUserUseCase;

// This interface combines all the use cases related to user management.
// It extends the individual use case interfaces to provide a single entry point for user-related operations.
public interface UserServicePort extends
        CreateUserUseCase, RetrieveUserUseCase, UpdateUserUseCase, DeleteUserUseCase {

}
