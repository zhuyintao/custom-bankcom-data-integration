package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Document("t_key")
public class Key {
    @Id
    private String id;

    private Integer priority;

    @Relations(edges = KeyElement.class, direction = Relations.Direction.OUTBOUND)
    private List<Element> elements;
}
