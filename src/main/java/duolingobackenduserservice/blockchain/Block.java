package duolingobackenduserservice.blockchain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
