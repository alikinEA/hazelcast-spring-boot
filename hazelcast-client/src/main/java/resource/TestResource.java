package resource;

import model.TestPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.TestService;

/**
 * Created by Alikin E.A. on 17.02.18.
 */
@RestController
@RequestMapping(path = "/test")
public class TestResource {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public TestPojo put(@RequestBody TestPojo object) {
        return testService.put(object.getId(),object);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public TestPojo get(@PathVariable Integer id) {
        return testService.get(id);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public TestPojo remove(@PathVariable Integer id) {
        return testService.remove(id);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    public void clear() {
        testService.clear();
    }
}
