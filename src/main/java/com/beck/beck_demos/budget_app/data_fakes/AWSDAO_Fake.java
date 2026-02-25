package com.beck.beck_demos.budget_app.data_fakes;

import com.beck.beck_demos.budget_app.iData.iAWSDAO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload2.core.DiskFileItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AWSDAO_Fake implements iAWSDAO {
  @Override
  public Map<File, String> uploadToS3(List<File> files, String bucketName, String UserID) throws Exception {
    Map<File, String> uploadedFiles = new java.util.HashMap<>();
    for (File file : files) {

      // Simulate S3 URL: just create a dummy URL string
      String fakeUrl = "https://fake-s3.amazonaws.com/" + bucketName + "/" + file.getName();
      uploadedFiles.put(file, fakeUrl);
    }
    return uploadedFiles;
  }

  @Override
  public List<File> saveUploadedFiles(HttpServletRequest req, String applicationPath, List<DiskFileItem> items, String UPLOAD_DIR) throws Exception {
    List<File> fakeSavedFiles = new ArrayList<>();

    for (DiskFileItem item : items) {
      if (!item.isFormField() && item.getName() != null && !item.getName().isEmpty()) {
        // Just simulate file creation with fake path, no actual IO
        String fakeFilePath = applicationPath + File.separator + UPLOAD_DIR + File.separator + item.getName();
        File fakeFile = new File(fakeFilePath);

        fakeSavedFiles.add(fakeFile);
      }
    }

    return fakeSavedFiles;
  }

  @Override
  public Integer deleteReceipt(String receiptID) {
    return receiptID.equals("Bad") ? 0 :1;
  }
}
