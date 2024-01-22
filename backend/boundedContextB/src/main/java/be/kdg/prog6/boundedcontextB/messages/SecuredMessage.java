package be.kdg.prog6.boundedcontextB.messages;

import lombok.Getter;

@Getter
public class SecuredMessage extends UnsecuredMessage {
    private String subjectId;
    private String username;
    private String password;
    private String email;
    private String message;

    private SecuredMessage(Builder builder) {
        subjectId = builder.subjectId;
        username = builder.username;
        password = builder.password;
        email = builder.email;
        message = builder.message;
    }

    @Override
    public String getMessage() {
        return subjectId;
    }
    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String subjectId;
        private String username;
        private String password;
        private String email;
        private String message;
        private Builder() {
        }
        public Builder subjectId(String val) {
            subjectId = val;
            return this;
        }
        public Builder username(String val) {
            username = val;
            return this;
        }
        public Builder password(String val) {
            password = val;
            return this;
        }
        public Builder email(String val) {
            email = val;
            return this;
        }
        public Builder message(String val) {
            message = val;
            return this;
        }

        public SecuredMessage build() {
            return new SecuredMessage(this);
        }
    }
}
