package test4solanteq.service;

import test4solanteq.model.PositionModel;

import java.util.List;

public interface PositionService {
    String addPosition(String name);

    List<PositionModel> getAllPositions();

    PositionModel getPosition(final String positionId);
}