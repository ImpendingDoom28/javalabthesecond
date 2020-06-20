package ru.itis.springemail.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.itis.springemail.models.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    //language=SQL
    private final String SQL_INSERT = "INSERT INTO user(email, password, name, surname) VALUE (?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, rowColumn) ->
        User.builder()
                .id(row.getLong("id"))
                .email(row.getString("email"))
                .name(row.getString("name"))
                .password(row.getString("password"))
                .surname(row.getString("surname"))
                .build();

    @Override
    public Optional<User> save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, model.getEmail());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setString(3, model.getName());
            preparedStatement.setString(4, model.getSurname());
            return preparedStatement;
        }, keyHolder);

        model.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return Optional.of(model);
    }

    @Override
    public Optional<User> find(User model) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(User model) {

    }
}
