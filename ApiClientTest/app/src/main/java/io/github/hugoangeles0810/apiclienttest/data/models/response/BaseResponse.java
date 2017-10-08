package io.github.hugoangeles0810.apiclienttest.data.models.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

  @SerializedName("data")
  private T data;

  public BaseResponse(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }
}
