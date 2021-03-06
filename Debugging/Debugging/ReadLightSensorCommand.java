package Debugging;

import java.util.Map;

public class ReadLightSensorCommand extends AbstractSensorCommand {

    public Map<String, Float> execute(SensorsSystem system) {

        return system.readSensor("Light");
    }
}
