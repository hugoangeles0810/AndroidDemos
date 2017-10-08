package io.github.hugoangeles0810.apiclienttest;

import android.support.annotation.Nullable;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Set;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;

public abstract class RestDataStoreIntegrationTest {

  private MockWebServer server;

  @Before
  public void setUpServer() throws Exception {
    server = new MockWebServer();
    server.start();
    HttpUrl baseUrl = server.url("");
    setUp(baseUrl.toString());
  }

  protected abstract void setUp(String baseUrl) throws Exception;

  protected abstract void tearDown() throws Exception;

  @After
  public void tearDownServer() throws Exception {
    tearDown();
    server.shutdown();
  }

  protected void enqueueResponse(int code, String body, @Nullable Map<String, String> headers) {
    MockResponse mockResponse = new MockResponse();
    mockResponse.setResponseCode(code);
    mockResponse.setBody(body);

    if (headers != null) {
      Set<String> headerNames = headers.keySet();
      for (String headerName : headerNames) {
        mockResponse.addHeader(headerName, headers.get(headerName));
      }
    }

    server.enqueue(mockResponse);
  }

  protected void enqueueResponse(String body) {
    enqueueResponse(HttpURLConnection.HTTP_OK, body, null);
  }

  protected void enqueueResponseFromFile(String filename) {
    try {
      String body = convertStreamToString(getClass().getResourceAsStream("/stubs/" + filename));
      enqueueResponse(HttpURLConnection.HTTP_OK, body, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String convertStreamToString(InputStream is) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    while ((line = reader.readLine()) != null) {
      sb.append(line).append("\n");
    }
    reader.close();
    return sb.toString();
  }

}
