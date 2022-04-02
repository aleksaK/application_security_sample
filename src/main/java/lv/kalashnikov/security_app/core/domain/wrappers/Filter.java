package lv.kalashnikov.security_app.core.domain.wrappers;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    private String field;
    private String operator;
    private Integer integerTarget;
    private String stringTarget;

}