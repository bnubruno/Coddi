package coddi.com.br.App;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rest {

    private static final int TIMEOUT_CONNECTION = 60000;
    private static final int TIMEOUT_SOCKET = 65000;

    public static String get(String url) throws Throwable {
        HttpParams httpParameters = getParametros();
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpContext localContext = new BasicHttpContext();
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest, localContext);
        if (!isCodigoEsperado(response, 200)) {
            throw new RuntimeException("Falha : HTTP cod : " + response.getStatusLine().getStatusCode());
        }
        StringBuilder resultado = lerResposta(response);
        httpClient.getConnectionManager().shutdown();
        return resultado.toString();

    }

    public static String post(String url, String json) throws Throwable {
        HttpParams httpParameters = getParametros();
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpContext localContext = new BasicHttpContext();
        HttpPost postRequest = new HttpPost(url);
        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");
        postRequest.setEntity(input);
        HttpResponse response = httpClient.execute(postRequest, localContext);
        if (!isCodigoEsperado(response, 201)) {
            throw new RuntimeException("Falha : HTTP cod : " + response.getStatusLine().getStatusCode());
        }
        StringBuilder resultado = lerResposta(response);
        httpClient.getConnectionManager().shutdown();
        return resultado.toString();
    }

    public static String delete(String url) throws Throwable {
        HttpParams httpParameters = getParametros();
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpContext localContext = new BasicHttpContext();
        HttpDelete deleteRequest = new HttpDelete(url);
        deleteRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(deleteRequest, localContext);
        if (!isCodigoEsperado(response, 200)) {
            throw new RuntimeException("Falha : HTTP cod : " + response.getStatusLine().getStatusCode());
        }
        StringBuilder resultado = lerResposta(response);
        httpClient.getConnectionManager().shutdown();
        return resultado.toString();


    }

    public static String put(String url, String json) throws Throwable {
        HttpParams httpParameters = getParametros();
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpContext localContext = new BasicHttpContext();
        HttpPut putRequest = new HttpPut(url);
        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");
        putRequest.setEntity(input);
        HttpResponse response = httpClient.execute(putRequest, localContext);
        if (!isCodigoEsperado(response, 201)) {
            throw new RuntimeException("Falha : HTTP cod : " + response.getStatusLine().getStatusCode());
        }
        StringBuilder resultado = lerResposta(response);
        httpClient.getConnectionManager().shutdown();
        return resultado.toString();

    }

    private static HttpParams getParametros() {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);
        return httpParameters;
    }

    private static StringBuilder lerResposta(HttpResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        String output;
        StringBuilder resultado = new StringBuilder();
        while ((output = br.readLine()) != null) {
            resultado.append(output);
        }
        return resultado;
    }

    private static boolean isCodigoEsperado(HttpResponse response, int codigo) {
        return response.getStatusLine().getStatusCode() == codigo;
    }

}
