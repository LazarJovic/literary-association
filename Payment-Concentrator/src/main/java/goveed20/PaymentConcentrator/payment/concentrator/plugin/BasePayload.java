package goveed20.PaymentConcentrator.payment.concentrator.plugin;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BasePayload {
    private HashMap<String, Object> paymentFields;
}