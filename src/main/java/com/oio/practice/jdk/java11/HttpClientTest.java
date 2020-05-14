package com.oio.practice.jdk.java11;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Liqc
 * @date 2020/5/14 12:17
 */
public class HttpClientTest {

    @Test
    public void httpSyncTest() throws IOException, InterruptedException {
        // 封装请求
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://httpbin.org/ip")).GET().build();

        // 创建HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        // 同步信息
        HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // 打印返回信息
        System.out.println(send.body());
        /*
        {
          "origin": "124.204.33.82"
        }
         */
    }

    @Test
    public void httpAsyncTest() throws IOException, InterruptedException, ExecutionException {
        // 封装请求
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://httpbin.org/ip")).GET().build();

        // 创建HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        // 异常步请求
        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = future.get();
        System.out.println(response.body());
        /*
        {
          "origin": "124.204.33.82"
        }
         */
    }


}
