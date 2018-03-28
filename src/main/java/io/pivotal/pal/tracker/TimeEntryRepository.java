package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TimeEntryRepository {
    //    private final List<TimeEntry> list = new ArrayList<>();
    private final Map<Long, TimeEntry> list = new HashMap<>();


    public TimeEntry create(TimeEntry any) {
        TimeEntry entry = list.put(any.getId(), any);
        return any;
    }

    public TimeEntry find(long l) {
        return list.get(l);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(list.values());
    }

    public TimeEntry update(long eq, TimeEntry any) {
        list.replace(eq, any);
        return any;
    }

    public TimeEntry delete(long l) {
        return list.remove(l);
    }
}
