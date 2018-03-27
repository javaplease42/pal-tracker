package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository {

    Map<Long, TimeEntry> l = new HashMap<Long, TimeEntry>();


    public TimeEntry create(TimeEntry timeEntry) {
        l.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {

        return l.get(id);
    }



    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry TimeEntry = new TimeEntry();
        return TimeEntry;
    }

    public void delete(long id) {
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries =
                new ArrayList<TimeEntry>(l.values());

        timeEntries.sort();

        return timeEntries;
    }
}
