package com.jhernandez.backend.bazaar.application.service;

import java.util.ArrayList;
import java.util.List;

import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.User;

/**
 * This class implements the ProductServicePort interface, which defines the
 * contract for product-related operations.
 * It can include methods for creating, retrieving, updating, and deleting
 * products.
 * The actual implementation of these methods would depend on the specific
 * requirements of the application.
 * The ProductService class is responsible for implementing the business logic
 * related to products and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on products and
 * handles any exceptions that may occur.
 */
public class ProductService implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ImageServicePort imageServicePort;

    public ProductService(ProductRepositoryPort productRepositoryPort, UserRepositoryPort userRepositoryPort,
            ImageServicePort imageServicePort) {
        this.productRepositoryPort = productRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.imageServicePort = imageServicePort;
    }

    @Override
    public void createProduct(Product product, Long userId, List<ImageFile> productImages) throws ImageFileException, UserException {
        product.setImagesUrl(imageServicePort.saveImagesList(productImages)
                .stream()
                .map(imageFile -> imageFile.getImageUrl())
                .toList());
        User productOwner = userRepositoryPort.findUserById(userId)
                .orElseThrow(() -> new UserException("User not found"));            
        if (!productOwner.getEnabled()) throw new UserException("Product owner user is not enabled");
        productOwner.addProductToShop(product);
        userRepositoryPort.saveUser(productOwner);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepositoryPort.findAllProducts();
    }

    @Override
    public List<Product> findAllEnabledProducts() {
        return productRepositoryPort.findAllEnabledProducts();
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) throws CategoryException {
        if (categoryId == null) throw new CategoryException("Category ID must not be null");
        return productRepositoryPort.findProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findEnabledProductsByCategoryId(Long categoryId) throws CategoryException {
        if (categoryId == null) throw new CategoryException("Category ID must not be null");
        return productRepositoryPort.findEnabledProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findProductsByUserId(Long userId) throws UserException {
        if (userId == null) throw new UserException("User ID must not be null");
        return userRepositoryPort.findUserById(userId)
                .orElseThrow(() -> new UserException("User not found"))
                .getShop();
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        if (id == null) throw new ProductException("Product ID must not be null");
        return productRepositoryPort.findProductById(id)
                .orElseThrow(() -> new ProductException("Product not found"));
    }

    @Override
    public void updateProduct(Product product, List<ImageFile> productsImages) throws ProductException, ImageFileException {
        if (product.getId() == null) throw new ProductException("Product ID must not be null");
        Product existingProduct = findProductById(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setShipping(product.getShipping());
        existingProduct.setCategories(product.getCategories());

        List<String> finalImages = new ArrayList<>(product.getImagesUrl());
        List<String> imagesToDelete = new ArrayList<>(existingProduct.getImagesUrl()
            .stream()
            .filter(img -> !finalImages.contains(img))
            .toList());
        
        if (imagesToDelete != null && !imagesToDelete.isEmpty()) imageServicePort.deleteImageListByUrl(imagesToDelete);
        if (productsImages != null && !productsImages.isEmpty()) {
            finalImages.addAll(
                    imageServicePort.saveImagesList(productsImages).stream()
                            .map(ImageFile::getImageUrl)
                            .toList());
        }
        existingProduct.setImagesUrl(finalImages);

        productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public void enableProductById(Long id) throws ProductException, UserException {
        if (id == null) throw new ProductException("Product ID must not be null");
        Product existingProduct = findProductById(id);
        if (existingProduct.getEnabled()) throw new ProductException("Product is already enabled");
        // User productOwner = userRepositoryPort.findUserById(existingProduct.getUser().getId())
        //         .orElseThrow(() -> new UserException("User not found"));
        // if (!productOwner.isEnabled()) throw new UserException("Product owner user is not enabled");
        existingProduct.setEnabled(true);
        productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public void disableProductById(Long id) throws ProductException {
        if (id == null) throw new ProductException("Product ID must not be null");
        Product existingProduct = findProductById(id);
        if (!existingProduct.getEnabled()) throw new ProductException("Product is already disabled");
        existingProduct.setEnabled(false);
        productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public void deleteProductById(Long id) throws ProductException, ImageFileException {
        if (id == null) throw new ProductException("Product ID must not be null");
        Product existingProduct = findProductById(id);
        imageServicePort.deleteImageListByUrl(existingProduct.getImagesUrl());
        productRepositoryPort.deleteProductById(id);
    }

    // @Override
    // public void deleteProductsByUserId(Long userId) throws UserException, ImageFileException {
    //     if (userId == null) throw new UserException("User ID must not be null");
    //     User user = userRepositoryPort.findUserById(userId)
    //             .orElseThrow(() -> new UserException("User not found"));
    //     List<Product> userShop = user.getShop();
    //     if (userShop != null && !userShop.isEmpty()) {
    //         for (Product product : userShop) {
    //             imageServicePort.deleteImageListByUrl(product.getImagesUrl());
    //         }
    //     user.getShop().clear();
    //     }
    //     userRepositoryPort.saveUser(user);
    // }

}
