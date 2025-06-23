CREATE TABLE customers (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 customer_id varchar(255),
                                 name VARCHAR(255),
                                 surname VARCHAR(255),
                                 credit_Limit DECIMAL(19, 2),
                                 used_credit_limit DECIMAL(19, 2)
);
CREATE TABLE loans (

                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                loan_id varchar(255),
                                customer_id varchar(255),
                                credit_amount DECIMAL(19, 2),
                                loan_amount DECIMAL(19, 2),
                                paid_amount DECIMAL(19, 2),
                                number_of_installments INT,
                                create_date DATE,
                                is_paid varchar(1)
);
CREATE TABLE loan_installments (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                loan_id varchar(255),
                                customer_id varchar(255),
                                installment_order INT,
                                amount DECIMAL(19, 2),
                                paid_amount DECIMAL(19, 2),
                                due_date DATE,
                                payment_date DATE,
                                is_paid varchar(1)
);