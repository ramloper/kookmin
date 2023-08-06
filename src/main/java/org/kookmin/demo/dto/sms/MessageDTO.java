package org.kookmin.demo.dto.sms;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MessageDTO {
    String to;
    String content;
}