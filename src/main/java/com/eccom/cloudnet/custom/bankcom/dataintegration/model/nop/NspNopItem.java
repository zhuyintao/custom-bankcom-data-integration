package com.eccom.cloudnet.custom.bankcom.dataintegration.model.nop;
import lombok.*;

import java.util.List;
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NspNopItem {
    private String nopTable;
    private String nspTable;
    private List<FieldPair> fieldPair;
}
