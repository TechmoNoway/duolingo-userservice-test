package duolingobackenduserservice.blockchain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Block implements Serializable {
    private static final long serialVersionID = 1L;

    protected int index;
    protected Long timestamp;
    protected String hash;
    protected String previousHash;
    protected String creator;
    protected Integer nonce;
    protected String data;
    private ProofOfWork proofOfWork;
}
