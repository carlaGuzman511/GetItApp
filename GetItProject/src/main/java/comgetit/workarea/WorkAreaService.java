package comgetit.workarea;

import comgetit.workarea.dto.WorkAreaDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkAreaService {

    private WorkAreaRepository workAreaRepository;

    @Autowired
    public WorkAreaService(WorkAreaRepository workAreaRepository) {
        this.workAreaRepository = workAreaRepository;
    }

    public List<WorkAreaDTO> getWorkAreas() {
        return workAreaRepository.findAll().stream()
            .map((workArea -> new WorkAreaDTO(workArea.getId(), workArea.getName())))
            .collect(Collectors.toList());
    }
}
