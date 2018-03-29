package io.pivotal.pal.tracker;



import java.util.List;


public interface TimeEntryRepository{
    TimeEntry create(TimeEntry timeEntryToCreate);

    TimeEntry find(long id);

    TimeEntry update(long id, TimeEntry expected);

    void delete(long id);

    List<TimeEntry> list();
//    //    private final List<TimeEntry> list = new ArrayList<>();
//    private final Map<Long, TimeEntry> list = new HashMap<>();
//
//
//    public TimeEntry create(TimeEntry any) {
//        TimeEntry entry = list.put(any.getId(), any);
//        return any;
//    }
//
//    public TimeEntry find(long l) {
//        return list.get(l);
//    }
//
//    public List<TimeEntry> list() {
//        return new ArrayList<>(list.values());
//    }
//
//    public TimeEntry update(long eq, TimeEntry any) {
//        list.replace(eq, any);
//        return any;
//    }
//
//    public TimeEntry delete(long l) {
//        return list.remove(l);
//    }
}
