package it.eg.cookbook.service;

import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void throwException() {
        throw new BatchException("Test Exception", ResponseCode.BUSINESS_ERROR);
    }
}
