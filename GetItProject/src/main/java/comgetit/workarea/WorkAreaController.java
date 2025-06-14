package comgetit.workarea;

import comgetit.workarea.dto.WorkAreaDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkAreaController {

    private WorkAreaService workAreaService;

    @Autowired
    public WorkAreaController(WorkAreaService workAreaService) {
        this.workAreaService = workAreaService;
    }

    @GetMapping("workareas")
    public List<WorkAreaDTO> getWorkAreaDTOS() {
        return workAreaService.getWorkAreas();
    }
}
