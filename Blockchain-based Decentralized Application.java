import java.util.ArrayList;
import java.util.List;

class Block {
    private String previousHash;
    private String data;
    private String hash;

    // Constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    // Calculate hash
    public String calculateHash() {
        // Implementation of hash calculation
        return StringUtil.applySha256(previousHash + data);
    }

    // Getters and Setters
    // ...
}

class StringUtil {
    public static String applySha256(String input) {
        // Implementation of SHA-256 hashing algorithm
        return ""; // Placeholder, replace with actual implementation
    }
}

class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block("Genesis Block", "0");
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        newBlock.previousHash = getLatestBlock().hash;
        newBlock.hash = newBlock.calculateHash();
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        // Iterate over the chain to validate blocks
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                return false;
            }
        }
        return true;
    }
}






public class DecentralizedApp {
    private static Blockchain blockchain = new Blockchain();

    public static void main(String[] args) {
        // Your DApp logic goes here
        // Example: Adding blocks to the blockchain
        blockchain.addBlock(new Block("Transaction 1", blockchain.getLatestBlock().hash));
        blockchain.addBlock(new Block("Transaction 2", blockchain.getLatestBlock().hash));

        // Example: Checking if the blockchain is valid
        System.out.println("Is blockchain valid? " + blockchain.isChainValid());
    }
}
