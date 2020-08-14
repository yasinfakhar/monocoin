package ir.monocoin.model.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import static ir.monocoin.service.Hash.getSHA;
import static ir.monocoin.service.Hash.toHexString;

public class BlockChain {


    private List<Block> blockChain;

    public List<Block> getBlockChain() {
        return blockChain;
    }

    public BlockChain() {
        this.blockChain = new ArrayList<>();
        this.createBlock(1, "0");
    }

    public Block createBlock(Integer proof, String previousHash) {
        Block block = new Block(blockChain.size() + 1, new Date(), proof, previousHash);
        this.blockChain.add(block);
        return block;
    }

    public Block getPreviousBlock() {
        return blockChain.get(blockChain.size() - 1);
    }


    public Integer proofOfWork(Integer previousProof) throws NoSuchAlgorithmException {
        Integer newProof = 1;
        Boolean checkProof = false;
        while (!checkProof) {
	String text = String.valueOf(newProof ^ 2 - previousProof ^ 2);
	String hash = toHexString(getSHA(text));
	if (hash.startsWith("ff")){
	    checkProof = true;
	    System.out.println(newProof);
	    System.out.println(hash+"\n");
	}

	else{
	    newProof++;
	}

        }
        return newProof;
    }

    public String hashBlock(Block block) throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(block);
        String hashedBlock = toHexString(getSHA(jsonString));
        return hashedBlock;
    }


    public Boolean isChainValid() throws JsonProcessingException, NoSuchAlgorithmException {
        Block previousBlock = this.blockChain.get(0);
        Integer blockIndex = 1;
        while (blockIndex < this.blockChain.size()) {
	Block block = this.blockChain.get(blockIndex);
	if (!block.getPreviosHash().equals(hashBlock(previousBlock))) {
	    return false;
	}
	Integer previousProof = previousBlock.getProof();
	Integer proof = block.getProof();
	String text = String.valueOf(proof ^ 2 - previousProof ^ 2);
	String hash = toHexString(getSHA(text));
	if (!hash.startsWith("ff"))
	   return false;
	previousBlock = block;
	blockIndex++;
        }
        return true;

    }

}
