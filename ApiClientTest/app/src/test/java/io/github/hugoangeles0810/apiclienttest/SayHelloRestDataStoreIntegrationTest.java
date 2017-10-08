package io.github.hugoangeles0810.apiclienttest;

import io.github.hugoangeles0810.apiclienttest.data.ApiClient;
import io.github.hugoangeles0810.apiclienttest.data.SayHelloDataStore;
import io.github.hugoangeles0810.apiclienttest.data.SayHelloRestDataStore;
import io.github.hugoangeles0810.apiclienttest.data.models.response.Person;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SayHelloRestDataStoreIntegrationTest extends RestDataStoreIntegrationTest {

  private SayHelloDataStore sayHelloDataStore;


  @Override protected void setUp(String baseUrl) throws Exception {
    sayHelloDataStore = new SayHelloRestDataStore(ApiClient.getInstance(baseUrl));
  }

  @Test
  public void shouldReturnSayHello() {
    enqueueResponse("Hello!");
    assertEquals("Hello!", sayHelloDataStore.sayHello());
  }

  @Test
  public void shouldReturnPersonList() {
    enqueueResponseFromFile("persons_ok.json");
    List<Person> personList = sayHelloDataStore.listPersons();
    assertNotNull(personList);
    assertTrue(!personList.isEmpty());
  }

  @Override protected void tearDown() throws Exception {
    sayHelloDataStore = null;
    ApiClient.destroyInstance();
  }
}
