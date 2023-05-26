package com.woofking.management.service;

import com.woofking.management.util.Notification;
import com.woofking.management.util.Supervisor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NotificationService {

    @Value("${managers.url}")
    private String MANAGERS_API_URL;

    public List<String> getSupervisors() {
        List<String> supervisors = new ArrayList<>();

        ResponseEntity<List<Supervisor>> response = new RestTemplate().exchange(
                MANAGERS_API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        List<Supervisor> supervisorList = response.getBody();
        if (supervisorList != null) {
            supervisorList.stream()
                    .filter(supervisor -> !isNumeric(supervisor.getJurisdiction()))
                    .sorted(Comparator.comparing(Supervisor::getJurisdiction)
                            .thenComparing(Supervisor::getLastName)
                            .thenComparing(Supervisor::getFirstName))
                    .forEach(supervisor -> {
                        String supervisorFormat = String.format("%s - %s, %s",
                                supervisor.getJurisdiction(), supervisor.getLastName(), supervisor.getFirstName());
                        supervisors.add(supervisorFormat);
                    });
        }

        return supervisors;
    }

    public String createNotificationRequest(Notification request) {

        // Print the notification request details to the console
        System.out.println("Notification Request:");
        System.out.println("First Name: " + request.getFirstName());
        System.out.println("Last Name: " + request.getLastName());
        System.out.println("Email: " + request.getEmail());
        System.out.println("Phone Number: " + request.getPhoneNumber());
        System.out.println("Supervisor: " + request.getSupervisor());

        return "Notification created successfully.";
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d");
    }

}
