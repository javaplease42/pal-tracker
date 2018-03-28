package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long, TimeEntry> l = new HashMap<Long, TimeEntry>();


    public TimeEntry create(TimeEntry timeEntry) {

//        TimeEntry entry = l.put(timeEntry.getId(), timeEntry);
//        return timeEntry;

        if(timeEntry.getId() < 1){

            timeEntry.setId(1L);
        }

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

        if(timeEntry1 != null) {
            timeEntry1.setDate(timeEntry.getDate());
            timeEntry1.setHours(timeEntry.getHours());
            timeEntry1.setProjectId(timeEntry.getProjectId());
            timeEntry1.setUserId(timeEntry.getUserId());
        }else{
            timeEntry1= l.put(id, timeEntry);
        }

        return timeEntry1;
//        return timeEntry;
    }

    public void delete(long id) {

        l.remove(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(l.values());

    }
}
