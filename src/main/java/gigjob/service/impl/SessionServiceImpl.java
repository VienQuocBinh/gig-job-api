package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.model.response.SessionResponse;
import gigjob.model.response.SessionShopResponse;
import gigjob.repository.SessionRepository;
import gigjob.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final ModelMapper modelMapper;
    private final SessionRepository sessionRepository;

    @Override
    public List<SessionShopResponse> getSessionByShopId(UUID shopId, Date date) {
        try {
            return sessionRepository.findByDateAndShopId(shopId, date).stream()
                    .map(session -> modelMapper.map(session, SessionShopResponse.class))
                    .toList();
        } catch (Exception exception) {
            throw new InternalServerErrorException(exception.getMessage());
        }
    }

    public SessionResponse checkIn() {
        
        return null;
    }
}
