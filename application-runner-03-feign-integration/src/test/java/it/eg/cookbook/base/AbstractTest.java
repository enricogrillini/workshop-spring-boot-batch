package it.eg.cookbook.base;

import it.eg.cookbook.client.DocumentClient;
import it.eg.cookbook.client.SecurityClient;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class AbstractTest {

    @MockBean
    protected SecurityClient securityClient;

    @MockBean
    protected DocumentClient documentClient;

}
