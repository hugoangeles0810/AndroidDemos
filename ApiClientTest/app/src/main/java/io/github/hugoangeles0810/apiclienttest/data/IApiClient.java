package io.github.hugoangeles0810.apiclienttest.data;

import io.github.hugoangeles0810.apiclienttest.data.models.response.BaseResponse;
import io.github.hugoangeles0810.apiclienttest.data.models.response.Person;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiClient {

  @GET("sayHello")
  Call<String> sayHello();

  @GET("person")
  Call<BaseResponse<List<Person>>> listPersons();

}
