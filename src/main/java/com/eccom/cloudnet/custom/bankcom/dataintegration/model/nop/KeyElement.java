package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Edge("t_key_element")
public class KeyElement {

    @Id
    private String id;

    @From
    private Key key;

    @To
    private Element element;

    public KeyElement(Key key, Element element) {
        this.key = key;
        this.element = element;
    }

    public KeyElement() { }

}
