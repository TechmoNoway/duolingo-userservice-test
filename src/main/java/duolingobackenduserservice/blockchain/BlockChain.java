package duolingobackenduserservice.blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockChain implements Serializable {
    private List<Block> blocks = new LinkedList<>();
    private Object lock = new Object();

    public BlockChain(Block root) {

    }

    public void add(List<Block> blockList) {
        synchronized (lock) {
            for (Block block : blockList) {
                boolean find = false;
                for (Block old : blocks) {
                    if (old.getHash().equals(block.getHash())) {
                        find = true;
                        break;
                    }
                }

                if (find) {
                    continue;
                }
                blocks.add(block);
            }
        }
    }

    public boolean isEmpty() {
        return blocks.isEmpty();
    }

    public Block getLatestBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public int size() {
        return blocks.size();
    }

}
