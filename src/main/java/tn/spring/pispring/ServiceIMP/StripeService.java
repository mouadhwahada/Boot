package tn.spring.pispring.ServiceIMP;

import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Address;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.repo.AddressRepo;
import tn.spring.pispring.repo.PaymentRepository;
import tn.spring.pispring.repo.RoleRepository;
import tn.spring.pispring.repo.UserRepository;


import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StripeService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    AddressRepo addressRepo;

    @Value("${stripe.key.secret}")
    private String stripeSecretKey;

@PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }
    public PaymentIntent createPaymentIntent(BigDecimal amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValue());
        params.put("currency", "usd");
        params.put("payment_method_types", Collections.singletonList("card"));

        return PaymentIntent.create(params);
    }
    public List<User> getUsersByRole(String roleName) {
        return userRepo.findByRoleName(roleName);
    }

    public List<Address> getAddressesOfDeliveryMen() {
        Role deliveryManRole = roleRepo.findByName("Delivery_man");
        if (deliveryManRole != null) {
            return addressRepo.findByUser_Role(deliveryManRole);
        }
        return null;
    }

}
