package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import model.Customer;

public class CustomerService {
    private static Map<String, Customer> mapCustomers = new HashMap<String, Customer>();
    private static Collection<Customer> customerHash = new HashSet<>();
    private static CustomerService instcustomerService = null;
    
    Customer customerr;

    public void addCustomer(String email, String fname, String lname){
        try {
            if (mapCustomers.containsKey(email))
            {
    
                System.out.println("Error: This email is already in use");
                return;
            }
    
            customerr = new Customer (fname, lname, email);
                mapCustomers.put(email, customerr);
                customerHash.add(customerr);
            }catch(IllegalArgumentException ex){
                System.out.println(ex.getLocalizedMessage());
            }
    }

    public  Customer getCustomer(String customerEmail){
        if (!mapCustomers.containsKey(customerEmail)) {

            return null;
           
        }
       else {

             return mapCustomers.get(customerEmail);

       }    }

    public Collection<Customer> getAllCustomers(){
        return customerHash;
        
    }

    public static CustomerService getInstance() {
        if (instcustomerService == null ) {
            instcustomerService = new CustomerService();
        }
        return instcustomerService;
    }
}
