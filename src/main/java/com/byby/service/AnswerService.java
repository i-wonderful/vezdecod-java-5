package com.byby.service;

import com.byby.dto.request.CheckRequest;
import com.byby.dto.response.CheckResponse;

public interface AnswerService {
    CheckResponse check(CheckRequest checkRequest);
}
