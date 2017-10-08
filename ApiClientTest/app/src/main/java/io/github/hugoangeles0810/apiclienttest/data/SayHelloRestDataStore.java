package io.github.hugoangeles0810.apiclienttest.data;

import io.github.hugoangeles0810.apiclienttest.data.models.response.Person;
import java.io.IOException;
import java.util.List;

public class SayHelloRestDataStore  implements SayHelloDataStore {

  private IApiClient apiClient;

  public SayHelloRestDataStore(IApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @Override public String sayHello() {
    try {
      return apiClient.sayHello().execute().body();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override public List<Person> listPersons() {
    try {
      return apiClient.listPersons().execute().body().getData();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
