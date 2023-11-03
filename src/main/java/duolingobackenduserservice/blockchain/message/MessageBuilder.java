package duolingobackenduserservice.blockchain.message;

public class MessageBuilder {
    private final Message message = new Message();

    MessageBuilder withSender(final int sender) {
        message.sender = sender;
        return this;
    }

    MessageBuilder withReceiver(final int receiver){
        message.receiver = receiver;
        return this;
    }

    MessageBuilder withType(final MESSAGE_TYPE type) {
        message.type = type;
        return this;
    }

    Message build() {
        return message;
    }
}
