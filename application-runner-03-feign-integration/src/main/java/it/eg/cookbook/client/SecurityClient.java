package it.eg.cookbook.client;

import it.eg.cookbook.gen.controller.SecurityApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient(
        value = "backend-security",
        url = "${endpoint:http://localhost}")
@Service
public interface SecurityClient extends SecurityApi {
}
