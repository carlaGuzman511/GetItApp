package comgetit.workarea.dto;

public class WorkAreaDTO {

    private Long id;

    private String name;

    public WorkAreaDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
