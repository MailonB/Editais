package org.example;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequester {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequester.class);

  private final HttpClient client;

  public HttpRequester() {
    this.client = HttpClient.newHttpClient();
  }

  public HttpResponse<String> post(String url, JsonObject body) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(url))
          .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
          .header("Content-Type", "application/json")
          .timeout(Duration.of(60, ChronoUnit.SECONDS))
          .build();
      return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (URISyntaxException e) {
      LOGGER.warn("Error parsing string as a URI reference", e);
    } catch (IOException | InterruptedException e) {
      LOGGER.warn("Error sending request", e);
    }
    throw new RuntimeException("Exception thrown in post method");
  }
  public HttpResponse<String> get(String url) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(url))
          .GET()
          .timeout(Duration.of(60, ChronoUnit.SECONDS))
          .build();
      return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (URISyntaxException e) {
      LOGGER.warn("Error parsing string as a URI reference", e);
    } catch (IOException | InterruptedException e) {
      LOGGER.warn("Error sending request", e);
    }
    throw new RuntimeException("Exception thrown in post method");
  }
}
