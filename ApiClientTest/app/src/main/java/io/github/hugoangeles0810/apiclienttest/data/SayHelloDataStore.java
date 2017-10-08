package io.github.hugoangeles0810.apiclienttest.data;

import io.github.hugoangeles0810.apiclienttest.data.models.response.Person;
import java.util.List;

public interface SayHelloDataStore extends BaseRestDataStore {
  String sayHello();
  List<Person> listPersons();
}
