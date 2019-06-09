package dreamteam.Actuator;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HealthMetrics  extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception
    {
        try
        {
            builder.up()
                    .withDetail("api", "UP");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            builder.down()
                    .withDetail("api", "DOWN");
        }
    }
}
