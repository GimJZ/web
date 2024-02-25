package org.blockchain.model;

import java.util.List;

import java.util.List;

public class Block {
    private List<Transaction> all_tx;
    private String pow;
    private int id;
    private String hash;
    private String target;

    // Default constructor
    public Block() {
    }

    // Parameterized constructor
    public Block(List<Transaction> all_tx, String pow,
                 int id,String hash, String target) {
        this.all_tx = all_tx;
        this.pow = pow;
        this.id = id;
        this.hash = hash;
        this.target = target;
    }

    // Getters
    public List<Transaction> getAll_tx() {
        return all_tx;
    }

    public String getPow() {
        return pow;
    }

    public int getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public String getTarget() {
        return target;
    }

    // Setters
    public void setAll_tx(List<Transaction> all_tx) {
        this.all_tx = all_tx;
    }

    public void setPow(String pow) {
        this.pow = pow;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}


