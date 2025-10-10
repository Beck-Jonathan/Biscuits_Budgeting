package com.beck.beck_demos.budget_app.data;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.beck.beck_demos.budget_app.iData.iAWSDAO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.io.IOUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AWSDAO implements iAWSDAO {
  private String bucketName = "budgetreceipts";
  @Override
  public List<File> saveUploadedFiles(HttpServletRequest req, String applicationPath, List<DiskFileItem> items, String UPLOAD_DIR) throws Exception {
    List<File> files = new ArrayList<>();
    for (DiskFileItem x : items) {
      if (x.getName() != null) {
        InputStream inputStream = x.getInputStream();
        boolean created = false;
        File file = new File(applicationPath + File.separator + UPLOAD_DIR + x.getName());
        try {
           created = file.createNewFile();
        }catch(Exception e) {
          file.mkdirs();
          created = file.createNewFile();
        }

        if (!created) {
          file.delete();
          file.createNewFile();
        }
        try (FileOutputStream outstream = new FileOutputStream(file)) {
          IOUtils.copy(inputStream, outstream);
        }
        files.add(file);
      }
    }
    return files;
  }

  @Override
  public Integer deleteReceipt(String receiptID) throws SQLException {
    int result = 1;
    try  {
      receiptID = receiptID.replace("https://budgetreceipts.s3.amazonaws.com/","");
      AmazonS3 client = AmazonS3ClientBuilder.standard().build();
      //GetObjectRequest getRequest = new GetObjectRequest(bucketName, receiptID);
      //var x = client.getObject(getRequest);

      DeleteObjectRequest request = new DeleteObjectRequest(bucketName, receiptID);
      client.deleteObject(request);
      }catch(Exception e) {

      throw new SQLException(e);
    }


    return result;
  }

  @Override
  public Map<File, String> uploadToS3(List<File> files, String bucketName, int UserID) throws Exception {
    Map<File, String> fileUrlMap = new HashMap<>();
    try (S3Client client = S3Client.builder().build()) {
      for (File file : files) {
        String filename = file.getName();
        PutObjectRequest request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(UserID+"/"+filename)
            .build();
        client.putObject(request, RequestBody.fromFile(file));

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(bucketName)
            .key(UserID+"/"+filename)
            .build();
        String url = client.utilities().getUrl(getUrlRequest).toExternalForm();

        fileUrlMap.put(file, url);
      }
    }
    return fileUrlMap;
  }
}
