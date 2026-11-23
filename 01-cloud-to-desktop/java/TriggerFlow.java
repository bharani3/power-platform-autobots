import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/** Calls a Power Automate HTTP-trigger flow. Requires Java 11+. */
public class TriggerFlow {
    public static void main(String[] args) throws Exception {
        String flowUrl = System.getenv("FLOW_URL"); // never hard-code this
        if (flowUrl == null || flowUrl.isBlank()) {
            throw new IllegalStateException("Set the FLOW_URL environment variable");
        }

        String json = """
            {"summary":"Create order ticket","priority":"High","requester":"user@example.com"}
            """;

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)).build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(flowUrl))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        if (response.statusCode() >= 400) System.exit(1);
    }
}
