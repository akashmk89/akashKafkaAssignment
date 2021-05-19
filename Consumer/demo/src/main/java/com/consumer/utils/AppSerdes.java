/*
 * Copyright (c) 2019. Prashant Kumar Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.consumer.utils;


import com.consumer.models.Station;
import com.consumer.models.TransformedStation;
import com.consumer.utils.serde.JsonDeserializer;
import com.consumer.utils.serde.JsonSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

/**
 * Application level Serdes
 *
 * @author prashant
 * @author www.learningjournal.guru
 */
class AppSerdes extends Serdes {

    static final class StationSerde extends WrapperSerde<Station> {
        StationSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static Serde<Station> Station() {
        StationSerde serde = new StationSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", Station.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class TransformedStaionSerde extends WrapperSerde<TransformedStation> {
        TransformedStaionSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static Serde<TransformedStation> DepartmentAggregate() {
        TransformedStaionSerde serde = new TransformedStaionSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", TransformedStation.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
