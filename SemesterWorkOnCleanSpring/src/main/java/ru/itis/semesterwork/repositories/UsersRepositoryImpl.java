package ru.itis.semesterwork.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.semesterwork.models.Role;
import ru.itis.semesterwork.models.State;
import ru.itis.semesterwork.models.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

//Uses JdbcTemplate to work
@Repository
public class UsersRepositoryImpl implements UsersRepository {

    //Ниже запросы к базе данных на языке SQL в виде строчек-констант
    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO user_codep(created_at, email, hash_password, nickname, role, state) " +
            "VALUE (?, ?, ?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_FIND_BY_NICKNAME = "SELECT * FROM user_codep WHERE nickname = ?";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE user_codep SET user_codep.state = ?";
    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM user_codep WHERE id = ?";
    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM user_codep";

    //RowMapper позволяет преобразовывать результат из базы данных в Java-объект
    private RowMapper<User> userRowMapper = (row, rowIndex) ->
        User.builder()
                .id(row.getLong("id"))
                .email(row.getString("email"))
                .state(State.valueOf(row.getString("state")))
                .role(Role.valueOf(row.getString("role")))
                .nickname(row.getString("nickname"))
                .hashPassword(row.getString("hash_password"))
                .createdAt(row.getTimestamp("created_at").toLocalDateTime())
                .build();

    //Сам класс для работы с JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findUserByNickname(String nickname) {
        ArrayList<User> userList = (ArrayList<User>) jdbcTemplate.query(SQL_FIND_BY_NICKNAME, new Object[]{nickname}, userRowMapper);
        System.out.println(Arrays.toString(userList.toArray()));
        return Optional.ofNullable(userList.isEmpty() ? null : (User)(userList.toArray()[0]));
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> userList = (ArrayList<User>) jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
        return userList;
    }

    @Override
    public Optional<User> save(User entity) {
        //Держатель ключей - нужен, чтобы бд проставила туда сгенерированный ключ
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getCreatedAt().toString());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getHashPassword());
            preparedStatement.setString(4, entity.getNickname());
            preparedStatement.setString(5, entity.getRole().name());
            preparedStatement.setString(6, entity.getState().name());
            return preparedStatement;
        }, keyHolder);
        //Получаем тот самый ключ из бд
        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return Optional.of(entity);
    }

    @Override
    public Optional<User> delete(User entity) {
        return Optional.empty();
    }

    @Override
    public Optional<User> find(User entity) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, userRowMapper);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> update(User entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getState().name());
        return Optional.of(entity);
    }
}
