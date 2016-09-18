package com.wmdebrito.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmdebrito.config.PropertiesConfig;
import com.wmdebrito.model.json.list.ListOfPartners;
import com.wmdebrito.model.json.result.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HttpHandler {

    @Autowired
    private PropertiesConfig propertiesConfig;

    public void sendResults(Result result) throws IOException {
        ObjectMapper mapper = getMapper();

        Map<String, String> header = new HashMap<String, String>();
        header.put(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpRequestBase request = createPostRequest(new URL(propertiesConfig.getResultsUrl()), header, mapper.writeValueAsString(result));
        HttpEntity entity = getHttpEntity(request);
    }

    public ListOfPartners getListOfPartners() throws IOException {
        ObjectMapper mapper = getMapper();

        Map<String, String> header = new HashMap<String, String>();
        header.put(HttpHeaders.CONTENT_TYPE, "application/json");

        Map<String, String> params = new HashMap<String, String>();
        params.put("userKey", propertiesConfig.getUserKey());

        HttpRequestBase request = createGetRequest(new URL(propertiesConfig.getListOfPartnersUrl()), header, params);

        HttpEntity entity = getHttpEntity(request);
        return mapper.readValue(entity.getContent(), ListOfPartners.class);
    }


    protected HttpRequestBase createPostRequest(URL url, Map<String, String> header, String body) throws UnsupportedEncodingException {
        HttpPost request = new HttpPost(url.toString());
        setHeader(header, request);
        StringEntity postEntity = new StringEntity(body);
        request.setEntity(postEntity);

        return request;
    }

    protected HttpRequestBase createGetRequest(URL url, Map<String, String> header, Map<String, String> params) throws UnsupportedEncodingException {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(url.toString());
        uriBuilder.setParameters(getNameValuePairs(params));

        HttpGet request = new HttpGet(uriBuilder.toString());
        setHeader(header, request);


        return request;
    }

    protected List<NameValuePair> getNameValuePairs(Map<String, String> params) {
        List<NameValuePair> listValuePair = new ArrayList();
        if (params != null) {
            for (String key : params.keySet()) {
                listValuePair.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        return listValuePair;
    }

    protected void setHeader(Map<String, String> header, HttpRequestBase request) {
        if (header != null) {
            for (String key : header.keySet()) {
                request.setHeader(key, header.get(key));
            }
        }
    }


    protected HttpEntity getHttpEntity(HttpRequestBase request) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        int statusCode = response.getStatusLine().getStatusCode();
        String message = response.getStatusLine().getReasonPhrase();
        if (entity == null || statusCode != 200)
            throw new IOException("Http Error: Status Code: " + statusCode + ", message: " + message);
        return entity;
    }

    private ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat(propertiesConfig.getDateFormat());
        mapper.setDateFormat(df);
        return mapper;
    }
}