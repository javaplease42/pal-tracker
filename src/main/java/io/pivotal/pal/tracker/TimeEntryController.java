package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private  TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        return new ResponseEntity(this.timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {

        TimeEntry entry = this.timeEntryRepository.find(id);
        HttpStatus status = entry==null?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity(entry, status);

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>>  list() {
        return new ResponseEntity(this.timeEntryRepository.list() , HttpStatus.OK)  ;
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry entry = this.timeEntryRepository.update(id, expected);
        HttpStatus status = entry==null?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity(entry, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        this.timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
