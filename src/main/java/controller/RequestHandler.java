package controller;

import response.BaseResponse;

@FunctionalInterface
public interface RequestHandler<T> {
    BaseResponse execute(T request);
}
