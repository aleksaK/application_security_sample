package lv.kalashnikov.security_app.core.domain;

import lombok.*;

@Data
@NoArgsConstructor
public class Filter {

    private String field;
    private String operator;
    private Integer integerTarget;
    private String stringTarget;

}