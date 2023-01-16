package com.example.shamo.service;

import com.example.shamo.dao.FileDao;
import com.example.shamo.dto.DeleteRes;
import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.InsertResData;
import com.example.shamo.dto.file.InsertFileReq;
import com.example.shamo.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileDao fileDao;

    public Files getById(String id) throws Exception {
        Files file = fileDao.findById(id);
        return file;
    }

    public InsertRes insert(InsertFileReq data) throws Exception {
        Files file = new Files();
        file.setFileName(data.getFile());
        file.setFileExtension(data.getFile());

        Files inserted = fileDao.insert(file);

        InsertResData resData = new InsertResData();
        resData.setId(inserted.getId());

        InsertRes res = new InsertRes();
        res.setData(resData);
        res.setMessage("Berhasil");
        return res;
    }

    public DeleteRes delete(String id) throws Exception {
        Boolean delete = fileDao.delete(id);
        DeleteRes res = null;
        if (delete) {
            res = new DeleteRes();
            res.setMessage("Berhasil");
        }

        return res;
    }


}
