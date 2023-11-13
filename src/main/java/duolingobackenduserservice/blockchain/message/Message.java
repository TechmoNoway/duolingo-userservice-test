package duolingobackenduserservice.blockchain.message;

import duolingobackenduserservice.blockchain.Block;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    int sender;
    int receiver;
    MESSAGE_TYPE type;

    List<Block> blocks;

    @Override
    public String toString() {
        return String.format("Message {type=%s, sender=%d, receiver=%d, blocks=%s}", type, sender, receiver, blocks);
    }

}
