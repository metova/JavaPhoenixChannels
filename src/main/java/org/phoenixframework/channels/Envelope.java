package org.phoenixframework.channels;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.logging.Logger;

public class Envelope {

    private static final Logger LOG = Logger.getLogger(Socket.class.getName());

    private String topic;

    private String event;

    private JsonObject payload;

    private String ref;

    @SuppressWarnings("unused")
    public Envelope() {
    }

    public Envelope(final String topic, final String event, final JsonObject payload, final String ref) {
        this.topic = topic;
        this.event = event;
        this.payload = payload;
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "topic='" + topic + '\'' +
                ", event='" + event + '\'' +
                ", payload=" + payload +
                '}';
    }

    public String getTopic() {
        return topic;
    }

    public String getEvent() {
        return event;
    }

    public JsonElement getPayload() {
        return payload;
    }

    /**
     * Helper to retrieve the value of "ref" from the payload
     *
     * @return The ref string or null if not found
     */
    public String getRef() {
        if (ref != null) {
            return ref;
        }
        JsonElement refNode = payload.get("ref");
        if (refNode != null && refNode.isJsonPrimitive() && ((JsonPrimitive) refNode).isString()) {
            return refNode.getAsString();
        } else {
            return null;
        }
    }

    /**
     * Helper to retrieve the value of "status" from the payload
     *
     * @return The status string or null if not found
     */
    public String getResponseStatus() {
        JsonElement statusNode = payload.get("status");
        if (statusNode != null && statusNode.isJsonPrimitive() && ((JsonPrimitive) statusNode).isString()) {
            return statusNode.getAsString();
        } else {
            return null;
        }
    }

    /**
     * Helper to retrieve the value of "reason" from the payload
     *
     * @return The reason string or null if not found
     */
    public String getReason() {
        JsonElement reasonNode = payload.get("reason");
        if (reasonNode != null && reasonNode.isJsonPrimitive() && ((JsonPrimitive) reasonNode).isString()) {
            return reasonNode.getAsString();
        } else {
            return null;
        }
    }
}
