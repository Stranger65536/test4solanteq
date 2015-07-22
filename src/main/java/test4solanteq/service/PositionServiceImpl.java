package test4solanteq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import test4solanteq.dao.PositionDao;
import test4solanteq.model.PositionModel;

import java.util.List;

@SuppressWarnings("unused")
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
@Service("PositionService")
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionDao positionDao;

    @Override
    public String addPosition(final String name) {
        return positionDao.addPosition(name);
    }

    @Override
    public List<PositionModel> getAllPositions() {
        return positionDao.getAllPositions();
    }

    @Override
    public PositionModel getPosition(final String positionId) {
        return positionDao.getPosition(positionId);
    }
}
