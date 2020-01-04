/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.warp10.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Warp10RestClient {

    private OkHttpClient client = new OkHttpClient();

    private String url;

    @SuppressWarnings("unused")
    private int port;

    private Warp10Token readToken;

    private Warp10Token writeToken;

    private String tokenSecret;

    private String application;

    private static final String X_WARP_10_TOKEN = "X-Warp10-Token";

    public static Builder newBuilder() {
	return new Builder();
    }

    /**
     * Builder class.
     */
    public static class Builder {

	private Warp10RestClient client = new Warp10RestClient();

	public Warp10RestClient build() {
	    return client;
	}

	public Builder withConnectionTo(String url, String tokenSecret, String application) {
	    client.setUrl(url);
	    client.setTokenSecret(tokenSecret);
	    client.setApplication(application);
	    return this;
	}
    }

    public int ingress(GTSInput data) {
	if (writeToken == null) {
	    writeToken = getToken(TokenType.WRITE);
	}

	Response response = null;
	try {
	    MediaType textPlainMT = MediaType.parse("text/plain; charset=utf-8");
	    Request request = new Request.Builder().url(url + "/update").header("Content-Type", "text/plain")
		    .header(X_WARP_10_TOKEN, writeToken.getToken())
		    .post(RequestBody.create(textPlainMT, data.toInputFormat())).build();

	    response = client.newCall(request).execute();
	    int responseCode = response.code();
	    if (responseCode == 500 && response.peekBody(Long.MAX_VALUE).string().contains("Token Expired")) {
		writeToken = null;
		responseCode = ingress(data);
	    }
	    return responseCode;
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (response != null) {
		response.body().close();
	    }
	}
	return 500;
    }

    public List<GTSOutput> fetch(QueryParams queryParams) {
	if (readToken == null) {
	    readToken = getToken(TokenType.READ);
	}

	Response response = null;

	try {
	    Request request = new Request.Builder().url(url + "/fetch?" + queryParams.toString())
		    .header(X_WARP_10_TOKEN, readToken.getToken()).get().build();

	    response = client.newCall(request).execute();
	    String respuestas = response.peekBody(Long.MAX_VALUE).string();
	    List<GTSOutput> gtsOutputs = GTSOutput.fromOutputFormat(respuestas);
	    if (response.code() == 500 && respuestas.contains("Token Expired")) {
		readToken = null;
		gtsOutputs = fetch(queryParams);
	    }
	    return gtsOutputs;
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (response != null) {
		response.body().close();
	    }
	}
	return null;
    }

    public int delete(String query) {

	if (writeToken == null) {
	    writeToken = getToken(TokenType.WRITE);
	}

	Request request = null;
	Response response = null;
	try {
	    request = new Request.Builder().url(url + "/delete?" + query).header(X_WARP_10_TOKEN, writeToken.getToken())
		    .get().build();
	    response = client.newCall(request).execute();

	    int responseCode = response.code();
	    if (responseCode == 500 && response.peekBody(Long.MAX_VALUE).string().contains("Token Expired")) {
		writeToken = null;
		responseCode = delete(query);
	    }
	    return responseCode;
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (response != null) {
		response.body().close();
	    }
	}
	return 564;
    }

    private Warp10Token getToken(TokenType tokenType) {
	try {
	    TokenRequest tokenRequest = createTokenRequest(tokenType);
	    MediaType textPlainMT = MediaType.parse("application/octet-stream");
	    Request request = new Request.Builder().url(url + "/exec").header("Content-Type", "text/plain")
		    .post(RequestBody.create(textPlainMT, tokenRequest.toString())).build();

	    Response response = client.newCall(request).execute();
	    Gson gson = new Gson();
	    Warp10Token[] warp10Tokens = gson.fromJson(response.peekBody(Long.MAX_VALUE).string(), Warp10Token[].class);
	    return warp10Tokens[0];
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    private TokenRequest createTokenRequest(TokenType tokenType) {
	TokenRequest tokenRequest = new TokenRequest();
	tokenRequest.setId(UUID.randomUUID().toString());
	tokenRequest.setTokenType(tokenType);
	tokenRequest.setApplication(this.application);
	tokenRequest.setOwner(UUID.randomUUID().toString());
	tokenRequest.setProducer(UUID.randomUUID().toString());
	tokenRequest.setIssuance("NOW 1 ms");
	tokenRequest.setExpiry("NOW 30 d + 1 ms");
	tokenRequest.setTtl("300 d 1 ms");
	tokenRequest.setTokenSecret(tokenSecret);

	List<String> applications = new ArrayList<>();
	applications.add(this.application);

	tokenRequest.setApplications(applications);
	return tokenRequest;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public void setPort(int port) {
	this.port = port;
    }

    public void setReadToken(Warp10Token readToken) {
	this.readToken = readToken;
    }

    public void setWriteToken(Warp10Token writeToken) {
	this.writeToken = writeToken;
    }

    public void setTokenSecret(String tokenSecret) {
	this.tokenSecret = tokenSecret;
    }

    public String getApplication() {
	return application;
    }

    public void setApplication(String application) {
	this.application = application;
    }
}
