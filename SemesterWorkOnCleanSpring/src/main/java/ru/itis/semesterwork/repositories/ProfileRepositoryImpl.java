package ru.itis.semesterwork.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.semesterwork.models.Profile;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProfileRepositoryImpl implements ProfileRepository {

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO user_profile(bio, user_id)" +
            "VALUE (?, ?)";
    //language=SQL
    private static final String SQL_FIND_BY_USER_ID = "SELECT * FROM user_profile WHERE user_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Profile> profileRowMapper = (row, rowIndex) -> {
        return Profile.builder()
                .bio(row.getString("bio"))
                .id(row.getLong("id"))
                .build();
    };

    @Override
    public Optional<Profile> save(Profile entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getBio());
            preparedStatement.setLong(2, entity.getId());
            return preparedStatement;
        }, keyHolder);
        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return Optional.of(entity);
    }

    @Override
    public Optional<Profile> delete(Profile entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Profile> find(Profile entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Profile> update(Profile entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Profile> findProfileByUserId(Long userId) {
        ArrayList<Profile> profileList = (ArrayList<Profile>)jdbcTemplate.query(SQL_FIND_BY_USER_ID, new Object[]{userId},profileRowMapper);
        Profile profile = profileList.get(0);
        return Optional.ofNullable(profile);
    }
}
