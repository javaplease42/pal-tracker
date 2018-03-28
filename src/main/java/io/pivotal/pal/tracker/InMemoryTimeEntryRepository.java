package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository {

    Map<Long, TimeEntry> l = new HashMap<Long, TimeEntry>();


    public TimeEntry create(TimeEntry timeEntry) {


        TimeEntry timeEntry1 = this.find(timeEntry.getId());
        if(timeEntry1 == null) {
            l.put(timeEntry.getId(), timeEntry);
        }
        else
        {
            timeEntry.setId(timeEntry.getId() + 1L);
            l.put(timeEntry.getId(), timeEntry);
        }


        return timeEntry;
    }

    public TimeEntry find(long id) {

        return l.get(id);
    }



    public TimeEntry update(long id, TimeEntry timeEntry) {

        TimeEntry timeEntry1 = this.find(id);
        if(timeEntry1 == null) {

        }
        else
        {

            l.put(id, timeEntry);
        }

        return timeEntry;
    }

    public void delete(long id) {

        l.remove(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries =
                new ArrayList<TimeEntry>(l.values());

       // timeEntries.sort();

        return timeEntries;
    }
}
