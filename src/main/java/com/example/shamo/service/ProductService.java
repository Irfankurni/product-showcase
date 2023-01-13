package com.example.shamo.service;

import com.example.shamo.dao.FileDao;
import com.example.shamo.dao.ProductCategoryDao;
import com.example.shamo.dao.ProductDao;
import com.example.shamo.dao.ProductGalleryDao;
import com.example.shamo.dto.*;
import com.example.shamo.dto.product.*;
import com.example.shamo.dto.productgallery.ProductGalleryData;
import com.example.shamo.model.Files;
import com.example.shamo.model.ProductCategories;
import com.example.shamo.model.ProductGalleries;
import com.example.shamo.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryDao categoryDao;

    @Autowired
    private ProductGalleryDao galleryDao;

    @Autowired
    private FileDao fileDao;

	@Autowired
    private ProductGalleryService productGalleryService;

    public FindAllProductRes findAllProduct(String category) throws Exception {
        List<ProductListData> productData = new ArrayList<>();

        List<LinkedHashMap<String, Object>> products = productDao.findAllProduct(category);
        for (LinkedHashMap<String, Object> value : products) {
            ProductListData product = new ProductListData();
            product.setId(value.get("id").toString());
            product.setProductName((String) value.get("productName"));

            product.setCategoryName((String) value.get("category"));

            product.setPrice(Float.parseFloat(value.get("price").toString()));
            product.setDescription((String) value.get("description"));
            product.setTags((String) value.get("tags"));
            if (value.get("fileId") != null) {
                product.setFileId(value.get("fileId").toString());
            }
            productData.add(product);

        }

        FindAllProductRes res = new FindAllProductRes();
        res.setData(productData);

        return res;
    }

    public List<LinkedHashMap<String, Object>> findAllProducts(String category) throws Exception {
        return productDao.findAllProduct(category);
    }

    public FindByIdProductRes findByIdProduct(String id) throws Exception {
        Products products = productDao.findByIdProduct(id);

        ProductData product = new ProductData();
        product.setId(products.getId());
        product.setProductName(products.getProductName());
        product.setCategoryId(products.getCategory().getId());

        ProductCategories categories = categoryDao.findByIdCategory(products.getCategory().getId());
        product.setCategoryName(categories.getCategory());

        product.setPrice(products.getPrice());
        product.setDescription(products.getDescription());
        product.setTags(products.getDescription());

        List<ProductGalleries> galleries = galleryDao.findByProductId(products.getId());
        List<ProductGalleryData> galleriesData = new ArrayList<>();

        for (ProductGalleries productGalleries : galleries) {
            ProductGalleryData gallery = new ProductGalleryData();

            Files files = fileDao.findById(productGalleries.getFile().getId());
            gallery.setFileId(files.getId());

            galleriesData.add(gallery);
        }
        product.setGalleries(galleriesData);

        FindByIdProductRes res = new FindByIdProductRes();
        res.setData(product);

        return res;
    }

    @Transactional(rollbackOn = Exception.class)
    public InsertRes insertProduct(InsertProductReq products) throws Exception {
        Products product = new Products();
        product.setProductName(products.getProductName());

        ProductCategories categories = categoryDao.findByIdCategory(products.getCategoryId());
        product.setCategory(categories);
        product.setPrice(products.getPrice());
        product.setTags(products.getTags());
        product.setDescription(products.getDescription());

        Products inserted = productDao.insertProduct(product);

        InsertResData resData = new InsertResData();
        resData.setId(inserted.getId());

        InsertRes res = new InsertRes();
        res.setData(resData);
        res.setMessage("Berhasil");
        return res;
    }

    @Transactional(rollbackOn = Exception.class)
    public UpdateRes updateProduct(UpdateProductReq product) throws Exception {
        Products products = productDao.findByIdProduct(product.getId());
        products.setProductName(product.getProductName());

        ProductCategories categories = categoryDao.findByIdCategory(product.getCategoryId());
        products.setCategory(categories);

        products.setPrice(product.getPrice());
        products.setDescription(product.getDescription());
        products.setTags(product.getTags());

        Products updated = productDao.updateProduct(products);

        UpdateResData resData = new UpdateResData();
        resData.setVersion(updated.getVersion());

        UpdateRes res = new UpdateRes();
        res.setData(resData);
        res.setMessage("Success");
        return res;
    }

    @Transactional(rollbackOn = Exception.class)
    public DeleteRes deleteProduct(String id) throws Exception {
        List<ProductGalleries> galleries = galleryDao.findByProductId(id);
        galleries.forEach(data -> {
            try {
				productGalleryService.delete(data.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Boolean delete = productDao.deleteProduct(id);
        DeleteRes res = null;
        if (delete) {
            res = new DeleteRes();
            res.setMessage("Success");
        }
        return res;
    }


}
