package com.se1020.restaurant.config;

import com.se1020.restaurant.models.Customer;
import com.se1020.restaurant.models.RestaurantTable;
import com.se1020.restaurant.models.Waiter;
import com.se1020.restaurant.models.Admin;
import com.se1020.restaurant.repositories.CustomerRepository;
import com.se1020.restaurant.repositories.TableRepository;
import com.se1020.restaurant.repositories.AdminRepository;
import com.se1020.restaurant.repositories.WaiterRepository;
import com.se1020.restaurant.repositories.AreaRepository;
import com.se1020.restaurant.models.RestaurantArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only run the seeder if the database is completely empty
        if (tableRepository.count() == 0) {
            System.out.println("🌱 Database is empty! Seeding tables, customers, and staff...");

            // 1. Create a mock customer for testing APIs
            createCustomer();


            // 1.5 Create a permanent Walk-In Customer for the Waiter Dashboard
//            Customer walkInCustomer = new Customer();
//            walkInCustomer.setUsername("Walk-In Customer");
//            walkInCustomer.setEmail("walkin@restaurant.local");
//            walkInCustomer.setPassword("secure_password_walkin");
//            walkInCustomer.setPhoneNumber("-");
//            walkInCustomer.setLoyaltyPoints(0);
//            customerRepository.save(walkInCustomer);

//            System.out.println("👤 Walk-In Fallback Customer Created with ID: " + walkInCustomer.getId());

            // 1.8 Create Mock Staff (Admin and Waiter)
            Admin admin = new Admin();
            admin.setUsername("Admin-demo 1");
            admin.setEmail("admin1@rest.local");
            admin.setPassword("admin123");
            admin.setAdminSecurityClearanceLevel("LEVEL_5_MGT");
            adminRepository.save(admin);

            // 1.8 Create Mock Staff (Admin and Waiter)
//            createWaiter();

            // 2. Generate exactly the 4 Areas and 50 tables defined in the UI
            createAreas();

        } else {
            System.out.println("✅ Database already has records. Seeding skipped.");
        }
    }


    private void createCustomer() {
        Customer user01 = new Customer();
        user01.setUsername("user-demo 1");
        user01.setEmail("user1@gmail.com");
        user01.setPassword("user123");
        user01.setPhoneNumber("+94783293909");
        user01.setLoyaltyPoints(100);
        customerRepository.save(user01);

        System.out.println("👤 Mock Customer Created with ID: " + user01.getId());

        Customer user02 = new Customer();
        user02.setUsername("user-demo 2");
        user02.setEmail("user2@gmail.com");
        user02.setPassword("user123");
        user02.setPhoneNumber("+94784382901");
        user02.setLoyaltyPoints(100);
        customerRepository.save(user02);

        System.out.println("👤 Mock Customer Created with ID: " + user02.getId());

    }

    private void createWaiter() {
        Waiter waiter01 = new Waiter();
        waiter01.setUsername("Waiter-demo 1");
        waiter01.setEmail("waiter1@rest.local");
        waiter01.setPassword("waiter123");
        waiter01.setEmployeeId("EMP-001");
        waiterRepository.save(waiter01);

        Waiter waiter02 = new Waiter();
        waiter02.setUsername("Waiter-demo 2");
        waiter02.setEmail("waiter2@rest.local");
        waiter02.setPassword("waiter123");
        waiter02.setEmployeeId("EMP-002");
        waiterRepository.save(waiter02);

        System.out.println("👔 Staff Account Seeded! - Waiter");
    }

    private void createAreas() {
        seedArea("Regular Area", "R", 20, new String[]{"Air-conditioned", "Power outlet available", "Wi-Fi"});
        seedArea("Balcony Area", "B", 10, new String[]{"Window-side", "Good lighting"});
        seedArea("BBQ Area", "Q", 10, new String[]{"Outdoor", "Fan only", "Smoking allowed"});
        seedArea("Garden Area", "G", 10, new String[]{"Outdoor", "Quiet zone", "Good lighting"});
        seedArea("Roof Top Area", "RT", 10, new String[]{"Outdoor", "Good lighting"});

        System.out.println("🍽 Successfully seeded " + areaRepository.count() + " areas and " + tableRepository.count() + " tables!");
    }

    private void seedArea(String areaName, String prefix, int count, String[] baseFeatures) {
        RestaurantArea area = new RestaurantArea();
        area.setName(areaName);
        area.setReservable(true); // Allow VIP whole-area bookings

        List<String> areaFeatures = new ArrayList<>();
        for (String f : baseFeatures) areaFeatures.add(f);
        area.setFeatures(areaFeatures);

        area = areaRepository.save(area);

        int totalSeating = 0;

        for (int i = 1; i <= count; i++) {
            RestaurantTable t = new RestaurantTable();
            t.setTableNumber(prefix + String.format("%02d", i));
            t.setArea(area);

            int capacity = 0;
            if (i <= count * 0.3) capacity = 2;
            else if (i <= count * 0.6) capacity = 4;
            else if (i <= count * 0.9) capacity = 6;
            else capacity = 8;

            t.setSeatingCapacity(capacity);
            totalSeating += capacity;

            java.util.List<String> features = new java.util.ArrayList<>(java.util.Arrays.asList(baseFeatures));

            // Custom mixing
            if (areaName.equals("Balcony Area")) {
                if (i <= 5) features.add("Smoking allowed");
                else features.add("Non-smoking");
            }
            if (areaName.equals("Regular Area") && i % 4 == 0) {
                features.add("Corner table");
            }

            t.setFeatures(features);
            tableRepository.save(t);
        }

        // Update total capacity of the area now that we know it
        area.setTotalCapacity(totalSeating);
        areaRepository.save(area);
    }
}