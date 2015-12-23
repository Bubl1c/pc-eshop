package com.kpi.ip41m.pceshop;

import com.kpi.ip41m.pceshop.entity.account.*;
import com.kpi.ip41m.pceshop.entity.order.*;
import com.kpi.ip41m.pceshop.repository.CustomerRepository;
import com.kpi.ip41m.pceshop.repository.OrderRepository;
import com.kpi.ip41m.pceshop.repository.UserAccountRepository;
import com.kpi.ip41m.pceshop.entity.product.Product;
import com.kpi.ip41m.pceshop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii on 29.10.2015.
 */
@Component
public class StartupService {

    private static final Logger logger = LoggerFactory.getLogger(StartupService.class);

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public void initializeDatabase() {
//        createDefaultUserAccounts();
    }

    @Transactional
    public void createDefaultUserAccounts() {
        UserAccount customerAccount = userAccountRepository.save(createUser("user", "user", "Default", "0677699944", AccountType.CUSTOMER));
        userAccountRepository.save(createUser("admin", "admin", "Admin", "0677699944", AccountType.ADMIN));

        UserAccount cus = userAccountRepository.findOne(customerAccount.getId());

        Customer customer = customerRepository.save(createCustomer(cus));

        System.out.println(userAccountRepository.findAll());

        Product savedProduct = productRepository.save(createProduct(
                "Apple - MacBook Pro with Retina display (Latest Model)",
                1299.99,
                "MacBook Pro with Retina display - 13.3\" Display - 8GB Memory - 128GB Flash Storage, Silver"));

        productRepository.save(createProduct(
                "Dell Inspiron 13 7000 Series 2-in-1 Special Edition",
                749.99,
                "The new Inspiron 13 7000 Series 2-in-1 Special Edition combines tablet mobility, laptop performance and premium design for work or play anywhere."));

        productRepository.save(createProduct(
                "ASUS ZENBOOK UX301LA",
                1699.99,
                "Мощный процессор Intel® (вплоть до Intel® Core™ i7-4558U)"));

        System.out.println(productRepository.findAll());

        orderRepository.save(createDefaultOrder(customer, savedProduct));

        System.out.println(orderRepository.findAll());

    }

    @Transactional
    private UserAccount createUser(String username,
                                   String password,
                                   String firstName,
                                   String phone,
                                   AccountType accountType) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setPhone(phone);
        userAccount.setFirstName(firstName);
        userAccount.setAccountState(AccountState.ACTIVE);
        userAccount.setAccountType(accountType);
        return userAccount;
    }

    @Transactional
    private Customer createCustomer(UserAccount userAccount) {
        Customer customer = new Customer();
        customer.setAccount(userAccount);
        customer.setAddress(getDefaultAddress());
        return customer;
    }

    @Transactional
    private Product createProduct(String name, Double price, String description) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        return product;
    }

    @Transactional
    private Order createDefaultOrder(Customer customer, Product product) {
        Payment payment = new Payment();
        payment.setPaid(new Date());
        payment.setDetails("Payed successfully");
        payment.setTotalSum(99.99);

        Shipping shipping = new Shipping();
        shipping.setPrice(9.99);
        shipping.setType(ShippingType.COURIER);
        shipping.setShipTo(getDefaultAddress());

        OrderRow row = new OrderRow();
        row.setQuantity(10);
        row.setProduct(product);

        return createOrder(customer, payment, shipping, Collections.singletonList(row));
    }

    @Transactional
    private Order createOrder(Customer customer, Payment payment, Shipping shipping, List<OrderRow> rows) {
        Order order = new Order();

        order.setCustomer(customer);
        order.setPayment(payment);
        order.setShipping(shipping);
        order.setRows(new ArrayList<>());

        return order;
    }

    @Transactional
    private Address getDefaultAddress() {
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setCity("Kyiv");
        address.setStreet("Ianhelia");
        address.setBuildingNumber("22/a");
        address.setApartment("402");
        return address;
    }
}
