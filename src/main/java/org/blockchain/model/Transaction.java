package org.blockchain.model;

public class Transaction {
    private Long sig;
    private Long recv;
    private Integer fee;
    private Long amt;
    private String time;
    private Long send;
    private String hash;

    // Default constructor
    public Transaction() {
    }

    // Parameterized constructor
    public Transaction(Long sig, Long recv, Integer fee,
                       Long amt, String time, Long send,
                       String hash) {
        this.sig = sig;
        this.recv = recv;
        this.fee = fee;
        this.amt = amt;
        this.time = time;
        this.send = send;
        this.hash = hash;
    }

    // Getters
    public Long getSig() {
        return sig;
    }

    public Long getRecv() {
        return recv;
    }

    public Integer getFee() {
        return fee;
    }

    public Long getAmt() {
        return amt;
    }

    public String getTime() {
        return time;
    }

    public Long getSend() {
        return send;
    }

    public String getHash() {
        return hash;
    }

    // Setters
    public void setSig(Long sig) {
        this.sig = sig;
    }

    public void setRecv(Long recv) {
        this.recv = recv;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public void setAmt(Long amt) {
        this.amt = amt;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSend(Long send) {
        this.send = send;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
