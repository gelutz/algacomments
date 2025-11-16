package com.lutz.alga.api.client;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.lutz.alga.domain.exceptions.ModerationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestClientFactory {
	private final RestClient.Builder builder;

	@Value("${api.moderation.port}")
	private String port;
	@Value("${api.moderation.url}")
	private String url;

	public RestClient monitorClient() {
		return builder.baseUrl(url + ":" + port)
				.requestFactory(generateClientHttpRequestFactory())
				.defaultStatusHandler(
						HttpStatusCode::isError,
						(request, response) -> {
							log.error("Request error {}", response.getBody());
							throw new ModerationException(response.getBody().toString());
						})
				.build();
	}

	private ClientHttpRequestFactory generateClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

		factory.setReadTimeout(Duration.ofSeconds(5));
		factory.setConnectTimeout(Duration.ofSeconds(3));

		return factory;
	}
}
