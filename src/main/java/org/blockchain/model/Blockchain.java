package org.blockchain.model;

import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private String new_target;
    private List<Transaction> new_tx;

    // Default constructor
    public Blockchain() {}

    // Getters and Setters
    public List<Block> getChain() {
        return chain;
    }

    public void setChain(List<Block> chain) {
        this.chain = chain;
    }

    public String getNew_target() {
        return new_target;
    }

    public void setNew_target(String new_target) {
        this.new_target = new_target;
    }

    public List<Transaction> getNew_tx() {
        return new_tx;
    }

    public void setNew_tx(List<Transaction> new_tx) {
        this.new_tx = new_tx;
    }
}
