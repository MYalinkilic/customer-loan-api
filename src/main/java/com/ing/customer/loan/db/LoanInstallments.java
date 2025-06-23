package com.ing.customer.loan.db;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/*id BIGINT PRIMARY KEY,
                                loan_id varchar(255),
                                customer_id varchar(255),
                                installment_order INT,
                                amount FLOAT,
                                paid_amount FLOAT,
                                due_date DATE,
                                payment_date DATE,
                                is_paid INT
                                */

@Entity
@Table(name="loan_installments")
public class LoanInstallments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String loanId;
    private String customerId;
    private int installmentOrder;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private Date dueDate;
    private Date paymentDate;
    private String isPaid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getInstallmentOrder() {
        return installmentOrder;
    }

    public void setInstallmentOrder(int installmentOrder) {
        this.installmentOrder = installmentOrder;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }
}