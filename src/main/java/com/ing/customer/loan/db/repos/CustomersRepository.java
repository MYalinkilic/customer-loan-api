package com.ing.customer.loan.db.repos;

import com.ing.customer.loan.db.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers,Long> {

    Customers findByCustomerId(String customerId);

}
