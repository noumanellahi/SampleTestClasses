package com.mycompany.api.acq;

import com.google.common.collect.Maps;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 * Simple test application to access the CDI Search API.
 */
public class CDISearchRunner {

    // -----------------------------------------------
    // Client credentials - add credentials
    // -----------------------------------------------
    private static final String CLIENT_SHORT_NAME = "proquestplatform";
    private static final String CLIENT_KEY = "NX7FB8LW2D";  // ""<libhash>";
    private static final String SECRET_KEY = "04H2CJa8l4IX1r15s7n5L9S6r66VOGJ7";  // "<secret-key>";
    // -----------------------------------------------
    // Client credentials - add credentials
    // -----------------------------------------------

    private static final Charset CHARSET = Charset.forName("utf-8");
    private static final String ALGORITHM = "HmacSHA1";
//    private static final String APPLICATION_JSON = "application/json";
//    private static final String PROTOCOL = "https";
//    private static final String URL = "/2.0.0/search";
//    private static final String CDI_API_HOST = "api.summon.serialssolutions.com";
//    private static final String CDI_POST_URL = String.format("%s://%s%s", PROTOCOL, CDI_API_HOST, URL);

    /**
     * Performs the CDI search request.
     *
     * @param queryString Query String (s.role=authenticated&s.q=test)
     */
    public void search(Map<String,String> queryParams) throws SignatureException, IOException {
    	
        String APPLICATION_JSON = "application/json";
        String PROTOCOL = "http";
        String URL = "/2.0.0/search";
        String CDI_API_HOST = "api.summon.serialssolutions.com";
        String CDI_POST_URL = String.format("%s://%s%s", PROTOCOL, CDI_API_HOST, URL);
        
        // Build hash
        final String dateTime = getDateTime();
        
        final Map<String,String> sortedQueryParams = sortQueryParams(queryParams);
        final String queryString = getQueryString(sortedQueryParams);
        String hash = buildHash(SECRET_KEY, APPLICATION_JSON, dateTime, CDI_API_HOST, URL, queryString);

        // Post request
        PostMethod post = new PostMethod(CDI_POST_URL);
        post.addRequestHeader("Accept", APPLICATION_JSON);
        post.addRequestHeader("Authorization", String.format("Summon %s;%s;%s", CLIENT_SHORT_NAME, CLIENT_KEY, hash));
        post.addRequestHeader("Host", CDI_API_HOST);
        post.addRequestHeader("x-summon-date", dateTime);
        String randomString = UUID.randomUUID().toString();
        System.out.println("Summon proquestplatform;NX7FB8LW2D;"+hash);
        System.out.println(dateTime);
        System.out.println(randomString);
        post.addRequestHeader("x-summon-session-id", randomString);
        NameValuePair[] requestBody = sortedQueryParams.entrySet().stream()
            .map(e -> new NameValuePair(e.getKey(), e.getValue()))
            .collect(Collectors.toList())
            .toArray(new NameValuePair[0]);
        post.setRequestBody(requestBody);
//        postRequest(post);
    }

    private String getQueryString(Map<String, String> sortedQueryParams) {
        final String queryString = sortedQueryParams.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining("&"));
        return queryString;
    }

    private static void postRequest(HttpMethod httpMethod) throws IOException {
        HttpClient httpClient = new HttpClient();
        int ret = httpClient.executeMethod(httpMethod);
        if (ret != HttpStatus.SC_OK) {
            System.out.println("{ \"Error-ReturnCode\" : \"" + ret + "\" }");
        }
        InputStream is = new BufferedInputStream(httpMethod.getResponseBodyAsStream());
        byte[] bytes = new byte[10240];
        int len;
        while ((len = is.read(bytes, 0, bytes.length - 1)) != -1) {
            System.out.print(new String(bytes, 0, len, CHARSET));
        }
    }

    /**
     * @param key Client secrete key
     * @param strings The following list of String params:
     *                APPLICATION_JSON, dateTime, CDI_API_HOST, URL, QUERY_STRING
     * @return The CDI API hash required for queries
     */
    private static String buildHash(String key, String... strings) throws SignatureException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string).append("\n");
        }
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(CHARSET), ALGORITHM);
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(signingKey);
            result = new String(encodeBase64(mac.doFinal(stringBuilder.toString().getBytes(CHARSET))), CHARSET);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    /**
     * @param queryParams String representing the query parameters.
     * @return The query parameters string sorted by key.
     */
    private static Map<String, String> sortQueryParams(Map<String, String> queryParams) {
        queryParams = new TreeMap<>(queryParams);
        return queryParams.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }

    /**
     * @return Gets the formatted Date and Time for authentication.
     */
    private static String getDateTime() {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormatGmt.format(new Date());
    }

    public static void main(String[] args) throws Exception {
        CDISearchRunner searchTest = new CDISearchRunner();
        Map<String,String> queryParams = Maps.newHashMap();
        queryParams.put("s.solrPassThroughQuery", "sys_source_id:(proquest AND NOT webofscience)");
        queryParams.put("s.fvf", "IsFullText,true");
        queryParams.put("s.role", "authenticated");
        queryParams.put("s.fl", "id,title,discipline");
        queryParams.put("s.ps", "3");
        searchTest.search(queryParams);
    }

}
