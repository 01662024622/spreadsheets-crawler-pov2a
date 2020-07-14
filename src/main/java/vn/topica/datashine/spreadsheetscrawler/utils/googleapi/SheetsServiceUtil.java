package vn.topica.datashine.spreadsheetscrawler.utils.googleapi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class SheetsServiceUtil {

  private static final String APPLICATION_NAME = "Google Sheets Example";

  private SheetsServiceUtil(){};
  /**
   * Create google sheets service
   */
  public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
    Credential credential = GoogleAuthorizeUtil.authorize();
    return new Sheets.Builder(
        GoogleNetHttpTransport.newTrustedTransport(),
        JacksonFactory.getDefaultInstance(), GoogleAuthorizeUtil.setHttpTimeout(credential))
        .setApplicationName(APPLICATION_NAME)
        .build();
  }
}
