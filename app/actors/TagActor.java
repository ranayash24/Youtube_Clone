package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class TagActor extends AbstractActor {

    public static Props props() {
        return Props.create(TagActor.class);
    }

    // Messages
    public static class TagRequest {
        public final String tagName;

        public TagRequest(String tagName) {
            this.tagName = tagName;
        }
    }

    public static class TagResponse {
        public final String tagName;
        public final int videoCount;
        public final String errorMessage;

        public TagResponse(String tagName, int videoCount, String errorMessage) {
            this.tagName = tagName;
            this.videoCount = videoCount;
            this.errorMessage = errorMessage;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TagRequest.class, request -> {
                    if (request.tagName == null || request.tagName.isEmpty()) {
                        getSender().tell(new TagResponse(null, 0, "Invalid tag name"), getSelf());
                    } else {
                        // Mock response for testing
                        getSender().tell(new TagResponse(request.tagName, 10, null), getSelf());
                    }
                })
                .build();
    }
}
