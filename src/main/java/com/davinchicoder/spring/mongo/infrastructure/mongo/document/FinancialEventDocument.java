package com.davinchicoder.spring.mongo.infrastructure.mongo.document;

import com.davinchicoder.spring.mongo.domain.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document("events")
@CompoundIndexes({
        @CompoundIndex(name = "currency_time_idx", def = "{'currencyId': 1, 'timestamp': 1}")
})
@EqualsAndHashCode(callSuper = true)
@Data
public class FinancialEventDocument extends BaseDocument {

    @Id
    private ObjectId id;
    private String currencyId;
    private Instant timestamp;
    private BigDecimal price;
    private EventType type;
    private GeoJsonPoint source;

}

