package ru.itis.mail.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.mail.models.FileInfo;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public class FilesRepositoryJdbcTemplateImpl implements FilesRepository {

    //language=SQL
    private static final String SQL_SAVE_FILE = "INSERT INTO file_info(storage_file_name, original_file_name, size, type, url) " +
            "VALUE (?, ?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_FIND_BY_ORIGINAL_FILE_NAME = "SELECT * FROM file_info WHERE file_info.original_file_name = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<FileInfo> fileInfoRowMapper = (row, rowIndex) ->
            FileInfo.builder()
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .originalFileName(row.getString(("original_file_name")))
                    .url(row.getString("url"))
                    .build();

    @Override
    public Optional<FileInfo> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<FileInfo> findAll() {
        return null;
    }

    @Override
    public Optional<FileInfo> save(FileInfo entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_SAVE_FILE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getStorageFileName());
            statement.setString(2, entity.getOriginalFileName());
            statement.setLong(3, entity.getSize());
            statement.setString(4, entity.getType());
            statement.setString(5, entity.getUrl());
            return statement;
        }, keyHolder);
        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return Optional.of(entity);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<FileInfo> findByOriginalFileName(String originalFileName) {
        FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_FIND_BY_ORIGINAL_FILE_NAME, new Object[]{originalFileName}, fileInfoRowMapper);
        return Optional.ofNullable(fileInfo);
    }
}
