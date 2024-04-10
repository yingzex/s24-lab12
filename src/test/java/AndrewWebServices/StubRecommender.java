package AndrewWebServices;

import java.util.HashMap;
import java.util.Map;

public class StubRecommender extends RecSys {
    private final Map<String, String> recommendations;

    public StubRecommender() {
        recommendations = new HashMap<>();
        recommendations.put("Scotty", "Animal House");
    }

    public String getRecommendation(String accountName) {
        return recommendations.getOrDefault(accountName, "Default Movie");
    }
}
