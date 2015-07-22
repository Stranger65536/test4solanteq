package test4solanteq.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import test4solanteq.config.Test4solanteqApplication;
import test4solanteq.model.PositionModel;
import test4solanteq.utils.Utils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

import static test4solanteq.dao.DaoConstants.Positions.*;

@SuppressWarnings({"unused", "SpringElInspection"})
@Repository("PositionDao")
public class PositionDaoImpl extends JdbcDaoSupport implements PositionDao {

    @SuppressWarnings("unused")
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public String addPosition(final String name) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                connection -> {
                    final String query = "INSERT INTO " + POSITIONS_TABLE + " (" +
                            POSITION_NAME +
                            ") VALUES (?)";
                    PreparedStatement ps = connection.prepareStatement(query,
                            new String[]{POSITION_ID});
                    ps.setNString(1, name);
                    return ps;
                }, keyHolder);
        return Utils.rawToHex((byte[]) keyHolder.getKeys().get(POSITION_ID));
    }

    //Can be replaced by SQL JOIN operation, but JOIN is very expensive operation,
    //and caching rows of little table with manually joining should speed up performance
    @SuppressWarnings({"DuplicateStringLiteralInspection", "MagicCharacter"})
    @Override
    @Cacheable(value = Test4solanteqApplication.POSITIONS_CACHE, key = "#id")
    public PositionModel getPosition(final String id) {
        final String query = "SELECT * FROM " + POSITIONS_TABLE + ' ' +
                "WHERE " + POSITION_ID + " = ?";
        return getJdbcTemplate().queryForObject(query, new Object[]{id},
                new BeanPropertyRowMapper<>(PositionModel.class));
    }

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public List<PositionModel> getAllPositions() {
        final String query = "SELECT * FROM " + POSITIONS_TABLE;
        return getJdbcTemplate().query(query, ps -> {
        }, new BeanPropertyRowMapper<>(PositionModel.class));
    }
}