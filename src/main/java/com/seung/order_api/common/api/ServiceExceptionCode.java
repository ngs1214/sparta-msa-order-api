package com.seung.order_api.common.api;

import lombok.Getter;

@Getter
public enum ServiceExceptionCode {

  NOT_FOUND_PRODUCT("NOT_FOUND_PRODUCT", "상품을 찾을 수 없습니다"),
  ;

  private final String code;
  private final String message;

  ServiceExceptionCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String toString() {
    return "code : " + code + ", message :" + message;
  }
}
