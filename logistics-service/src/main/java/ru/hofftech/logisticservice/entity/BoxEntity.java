package ru.hofftech.logisticservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.hofftech.logisticservice.constants.BoxColumn;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class BoxEntity {

    @Column(BoxColumn.ID)
    private Long id;

    @Column(BoxColumn.NAME)
    private String name;

    @Column(BoxColumn.FORM)
    private String form;

    @Column(BoxColumn.SYMBOL)
    private String symbol;
}
