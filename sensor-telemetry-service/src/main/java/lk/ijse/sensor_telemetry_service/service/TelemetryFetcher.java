package lk.ijse.sensor_telemetry_service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelemetryFetcher {
    @Scheduled(fixedRate = 10000)
    public void fetchData(){
        RestTemplate rest = new RestTemplate();

        String url = "http://104.211.95.241:8080/api/devices";

        String response = rest.getForObject(url,String.class);

        System.out.println(response);
    }
}
