package io.pivotal.pal.tracker;

import jdk.net.SocketFlow;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLEngineResult;
import java.util.List;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {

    TimeEntryRepository timeEntryRepo;

    public TimeEntryHealthIndicator(TimeEntryRepository timeEntryRepo) {

        this.timeEntryRepo = timeEntryRepo;
    }

    @Override
    public Health health() {

        Health.Builder builder = new Health.Builder();

        List<TimeEntry> list = timeEntryRepo.list();

        if (list.size() < 5) {
            builder.up();
        }
        else {
            builder.down();
        }

        return builder.build();
    }
}
