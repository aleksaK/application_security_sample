package lv.kalashnikov.security_app.core.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {

    private String field;
    private String operator;
    private int target;

}