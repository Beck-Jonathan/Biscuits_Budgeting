package com.beck.beck_demos.budget_app.iData;



import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload2.core.DiskFileItem;


import java.io.File;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface iAWSDAO {

  public Map<File, String> uploadToS3(List<File> files, String bucketName, int UserID) throws Exception;
  public List<File> saveUploadedFiles(HttpServletRequest req, String applicationPath, List<DiskFileItem> items, String UPLOAD_DIR) throws Exception ;

  Integer deleteReceipt(String receiptID) throws SQLException;
}