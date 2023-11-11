package duolingobackenduserservice.model;

import duolingobackenduserservice.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
}
