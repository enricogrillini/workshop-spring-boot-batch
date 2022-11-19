package it.eg.cookbook.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import it.eg.cookbook.gen.controller.DocumentApi;

@FeignClient(
        value = "backend-document",
        url = "${endpoint:http://localhost}"
)
@Service
public interface DocumentClient extends DocumentApi {
}
