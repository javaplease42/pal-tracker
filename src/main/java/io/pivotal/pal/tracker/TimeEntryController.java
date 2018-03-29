package io.pivotal.pal.tracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    @Qualifier("timeEntryRepository")
    private TimeEntryRepository timeEntryRepository;

    private final CounterService counter;
    private final GaugeService gauge;

//    @Autowired
//    @Qualifier("objectMapper")
//    private ObjectMapper objectMapper;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, CounterService counter, GaugeService gauge) {
        this.timeEntryRepository = timeEntryRepository;
        this.counter = counter;
        this.gauge = gauge;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return new ResponseEntity(this.timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {

        TimeEntry entry = this.timeEntryRepository.find(id);
        if (entry != null) counter.increment("TimeEntry.read");
        HttpStatus status = entry == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity(entry, status);

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        counter.increment("TimeEntry.listed");
        return new ResponseEntity(this.timeEntryRepository.list(), HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry entry = this.timeEntryRepository.update(id, expected);
        if (entry != null) counter.increment("TimeEntry.updated");
        HttpStatus status = entry == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity(entry, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        this.timeEntryRepository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
