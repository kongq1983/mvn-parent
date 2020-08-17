package com.kq.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * DestinationConstants
 *
 * @author kq
 * @date 2019-08-06
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DestinationConstants {

    public static class TOPIC {

        public  static final String MAIL_TOPIC_NAME = "com.mailbox.topic";
        public  static final String STRING_TOPIC_NAME = "com.string.topic";
        public  static final String STRING_PERSISTENT_TOPIC_NAME = "string.persistent.topic";

    }

    public static class QUEUE {

        public  static final String STRING_QUEUE_NAME = "com.string.queue";
        public  static final String JSON_QUEUE_NAME = "com.json.queue";

    }

}
