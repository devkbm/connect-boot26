package com.like.restdocs.utils;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

public interface ApiDocumentUtils {

	//https://techblog.woowahan.com/2597/
	//https://github.com/hojinDev/restdocs-sample
	static OperationRequestPreprocessor getDocumentRequest() {
				
        return preprocessRequest(
                        modifyUris() // (1)
                                .scheme("https")
                                .host("docs.api.com")
                                .removePort(),
                        prettyPrint()); // (2)
        
	}

	static OperationResponsePreprocessor getDocumentResponse() {    	
        return preprocessResponse(prettyPrint()); // (3)
	}
}
