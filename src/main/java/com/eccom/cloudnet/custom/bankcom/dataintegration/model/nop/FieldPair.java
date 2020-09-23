package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;

import lombok.*;

@Builder
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldPair {
    private String nopField;
    private String nspField;
}
