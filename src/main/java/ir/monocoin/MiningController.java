package ir.monocoin;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.monocoin.model.entity.Block;
import ir.monocoin.model.entity.BlockChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class MiningController {

   private BlockChain blockchain = new BlockChain();

    @GetMapping("/mine_block")
    public Block mineBlock() throws NoSuchAlgorithmException, JsonProcessingException {
        Block previousBlock = blockchain.getPreviousBlock();
        Integer previousProof = previousBlock.getProof();
        Integer proof = blockchain.proofOfWork(previousProof);
        String previousHash = blockchain.hashBlock(previousBlock);
        blockchain.createBlock(proof, previousHash);
        return blockchain.getPreviousBlock();
    }

    @GetMapping("/get_chain")
    public ArrayList<Block> getChain() {
        return (ArrayList<Block>) blockchain.getBlockChain();
    }


    @PostMapping("/is_valid")
    public String isValid() {
        Boolean is_valid = false;
        try {
	is_valid = blockchain.isChainValid();
        } catch (JsonProcessingException e) {
	e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
	e.printStackTrace();
        }
        if (is_valid)
	return "All good. The Blockchain is valid";
        else
	return " we have a problem. The Blockchain is not valid";
    }
}


