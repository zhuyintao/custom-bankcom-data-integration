package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Edge("t_property")
public class Property {
    @Id
    private String id;

    @From
    private Model model;

    @To
    private Element element;

    private Integer sort;

    private boolean extend;

    private boolean related;    //是否关联属性

    private boolean isKey;        //是否（独立）关键属性

    private RelatedElement relatedElement;

    @Getter
    @Setter
    public static class RelatedElement {
        private String modelRelationId;

        private String relationCode;

        private String direction;

        private String modelCode;

        private String elementCode;

        private RelatedElement next;

        public RelatedElement() {
            //nothing
        }
    }

    public Property(final Model model, final Element element, Integer sort, Boolean extend, Boolean related, RelatedElement relatedElement) {
        this.model = model;
        this.element = element;
        this.sort = sort;
        this.setExtend(extend == null || extend);
        this.setRelated(related != null && related);
        this.setRelatedElement(relatedElement);
    }
}
