package com.example.shamo.service.impl;

import com.example.shamo.dao.ProductCategoryDao;
import com.example.shamo.dto.*;
import com.example.shamo.dto.productcategory.*;
import com.example.shamo.model.ProductCategories;
import com.example.shamo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao categoryDao;

    @Override
    public FindAllProductCategoryRes findAllProductCategory() throws Exception {
        List<ProductCategoryData> categoryData = new ArrayList<>();
        List<ProductCategories> categories = categoryDao.findAllCategory();
        categories.forEach(data -> {
            ProductCategoryData category = new ProductCategoryData();
            category.setId(data.getId());
            category.setCategory(data.getCategory());

            categoryData.add(category);
        });

        FindAllProductCategoryRes res = new FindAllProductCategoryRes();
        res.setData(categoryData);

        return res;
    }

    @Override
    public FindByIdProductCategoryRes findByIdProductCategory(Long id) throws Exception {
        ProductCategoryData category = new ProductCategoryData();
        ProductCategories categories = categoryDao.findByIdCategory(id);
        category.setId(categories.getId());
        category.setCategory(categories.getCategory());

        FindByIdProductCategoryRes res = new FindByIdProductCategoryRes();
        res.setData(category);

        return res;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public InsertRes insert(InsertProductCategoryReq category) throws Exception {
        ProductCategories categories = new ProductCategories();
        categories.setCategory(category.getCategory());

        ProductCategories inserted = categoryDao.insertCategory(categories);

        InsertResData resData = new InsertResData();
        resData.setId(inserted.getId());

        InsertRes res = new InsertRes();
        res.setData(resData);
        res.setMessage("Berhasil");

        return res;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UpdateRes update(UpdateProductCategoryReq category) throws Exception {
        ProductCategories categoryData = categoryDao.findByIdCategory(category.getId());
        categoryData.setCategory(category.getCategory());

        ProductCategories update = categoryDao.updateCategory(categoryData);

        UpdateResData resData = new UpdateResData();
        resData.setVersion(update.getVersion());

        UpdateRes res = new UpdateRes();
        res.setData(resData);
        res.setMessage("Berhasil");

        return res;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public DeleteRes delete(Long id) throws Exception {
        Boolean delete = categoryDao.deleteCategory(id);
        DeleteRes res = null;
        if (delete) {
            res = new DeleteRes();
            res.setMessage("Berhasil");
        }

        return res;
    }

}
