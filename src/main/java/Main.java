import model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        final String ROOT_URI = "http://94.198.50.185:7081/api/users/";

        User user1 = new User(3L, "James", "Brown", (byte) 42);
        User user2 = new User(3L, "Thomas", "Shelby", (byte) 42);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entityHeaders = new HttpEntity<>(headers);
        HttpEntity<User> entityUser1 = new HttpEntity<>(user1, headers);
        HttpEntity<User> entityUser2 = new HttpEntity<>(user2, headers);

        ResponseEntity<String> response = restTemplate.exchange(ROOT_URI, HttpMethod.GET, entityHeaders, String.class);
        headers.add("Cookie", String.valueOf(response.getHeaders().getFirst(HttpHeaders.SET_COOKIE)));

        ResponseEntity<String> response2 = restTemplate.exchange(ROOT_URI, HttpMethod.POST, entityUser1, String.class);

        ResponseEntity<String> response3 = restTemplate.exchange(ROOT_URI, HttpMethod.PUT, entityUser2, String.class);

        ResponseEntity<String> response4 = restTemplate.exchange(ROOT_URI + "3", HttpMethod.DELETE, entityHeaders, String.class);
        System.out.println(response2.getBody() + response3.getBody() + response4.getBody());
    }
}
