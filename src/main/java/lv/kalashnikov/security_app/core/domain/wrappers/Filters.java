package lv.kalashnikov.security_app.core.domain.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filters {

    private List<Filter> filters = new ArrayList<>();

    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }

}