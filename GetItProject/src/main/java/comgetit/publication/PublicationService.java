package comgetit.publication;

import comgetit.publication.dto.PublicationDTO;
import comgetit.publication.dto.PublicationCreationDTO;
import comgetit.publication.exception.PublicationTypeException;
import comgetit.user.User;
import comgetit.user.UserRepository;
import comgetit.user.exception.UserNotFoundException;
import comgetit.workarea.WorkArea;
import comgetit.workarea.WorkAreaRepository;
import comgetit.workarea.exception.WorkAreNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;

    private final WorkAreaRepository workAreaRepository;

    private final UserRepository userRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository
        , WorkAreaRepository workAreaRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.workAreaRepository = workAreaRepository;
        this.userRepository = userRepository;
    }

    public Publication createPublication(final PublicationCreationDTO publicationCreationDTO) {
        User user = userRepository.findById(publicationCreationDTO.getUserId())
            .orElseThrow(UserNotFoundException::new);
        WorkArea workArea = workAreaRepository.findById(publicationCreationDTO.getWorkAreaId())
            .orElseThrow(WorkAreNotFoundException::new);
        PublicationType publicationType = convertToTypePublication(publicationCreationDTO.getTypePublication());
        
        Publication publication = new Publication(UUID.randomUUID().getMostSignificantBits()
            , publicationType, workArea, publicationCreationDTO.getTariff(), publicationCreationDTO.getAddress()
            , publicationCreationDTO.getTimeRequiredOrOffered(), publicationCreationDTO.getDescription()
            , user, new Date(), publicationCreationDTO.getImage());
        return publicationRepository.save(publication);
    }

    public PublicationType convertToTypePublication(String typePublication) {
        try {
            return PublicationType.valueOf(typePublication);
        } catch (IllegalArgumentException exception) {
            throw new PublicationTypeException(typePublication);
        }
    }

    public List<PublicationDTO> getAllPublications() {
        return publicationRepository.findAll().stream()
            .map(PublicationDTO::new)
            .collect(Collectors.toList());
    }

    public List<PublicationDTO> searchByWorkArea(String workAreaName) {
        WorkArea workArea = workAreaRepository.findByName(workAreaName)
            .orElseThrow(WorkAreNotFoundException::new);
        return publicationRepository.findAllByWorkAreaOrderByCreatedDesc(workArea)
            .stream().map(PublicationDTO::new)
            .collect(Collectors.toList());
    }
}
