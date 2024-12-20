/**
 * Name: HungHsu(Allen) Chen 
 * Course: CS-665 Software Designs & Patterns 
 * Date: 12/01/2024 
 * File Name: APIUtil.java 
 * Description: API Utilities.
 */

package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;

/**
 * An class for making API request and sending back the response.
 */
public class APIUtil {
  /**
   * Make a HTTP GET reuqest to an api and returns back a String of JSON.
   * 
   * @param url String url of the api
   * @param headers can be null, extra headers
   * @return String that contains the data in JSON.
   */
  public static String APIRequest(String url, HashMap<String,String> headers) {
    try {
      Builder requestBuilder = HttpRequest
          .newBuilder(new URI(url))
          .GET()
          .timeout(Duration.ofSeconds(10));
      if (headers != null) {
        for (String key : headers.keySet()) {
          requestBuilder.header(key, headers.get(key));
        }
      }
      
      HttpRequest request = requestBuilder.build();
      
      HttpResponse<String> response = HttpClient
          .newHttpClient()
          .send(request, HttpResponse.BodyHandlers.ofString());
      
      return response.body();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
