package test4solanteq.dao;

import test4solanteq.model.PositionModel;

import java.util.List;

/**
 * @author vladislav.trofimov@emc.com
 */
public interface PositionDao {
    String addPosition(final String name);

    PositionModel getPosition(final String id);

    List<PositionModel> getAllPositions();
}