package ir.monocoin.model.entity;

import java.util.Date;

public class Block {
    private Integer index;
    private Date timestamp;
    private Integer proof;
    private String previosHash;

    public Block(Integer index, Date timestamp, Integer proof, String previosHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.proof = proof;
        this.previosHash = previosHash;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getProof() {
        return proof;
    }

    public void setProof(Integer proof) {
        this.proof = proof;
    }

    public String getPreviosHash() {
        return previosHash;
    }

    public void setPreviosHash(String previosHash) {
        this.previosHash = previosHash;
    }
}
